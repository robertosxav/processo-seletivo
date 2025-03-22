package br.com.robertoxavier.api.ports.servidor;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.api.mappers.pessoa.PessoaMapper;
import br.com.robertoxavier.api.mappers.servidor.ServidorTemporarioMapper;
import br.com.robertoxavier.data.entities.*;
import br.com.robertoxavier.data.repositories.EnderecoRepository;
import br.com.robertoxavier.data.repositories.PessoaEnderecoRepository;
import br.com.robertoxavier.data.repositories.PessoaRepository;
import br.com.robertoxavier.data.repositories.ServidorTemporarioRepository;
import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.model.PessoaModel;
import br.com.robertoxavier.model.ServidorEfetivoModel;
import br.com.robertoxavier.model.ServidorTemporarioModel;
import br.com.robertoxavier.ports.servidor.ServidorTemporarioPort;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ServidorTemporarioPortImp implements ServidorTemporarioPort {

    private final ServidorTemporarioRepository servidorTemporarioRepository;

    private final PessoaRepository pessoaRepository;

    private final ServidorTemporarioMapper servidorTemporarioMapper;

    private final PessoaMapper pessoaMapper;

    private final PessoaEnderecoRepository pessoaEnderecoRepository;

    private final EnderecoRepository enderecoRepository;

    private final EnderecoMapper enderecoMapper;

    public ServidorTemporarioPortImp(ServidorTemporarioRepository servidorTemporarioRepository,
                                     ServidorTemporarioMapper servidorTemporarioMapper,
                                     PessoaRepository pessoaRepository,
                                     PessoaMapper pessoaMapper,
                                     PessoaEnderecoRepository pessoaEnderecoRepository, EnderecoRepository enderecoRepository,
                                     EnderecoMapper enderecoMapper) {
        this.servidorTemporarioRepository = servidorTemporarioRepository;
        this.servidorTemporarioMapper = servidorTemporarioMapper;
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
    }

    @Override
    public ServidorTemporarioModel buscarPorId(Long pesId) {

       ServidorTemporarioModel servidorTemporarioModelBd =  servidorTemporarioMapper
                .servidorTemporarioEntityToModel( servidorTemporarioRepository.findById(pesId)
                        .orElseThrow(() -> new RuntimeException("Servidor Temporario não encontrado")));

        Set<EnderecoEntity> enderecoEntityList = pessoaEnderecoRepository
                .listaEnderecosPessoa(servidorTemporarioModelBd.getPessoa().getPesId());
        servidorTemporarioModelBd.getPessoa().setEnderecoList(enderecoMapper.enderecoEntityListToEnderecoModelList(
                enderecoEntityList));
        return servidorTemporarioModelBd;
    }

    @Transactional
    @Override
    public ServidorTemporarioModel criar(ServidorTemporarioModel servidorTemporarioModel) {

        regrasNegocio(servidorTemporarioModel);

        ServidorTemporarioModel servidorTemporarioModeBD=  servidorTemporarioMapper.servidorTemporarioEntityToModel(
                servidorTemporarioRepository.saveAndFlush(
                        servidorTemporarioMapper.servidorTemporarioModelToEntity(servidorTemporarioModel)
                )
        );

       Set<EnderecoEntity> enderecoEntityList = new HashSet<>();

       servidorTemporarioModeBD.getPessoa().setEnderecoIdList(servidorTemporarioModel.getPessoa().getEnderecoIdList());

       servidorTemporarioModeBD.getPessoa().getEnderecoIdList().forEach(e -> {
            EnderecoModel enderecoModelBanco = enderecoMapper
                    .enderecoEntityToModel(enderecoRepository.findById(e)
                            .orElseThrow(() -> new RuntimeException("Endereco não encontrado")));

            PessoaEnderecoId pessoaEnderecoId = new PessoaEnderecoId();
            pessoaEnderecoId.setPessoa(servidorTemporarioModeBD.getPessoa().getPesId());
            pessoaEnderecoId.setEndereco(enderecoModelBanco.getEndId());

            PessoaEnderecoEntity pessoaEnderecoEntity = new PessoaEnderecoEntity();
            pessoaEnderecoEntity.setPesEndId(pessoaEnderecoId);
            pessoaEnderecoEntity.setPessoa(pessoaMapper.pessoaModelToEntity(servidorTemporarioModeBD.getPessoa()));
            pessoaEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));
            enderecoEntityList.add(pessoaEnderecoRepository.save(pessoaEnderecoEntity).getEndereco());
        });

        servidorTemporarioModeBD.getPessoa().setEnderecoList(enderecoMapper
                .enderecoEntityListToEnderecoModelList(enderecoEntityList));

        return servidorTemporarioModeBD;
    }

    @Transactional
    @Override
    public ServidorTemporarioModel atualizar(Long pesId, ServidorTemporarioModel servidorTemporarioModel) {

        regrasNegocio(servidorTemporarioModel);

        // Busca o servidor no banco
        ServidorTemporarioModel servidorTemporarioModeBD = servidorTemporarioMapper.servidorTemporarioEntityToModel(
                servidorTemporarioRepository.findById(pesId)
                        .orElseThrow(() -> new RuntimeException("Servidor Temporário não encontrado"))
        );

        servidorTemporarioModeBD.setStDataAdmissao(servidorTemporarioModel.getStDataAdmissao());
        servidorTemporarioModeBD.setStDataDemissao(servidorTemporarioModel.getStDataDemissao());

        PessoaModel pessoaModel = servidorTemporarioModel.getPessoa();
        PessoaEntity pessoaEntityBD = pessoaMapper.pessoaModelToEntity(servidorTemporarioModeBD.getPessoa());

        pessoaEntityBD.setPesNome(pessoaModel.getPesNome());
        pessoaEntityBD.setPesDataNascimento(pessoaModel.getPesDataNascimento());
        pessoaEntityBD.setPesSexo(pessoaModel.getPesSexo());
        pessoaEntityBD.setPesMae(pessoaModel.getPesMae());
        pessoaEntityBD.setPesPai(pessoaModel.getPesPai());


        Set<Long> enderecoIdsNovos = new HashSet<>(pessoaModel.getEnderecoIdList());
        Set<EnderecoModel> enderecoModelBancoList = enderecoMapper.enderecoEntityListToEnderecoModelList(
                pessoaEnderecoRepository.listaEnderecosPessoa(pesId));

        enderecoModelBancoList.forEach(endereco -> {
            if (!enderecoIdsNovos.contains(endereco.getEndId())) {
                PessoaEnderecoId pessoaEnderecoId = new PessoaEnderecoId(pesId, endereco.getEndId());
                pessoaEnderecoRepository.deleteById(pessoaEnderecoId);
            }
        });

        Set<EnderecoEntity> enderecoEntityList = new HashSet<>();
        enderecoIdsNovos.forEach(enderecoId -> {
            EnderecoModel enderecoModelBanco = enderecoMapper.enderecoEntityToModel(
                    enderecoRepository.findById(enderecoId)
                            .orElseThrow(() -> new RuntimeException("Endereco não encontrado"))
            );

            PessoaEnderecoId pessoaEnderecoId = new PessoaEnderecoId();
            pessoaEnderecoId.setPessoa(pessoaEntityBD.getPesId());
            pessoaEnderecoId.setEndereco(enderecoModelBanco.getEndId());

            PessoaEnderecoEntity pessoaEnderecoEntity = new PessoaEnderecoEntity();
            pessoaEnderecoEntity.setPesEndId(pessoaEnderecoId);
            pessoaEnderecoEntity.setPessoa(pessoaEntityBD);
            pessoaEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));
            enderecoEntityList.add(pessoaEnderecoRepository.save(pessoaEnderecoEntity).getEndereco());
        });

        pessoaEntityBD.setEnderecoList(enderecoEntityList);

        pessoaRepository.save(pessoaEntityBD);

        servidorTemporarioModeBD.setPessoa(pessoaMapper.pessoaEntityToModel(pessoaEntityBD));

        servidorTemporarioRepository.save(
                servidorTemporarioMapper.servidorTemporarioModelToEntity(servidorTemporarioModeBD));

        return servidorTemporarioModeBD;
    }


    @Override
    public PageResponse<ServidorTemporarioModel> listaServidoresTemporariosPaginado(PageQuery pageQuery) {
        Page<ServidorTemporarioEntity> page = servidorTemporarioRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );
        page.getContent().forEach((p)->{
            Set<EnderecoEntity> enderecoEntityList = pessoaEnderecoRepository
                    .listaEnderecosPessoa(p.getPessoa().getPesId());
            p.getPessoa().setEnderecoList(
                    enderecoEntityList);
        });

        Page<ServidorTemporarioModel> servidorTemporarioModelPage = page.map(servidorTemporarioMapper::servidorTemporarioEntityToModel);

        return new PageResponse<>(
                servidorTemporarioModelPage.getNumber(),
                servidorTemporarioModelPage.getTotalPages(),
                servidorTemporarioModelPage.getTotalElements(),
                servidorTemporarioModelPage.getSize(),
                servidorTemporarioModelPage.getContent()
        );
    }

    @Override
    public void excluir(Long pesId) {
        ServidorTemporarioModel servidorTemporarioModelBanco = buscarPorId(pesId);
        servidorTemporarioRepository.delete(servidorTemporarioMapper.servidorTemporarioModelToEntity(servidorTemporarioModelBanco));
    }


    private void regrasNegocio(ServidorTemporarioModel servidorTemporarioModel) {
        if (servidorTemporarioModel.getStDataAdmissao() == null){
            throw new RuntimeException("Data de admissao é obrigatoria");
        }


        if(servidorTemporarioModel.getPessoa().getPesNome().isBlank()){
            throw new RuntimeException("Nome é obrigatorio");
        }

        if(servidorTemporarioModel.getPessoa().getPesNome().length() > 200){
            throw new RuntimeException("Nome deve ter no maximo 200 caracteres");
        }

        if(servidorTemporarioModel.getPessoa().getPesPai().isBlank()){
            throw new RuntimeException("Nome do Pai é obrigatorio");
        }

        if(servidorTemporarioModel.getPessoa().getPesPai().length() > 200){
            throw new RuntimeException("Nome do Pai deve ter no maximo 200 caracteres");
        }

        if(servidorTemporarioModel.getPessoa().getPesMae().isBlank()){
            throw new RuntimeException("Nome da Mae é obrigatorio");
        }

        if(servidorTemporarioModel.getPessoa().getPesMae().length() > 200){
            throw new RuntimeException("Nome da Mae deve ter no maximo 200 caracteres");
        }

        if(servidorTemporarioModel.getPessoa().getPesSexo().isBlank()){
            throw new RuntimeException("Sexo é obrigatorio");
        }

        if(servidorTemporarioModel.getPessoa().getPesSexo().length() > 200){
            throw new RuntimeException("Sexo deve ter no maximo 09 caracteres");
        }

        if(servidorTemporarioModel.getPessoa().getPesDataNascimento() == null){
            throw new RuntimeException("Data de Nascimento é obrigatorio");
        }

    }
}

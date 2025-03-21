package br.com.robertoxavier.api.ports.servidor;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.api.mappers.pessoa.PessoaMapper;
import br.com.robertoxavier.api.mappers.servidor.ServidorTemporarioMapper;
import br.com.robertoxavier.data.entities.EnderecoEntity;
import br.com.robertoxavier.data.entities.PessoaEnderecoEntity;
import br.com.robertoxavier.data.entities.PessoaEnderecoId;
import br.com.robertoxavier.data.entities.ServidorTemporarioEntity;
import br.com.robertoxavier.data.repositories.EnderecoRepository;
import br.com.robertoxavier.data.repositories.PessoaEnderecoRepository;
import br.com.robertoxavier.data.repositories.PessoaRepository;
import br.com.robertoxavier.data.repositories.ServidorTemporarioRepository;
import br.com.robertoxavier.model.EnderecoModel;
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

        ServidorTemporarioModel servidorTemporarioModeBD = servidorTemporarioMapper.servidorTemporarioEntityToModel(
                servidorTemporarioRepository.findById(pesId)
                        .orElseThrow(() -> new RuntimeException("Servidor Temporário não encontrado"))
        );

        servidorTemporarioModeBD.setStDataAdmissao(servidorTemporarioModel.getStDataAdmissao());
        servidorTemporarioModeBD.setStDataDemissao(servidorTemporarioModel.getStDataDemissao());
        servidorTemporarioModeBD.getPessoa().setPesId(pesId);
        servidorTemporarioModeBD.getPessoa().setPesNome(servidorTemporarioModel.getPessoa().getPesNome());
        servidorTemporarioModeBD.getPessoa().setPesDataNascimento(servidorTemporarioModel.getPessoa().getPesDataNascimento());
        servidorTemporarioModeBD.getPessoa().setPesSexo(servidorTemporarioModel.getPessoa().getPesSexo());
        servidorTemporarioModeBD.getPessoa().setPesMae(servidorTemporarioModel.getPessoa().getPesMae());
        servidorTemporarioModeBD.getPessoa().setPesPai(servidorTemporarioModel.getPessoa().getPesPai());

        servidorTemporarioRepository.save(
                servidorTemporarioMapper.servidorTemporarioModelToEntity(servidorTemporarioModeBD));

        Set<EnderecoModel> enderecoModelBancoList = enderecoMapper.enderecoEntityListToEnderecoModelList(pessoaEnderecoRepository.listaEnderecosPessoa(pesId));

        Set<Long> enderecoIdsNovos = new HashSet<>(servidorTemporarioModel.getPessoa().getEnderecoIdList());

        // Remover endereços que estão no banco mas não estão na nova lista
        enderecoModelBancoList.forEach(endereco -> {
            if (!enderecoIdsNovos.contains(endereco.getEndId())) {
                PessoaEnderecoId pessoaEnderecoId = new PessoaEnderecoId(pesId,endereco.getEndId());
                pessoaEnderecoRepository.deleteById(pessoaEnderecoId);
            }
        });

        // Inserir ou atualizar os endereços novos
        Set<EnderecoEntity> enderecoEntityList = new HashSet<>();
        enderecoIdsNovos.forEach(enderecoId -> {
            EnderecoModel enderecoModelBanco = enderecoMapper.enderecoEntityToModel(
                    enderecoRepository.findById(enderecoId)
                            .orElseThrow(() -> new RuntimeException("Endereco não encontrado"))
            );

            PessoaEnderecoId pessoaEnderecoId = new PessoaEnderecoId();
            pessoaEnderecoId.setPessoa(servidorTemporarioModeBD.getPessoa().getPesId());
            pessoaEnderecoId.setEndereco(enderecoModelBanco.getEndId());

            PessoaEnderecoEntity pessoaEnderecoEntity = new PessoaEnderecoEntity();
            pessoaEnderecoEntity.setPesEndId(pessoaEnderecoId);
            pessoaEnderecoEntity.setPessoa(pessoaMapper.pessoaModelToEntity(servidorTemporarioModeBD.getPessoa()));
            pessoaEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));
            enderecoEntityList.add(pessoaEnderecoRepository.save(pessoaEnderecoEntity).getEndereco());
        });

        // Atualizar a lista de endereços vinculados ao servidor
        servidorTemporarioModeBD.getPessoa().setEnderecoList(enderecoMapper.enderecoEntityListToEnderecoModelList(enderecoEntityList));

        return servidorTemporarioModeBD;
    }



    @Override
    public PageResponse<ServidorTemporarioModel> listaServidoresEfetivosPaginado(PageQuery pageQuery) {
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

}

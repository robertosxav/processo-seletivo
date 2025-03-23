package br.com.robertoxavier.api.ports.servidor;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.config.NotFoundException;
import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.api.mappers.pessoa.PessoaMapper;
import br.com.robertoxavier.api.mappers.servidor.ServidorEfetivoMapper;
import br.com.robertoxavier.data.entities.*;
import br.com.robertoxavier.data.repositories.*;
import br.com.robertoxavier.model.*;
import br.com.robertoxavier.ports.servidor.ServidorEfetivoPort;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ServidorEfetivoPortImp implements ServidorEfetivoPort {

    private final ServidorEfetivoRepository servidorEfetivoRepository;

    private final PessoaRepository pessoaRepository;

    private final ServidorEfetivoMapper servidorEfetivoMapper;

    private final PessoaMapper pessoaMapper;

    private final PessoaEnderecoRepository pessoaEnderecoRepository;

    private final EnderecoRepository enderecoRepository;

    private final EnderecoMapper enderecoMapper;

    private final LotacaoRepository lotacaoRepository;

    private final FotoRepository fotoRepository;

    public ServidorEfetivoPortImp(ServidorEfetivoRepository servidorEfetivoRepository,
                                  ServidorEfetivoMapper servidorEfetivoMapper,
                                  PessoaRepository pessoaRepository,
                                  PessoaMapper pessoaMapper,
                                  PessoaEnderecoRepository pessoaEnderecoRepository, EnderecoRepository enderecoRepository,
                                  EnderecoMapper enderecoMapper, LotacaoRepository lotacaoRepository, FotoRepository fotoRepository) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.servidorEfetivoMapper = servidorEfetivoMapper;
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
        this.lotacaoRepository = lotacaoRepository;
        this.fotoRepository = fotoRepository;
    }

    @Override
    public ServidorEfetivoModel buscarPorId(Long pesId) {

       ServidorEfetivoModel servidorEfetivoModelBd =  servidorEfetivoMapper
                .servidorEfetivoEntityToModel( servidorEfetivoRepository.findById(pesId)
                        .orElseThrow(() -> new RuntimeException("Servidor Efetivo não encontrado")));

        Set<EnderecoEntity> enderecoEntityList = pessoaEnderecoRepository
                .listaEnderecosPessoa(servidorEfetivoModelBd.getPessoa().getPesId());
        servidorEfetivoModelBd.getPessoa().setEnderecoList(enderecoMapper.enderecoEntityListToEnderecoModelList(
                enderecoEntityList));
        return servidorEfetivoModelBd;
    }

    @Transactional
    @Override
    public ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel) {
        regrasNegocio(servidorEfetivoModel);

        ServidorEfetivoModel servidorEfetivoModel1=  servidorEfetivoMapper.servidorEfetivoEntityToModel(
                servidorEfetivoRepository.saveAndFlush(
                        servidorEfetivoMapper.servidorEfetivoModelToEntity(servidorEfetivoModel)
                )
        );

       Set<EnderecoEntity> enderecoEntityList = new HashSet<>();

       servidorEfetivoModel1.getPessoa().setEnderecoIdList(servidorEfetivoModel.getPessoa().getEnderecoIdList());

       servidorEfetivoModel1.getPessoa().getEnderecoIdList().forEach(e -> {
            EnderecoModel enderecoModelBanco = enderecoMapper
                    .enderecoEntityToModel(enderecoRepository.findById(e)
                            .orElseThrow(() -> new NotFoundException("Endereco não encontrado")));

            PessoaEnderecoId pessoaEnderecoId = new PessoaEnderecoId();
            pessoaEnderecoId.setPessoa(servidorEfetivoModel1.getPessoa().getPesId());
            pessoaEnderecoId.setEndereco(enderecoModelBanco.getEndId());

            PessoaEnderecoEntity pessoaEnderecoEntity = new PessoaEnderecoEntity();
            pessoaEnderecoEntity.setPesEndId(pessoaEnderecoId);
            pessoaEnderecoEntity.setPessoa(pessoaMapper.pessoaModelToEntity(servidorEfetivoModel1.getPessoa()));
            pessoaEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));
            enderecoEntityList.add(pessoaEnderecoRepository.save(pessoaEnderecoEntity).getEndereco());
        });

        servidorEfetivoModel1.getPessoa().setEnderecoList(enderecoMapper
                .enderecoEntityListToEnderecoModelList(enderecoEntityList));

        return servidorEfetivoModel1;
    }

    private void regrasNegocio(ServidorEfetivoModel servidorEfetivoModel) {
        if (servidorEfetivoModel.getSeMatricula().isBlank()){
            throw new RuntimeException("É obrigatório a matricula");
        }

        if(servidorEfetivoModel.getSeMatricula().length() >20){
            throw new RuntimeException("Matricula deve ter no maximo 20 caracteres");
        }

        if(servidorEfetivoModel.getPessoa().getPesNome().isBlank()){
            throw new RuntimeException("Nome é obrigatorio");
        }

        if(servidorEfetivoModel.getPessoa().getPesNome().length() > 200){
            throw new RuntimeException("Nome deve ter no maximo 200 caracteres");
        }

        if(servidorEfetivoModel.getPessoa().getPesPai().isBlank()){
            throw new RuntimeException("Nome do Pai é obrigatorio");
        }

        if(servidorEfetivoModel.getPessoa().getPesPai().length() > 200){
            throw new RuntimeException("Nome do Pai deve ter no maximo 200 caracteres");
        }

        if(servidorEfetivoModel.getPessoa().getPesMae().isBlank()){
            throw new RuntimeException("Nome da Mae é obrigatorio");
        }

        if(servidorEfetivoModel.getPessoa().getPesMae().length() > 200){
            throw new RuntimeException("Nome da Mae deve ter no maximo 200 caracteres");
        }

        if(servidorEfetivoModel.getPessoa().getPesSexo().isBlank()){
            throw new RuntimeException("Sexo é obrigatorio");
        }

        if(servidorEfetivoModel.getPessoa().getPesSexo().length() > 200){
            throw new RuntimeException("Sexo deve ter no maximo 09 caracteres");
        }

        if(servidorEfetivoModel.getPessoa().getPesDataNascimento() == null){
            throw new RuntimeException("Data de Nascimento é obrigatorio");
        }

    }


    @Override
    public ServidorEfetivoModel atualizar(Long pesId, ServidorEfetivoModel servidorEfetivoModel) {
        regrasNegocio(servidorEfetivoModel);
        ServidorEfetivoModel servidorEfetivoModelBD = servidorEfetivoMapper.servidorEfetivoEntityToModel(
                servidorEfetivoRepository.findById(pesId)
                        .orElseThrow(() -> new RuntimeException("Servidor Efetivo não encontrado"))
        );

        servidorEfetivoModelBD.setSeMatricula(servidorEfetivoModel.getSeMatricula());

        PessoaModel pessoaModel = servidorEfetivoModel.getPessoa();
        PessoaEntity pessoaEntityBD = pessoaMapper
                .pessoaModelToEntity(servidorEfetivoModelBD.getPessoa());

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

        servidorEfetivoModelBD.setPessoa(pessoaMapper.pessoaEntityToModel(pessoaEntityBD));

        servidorEfetivoRepository.save(
                servidorEfetivoMapper.servidorEfetivoModelToEntity(servidorEfetivoModelBD));

        return servidorEfetivoModelBD;
    }

    @Override
    public PageResponse<ServidorEfetivoModel> listaServidoresEfetivosPaginado(PageQuery pageQuery) {
        Page<ServidorEfetivoEntity> page = servidorEfetivoRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );
        page.getContent().forEach((p)->{
            Set<EnderecoEntity> enderecoEntityList = pessoaEnderecoRepository
                    .listaEnderecosPessoa(p.getPessoa().getPesId());
            p.getPessoa().setEnderecoList(
                    enderecoEntityList);
        });

        Page<ServidorEfetivoModel> servidorEfetivoModelPage = page.map(servidorEfetivoMapper::servidorEfetivoEntityToModel);

        return new PageResponse<>(
                servidorEfetivoModelPage.getNumber(),
                servidorEfetivoModelPage.getTotalPages(),
                servidorEfetivoModelPage.getTotalElements(),
                servidorEfetivoModelPage.getSize(),
                servidorEfetivoModelPage.getContent()
        );
    }

    @Transactional
    @Override
    public void excluir(Long pesId) {
        ServidorEfetivoModel servidorEfetivoModelBanco = buscarPorId(pesId);
        servidorEfetivoRepository.delete(servidorEfetivoMapper.servidorEfetivoModelToEntity(servidorEfetivoModelBanco));
        excluirPessoa(servidorEfetivoModelBanco.getPessoa());
    }

    private void excluirPessoa(PessoaModel pessoaModel) {

        LotacaoEntity lotacaoEntity = lotacaoRepository.finByPessoaPesId(pessoaModel.getPesId());

        if(lotacaoEntity!= null){
            throw new RuntimeException("Não é possivel exluir a pessoa pois a mesma possui lotacoes ligadas a ela");
        }
        Set<PessoaEnderecoEntity> listaPessoasEnderecos = pessoaEnderecoRepository
                .findByPessoaId(pessoaModel.getPesId());

        listaPessoasEnderecos.forEach(pessoaEnderecoRepository::delete);


        Set<FotoEntity> listaFotosPessoa = fotoRepository
                .findByPessoaId(pessoaModel.getPesId());

        listaFotosPessoa.forEach(fotoRepository::delete);

        pessoaRepository.delete(pessoaMapper
                .pessoaModelToEntity(pessoaModel));
    }

}

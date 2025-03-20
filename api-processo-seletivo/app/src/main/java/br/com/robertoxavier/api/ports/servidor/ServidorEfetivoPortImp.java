package br.com.robertoxavier.api.ports.servidor;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.api.mappers.pessoa.PessoaMapper;
import br.com.robertoxavier.api.mappers.servidor.ServidorEfetivoMapper;
import br.com.robertoxavier.data.entities.EnderecoEntity;
import br.com.robertoxavier.data.entities.ServidorEfetivoEntity;
import br.com.robertoxavier.data.entities.PessoaEnderecoEntity;
import br.com.robertoxavier.data.entities.PessoaEnderecoId;
import br.com.robertoxavier.data.repositories.EnderecoRepository;
import br.com.robertoxavier.data.repositories.PessoaEnderecoRepository;
import br.com.robertoxavier.data.repositories.PessoaRepository;
import br.com.robertoxavier.data.repositories.ServidorEfetivoRepository;
import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.model.ServidorEfetivoModel;
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

    public ServidorEfetivoPortImp(ServidorEfetivoRepository servidorEfetivoRepository,
                                  ServidorEfetivoMapper servidorEfetivoMapper,
                                  PessoaRepository pessoaRepository,
                                  PessoaMapper pessoaMapper,
                                  PessoaEnderecoRepository pessoaEnderecoRepository, EnderecoRepository enderecoRepository,
                                  EnderecoMapper enderecoMapper) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.servidorEfetivoMapper = servidorEfetivoMapper;
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
    }

    @Override
    public ServidorEfetivoModel buscarPorId(Long cidId) {

       ServidorEfetivoModel servidorEfetivoModelBd =  servidorEfetivoMapper
                .servidorEfetivoEntityToModel( servidorEfetivoRepository.findById(cidId)
                        .orElseThrow(() -> new RuntimeException("ServidorEfetivo não encontrado")));

        Set<EnderecoEntity> enderecoEntityList = pessoaEnderecoRepository
                .listaEnderecosPessoa(servidorEfetivoModelBd.getPessoa().getPesId());
        servidorEfetivoModelBd.getPessoa().setEnderecoList(enderecoMapper.enderecoEntityListToEnderecoModelList(
                enderecoEntityList));
        return servidorEfetivoModelBd;
    }

    @Transactional
    @Override
    public ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel) {

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
                            .orElseThrow(() -> new RuntimeException("Endereco não encontrado")));

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


    @Override
    public ServidorEfetivoModel atualizar(Long cidId, ServidorEfetivoModel servidorEfetivoModel) {

       /* if(servidorEfetivoModel.getCidUf().length() !=2){
            throw new RuntimeException("UF deve ter dois caracteres");
        }*/

      

        ServidorEfetivoModel servidorEfetivoModelBanco = buscarPorId(cidId);

       // servidorEfetivoModelBanco.setCidNome(servidorEfetivoModel.getCidNome());
        //servidorEfetivoModelBanco.setCidUf(servidorEfetivoModel.getCidUf());

        return servidorEfetivoMapper.servidorEfetivoEntityToModel(
                servidorEfetivoRepository.save(
                        servidorEfetivoMapper.servidorEfetivoModelToEntity(servidorEfetivoModelBanco)
                )
        );
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

    @Override
    public void excluir(Long cidId) {
        ServidorEfetivoModel servidorEfetivoModelBanco = buscarPorId(cidId);
        servidorEfetivoRepository.delete(servidorEfetivoMapper.servidorEfetivoModelToEntity(servidorEfetivoModelBanco));
    }

}

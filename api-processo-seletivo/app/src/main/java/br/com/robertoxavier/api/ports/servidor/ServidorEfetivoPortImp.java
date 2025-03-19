package br.com.robertoxavier.api.ports.servidor;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.pessoa.PessoaMapper;
import br.com.robertoxavier.api.mappers.servidor.ServidorEfetivoMapper;
import br.com.robertoxavier.data.entities.ServidorEfetivoEntity;
import br.com.robertoxavier.data.repositories.PessoaRepository;
import br.com.robertoxavier.data.repositories.ServidorEfetivoRepository;
import br.com.robertoxavier.model.PessoaModel;
import br.com.robertoxavier.model.ServidorEfetivoModel;
import br.com.robertoxavier.ports.servidor.ServidorEfetivoPort;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class ServidorEfetivoPortImp implements ServidorEfetivoPort {

    private final ServidorEfetivoRepository servidorEfetivoRepository;

    private final PessoaRepository pessoaRepository;

    private final ServidorEfetivoMapper servidorEfetivoMapper;

    private final PessoaMapper pessoaMapper;

    public ServidorEfetivoPortImp(ServidorEfetivoRepository servidorEfetivoRepository,
                                  ServidorEfetivoMapper servidorEfetivoMapper,
                                  PessoaRepository pessoaRepository,
                                  PessoaMapper pessoaMapper) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.servidorEfetivoMapper = servidorEfetivoMapper;
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    @Override
    public ServidorEfetivoModel buscarPorId(Long cidId) {

       return servidorEfetivoMapper
                .servidorEfetivoEntityToModel( servidorEfetivoRepository.findById(cidId)
                        .orElseThrow(() -> new RuntimeException("ServidorEfetivo não encontrado")));

    }

    @Transactional
    @Override
    public ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel) {
        /*if(servidorEfetivoModel.getCidUf().length() !=2){
            throw new RuntimeException("UF deve ter dois caracteres");
        }*/

        /*PessoaModel pessoaModelBanco = pessoaMapper.pessoaEntityToModel(
                pessoaRepository.saveAndFlush(
                        pessoaMapper.pessoaModelToEntity(servidorEfetivoModel.getPessoa())
                )
        );
        servidorEfetivoModel.setPessoa(pessoaModelBanco);*/

        return servidorEfetivoMapper.servidorEfetivoEntityToModel(
                servidorEfetivoRepository.saveAndFlush(
                        servidorEfetivoMapper.servidorEfetivoModelToEntity(servidorEfetivoModel)
                )
        );
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

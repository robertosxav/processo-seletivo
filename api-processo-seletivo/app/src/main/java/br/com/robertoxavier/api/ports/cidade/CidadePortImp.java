package br.com.robertoxavier.api.ports.cidade;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.config.NotFoundException;
import br.com.robertoxavier.api.mappers.cidade.CidadeMapper;
import br.com.robertoxavier.data.entities.CidadeEntity;
import br.com.robertoxavier.data.entities.EnderecoEntity;
import br.com.robertoxavier.data.repositories.CidadeRepository;
import br.com.robertoxavier.data.repositories.EnderecoRepository;
import br.com.robertoxavier.model.CidadeModel;
import br.com.robertoxavier.ports.cidade.CidadePort;
import jakarta.transaction.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CidadePortImp implements CidadePort {

    private final CidadeRepository cidadeRepository;

    private final CidadeMapper cidadeMapper;

    private final EnderecoRepository enderecoRepository;

    public CidadePortImp(CidadeRepository cidadeRepository, CidadeMapper cidadeMapper, EnderecoRepository enderecoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeMapper = cidadeMapper;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public CidadeModel buscarPorId(Long cidId) {

        return cidadeMapper
                .cidadeEntityToModel( cidadeRepository.findById(cidId)
                        .orElseThrow(() -> new NotFoundException("Cidade não encontrada")));
    }

    @Transactional
    @Override
    public CidadeModel criar(CidadeModel cidadeModel) {
        if(cidadeModel.getCidUf().length() !=2){
            throw new RuntimeException("UF deve ter dois caracteres");
        }

        if(cidadeModel.getCidNome().isBlank() || cidadeModel.getCidNome().length() >200){
            throw new RuntimeException("Nome da cidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }
        return cidadeMapper.cidadeEntityToModel(
                cidadeRepository.saveAndFlush(
                        cidadeMapper.cidadeModelToEntity(cidadeModel)
                )
        );
    }

    @Override
    public CidadeModel atualizar(Long cidId, CidadeModel cidadeModel) {

        if(cidadeModel.getCidUf().length() !=2){
            throw new RuntimeException("UF deve ter dois caracteres");
        }

        if(cidadeModel.getCidNome().isBlank() || cidadeModel.getCidNome().length() >200){
            throw new RuntimeException("Nome da cidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }

        CidadeModel cidadeModelBanco = buscarPorId(cidId);

        cidadeModelBanco.setCidNome(cidadeModel.getCidNome());
        cidadeModelBanco.setCidUf(cidadeModel.getCidUf());

        return cidadeMapper.cidadeEntityToModel(
                cidadeRepository.save(
                        cidadeMapper.cidadeModelToEntity(cidadeModelBanco)
                )
        );
    }

    @Override
    public PageResponse<CidadeModel> listaCidadesPaginado(PageQuery pageQuery) {
        Page<CidadeEntity> page = cidadeRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );

        Page<CidadeModel> cidadeModelPage = page.map(cidadeMapper::cidadeEntityToModel);

        return new PageResponse<>(
                cidadeModelPage.getNumber(),
                cidadeModelPage.getTotalPages(),
                cidadeModelPage.getTotalElements(),
                cidadeModelPage.getSize(),
                cidadeModelPage.getContent()
        );
    }

    @Override
    public void excluir(Long cidId) {
        CidadeModel cidadeModelBanco = buscarPorId(cidId);
        List<EnderecoEntity> enderecoEntityList = enderecoRepository.findByCidadeCidId(cidId);
        if(!enderecoEntityList.isEmpty()){
            throw new RuntimeException("Não foi possível excluir a cidade pois a mesma está ligada a um ou mais enderecos");
        }
        cidadeRepository.delete(cidadeMapper.cidadeModelToEntity(cidadeModelBanco));
    }

}

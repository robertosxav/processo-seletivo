package br.com.robertoxavier.api.ports.unidade;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.unidade.UnidadeMapper;
import br.com.robertoxavier.data.entities.UnidadeEntity;
import br.com.robertoxavier.data.repositories.UnidadeRepository;
import br.com.robertoxavier.model.UnidadeModel;
import br.com.robertoxavier.ports.unidade.UnidadePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class UnidadePortImp implements UnidadePort {

    private final UnidadeRepository unidadeRepository;

    private final UnidadeMapper unidadeMapper;

    public UnidadePortImp(UnidadeRepository unidadeRepository, UnidadeMapper unidadeMapper) {
        this.unidadeRepository = unidadeRepository;
        this.unidadeMapper = unidadeMapper;
    }

    @Override
    public UnidadeModel buscarPorId(Long cidId) {

        return unidadeMapper
                .unidadeEntityToModel( unidadeRepository.findById(cidId)
                        .orElseThrow(() -> new RuntimeException("Unidade não encontrada")));
    }

    @Override
    public UnidadeModel criar(UnidadeModel unidadeModel) {

        if(unidadeModel.getUnidSigla().isBlank() || unidadeModel.getUnidSigla().length()>20){
            throw new RuntimeException("Sigla da unidade não pode ser vazio e deve ter no máximo 20 caracteres");
        }

        if(unidadeModel.getUnidNome().isBlank() || unidadeModel.getUnidNome().length() >200){
            throw new RuntimeException("Nome da unidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }

        return unidadeMapper.unidadeEntityToModel(
                unidadeRepository.save(
                        unidadeMapper.unidadeModelToEntity(unidadeModel)
                )
        );
    }

    @Override
    public UnidadeModel atualizar(Long cidId, UnidadeModel unidadeModel) {

        if(unidadeModel.getUnidSigla().isBlank() || unidadeModel.getUnidSigla().length()>20){
            throw new RuntimeException("Sigla da unidade não pode ser vazio e deve ter no máximo 20 caracteres");
        }

        if(unidadeModel.getUnidNome().isBlank() || unidadeModel.getUnidNome().length() >200){
            throw new RuntimeException("Nome da unidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }

        UnidadeModel unidadeModelBanco = buscarPorId(cidId);

        unidadeModelBanco.setUnidNome(unidadeModel.getUnidNome());
        unidadeModelBanco.setUnidSigla(unidadeModel.getUnidSigla());

        return unidadeMapper.unidadeEntityToModel(
                unidadeRepository.save(
                        unidadeMapper.unidadeModelToEntity(unidadeModelBanco)
                )
        );
    }

    @Override
    public PageResponse<UnidadeModel> listaUnidadesPaginado(PageQuery pageQuery) {
        Page<UnidadeEntity> page = unidadeRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );

        Page<UnidadeModel> unidadeModelPage = page.map(unidadeMapper::unidadeEntityToModel);

        return new PageResponse<>(
                unidadeModelPage.getNumber(),
                unidadeModelPage.getTotalPages(),
                unidadeModelPage.getTotalElements(),
                unidadeModelPage.getSize(),
                unidadeModelPage.getContent()
        );
    }

    @Override
    public void excluir(Long cidId) {
        UnidadeModel unidadeModelBanco = buscarPorId(cidId);
        unidadeRepository.delete(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
    }

}

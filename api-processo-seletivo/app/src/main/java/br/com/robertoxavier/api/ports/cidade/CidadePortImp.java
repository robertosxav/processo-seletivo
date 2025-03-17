package br.com.robertoxavier.api.ports.cidade;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.cidade.CidadeMapper;
import br.com.robertoxavier.data.entities.CidadeEntity;
import br.com.robertoxavier.data.repositories.CidadeRepository;
import br.com.robertoxavier.model.CidadeModel;
import br.com.robertoxavier.ports.cidade.CidadePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class CidadePortImp implements CidadePort {

    private final CidadeRepository cidadeRepository;

    private final CidadeMapper cidadeMapper;

    public CidadePortImp(CidadeRepository cidadeRepository,CidadeMapper cidadeMapper) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeMapper = cidadeMapper;
    }

    @Override
    public CidadeModel buscarPorId(Long cidId) {

        return cidadeMapper
                .cidadeEntityToModel( cidadeRepository.findById(cidId)
                        .orElseThrow(() -> new RuntimeException("Cidade não encontrada")));
    }

    @Override
    public CidadeModel criar(CidadeModel cidadeModel) {
        if(cidadeModel.getCidUf().length() !=2){
            throw new RuntimeException("UF deve ter dois caracteres");
        }

        if(cidadeModel.getCidNome().isBlank() || cidadeModel.getCidNome().length() >200){
            throw new RuntimeException("Nome da cidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }
        return cidadeMapper.cidadeEntityToModel(
                cidadeRepository.save(
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
        cidadeRepository.delete(cidadeMapper.cidadeModelToEntity(cidadeModelBanco));
    }

}

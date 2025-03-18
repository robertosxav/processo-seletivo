package br.com.robertoxavier.api.ports.lotacao;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.api.mappers.lotacao.LotacaoMapper;
import br.com.robertoxavier.data.entities.LotacaoEntity;
import br.com.robertoxavier.data.repositories.LotacaoRepository;
import br.com.robertoxavier.model.LotacaoModel;
import br.com.robertoxavier.ports.cidade.CidadePort;
import br.com.robertoxavier.ports.lotacao.LotacaoPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class LotacaoPortImp implements LotacaoPort {

    private final LotacaoRepository lotacaoRepository;

    private final LotacaoMapper lotacaoMapper;

    public LotacaoPortImp(LotacaoRepository lotacaoRepository, LotacaoMapper lotacaoMapper) {
        this.lotacaoRepository = lotacaoRepository;
        this.lotacaoMapper = lotacaoMapper;
    }

    @Override
    public LotacaoModel buscarPorId(Long cidId) {

        return lotacaoMapper
                .lotacaoEntityToModel( lotacaoRepository.findById(cidId)
                        .orElseThrow(() -> new RuntimeException("Lotacao não encontrada")));
    }

    @Override
    public LotacaoModel criar(LotacaoModel lotacaoModel) {
        /*if(lotacaoModel.getCidUf().length() !=2){
            throw new RuntimeException("UF deve ter dois caracteres");
        }

        if(lotacaoModel.getCidNome().isBlank() || lotacaoModel.getCidNome().length() >200){
            throw new RuntimeException("Nome da cidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }*/
        return lotacaoMapper.lotacaoEntityToModel(
                lotacaoRepository.save(
                        lotacaoMapper.lotacaoModelToEntity(lotacaoModel)
                )
        );
    }

    @Override
    public LotacaoModel atualizar(Long cidId, LotacaoModel lotacaoModel) {

      /*  if(lotacaoModel.getCidUf().length() !=2){
            throw new RuntimeException("UF deve ter dois caracteres");
        }

        if(lotacaoModel.getCidNome().isBlank() || lotacaoModel.getCidNome().length() >200){
            throw new RuntimeException("Nome da cidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }
*/
        LotacaoModel lotacaoModelBanco = buscarPorId(cidId);

       /* lotacaoModelBanco.setCidNome(lotacaoModel.getCidNome());
        lotacaoModelBanco.setCidUf(lotacaoModel.getCidUf());*/

        return lotacaoMapper.lotacaoEntityToModel(
                lotacaoRepository.save(
                        lotacaoMapper.lotacaoModelToEntity(lotacaoModelBanco)
                )
        );
    }

    @Override
    public PageResponse<LotacaoModel> listaLotacoesPaginado(PageQuery pageQuery) {
        Page<LotacaoEntity> page = lotacaoRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );

        Page<LotacaoModel> lotacaoModelPage = page.map(lotacaoMapper::lotacaoEntityToModel);

        return new PageResponse<>(
                lotacaoModelPage.getNumber(),
                lotacaoModelPage.getTotalPages(),
                lotacaoModelPage.getTotalElements(),
                lotacaoModelPage.getSize(),
                lotacaoModelPage.getContent()
        );
    }

    @Override
    public void excluir(Long cidId) {
        LotacaoModel lotacaoModelBanco = buscarPorId(cidId);
        lotacaoRepository.delete(lotacaoMapper.lotacaoModelToEntity(lotacaoModelBanco));
    }

}

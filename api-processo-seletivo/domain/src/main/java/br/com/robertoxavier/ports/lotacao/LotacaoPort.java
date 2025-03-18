package br.com.robertoxavier.ports.lotacao;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.LotacaoModel;

public interface LotacaoPort {

    LotacaoModel criar(LotacaoModel lotacaoModel);

    LotacaoModel buscarPorId(Long cidId);

    LotacaoModel atualizar(Long cidId, LotacaoModel lotacaoModel);

    PageResponse<LotacaoModel> listaLotacoesPaginado(PageQuery pageQuery);

    void excluir(Long cidId);
}

package br.com.robertoxavier.ports.unidade;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.UnidadeModel;

public interface UnidadePort {

    UnidadeModel criar(UnidadeModel unidadeModel);

    UnidadeModel buscarPorId(Long unidId);

    UnidadeModel atualizar(Long unidId, UnidadeModel unidadeModel);

    PageResponse<UnidadeModel> listaUnidadesPaginado(PageQuery pageQuery);

    void excluir(Long unidId);
}

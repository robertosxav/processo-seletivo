package br.com.robertoxavier.ports.cidade;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.CidadeModel;

import java.util.List;

public interface CidadePort {

    CidadeModel criar(CidadeModel cidadeModel);

    CidadeModel buscarPorId(Long cidId);

    CidadeModel atualizar(Long cidId, CidadeModel cidadeModel);

    PageResponse<CidadeModel> listaCidadesPaginado(PageQuery pageQuery);

    void excluir(Long cidId);
}

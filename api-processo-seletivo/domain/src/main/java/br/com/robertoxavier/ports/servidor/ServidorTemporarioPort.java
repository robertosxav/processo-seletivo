package br.com.robertoxavier.ports.servidor;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.ServidorTemporarioModel;

public interface ServidorTemporarioPort {
    ServidorTemporarioModel criar(ServidorTemporarioModel servidorTemporarioModel);

    ServidorTemporarioModel buscarPorId(Long pesId);

    ServidorTemporarioModel atualizar(Long pesId, ServidorTemporarioModel servidorTemporarioModel);

    PageResponse<ServidorTemporarioModel> listaServidoresEfetivosPaginado(PageQuery pageQuery);

    void excluir(Long pesId);
}

package br.com.robertoxavier.ports.servidor;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.ServidorEfetivoModel;

public interface ServidorEfetivoPort {
    ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel);

    ServidorEfetivoModel buscarPorId(Long pesId);

    ServidorEfetivoModel atualizar(Long pesId, ServidorEfetivoModel servidorEfetivoModel);

    PageResponse<ServidorEfetivoModel> listaServidoresEfetivosPaginado(PageQuery pageQuery);

    void excluir(Long pesId);
}

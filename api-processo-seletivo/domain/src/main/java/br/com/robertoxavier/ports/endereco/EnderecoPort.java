package br.com.robertoxavier.ports.endereco;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.EnderecoModel;

import java.util.List;

public interface EnderecoPort {

    EnderecoModel criar(EnderecoModel EnderecoModel);

    EnderecoModel buscarPorId(Long cidId);

    EnderecoModel atualizar(Long cidId, EnderecoModel EnderecoModel);

    PageResponse<EnderecoModel> listaEnderecosPaginado(PageQuery pageQuery);

    void excluir(Long endId);

    PageResponse<EnderecoModel> buscarEnderecoFuncional(String nome, PageQuery pageQuery);
}

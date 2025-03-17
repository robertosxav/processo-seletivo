package br.com.robertoxavier.ports.endereco;

import br.com.robertoxavier.model.EnderecoModel;

import java.util.List;

public interface EnderecoPort {

    EnderecoModel criar(EnderecoModel EnderecoModel);

    EnderecoModel buscarPorId(Long cidId);

    EnderecoModel atualizar(Long cidId, EnderecoModel EnderecoModel);

    List<EnderecoModel> listaCidadesPaginado();

}

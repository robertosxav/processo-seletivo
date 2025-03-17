package br.com.robertoxavier.api.ports.endereco;

import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.ports.endereco.EnderecoPort;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class EnderecoPortImp implements EnderecoPort {

    @Override
    public EnderecoModel criar(EnderecoModel EnderecoModel) {
        return null;
    }

    @Override
    public EnderecoModel buscarPorId(Long cidId) {
        return null;
    }

    @Override
    public EnderecoModel atualizar(Long cidId, EnderecoModel EnderecoModel) {
        return null;
    }

    @Override
    public List<EnderecoModel> listaCidadesPaginado() {
        return List.of();
    }
}

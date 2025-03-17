package br.com.robertoxavier.stories.unidade;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.UnidadeModel;
import br.com.robertoxavier.ports.unidade.UnidadePort;

public class UnidadeUseStory {

    private final UnidadePort unidadePort;

    public UnidadeUseStory(UnidadePort unidadePort) {
        this.unidadePort = unidadePort;
    }

    public UnidadeModel buscarPorId(Long unidId){
        return unidadePort.buscarPorId(unidId);
    }

    public PageResponse<UnidadeModel> listaUnidadesPaginado(PageQuery pageQuery){
        return unidadePort.listaUnidadesPaginado(pageQuery);
    }

    public UnidadeModel criar(UnidadeModel unidadeModel){
        return unidadePort.criar(unidadeModel);
    }

    public UnidadeModel atualizar(Long unidId,UnidadeModel unidadeModel){
        return unidadePort.atualizar(unidId,unidadeModel);
    }

    public void excluir(Long unidId){
         unidadePort.excluir(unidId);
    }
}

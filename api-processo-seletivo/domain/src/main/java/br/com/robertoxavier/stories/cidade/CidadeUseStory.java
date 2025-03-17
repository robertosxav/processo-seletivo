package br.com.robertoxavier.stories.cidade;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.CidadeModel;
import br.com.robertoxavier.ports.cidade.CidadePort;

import java.util.List;

public class CidadeUseStory {

    private final CidadePort cidadePort;

    public CidadeUseStory(CidadePort cidadePort) {
        this.cidadePort = cidadePort;
    }

    public CidadeModel buscarPorId(Long cidId){
        return cidadePort.buscarPorId(cidId);
    }

    public PageResponse<CidadeModel> listaCidadesPaginado(PageQuery pageQuery){
        return cidadePort.listaCidadesPaginado(pageQuery);
    }

    public CidadeModel criar(CidadeModel cidadeModel){
        return cidadePort.criar(cidadeModel);
    }

    public CidadeModel atualizar(Long cidId,CidadeModel cidadeModel){
        return cidadePort.atualizar(cidId,cidadeModel);
    }

    public void excluir(Long cidId){
         cidadePort.excluir(cidId);
    }
}

package br.com.robertoxavier.stories.servidor;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.ServidorEfetivoModel;
import br.com.robertoxavier.ports.servidor.ServidorEfetivoPort;

import java.util.List;

public class ServidorEfetivoUseStory {

    private final ServidorEfetivoPort servidorEfetivoPort;

    public ServidorEfetivoUseStory(ServidorEfetivoPort servidorEfetivoPort) {
        this.servidorEfetivoPort = servidorEfetivoPort;
    }

    public ServidorEfetivoModel buscarPorId(Long pesId){
        return servidorEfetivoPort.buscarPorId(pesId);
    }

   public PageResponse<ServidorEfetivoModel> listaServidoresEfetivosPaginado(PageQuery pageQuery){
        return servidorEfetivoPort.listaServidoresEfetivosPaginado(pageQuery);
    }

    public ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel){
        return servidorEfetivoPort.criar(servidorEfetivoModel);
    }

    public ServidorEfetivoModel atualizar(Long pesId,ServidorEfetivoModel servidorEfetivoModel){
        return servidorEfetivoPort.atualizar(pesId,servidorEfetivoModel);
    }

    public void excluir(Long pesId){
         servidorEfetivoPort.excluir(pesId);
    }

    public PageResponse<ServidorEfetivoModel> buscarServidoreLotadosUnidade(Long unidId, PageQuery pageQuery) {
        return servidorEfetivoPort.buscarServidoresLotadosUnidade(unidId,pageQuery);
    }
}

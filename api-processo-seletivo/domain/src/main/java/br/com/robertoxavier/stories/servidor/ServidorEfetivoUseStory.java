package br.com.robertoxavier.stories.servidor;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.ServidorEfetivoModel;
import br.com.robertoxavier.ports.servidor.ServidorEfetivoPort;

public class ServidorEfetivoUseStory {

    private final ServidorEfetivoPort servidorEfetivoPort;

    public ServidorEfetivoUseStory(ServidorEfetivoPort servidorEfetivoPort) {
        this.servidorEfetivoPort = servidorEfetivoPort;
    }

    public ServidorEfetivoModel buscarPorId(Long cidId){
        return servidorEfetivoPort.buscarPorId(cidId);
    }

   public PageResponse<ServidorEfetivoModel> listaServidoresEfetivosPaginado(PageQuery pageQuery){
        return servidorEfetivoPort.listaServidoresEfetivosPaginado(pageQuery);
    }

    public ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel){
        return servidorEfetivoPort.criar(servidorEfetivoModel);
    }

    public ServidorEfetivoModel atualizar(Long cidId,ServidorEfetivoModel servidorEfetivoModel){
        return servidorEfetivoPort.atualizar(cidId,servidorEfetivoModel);
    }

    public void excluir(Long cidId){
         servidorEfetivoPort.excluir(cidId);
    }
}

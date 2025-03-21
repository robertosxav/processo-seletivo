package br.com.robertoxavier.stories.servidor;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.ServidorTemporarioModel;
import br.com.robertoxavier.ports.servidor.ServidorTemporarioPort;

public class ServidorTemporarioUseStory {

    private final ServidorTemporarioPort servidorTemporarioPort;

    public ServidorTemporarioUseStory(ServidorTemporarioPort servidorTemporarioPort) {
        this.servidorTemporarioPort = servidorTemporarioPort;
    }

    public ServidorTemporarioModel buscarPorId(Long pesId){
        return servidorTemporarioPort.buscarPorId(pesId);
    }

   public PageResponse<ServidorTemporarioModel> listaServidoresEfetivosPaginado(PageQuery pageQuery){
        return servidorTemporarioPort.listaServidoresEfetivosPaginado(pageQuery);
    }

    public ServidorTemporarioModel criar(ServidorTemporarioModel servidorTemporarioModel){
        return servidorTemporarioPort.criar(servidorTemporarioModel);
    }

    public ServidorTemporarioModel atualizar(Long pesId,ServidorTemporarioModel servidorTemporarioModel){
        return servidorTemporarioPort.atualizar(pesId,servidorTemporarioModel);
    }

    public void excluir(Long pesId){
         servidorTemporarioPort.excluir(pesId);
    }
}

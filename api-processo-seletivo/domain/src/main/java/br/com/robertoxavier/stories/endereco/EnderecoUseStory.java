package br.com.robertoxavier.stories.endereco;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.ports.endereco.EnderecoPort;

import java.util.List;

public class EnderecoUseStory {

    private final EnderecoPort enderecoPort;

    public EnderecoUseStory(EnderecoPort enderecoPort) {
        this.enderecoPort = enderecoPort;
    }

    public EnderecoModel buscarPorId(Long endId){
        return enderecoPort.buscarPorId(endId);
    }

    public PageResponse<EnderecoModel> listaEnderecosPaginado(PageQuery pageQuery){
        return enderecoPort.listaEnderecosPaginado(pageQuery);
    }

    public EnderecoModel criar(EnderecoModel EnderecoModel){
        return enderecoPort.criar(EnderecoModel);
    }

    public EnderecoModel atualizar(Long endId,EnderecoModel EnderecoModel){
        return enderecoPort.atualizar(endId,EnderecoModel);
    }

    public void excluir(Long endId){
        enderecoPort.excluir(endId);
    }

    public PageResponse<EnderecoModel> buscarEnderecoFuncional(String nome, PageQuery pageQuery) {
        return enderecoPort.buscarEnderecoFuncional(nome,pageQuery);
    }
}

package br.com.robertoxavier.stories.lotacao;

import br.com.robertoxavier.PageQuery;
import br.com.robertoxavier.PageResponse;
import br.com.robertoxavier.model.LotacaoModel;
import br.com.robertoxavier.ports.lotacao.LotacaoPort;

public class LotacaoUseStory {

    private final LotacaoPort lotacaoPort;

    public LotacaoUseStory(LotacaoPort lotacaoPort) {
        this.lotacaoPort = lotacaoPort;
    }

    public LotacaoModel buscarPorId(Long lotId){
        return lotacaoPort.buscarPorId(lotId);
    }

    public PageResponse<LotacaoModel> listaLotacoesPaginado(PageQuery pageQuery){
        return lotacaoPort.listaLotacoesPaginado(pageQuery);
    }

    public LotacaoModel criar(LotacaoModel lotacaoModel){
        return lotacaoPort.criar(lotacaoModel);
    }

    public LotacaoModel atualizar(Long lotId,LotacaoModel lotacaoModel){
        return lotacaoPort.atualizar(lotId,lotacaoModel);
    }

    public void excluir(Long lotId){
         lotacaoPort.excluir(lotId);
    }
}

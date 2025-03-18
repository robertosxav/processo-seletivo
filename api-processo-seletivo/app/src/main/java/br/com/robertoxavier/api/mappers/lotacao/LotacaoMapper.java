package br.com.robertoxavier.api.mappers.lotacao;

import br.com.robertoxavier.data.entities.LotacaoEntity;
import br.com.robertoxavier.dto.lotacao.LotacaoRequest;
import br.com.robertoxavier.dto.lotacao.LotacaoResponse;
import br.com.robertoxavier.model.LotacaoModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LotacaoMapper {

    public LotacaoResponse lotacaoModelToResponse(LotacaoModel LotacaoModel){

        if (LotacaoModel == null) {
            return null;
        }

        return new LotacaoResponse(
                /*LotacaoModel.getCidId(),
                LotacaoModel.getCidNome(),
                LotacaoModel.getCidUf()*/
        );
    }

    public LotacaoModel lotacaoRequestToModel(LotacaoRequest LotacaoRequest){
       /* if (LotacaoRequest == null) {
            return null;
        }

        return new LotacaoModel(
                LotacaoRequest.cidNome(),
                LotacaoRequest.cidUf()
        );*/
        return null;
    }

    public LotacaoEntity lotacaoModelToEntity(LotacaoModel LotacaoModel){
      /*  if (LotacaoModel == null) {
            return null;
        }

        return new LotacaoEntity(
                LotacaoModel.getCidId(),
                LotacaoModel.getCidNome(),
                LotacaoModel.getCidUf()
        );*/
        return null;
    }

    public LotacaoModel lotacaoEntityToModel(LotacaoEntity LotacaoEntity){
     /*   if (LotacaoEntity == null) {
            return null;
        }

        return new LotacaoModel(
                LotacaoEntity.getCidId(),
                LotacaoEntity.getCidNome(),
                LotacaoEntity.getCidUf()
        );*/
        return null;
    }

    public List<LotacaoModel> lotacaoEntityListToLotacaoModelList(List<LotacaoEntity>LotacaoEntityList){
        if (LotacaoEntityList == null) {
            return null;
        }

        return LotacaoEntityList.stream()
                .map(this::lotacaoEntityToModel)
                .collect(Collectors.toList());
    }

    public List<LotacaoResponse> lotacaoModelListToLotacaoResponseList(List<LotacaoModel>LotacaoModelList){
        if (LotacaoModelList == null) {
            return null;
        }

        return LotacaoModelList.stream()
                .map(this::lotacaoModelToResponse)
                .collect(Collectors.toList());
    }
}

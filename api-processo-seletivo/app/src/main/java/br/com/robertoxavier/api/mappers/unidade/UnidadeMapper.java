package br.com.robertoxavier.api.mappers.unidade;

import br.com.robertoxavier.data.entities.UnidadeEntity;
import br.com.robertoxavier.dto.unidade.UnidadeRequest;
import br.com.robertoxavier.dto.unidade.UnidadeResponse;
import br.com.robertoxavier.model.UnidadeModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UnidadeMapper {

    public UnidadeResponse unidadeModelToResponse(UnidadeModel unidadeModel){

        if (unidadeModel == null) {
            return null;
        }

        return new UnidadeResponse(
                unidadeModel.getUnidId(),
                unidadeModel.getUnidNome(),
                unidadeModel.getUnidSigla()
        );
    }

    public UnidadeModel unidadeRequestToModel(UnidadeRequest unidadeRequest){
        if (unidadeRequest == null) {
            return null;
        }

        return new UnidadeModel(
                unidadeRequest.unidNome(),
                unidadeRequest.unidSigla(),
                unidadeRequest.enderecoIdList()
        );
    }

    public UnidadeEntity unidadeModelToEntity(UnidadeModel unidadeModel){
        if (unidadeModel == null) {
            return null;
        }

        return new UnidadeEntity(
                unidadeModel.getUnidId(),
                unidadeModel.getUnidNome(),
                unidadeModel.getUnidSigla()
        );
    }

    public UnidadeModel unidadeEntityToModel(UnidadeEntity unidadeEntity){
        if (unidadeEntity == null) {
            return null;
        }

        return new UnidadeModel(
                unidadeEntity.getUnidId(),
                unidadeEntity.getUnidNome(),
                unidadeEntity.getUnidSigla()
        );
    }

    public List<UnidadeModel> unidadeEntityListToUnidadeModelList(List<UnidadeEntity>unidadeEntityList){
        if (unidadeEntityList == null) {
            return null;
        }

        return unidadeEntityList.stream()
                .map(this::unidadeEntityToModel)
                .collect(Collectors.toList());
    }

    public List<UnidadeResponse> unidadeModelListToUnidadeResponseList(List<UnidadeModel>unidadeModelList){
        if (unidadeModelList == null) {
            return null;
        }

        return unidadeModelList.stream()
                .map(this::unidadeModelToResponse)
                .collect(Collectors.toList());
    }
}

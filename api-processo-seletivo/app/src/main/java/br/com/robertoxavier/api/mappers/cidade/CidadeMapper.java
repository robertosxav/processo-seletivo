package br.com.robertoxavier.api.mappers.cidade;

import br.com.robertoxavier.data.entities.CidadeEntity;
import br.com.robertoxavier.dto.cidade.CidadeRequest;
import br.com.robertoxavier.dto.cidade.CidadeResponse;
import br.com.robertoxavier.model.CidadeModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeMapper {

    public CidadeResponse cidadeModelToResponse(CidadeModel cidadeModel){

        if (cidadeModel == null) {
            return null;
        }

        return new CidadeResponse(
                cidadeModel.getCidId(),
                cidadeModel.getCidNome(),
                cidadeModel.getCidUf()
        );
    }

    public CidadeModel cidadeRequestToModel(CidadeRequest cidadeRequest){
        if (cidadeRequest == null) {
            return null;
        }

        return new CidadeModel(
                cidadeRequest.cidNome(),
                cidadeRequest.cidUf()
        );
    }

    public CidadeEntity cidadeModelToEntity(CidadeModel cidadeModel){
        if (cidadeModel == null) {
            return null;
        }

        return new CidadeEntity(
                cidadeModel.getCidId(),
                cidadeModel.getCidNome(),
                cidadeModel.getCidUf()
        );
    }

    public CidadeModel cidadeEntityToModel(CidadeEntity cidadeEntity){
        if (cidadeEntity == null) {
            return null;
        }

        return new CidadeModel(
                cidadeEntity.getCidId(),
                cidadeEntity.getCidNome(),
                cidadeEntity.getCidUf()
        );
    }

    public List<CidadeModel> cidadeEntityListToCidadeModelList(List<CidadeEntity>cidadeEntityList){
        if (cidadeEntityList == null) {
            return null;
        }

        return cidadeEntityList.stream()
                .map(this::cidadeEntityToModel)
                .collect(Collectors.toList());
    }

    public List<CidadeResponse> cidadeModelListToCidadeResponseList(List<CidadeModel>cidadeModelList){
        if (cidadeModelList == null) {
            return null;
        }

        return cidadeModelList.stream()
                .map(this::cidadeModelToResponse)
                .collect(Collectors.toList());
    }
}

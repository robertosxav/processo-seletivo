package br.com.robertoxavier.api.mappers.endereco;

import br.com.robertoxavier.data.entities.EnderecoEntity;

import br.com.robertoxavier.dto.endereco.EnderecoRequest;
import br.com.robertoxavier.dto.endereco.EnderecoResponse;
import br.com.robertoxavier.model.EnderecoModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnderecoMapper {

    public EnderecoResponse enderecoModelToResponse(EnderecoModel enderecoModel){

        if (enderecoModel == null) {
            return null;
        }

        return new EnderecoResponse(
                enderecoModel.getEndId(),
                enderecoModel.getEndTipoLogradouro(),
                enderecoModel.getEndLogradouro(),
                enderecoModel.getEndNumero(),
                enderecoModel.getEndBairro(),
                enderecoModel.getCidade().getCidId()
        );
    }

    public EnderecoModel enderecoRequestToModel(EnderecoRequest enderecoRequest){
        if (enderecoRequest == null) {
            return null;
        }

        return new EnderecoModel(
                enderecoRequest.endTipoLogradouro(),
                enderecoRequest.endLogradouro(),
                enderecoRequest.endNumero(),
                enderecoRequest.endBairro()
        );
    }

    public EnderecoEntity enderecoModelToEntity(EnderecoModel enderecoModel){
        if (enderecoModel == null) {
            return null;
        }

        return new EnderecoEntity(
                enderecoModel.getEndTipoLogradouro(),
                enderecoModel.getEndLogradouro(),
                enderecoModel.getEndNumero(),
                enderecoModel.getEndBairro()
        );
    }

    public EnderecoModel enderecoEntityToModel(EnderecoEntity enderecoEntity){
        if (enderecoEntity == null) {
            return null;
        }

        return new EnderecoModel(
                enderecoEntity.getEndId(),
                enderecoEntity.getEndTipoLogradouro(),
                enderecoEntity.getEndLogradouro(),
                enderecoEntity.getEndNumero(),
                enderecoEntity.getEndBairro()
        );
    }

    public List<EnderecoModel> enderecoEntityListToEnderecoModelList(List<EnderecoEntity>enderecoEntityList){
        if (enderecoEntityList == null) {
            return null;
        }

        return enderecoEntityList.stream()
                .map(this::enderecoEntityToModel)
                .collect(Collectors.toList());
    }

    public List<EnderecoResponse> enderecoModelListToEnderecoResponseList(List<EnderecoModel>enderecoModelList){
        if (enderecoModelList == null) {
            return null;
        }

        return enderecoModelList.stream()
                .map(this::enderecoModelToResponse)
                .collect(Collectors.toList());
    }
}

package br.com.robertoxavier.api.mappers.endereco;

import br.com.robertoxavier.data.entities.CidadeEntity;
import br.com.robertoxavier.data.entities.EnderecoEntity;
import br.com.robertoxavier.dto.cidade.CidadeCompletoRequest;
import br.com.robertoxavier.dto.cidade.CidadeResponse;
import br.com.robertoxavier.dto.endereco.EnderecoRequest;
import br.com.robertoxavier.dto.endereco.EnderecoResponse;
import br.com.robertoxavier.model.CidadeModel;
import br.com.robertoxavier.model.EnderecoModel;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EnderecoMapper {

    public EnderecoResponse enderecoModelToResponse(EnderecoModel enderecoModel) {
        if (enderecoModel == null) {
            return null;
        }

        CidadeResponse cidadeResponse = (enderecoModel.getCidade() != null)
                ? new CidadeResponse(
                enderecoModel.getCidade().getCidId(),
                enderecoModel.getCidade().getCidNome(),
                enderecoModel.getCidade().getCidUf()
        )
                : null;

        return new EnderecoResponse(
                enderecoModel.getEndId(),
                enderecoModel.getEndTipoLogradouro(),
                enderecoModel.getEndLogradouro(),
                enderecoModel.getEndNumero(),
                enderecoModel.getEndBairro(),
                cidadeResponse
        );
    }


    public EnderecoModel enderecoRequestToModel(EnderecoRequest enderecoRequest) {
        if (enderecoRequest == null) {
            return null;
        }

        var cidadeRequest = enderecoRequest.cidadeCompletoRequest();
        var cidadeModel = (cidadeRequest != null)
                ? new CidadeModel(cidadeRequest.cidId(), cidadeRequest.cidNome(), cidadeRequest.cidUf())
                : null;

        return new EnderecoModel(
                enderecoRequest.endTipoLogradouro(),
                enderecoRequest.endLogradouro(),
                enderecoRequest.endNumero(),
                enderecoRequest.endBairro(),
                cidadeModel
        );
    }

    public EnderecoRequest enderecoModelToRequest(EnderecoModel enderecoModel) {
        if (enderecoModel == null) {
            return null;
        }

        var cidadeCompletoRequest = (enderecoModel.getCidade() != null)
                ? new CidadeCompletoRequest(
                enderecoModel.getCidade().getCidId(),
                enderecoModel.getCidade().getCidNome(),
                enderecoModel.getCidade().getCidUf()
        )
                : null;

        return new EnderecoRequest(
                enderecoModel.getEndTipoLogradouro(),
                enderecoModel.getEndLogradouro(),
                enderecoModel.getEndNumero(),
                enderecoModel.getEndBairro(),
                cidadeCompletoRequest // Adiciona a cidade ao request
        );
    }

    public EnderecoEntity enderecoModelToEntity(EnderecoModel enderecoModel) {
        if (enderecoModel == null) {
            return null;
        }

        CidadeEntity cidadeEntity = (enderecoModel.getCidade() != null)
                ? new CidadeEntity(
                enderecoModel.getCidade().getCidId(),
                enderecoModel.getCidade().getCidNome(),
                enderecoModel.getCidade().getCidUf()
        )
                : null;

        return new EnderecoEntity(
                enderecoModel.getEndId(),
                enderecoModel.getEndTipoLogradouro(),
                enderecoModel.getEndLogradouro(),
                enderecoModel.getEndNumero(),
                enderecoModel.getEndBairro(),
                cidadeEntity
        );
    }

    public EnderecoModel enderecoEntityToModel(EnderecoEntity enderecoEntity) {
        if (enderecoEntity == null) {
            return null;
        }

        // Converte CidadeEntity para CidadeModel
        CidadeModel cidadeModel = (enderecoEntity.getCidade() != null)
                ? new CidadeModel(
                enderecoEntity.getCidade().getCidId(),
                enderecoEntity.getCidade().getCidNome(),
                enderecoEntity.getCidade().getCidUf()
        )
                : null;

        return new EnderecoModel(
                enderecoEntity.getEndId(),
                enderecoEntity.getEndTipoLogradouro(),
                enderecoEntity.getEndLogradouro(),
                enderecoEntity.getEndNumero(),
                enderecoEntity.getEndBairro(),
                cidadeModel // Incluindo a cidade convertida
        );
    }


    public Set<EnderecoModel> enderecoEntityListToEnderecoModelList(Set<EnderecoEntity> enderecoEntitySet){
        if (enderecoEntitySet == null) {
            return null;
        }

        return enderecoEntitySet.stream()
                .map(this::enderecoEntityToModel)
                .collect(Collectors.toSet());
    }

    public Set<EnderecoResponse> enderecoModelListToEnderecoResponseList(Set<EnderecoModel>enderecoModelSet){
        if (enderecoModelSet == null) {
            return null;
        }

        return enderecoModelSet.stream()
                .map(this::enderecoModelToResponse)
                .collect(Collectors.toSet());
    }
}

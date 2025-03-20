package br.com.robertoxavier.api.mappers.unidade;

import br.com.robertoxavier.api.mappers.cidade.CidadeMapper;
import br.com.robertoxavier.api.mappers.endereco.EnderecoMapper;
import br.com.robertoxavier.data.entities.EnderecoEntity;
import br.com.robertoxavier.data.entities.UnidadeEntity;
import br.com.robertoxavier.dto.endereco.EnderecoResponse;
import br.com.robertoxavier.dto.unidade.UnidadeRequest;
import br.com.robertoxavier.dto.unidade.UnidadeResponse;
import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.model.UnidadeModel;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UnidadeMapper {
    private final EnderecoMapper enderecoMapper;
    private final CidadeMapper cidadeMapper;

    public UnidadeMapper(EnderecoMapper enderecoMapper, CidadeMapper cidadeMapper) {
        this.enderecoMapper = enderecoMapper;
        this.cidadeMapper = cidadeMapper;
    }

    public UnidadeResponse unidadeModelToResponse(UnidadeModel unidadeModel){

        if (unidadeModel == null) {
            return null;
        }

        Set<EnderecoResponse> enderecoResponseSet = new HashSet<>();


        for (EnderecoModel enderecoModel : unidadeModel.getEnderecoList()) {
            enderecoResponseSet.add(enderecoMapper.enderecoModelToResponse(enderecoModel));
        }

        return new UnidadeResponse(
                unidadeModel.getUnidId(),
                unidadeModel.getUnidNome(),
                unidadeModel.getUnidSigla(),
                enderecoResponseSet
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

    public UnidadeModel unidadeEntityToModel(UnidadeEntity unidadeEntity) {
        if (unidadeEntity == null) {
            return null;
        }
        Set<EnderecoModel> enderecoModelSet = new HashSet<>();

        if(unidadeEntity.getEnderecoList() != null){
            enderecoModelSet = unidadeEntity.getEnderecoList().stream()
                    .map(enderecoMapper::enderecoEntityToModel)
                    .collect(Collectors.toSet());

       }

        return new UnidadeModel(
                unidadeEntity.getUnidId(),
                unidadeEntity.getUnidNome(),
                unidadeEntity.getUnidSigla(),
                enderecoModelSet
        );
    }

    public UnidadeEntity unidadeModelToEntity(UnidadeModel unidadeModel) {
        if (unidadeModel == null) {
            return null;
        }

        Set<EnderecoEntity> enderecoEntitySet = new HashSet<>();

        if(unidadeModel.getEnderecoList() != null) {
            enderecoEntitySet = unidadeModel.getEnderecoList().stream()
                    .map(enderecoMapper::enderecoModelToEntity)
                    .collect(Collectors.toSet());
        }
        return new UnidadeEntity(
                    unidadeModel.getUnidId(),
                    unidadeModel.getUnidNome(),
                    unidadeModel.getUnidSigla(),
                    enderecoEntitySet
        );

    }

    private EnderecoModel enderecoEntityToModel(EnderecoEntity enderecoEntity) {
        if (enderecoEntity == null) {
            return null;
        }

        return new EnderecoModel(
                enderecoEntity.getEndId(),
                enderecoEntity.getEndTipoLogradouro(),
                enderecoEntity.getEndLogradouro(),
                enderecoEntity.getEndNumero(),
                enderecoEntity.getEndBairro(),
                cidadeMapper.cidadeEntityToModel(enderecoEntity.getCidade())
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

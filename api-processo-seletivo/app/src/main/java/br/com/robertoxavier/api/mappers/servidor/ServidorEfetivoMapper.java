package br.com.robertoxavier.api.mappers.servidor;

import br.com.robertoxavier.api.mappers.pessoa.PessoaMapper;
import br.com.robertoxavier.data.entities.ServidorEfetivoEntity;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoRequest;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoResponse;
import br.com.robertoxavier.model.ServidorEfetivoModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServidorEfetivoMapper {

    private final PessoaMapper pessoaMapper;

    public ServidorEfetivoMapper(PessoaMapper pessoaMapper) {
        this.pessoaMapper = pessoaMapper;
    }

    public ServidorEfetivoResponse servidorModelToResponse(ServidorEfetivoModel servidorEfetivoModel){


        if (servidorEfetivoModel == null) {
            return null;
        }


        return new ServidorEfetivoResponse(
                servidorEfetivoModel.getSeMatricula(),
                pessoaMapper.pessoaModelToResponse(servidorEfetivoModel.getPessoa())
        );
    }

    public ServidorEfetivoModel servidorEfetivoRequestToModel(ServidorEfetivoRequest servidorEfetivoRequest){
        if (servidorEfetivoRequest == null) {
            return null;
        }

        return new ServidorEfetivoModel(
                servidorEfetivoRequest.seMatricula(),
                pessoaMapper.pessoaRequestToModel(servidorEfetivoRequest.pessoaRequest())
        );
    }

    public ServidorEfetivoEntity servidorEfetivoModelToEntity(ServidorEfetivoModel servidorEfetivoModel){
        if (servidorEfetivoModel == null) {
            return null;
        }

        return new ServidorEfetivoEntity(
                servidorEfetivoModel.getId(),
                servidorEfetivoModel.getSeMatricula(),
                pessoaMapper.pessoaModelToEntity(servidorEfetivoModel.getPessoa())
        );
    }

    public ServidorEfetivoModel servidorEfetivoEntityToModel(ServidorEfetivoEntity servidorEfetivoEntity){
        if (servidorEfetivoEntity == null) {
            return null;
        }

        return new ServidorEfetivoModel(
                servidorEfetivoEntity.getId(),
                servidorEfetivoEntity.getSeMatricula(),
                pessoaMapper.pessoaEntityToModel(servidorEfetivoEntity.getPessoa())
        );
    }

    public List<ServidorEfetivoModel> servidorEntityListToServidorModelList(List<ServidorEfetivoEntity>servidorEfetivoEntityList){
        if (servidorEfetivoEntityList == null) {
            return null;
        }

        return servidorEfetivoEntityList.stream()
                .map(this::servidorEfetivoEntityToModel)
                .collect(Collectors.toList());
    }

    public List<ServidorEfetivoResponse> servidorEfetivoModelListToServidorResponseList(List<ServidorEfetivoModel>servidorModelList){
        if (servidorModelList == null) {
            return null;
        }

        return servidorModelList.stream()
                .map(this::servidorModelToResponse)
                .collect(Collectors.toList());
    }

}

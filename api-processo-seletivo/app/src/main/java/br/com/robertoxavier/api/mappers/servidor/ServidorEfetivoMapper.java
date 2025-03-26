package br.com.robertoxavier.api.mappers.servidor;

import br.com.robertoxavier.api.mappers.pessoa.PessoaMapper;
import br.com.robertoxavier.data.entities.LotacaoEntity;
import br.com.robertoxavier.data.entities.ServidorEfetivoEntity;
import br.com.robertoxavier.data.entities.vo.ServidoresUnidadeVo;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoLotacaoResponse;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoRequest;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoResponse;
import br.com.robertoxavier.model.LotacaoModel;
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

    public ServidorEfetivoResponse servidorEfetivoModelToResponse(ServidorEfetivoModel servidorEfetivoModel){


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
                .map(this::servidorEfetivoModelToResponse)
                .collect(Collectors.toList());
    }


    public ServidorEfetivoLotacaoResponse servidorEfetivLotacaoModelToResponse(ServidorEfetivoModel servidorEfetivoModel){

        if (servidorEfetivoModel == null) {
            return null;
        }

        return new ServidorEfetivoLotacaoResponse(
                servidorEfetivoModel.getNome(),
                servidorEfetivoModel.getIdade(),
                servidorEfetivoModel.getNomeUnidade(),
                servidorEfetivoModel.getListLinkFotos()
        );

    }

    public List<ServidorEfetivoLotacaoResponse> servidorEfetivoLotacaoModelListToServidorEfetivoLotacaoResponseList(List<ServidorEfetivoModel>servidorModelList){
        if (servidorModelList == null) {
            return null;
        }

        return servidorModelList.stream()
                .map(this::servidorEfetivLotacaoModelToResponse)
                .collect(Collectors.toList());
    }

    public ServidorEfetivoModel servidorEfetivoVoToModel(ServidoresUnidadeVo servidorUnidadeVo){
        if (servidorUnidadeVo == null) {
            return null;
        }

        return new ServidorEfetivoModel(
                servidorUnidadeVo.getNome(),
                Long.valueOf(servidorUnidadeVo.getIdade()),
                servidorUnidadeVo.getNomeUnidade(),
                servidorUnidadeVo.getlistLinkFotos()
        );
    }

    public List<ServidorEfetivoModel> servidorEfetivoVoListToServidorModelList(List<ServidoresUnidadeVo>servidoresUnidadeVoList){
        if (servidoresUnidadeVoList == null) {
            return null;
        }

        return servidoresUnidadeVoList.stream()
                .map(this::servidorEfetivoVoToModel)
                .collect(Collectors.toList());
    }

}

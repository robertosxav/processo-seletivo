package br.com.robertoxavier.api.mappers.fotoPessoa;

import br.com.robertoxavier.api.mappers.pessoa.PessoaMapper;
import br.com.robertoxavier.data.entities.CidadeEntity;
import br.com.robertoxavier.data.entities.FotoEntity;
import br.com.robertoxavier.dto.cidade.CidadeResponse;
import br.com.robertoxavier.dto.fotoPessoa.FotoRequest;
import br.com.robertoxavier.dto.fotoPessoa.FotoResponse;
import br.com.robertoxavier.model.FotoModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FotoMapper {
    private final PessoaMapper pessoaMapper;

    public FotoMapper(PessoaMapper pessoaMapper) {
        this.pessoaMapper = pessoaMapper;
    }

    public FotoModel fotoEntityToModel(FotoEntity fotoEntity) {
        if (fotoEntity == null) {
            return null;
        }

        return new FotoModel(
                fotoEntity.getPessoa().getPesId(),
                fotoEntity.getLinkFoto()
        );
    }

    public FotoEntity fotoModelToEntity(FotoModel fotoModel) {
        if (fotoModel == null) {
            return null;
        }

        return new FotoEntity();
   }

    public FotoResponse fotoModelToResponse(FotoModel fotoModel) {
        if (fotoModel == null) {
            return null;
        }

        return new FotoResponse(
                fotoModel.getPesId(),
                fotoModel.getLinkFoto()
        );
    }

    public FotoModel fotoRequestToModel(FotoRequest fotoRequest) {
        if (fotoRequest == null) {
            return null;
        }

        return new FotoModel(
                fotoRequest.pesId(),
                fotoRequest.foto()
        );
    }


    public List<FotoResponse> fotoModelListToFotoResponseList(List<FotoModel> fotoModelList){
        if (fotoModelList == null) {
            return null;
        }

        return fotoModelList.stream()
                .map(this::fotoModelToResponse)
                .collect(Collectors.toList());
    }

    public List<FotoModel> fotoRequestListToFotoModelList(List<FotoRequest> fotoRequestList){
        if (fotoRequestList == null) {
            return null;
        }

        return fotoRequestList.stream()
                .map(this::fotoRequestToModel)
                .collect(Collectors.toList());
    }
}

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
                fotoEntity.getFpId(),
                fotoEntity.getPessoa().getPesId(),
                fotoEntity.getFpData(),
                fotoEntity.getFpBucket(),
                fotoEntity.getFpHash()
        );
    }

    public FotoEntity fotoModelToEntity(FotoModel fotoModel) {
        if (fotoModel == null) {
            return null;
        }

        return new FotoEntity(
                fotoModel.getFpData(),
                fotoModel.getFpBucket(),
                fotoModel.getFpHash(),
                fotoModel.getPesId()
        );
    }

    public FotoResponse fotoModelToResponse(FotoModel fotoModel) {
        if (fotoModel == null) {
            return null;
        }

        return new FotoResponse(
                fotoModel.getPesId(),
                null
        );
    }

    public FotoModel fotoRequestToModel(FotoRequest fotoRequest) {
        if (fotoRequest == null) {
            return null;
        }

        return new FotoModel(
                fotoRequest.pesId(),
                LocalDate.now(),
                fotoRequest.pesId().toString(),
                fotoRequest.foto().checksum()
        );
    }
}

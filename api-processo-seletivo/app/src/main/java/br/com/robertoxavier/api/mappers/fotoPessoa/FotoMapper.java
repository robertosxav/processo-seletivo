package br.com.robertoxavier.api.mappers.fotoPessoa;

import br.com.robertoxavier.data.entities.FotoEntity;
import br.com.robertoxavier.dto.fotoPessoa.FotoResponse;
import br.com.robertoxavier.dto.servidor.ServidorEfetivoRequest;
import br.com.robertoxavier.model.FotoModel;
import br.com.robertoxavier.model.ServidorEfetivoModel;
import org.springframework.stereotype.Component;

@Component
public class FotoMapper {
    public FotoModel fotoEntityToModel(FotoEntity fotoEntity) {
        return null;
    }

    public FotoEntity fotoModelToEntity(FotoModel fotoModel) {
        return null;
    }

    public FotoResponse fotoModelToResponse(ServidorEfetivoModel criar) {
        return null;
    }

    public ServidorEfetivoModel fotoRequestToModel(ServidorEfetivoRequest servidorEfetivoRequest) {
        return null;
    }
}

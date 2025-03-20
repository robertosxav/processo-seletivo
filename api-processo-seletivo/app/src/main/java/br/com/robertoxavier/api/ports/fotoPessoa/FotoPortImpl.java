package br.com.robertoxavier.api.ports.fotoPessoa;

import br.com.robertoxavier.api.mappers.cidade.CidadeMapper;
import br.com.robertoxavier.api.mappers.fotoPessoa.FotoMapper;
import br.com.robertoxavier.data.repositories.CidadeRepository;
import br.com.robertoxavier.data.repositories.FotoRepository;
import br.com.robertoxavier.model.FotoModel;
import br.com.robertoxavier.ports.fotoModel.FotoPort;
import org.springframework.stereotype.Component;

@Component
public class FotoPortImpl implements FotoPort {

    private final FotoRepository fotoRepository;

    private final FotoMapper fotoMapper;

    public FotoPortImpl(FotoRepository fotoRepository, FotoMapper fotoMapper) {
        this.fotoRepository = fotoRepository;
        this.fotoMapper = fotoMapper;
    }

    public FotoModel criar(FotoModel fotoModel) {
        return fotoMapper.fotoEntityToModel(
                fotoRepository.saveAndFlush(
                        fotoMapper.fotoModelToEntity(fotoModel)
                )
        );
    }
}

package br.com.robertoxavier.stories.fotoPessoa;

import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.model.FotoModel;
import br.com.robertoxavier.ports.fotoModel.FotoPort;

import java.util.List;

public class FotoPessoaUseStory {

    private final FotoPort fotoPort;

    public FotoPessoaUseStory(FotoPort fotoPort) {
        this.fotoPort = fotoPort;
    }

    public List<FotoModel> uploadFotos(List<FotoModel> fotoModelList){
        return fotoPort.uploadFotos(fotoModelList);
    }
}

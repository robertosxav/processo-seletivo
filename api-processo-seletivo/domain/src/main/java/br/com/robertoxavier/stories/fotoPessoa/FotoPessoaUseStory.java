package br.com.robertoxavier.stories.fotoPessoa;

import br.com.robertoxavier.model.EnderecoModel;
import br.com.robertoxavier.model.FotoModel;
import br.com.robertoxavier.ports.fotoModel.FotoPort;

public class FotoPessoaUseStory {

    private final FotoPort fotoPort;

    public FotoPessoaUseStory(FotoPort fotoPort) {
        this.fotoPort = fotoPort;
    }

    public FotoModel criar(FotoModel fotoModel){
        return fotoPort.criar(fotoModel);
    }
}

package br.com.robertoxavier.ports.fotoModel;

import br.com.robertoxavier.model.CidadeModel;
import br.com.robertoxavier.model.FotoModel;

import java.util.List;

public interface FotoPort {

    List<FotoModel> uploadFotos(List<FotoModel> fotoModelList);
}

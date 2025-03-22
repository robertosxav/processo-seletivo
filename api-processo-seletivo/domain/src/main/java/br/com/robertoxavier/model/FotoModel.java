package br.com.robertoxavier.model;

import br.com.robertoxavier.service.Resource;

public class FotoModel {

    private Long pesId;

    private Resource foto;

    private String linkFoto;

    public Long getPesId() {
        return pesId;
    }

    public void setPesId(Long pesId) {
        this.pesId = pesId;
    }

    public Resource getFoto() {
        return foto;
    }

    public void setFoto(Resource foto) {
        this.foto = foto;
    }

    public String getLinkFoto() {
        return linkFoto;
    }

    public void setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
    }

    public FotoModel() {
    }

    public FotoModel(Long pesId, Resource foto) {
        this.pesId = pesId;
        this.foto = foto;
    }

    public FotoModel(Resource foto) {
        this.foto = foto;
    }

    public FotoModel(Long pesId, String linkFoto) {
        this.pesId = pesId;
        this.linkFoto = linkFoto;
    }
}

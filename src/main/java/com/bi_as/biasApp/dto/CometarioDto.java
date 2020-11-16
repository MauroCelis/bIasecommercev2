package com.bi_as.biasApp.dto;

import com.bi_as.biasApp.domain.Cometario;

public class CometarioDto {
    private Integer idComentario;
    private Integer producId;
    private Integer calificacion;
    private String comentario;


    public CometarioDto(){

    }

    public CometarioDto(Cometario cometario) {
        this.idComentario = cometario.getIdComentario();
        this.producId = cometario.getProductIdProduct().getIdProduct();
        this.calificacion = cometario.getCalificacion();
        this.comentario = cometario.getCometario();
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }


    public Integer getProducId() {
        return producId;
    }

    public void setProducId(Integer producId) {
        this.producId = producId;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}

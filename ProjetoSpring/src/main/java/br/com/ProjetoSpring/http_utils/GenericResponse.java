package br.com.ProjetoSpring.http_utils;

import javax.persistence.Transient;

public class GenericResponse {

    @Transient
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public GenericResponse() {
    }
}
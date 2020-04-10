package br.com.ProjetoSpring.models.enums;

public enum UsuarioStatus {

    ATIVO(true),
    INATIVO(false);

    private Boolean status;

    UsuarioStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

}

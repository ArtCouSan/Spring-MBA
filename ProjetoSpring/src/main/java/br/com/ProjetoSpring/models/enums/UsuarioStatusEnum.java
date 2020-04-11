package br.com.ProjetoSpring.models.enums;

public enum UsuarioStatusEnum {

    ATIVO(true),
    INATIVO(false);

    private Boolean status;

    UsuarioStatusEnum(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

}

package br.com.ProjetoSpring.models.enums;

public enum TipoTransferenciaEnum {

    CREDITO("C"),
    DEBITO("D");

    private String tipo;

    TipoTransferenciaEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static TipoTransferenciaEnum getName(String value) {
        for (TipoTransferenciaEnum tipo : TipoTransferenciaEnum.values()) {
            if (tipo.getTipo().equals(value)) {
                return tipo;
            }
        }
        return null;
    }

}

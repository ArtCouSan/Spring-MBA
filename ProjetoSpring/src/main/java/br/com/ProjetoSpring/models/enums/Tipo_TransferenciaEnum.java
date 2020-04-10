package br.com.ProjetoSpring.models.enums;

public enum Tipo_TransferenciaEnum {

    CREDITO("C"),
    DEBITO("D");

    private String tipo;

    Tipo_TransferenciaEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static Tipo_TransferenciaEnum getName(String value) {
        for (Tipo_TransferenciaEnum tipo : Tipo_TransferenciaEnum.values()) {
            if (tipo.getTipo().equals(value)) {
                return tipo;
            }
        }
        return null;
    }

}

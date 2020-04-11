package br.com.ProjetoSpring.models.enums;

public enum PerfilEnum {
    ADMIN("ROLE_ADMIN");

    private String perfil;

    PerfilEnum(String perfil) {
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public static PerfilEnum getName(String value) {
        for (PerfilEnum perfil : PerfilEnum.values()) {
            if (perfil.getPerfil().equals(value)) {
                return perfil;
            }
        }
        return null;
    }

}

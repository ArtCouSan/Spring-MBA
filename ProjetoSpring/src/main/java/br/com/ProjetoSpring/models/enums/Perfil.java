package br.com.ProjetoSpring.models.enums;

public enum Perfil {
    ADMIN("ROLE_ADMIN");

    private String perfil;

    Perfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public static Perfil getName(String value) {
        for (Perfil perfil : Perfil.values()) {
            if (perfil.getPerfil().equals(value)) {
                return perfil;
            }
        }
        return null;
    }

}

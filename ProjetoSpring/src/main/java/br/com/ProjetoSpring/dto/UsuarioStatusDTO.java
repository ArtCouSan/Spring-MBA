package br.com.ProjetoSpring.dto;

public class UsuarioStatusDTO {

    private Boolean status;

    private Long idUsuario;

    public UsuarioStatusDTO(Boolean status, Long idUsuario) {
        this.status = status;
        this.idUsuario = idUsuario;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}

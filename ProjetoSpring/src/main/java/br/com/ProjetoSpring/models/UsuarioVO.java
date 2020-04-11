package br.com.ProjetoSpring.models;

import br.com.ProjetoSpring.http_utils.GenericResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "TB_USUARIOS")
public class UsuarioVO extends GenericResponse {

    @Column(name = "NOME")
    private String nome;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "NICK")
    private String nick;

    @JsonIgnore
    @Column(name ="PASSWORD")
    private String password;

    @Column(name ="STATUS")
    private Boolean status;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CONTA", referencedColumnName = "ID_CONTA")
    private ContaVO conta;

    @JsonIgnore
    @Column(name ="PERFIL")
    private String perfil;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ContaVO getConta() {
        return conta;
    }

    public void setConta(ContaVO conta) {
        this.conta = conta;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public UsuarioVO() {
    }
}

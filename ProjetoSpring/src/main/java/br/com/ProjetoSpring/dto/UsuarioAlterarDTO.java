package br.com.ProjetoSpring.dto;

import br.com.ProjetoSpring.models.UsuarioVO;

public class UsuarioAlterarDTO {

    private Long id;

    private String nome;

    private String nick;

    private String password;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioAlterarDTO(Long id, String nome, String nick, String password) {
        this.id = id;
        this.nome = nome;
        this.nick = nick;
        this.password = password;
    }

    public UsuarioAlterarDTO() {
    }

    public UsuarioVO parseUsuarioVO(UsuarioVO usuarioVO) {
        if (this.nick != null) {
            usuarioVO.setNick(this.nick);
        }
        if (this.nome != null) {
            usuarioVO.setNome(this.nome);
        }
        if (this.password != null) {
            usuarioVO.setPassword(this.password);
        }
        return usuarioVO;
    }

}

package br.com.ProjetoSpring.dto;

import br.com.ProjetoSpring.models.UsuarioVO;

public class UsuarioAlterarDTO {

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

    public UsuarioAlterarDTO(String nome, String nick, String password) {
        this.nome = nome;
        this.nick = nick;
        this.password = password;
    }

    public UsuarioAlterarDTO() {
    }

    public UsuarioVO parseUsuarioVO(){
        UsuarioVO usuarioVO = new UsuarioVO();
        usuarioVO.setNick(this.nick);
        usuarioVO.setNome(this.nome);
        usuarioVO.setPassword(this.password);
        return usuarioVO;
    }

}

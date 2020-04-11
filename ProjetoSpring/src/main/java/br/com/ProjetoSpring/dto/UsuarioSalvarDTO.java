package br.com.ProjetoSpring.dto;

import br.com.ProjetoSpring.models.UsuarioVO;
import br.com.ProjetoSpring.models.enums.UsuarioStatusEnum;

public class UsuarioSalvarDTO {

    private String nome;

    private String nick;

    private String password;

    private ContaSalvarDTO conta;

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

    public ContaSalvarDTO getConta() {
        return conta;
    }

    public void setConta(ContaSalvarDTO conta) {
        this.conta = conta;
    }

    public UsuarioSalvarDTO(String nome, String nick, String password, ContaSalvarDTO conta) {
        this.nome = nome;
        this.nick = nick;
        this.password = password;
        this.conta = conta;
    }

    public UsuarioSalvarDTO() {
    }

    public UsuarioVO parseUsuarioVO(){
        UsuarioVO usuarioVO = new UsuarioVO();
        usuarioVO.setNick(this.nick);
        usuarioVO.setNome(this.nome);
        usuarioVO.setPassword(this.password);
        usuarioVO.setStatus(UsuarioStatusEnum.ATIVO.getStatus());
        if(conta == null){
            ContaSalvarDTO conta = new ContaSalvarDTO();
            usuarioVO.setConta(conta.parseContaVO());
        }else{
            usuarioVO.setConta(conta.parseContaVO());
        }
        return usuarioVO;
    }

}

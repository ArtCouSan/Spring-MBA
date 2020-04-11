package br.com.ProjetoSpring.services;


import br.com.ProjetoSpring.dto.UsuarioAlterarDTO;
import br.com.ProjetoSpring.dto.UsuarioSalvarDTO;
import br.com.ProjetoSpring.models.UsuarioVO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public UsuarioVO salvarUsuario(UsuarioSalvarDTO usuario);
    public UsuarioVO alterarUsuario(UsuarioAlterarDTO usuario);
    public List<UsuarioVO> listarUsuarios();
    public Optional<UsuarioVO> desativarUsuario(Long id);
    public Optional<UsuarioVO> pegarUsuarioPeloId(Long id);
}

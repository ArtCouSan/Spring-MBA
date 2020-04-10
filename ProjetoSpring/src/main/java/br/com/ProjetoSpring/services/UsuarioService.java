package br.com.ProjetoSpring.services;


import br.com.ProjetoSpring.dto.UsuarioAlterarDTO;
import br.com.ProjetoSpring.dto.UsuarioSalvarDTO;
import br.com.ProjetoSpring.dto.UsuarioStatusDTO;
import br.com.ProjetoSpring.models.UsuarioVO;

import java.util.Optional;

public interface UsuarioService {

    public UsuarioVO salvarUsuario(UsuarioSalvarDTO usuario);
    public UsuarioVO alterarUsuario(UsuarioAlterarDTO usuario);
    public Optional<UsuarioVO> alterarStatusUsuario(UsuarioStatusDTO usuario);
    public Optional<UsuarioVO> pegarUsuarioPeloId(Long id);
}

package br.com.ProjetoSpring.services.impl;

import br.com.ProjetoSpring.dto.UsuarioAlterarDTO;
import br.com.ProjetoSpring.dto.UsuarioSalvarDTO;
import br.com.ProjetoSpring.dto.UsuarioStatusDTO;
import br.com.ProjetoSpring.models.UsuarioVO;
import br.com.ProjetoSpring.models.enums.PerfilEnum;
import br.com.ProjetoSpring.repository.UsuarioRepository;
import br.com.ProjetoSpring.services.TransferenciaService;
import br.com.ProjetoSpring.services.UsuarioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repositoryUsuario;
    private BCryptPasswordEncoder pe;

    public UsuarioServiceImpl(UsuarioRepository repositoryUsuario, TransferenciaService serviceTransferencia, BCryptPasswordEncoder pe) {
        this.repositoryUsuario = repositoryUsuario;
        this.pe = pe;
    }

    @Override
    public UsuarioVO salvarUsuario(UsuarioSalvarDTO usuarioSalvarDTO) {
        UsuarioVO usuario = usuarioSalvarDTO.parseUsuarioVO();
        usuario.setPerfil(PerfilEnum.ADMIN.getPerfil());
        usuario.setPassword(pe.encode(usuario.getPassword()));
        return this.repositoryUsuario.save(usuario);
    }

    @Override
    public UsuarioVO alterarUsuario(UsuarioAlterarDTO usuarioAlterarDTO) {
        UsuarioVO usuario = usuarioAlterarDTO.parseUsuarioVO();
        usuario.setPerfil(PerfilEnum.ADMIN.getPerfil());
        usuario.setPassword(pe.encode(usuario.getPassword()));
        return this.repositoryUsuario.save(usuario);
    }

    @Override
    public Optional<UsuarioVO> alterarStatusUsuario(UsuarioStatusDTO usuarioAlterado) {
        Optional<UsuarioVO> usuarioVO = this.repositoryUsuario.findById(usuarioAlterado.getIdUsuario());
        if(usuarioVO.isPresent()){
            UsuarioVO usuario = usuarioVO.get();
            usuario.setStatus(usuarioAlterado.getStatus());
            return Optional.of(this.repositoryUsuario.save(usuario));
        }
        return usuarioVO;
    }

    @Override
    public Optional<UsuarioVO> pegarUsuarioPeloId(Long id) {
        return this.repositoryUsuario.findById(id);
    }

}

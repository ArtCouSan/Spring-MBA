package br.com.ProjetoSpring.services.impl;

import br.com.ProjetoSpring.dto.UsuarioAlterarDTO;
import br.com.ProjetoSpring.dto.UsuarioSalvarDTO;
import br.com.ProjetoSpring.models.UsuarioVO;
import br.com.ProjetoSpring.models.enums.PerfilEnum;
import br.com.ProjetoSpring.models.enums.UsuarioStatusEnum;
import br.com.ProjetoSpring.repository.UsuarioRepository;
import br.com.ProjetoSpring.services.TransferenciaService;
import br.com.ProjetoSpring.services.UsuarioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repositoryUsuario;
    private BCryptPasswordEncoder pe;

    public UsuarioServiceImpl(UsuarioRepository repositoryUsuario, TransferenciaService serviceTransferencia, BCryptPasswordEncoder pe) {
        this.repositoryUsuario = repositoryUsuario;
        this.pe = pe;
    }

    /**
     * Salva usuario
     * @param usuarioSalvarDTO
     * @return
     */
    @Override
    public UsuarioVO salvarUsuario(UsuarioSalvarDTO usuarioSalvarDTO) {
        UsuarioVO usuario = usuarioSalvarDTO.parseUsuarioVO();
        usuario.setPerfil(PerfilEnum.ADMIN.getPerfil());
        usuario.setPassword(pe.encode(usuario.getPassword()));

        Optional<UsuarioVO> usuarioExiste = this.repositoryUsuario.findByNickAndStatus(usuario.getNick(), true);

        if(usuarioExiste.isPresent()) {
            usuario.setMensagem("Usuario já existe");
            return usuario;
        }else {
            return this.repositoryUsuario.save(usuario);
        }

    }

    /**
     * Altera usuario
     * @param usuarioAlterarDTO
     * @return
     */
    @Override
    public UsuarioVO alterarUsuario(UsuarioAlterarDTO usuarioAlterarDTO) {

        Optional<UsuarioVO> usuario = this.repositoryUsuario.findById(usuarioAlterarDTO.getId());
        UsuarioVO usuarioVO = new UsuarioVO();

        if(usuario.isPresent()) {

            usuarioVO = usuarioAlterarDTO.parseUsuarioVO(usuario.get());
            usuarioVO.setPerfil(PerfilEnum.ADMIN.getPerfil());
            if(usuarioVO.getPassword() != null){
                usuarioVO.setPassword(pe.encode(usuarioVO.getPassword()));
            }
            return this.repositoryUsuario.save(usuarioVO);

        }else {
            usuarioVO.setMensagem("Usuario não encontrado");
            return usuarioVO;
        }

    }

    /**
     * Lista todos usuarios
     * @return
     */
    @Override
    public List<UsuarioVO> listarUsuarios() {
        return this.repositoryUsuario.findAll();
    }

    /**
     * Desativa o usuario
     * @param id
     * @return
     */
    @Override
    public Optional<UsuarioVO> desativarUsuario(Long id) {
        Optional<UsuarioVO> usuarioVO = this.repositoryUsuario.findById(id);
        if(usuarioVO.isPresent()){
            UsuarioVO usuario = usuarioVO.get();
            usuario.setStatus(UsuarioStatusEnum.INATIVO.getStatus());
            return Optional.of(this.repositoryUsuario.save(usuario));
        }
        return usuarioVO;
    }

    /**
     * Pega usuario pelo seu id
     * @param id
     * @return
     */
    @Override
    public Optional<UsuarioVO> pegarUsuarioPeloId(Long id) {
        return this.repositoryUsuario.findById(id);
    }

}

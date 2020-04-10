package br.com.ProjetoSpring.services.impl;

import br.com.ProjetoSpring.models.UsuarioVO;
import br.com.ProjetoSpring.repository.UsuarioRepository;
import br.com.ProjetoSpring.security.USS;
import br.com.ProjetoSpring.services.UsuarioService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsuarioRepository repositoryUsuario;

    public UserDetailsServiceImpl(UsuarioRepository repositoryUsuario) {
        this.repositoryUsuario = repositoryUsuario;
    }

    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        UsuarioVO usuarioVO = this.repositoryUsuario.findByNick(nick);

        if(usuarioVO == null){
            throw new UsernameNotFoundException(nick);
        }
        return new USS(
                usuarioVO.getIdUsuario(),
                usuarioVO.getNick(),
                usuarioVO.getPassword(),
                usuarioVO.getPerfil()
        );
    }
}

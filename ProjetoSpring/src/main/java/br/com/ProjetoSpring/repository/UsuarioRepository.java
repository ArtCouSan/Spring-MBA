package br.com.ProjetoSpring.repository;

import br.com.ProjetoSpring.models.ContaVO;
import br.com.ProjetoSpring.models.UsuarioVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

public interface UsuarioRepository extends JpaRepository<UsuarioVO, Long> {

    @Query("select u from UsuarioVO u where u.nick = :nick")
    public UsuarioVO findByNick(@Param("nick") String nick);

}

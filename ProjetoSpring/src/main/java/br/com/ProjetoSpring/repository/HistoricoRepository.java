package br.com.ProjetoSpring.repository;

import br.com.ProjetoSpring.models.HistoricoVO;
import br.com.ProjetoSpring.models.TransferenciaVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface HistoricoRepository extends JpaRepository<HistoricoVO, Long> {

    @Query("SELECT h FROM HistoricoVO h WHERE  h.conta.idConta = :idConta")
    public List<HistoricoVO> historico(@Param("idConta") Long idConta);

}

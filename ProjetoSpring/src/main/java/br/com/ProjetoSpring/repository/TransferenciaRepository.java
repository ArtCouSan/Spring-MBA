package br.com.ProjetoSpring.repository;

import br.com.ProjetoSpring.models.TransferenciaVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<TransferenciaVO, Long> {

    @Query("SELECT t FROM TransferenciaVO t WHERE t.tipo = 'D' and t.conta.idConta = :idConta and CURRENT_DATE BETWEEN CURRENT_DATE AND :dataUltimaParcela")
    public List<TransferenciaVO> debitosHojeAteUltimaParcela(@Param("dataUltimaParcela") Date dataUltimaParcela
    , @Param("idConta") Long idConta);

}

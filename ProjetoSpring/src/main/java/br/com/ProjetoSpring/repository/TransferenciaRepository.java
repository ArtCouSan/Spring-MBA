package br.com.ProjetoSpring.repository;

import br.com.ProjetoSpring.models.TransferenciaVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<TransferenciaVO, Long> {

    @Query("SELECT t FROM TransferenciaVO t WHERE t.tipo = 'D' and t.conta.idConta = :idConta and t.dataLimitePagamento BETWEEN :dataInicioParcela AND :dataFimParcela")
    public List<TransferenciaVO> debitosParcela(@Param("dataInicioParcela") Calendar dataInicioParcela, @Param("dataFimParcela") Calendar dataFimParcela
    , @Param("idConta") Long idConta);

}

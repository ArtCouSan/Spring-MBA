package br.com.ProjetoSpring.services;

import br.com.ProjetoSpring.models.ContaVO;
import br.com.ProjetoSpring.models.HistoricoVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ContaService {

    public Stream<ContaVO> listarContas();
    public Optional<ContaVO> pegarContaPeloId(Long id);
	public List<HistoricoVO> exportarHistorico(Long id);

}

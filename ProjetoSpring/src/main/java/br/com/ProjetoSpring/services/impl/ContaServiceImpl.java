package br.com.ProjetoSpring.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import br.com.ProjetoSpring.models.ContaVO;
import br.com.ProjetoSpring.models.HistoricoVO;
import br.com.ProjetoSpring.repository.ContaRepository;
import br.com.ProjetoSpring.repository.HistoricoRepository;
import org.springframework.stereotype.Service;

import br.com.ProjetoSpring.services.ContaService;

@Service
public class ContaServiceImpl implements ContaService{

    private ContaRepository contaRepository;
    private HistoricoRepository historicoRepository;

    public ContaServiceImpl(ContaRepository contaRepository, HistoricoRepository historicoRepository) {
        this.contaRepository = contaRepository;
        this.historicoRepository = historicoRepository;
    }

    @Override
    public Stream<ContaVO> listarContas() {
        return this.contaRepository.findAll().stream();
    }

    @Override
    public Optional<ContaVO> pegarContaPeloId(Long id) {
        return this.contaRepository.findByIdContaOrderByIdConta(id);
    }

    @Override
    public List<HistoricoVO> exportarHistorico(Long id) {
        return historicoRepository.historico(id);
    }

}

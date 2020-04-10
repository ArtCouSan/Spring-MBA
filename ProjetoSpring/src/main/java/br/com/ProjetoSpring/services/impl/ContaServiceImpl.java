package br.com.ProjetoSpring.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import br.com.ProjetoSpring.models.ContaVO;
import br.com.ProjetoSpring.repository.ContaRepository;
import org.springframework.stereotype.Service;

import br.com.ProjetoSpring.services.ContaService;

@Service
public class ContaServiceImpl implements ContaService{

    private ContaRepository contaRepository;

    ContaServiceImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public Stream<ContaVO> listarContas() {
        return this.contaRepository.findAll().stream();
    }

    @Override
    public Optional<ContaVO> pegarContaPeloId(Long id) {
        return this.contaRepository.findByIdContaOrderByIdConta(id);
    }
}

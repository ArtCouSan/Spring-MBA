package br.com.ProjetoSpring.services;

import br.com.ProjetoSpring.models.ContaVO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ContaService {

    public Stream<ContaVO> listarContas();
    public Optional<ContaVO> pegarContaPeloId(Long id);
	
}

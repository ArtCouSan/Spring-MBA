package br.com.ProjetoSpring.repository;

import br.com.ProjetoSpring.models.ContaVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<ContaVO, Long> {

    public List<ContaVO> findAll();
    public Optional<ContaVO> findByIdContaOrderByIdConta(Long idConta);

}

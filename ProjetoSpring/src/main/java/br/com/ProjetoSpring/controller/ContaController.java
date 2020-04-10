package br.com.ProjetoSpring.controller;

import br.com.ProjetoSpring.models.ContaVO;
import br.com.ProjetoSpring.services.ContaService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/conta")
public class ContaController {

    private ContaService serviceConta;

    ContaController(ContaService serviceConta) {
        this.serviceConta = serviceConta;
    }

    /**
     * Lista contas
     * @return contas
     */
    @GetMapping()
    public Stream<ContaVO> listarContas(){
        return this.serviceConta.listarContas();
    }

    /**
     * Lista conta pelo id
     * @return conta
     */
    @GetMapping("/{id}")
    public ContaVO pegarContaPeloId(@PathVariable Long id){
        return this.serviceConta.pegarContaPeloId(id).get();
    }

}

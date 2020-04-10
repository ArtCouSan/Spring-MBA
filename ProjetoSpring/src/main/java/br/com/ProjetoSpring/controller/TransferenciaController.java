package br.com.ProjetoSpring.controller;

import br.com.ProjetoSpring.dto.DepositarDTO;
import br.com.ProjetoSpring.dto.PagamentoAVistaDTO;
import br.com.ProjetoSpring.dto.PagamentoParceladoDTO;
import br.com.ProjetoSpring.models.TransferenciaVO;
import br.com.ProjetoSpring.services.TransferenciaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/conta/{id}/transferencia")
public class TransferenciaController {

    private TransferenciaService serviceTransferencia;

    public TransferenciaController(TransferenciaService serviceTransferencia) {
        this.serviceTransferencia = serviceTransferencia;
    }

    @PostMapping(value = "/depositar-conta")
    public TransferenciaVO depositar(@PathVariable Long id, @RequestBody DepositarDTO depositarDTO){
        return this.serviceTransferencia.depositar(id, depositarDTO);
    }

    @PostMapping(value = "/pagar-a-vista")
    public TransferenciaVO pagarAVista(@PathVariable Long id, @RequestBody PagamentoAVistaDTO debitoDTO){
        return this.serviceTransferencia.pagamentoAVista(id, debitoDTO);
    }

    @PostMapping(value = "/pagar-parcelado")
    public List<TransferenciaVO> pagarParcelado(@PathVariable Long id, @RequestBody PagamentoParceladoDTO pagamentoParceladoDTO){
        return this.serviceTransferencia.pagamentoParcelado(id, pagamentoParceladoDTO);
    }

}

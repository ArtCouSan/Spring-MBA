package br.com.ProjetoSpring.controller;

import br.com.ProjetoSpring.dto.DepositarDTO;
import br.com.ProjetoSpring.dto.PagamentoAVistaDTO;
import br.com.ProjetoSpring.dto.PagamentoParceladoDTO;
import br.com.ProjetoSpring.models.TransferenciaVO;
import br.com.ProjetoSpring.models.enums.TipoPagamentoEnum;
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

    @PostMapping(value = "/pagar-a-vista")
    public TransferenciaVO pagarAVista(@PathVariable Long id, @RequestBody PagamentoAVistaDTO debitoDTO){
        return this.serviceTransferencia.pagamento(id, debitoDTO, TipoPagamentoEnum.VISTA).get(0);
    }

    @PostMapping(value = "/pagar-parcelado")
    public List<TransferenciaVO> pagarParcelado(@PathVariable Long id, @RequestBody PagamentoParceladoDTO pagamentoParceladoDTO){
        return this.serviceTransferencia.pagamento(id, pagamentoParceladoDTO, TipoPagamentoEnum.PARCELADO);
    }

}

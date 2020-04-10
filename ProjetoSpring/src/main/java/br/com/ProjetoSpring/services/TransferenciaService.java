package br.com.ProjetoSpring.services;

import br.com.ProjetoSpring.dto.DepositarDTO;
import br.com.ProjetoSpring.dto.PagamentoAVistaDTO;
import br.com.ProjetoSpring.dto.PagamentoParceladoDTO;
import br.com.ProjetoSpring.models.TransferenciaVO;

import java.util.List;

public interface TransferenciaService {

    public TransferenciaVO depositar(Long idConta, DepositarDTO depositarDTO);
    public TransferenciaVO pagamentoAVista(Long idConta, PagamentoAVistaDTO pagamentoAVistaDTO);
    public List<TransferenciaVO> pagamentoParcelado(Long idConta, PagamentoParceladoDTO pagamentoParceladoDTO);

}

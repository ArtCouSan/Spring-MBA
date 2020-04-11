package br.com.ProjetoSpring.services;

import br.com.ProjetoSpring.dto.DepositarDTO;
import br.com.ProjetoSpring.dto.PagamentoAVistaDTO;
import br.com.ProjetoSpring.dto.PagamentoParceladoDTO;
import br.com.ProjetoSpring.models.ContaVO;
import br.com.ProjetoSpring.models.TransferenciaVO;
import br.com.ProjetoSpring.models.enums.TipoPagamentoEnum;

import java.util.List;
import java.util.Optional;

public interface TransferenciaService {

    public TransferenciaVO depositar(Long idConta, DepositarDTO depositarDTO);
    public TransferenciaVO pagamentoAVista(Optional<ContaVO> contaOptional, PagamentoAVistaDTO pagamentoAVistaDTO);
    public List<TransferenciaVO> pagamentoParcelado(Optional<ContaVO> contaOptional, PagamentoParceladoDTO pagamentoParceladoDTO);
    public List<TransferenciaVO> pagamento(Long idConta, Object pagamento, TipoPagamentoEnum tipoPagamentoEnum);

}

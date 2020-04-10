package br.com.ProjetoSpring.services.impl;

import br.com.ProjetoSpring.dto.DepositarDTO;
import br.com.ProjetoSpring.dto.PagamentoAVistaDTO;
import br.com.ProjetoSpring.dto.PagamentoParceladoDTO;
import br.com.ProjetoSpring.dto.ParcelaDTO;
import br.com.ProjetoSpring.models.ContaVO;
import br.com.ProjetoSpring.models.TransferenciaVO;
import br.com.ProjetoSpring.models.enums.Tipo_TransferenciaEnum;
import br.com.ProjetoSpring.repository.ContaRepository;
import br.com.ProjetoSpring.repository.TransferenciaRepository;
import br.com.ProjetoSpring.services.TransferenciaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private TransferenciaRepository repositoryTransferencia;
    private ContaRepository repositoryConta;

    public TransferenciaServiceImpl(
            TransferenciaRepository repositoryTransferencia,
            ContaRepository repositoryConta) {
        this.repositoryTransferencia = repositoryTransferencia;
        this.repositoryConta = repositoryConta;
    }

    @Override
    public TransferenciaVO depositar(Long idConta, DepositarDTO depositarDTO) {
        TransferenciaVO transferenciaVO = new TransferenciaVO();
        transferenciaVO.setDataInsercao(LocalDate.now());
        transferenciaVO.setTipo(Tipo_TransferenciaEnum.CREDITO.getTipo());
        transferenciaVO.setResponsavel(depositarDTO.getResponsavel());

        Optional<ContaVO> contaOptional = this.repositoryConta.findById(idConta);
        transferenciaVO.setConta(contaOptional.get());

        // Verifica se existe conta
        if (contaOptional.isPresent()) {
            transferenciaVO = calculoDepositar(transferenciaVO, depositarDTO.getCredito());
            transferenciaVO.setValor(depositarDTO.getCredito());
            transferenciaVO.setMensagem("Creditado com sucesso!");
            return this.repositoryTransferencia.save(transferenciaVO);
        } else {
            transferenciaVO.setValor(BigDecimal.ZERO);
            transferenciaVO.setMensagem("Erro conta não encontrada");
            return this.repositoryTransferencia.save(transferenciaVO);
        }
    }

    @Override
    public TransferenciaVO pagamentoAVista(Long idConta, PagamentoAVistaDTO pagamentoAVistaDTO) {
        TransferenciaVO transferenciaVO = new TransferenciaVO();
        transferenciaVO.setDataInsercao(LocalDate.now());
        transferenciaVO.setTipo(Tipo_TransferenciaEnum.DEBITO.getTipo());
        transferenciaVO.setResponsavel(pagamentoAVistaDTO.getResponsavel());

        Optional<ContaVO> contaOptional = this.repositoryConta.findById(idConta);
        transferenciaVO.setConta(contaOptional.get());

        // Verifica se existe conta
        if (contaOptional.isPresent()) {
            calculoParcelado(transferenciaVO, pagamentoAVistaDTO.getValor());

            if (MonthDay.now().getMonthValue() >= contaOptional.get().getDiaDoMesFatura()) {
                LocalDate dataLimitePagamento = LocalDate.of(Year.now().getValue(), MonthDay.now().getMonthValue(), contaOptional.get().getDiaDoMesFatura()).plusMonths(1);
                transferenciaVO.setDataLimitePagamento(dataLimitePagamento);
            } else {
                LocalDate dataLimitePagamento = LocalDate.of(Year.now().getValue(), MonthDay.now().getMonthValue(), contaOptional.get().getDiaDoMesFatura());
                transferenciaVO.setDataLimitePagamento(dataLimitePagamento);
            }

        } else {

            transferenciaVO.setMensagem("Conta não existe!");

        }

        return this.repositoryTransferencia.save(transferenciaVO);

    }

    @Override
    public List<TransferenciaVO> pagamentoParcelado(Long idConta, PagamentoParceladoDTO pagamentoParceladoDTO) {
        List<TransferenciaVO> listaTransferenciasVO = new ArrayList<TransferenciaVO>();
        Optional<ContaVO> contaOptional = this.repositoryConta.findById(idConta);
        Boolean transacoesRealizadas = true;

        // Verifica se existe conta
        if (contaOptional.isPresent()) {

            Integer numeroParcelas;

            // Verifica em que fatura entrara
            if (MonthDay.now().getMonthValue() >= contaOptional.get().getDiaDoMesFatura()) {
                numeroParcelas = 1;
            } else {
                numeroParcelas = 0;
            }

            for (ParcelaDTO parcela : pagamentoParceladoDTO.getParcelasDTO()) {
                TransferenciaVO transferenciaVO = new TransferenciaVO();
                transferenciaVO.setDataInsercao(LocalDate.now());
                transferenciaVO.setTipo(Tipo_TransferenciaEnum.DEBITO.getTipo());
                transferenciaVO.setResponsavel(pagamentoParceladoDTO.getResponsavel());
                transferenciaVO.setConta(contaOptional.get());

                LocalDate dataUltimaParcela = LocalDate.of(Year.now().getValue(), MonthDay.now().getMonthValue(), contaOptional.get().getDiaDoMesFatura()).plusMonths(numeroParcelas);

                // Debitos para pagamento ate a proxima fatura
                List<TransferenciaVO> debitosHojeAteUltimaParcela = this.repositoryTransferencia.debitosHojeAteUltimaParcela(Date.valueOf(dataUltimaParcela), idConta);

                // Debitos mais valor da parcela solicitado
                BigDecimal totalDebito = debitosHojeAteUltimaParcela.stream().map(TransferenciaVO::getValor).reduce(BigDecimal.ZERO, BigDecimal::add).add(parcela.getValorParcela());

                // Calculo de saldo e debito
                if (!calculoParcelado(transferenciaVO, totalDebito)) {
                    transacoesRealizadas = false;
                }
                listaTransferenciasVO.add(transferenciaVO);
            }
        }

        // Se alguma transacao falhar
        if(!transacoesRealizadas){
            listaTransferenciasVO.stream().forEach(transacoes -> {
                transacoes.setMensagem("Valor de saldo inferior ao solicitado!");
                transacoes.setValor(BigDecimal.ZERO);
            });
        }

        return this.repositoryTransferencia.saveAll(listaTransferenciasVO);
    }


    private Boolean calculoParcelado(TransferenciaVO transferenciaVO, BigDecimal debito) {
        BigDecimal saldo = transferenciaVO.getConta().getSaldo().subtract(debito);
        if (saldo.compareTo(new BigDecimal(0)) < 0) {
            transferenciaVO.setValor(new BigDecimal(0));
            transferenciaVO.setMensagem("Valor de saldo inferior ao solicitado!");
            return false;
        } else {
            transferenciaVO.setValor(debito);
            transferenciaVO.getConta().setSaldo(saldo);
            transferenciaVO.setMensagem("Debitado com sucesso!");
            return true;
        }
    }

    private TransferenciaVO calculoDepositar(TransferenciaVO transferenciaVO, BigDecimal valor) {
        BigDecimal saldoCredito = transferenciaVO.getConta().getSaldo().add(valor);
        transferenciaVO.getConta().setSaldo(saldoCredito);
        return transferenciaVO;
    }

}

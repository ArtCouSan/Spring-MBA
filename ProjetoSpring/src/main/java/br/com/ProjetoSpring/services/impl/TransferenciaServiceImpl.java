package br.com.ProjetoSpring.services.impl;

import br.com.ProjetoSpring.dto.DepositarDTO;
import br.com.ProjetoSpring.dto.PagamentoAVistaDTO;
import br.com.ProjetoSpring.dto.PagamentoParceladoDTO;
import br.com.ProjetoSpring.models.ContaVO;
import br.com.ProjetoSpring.models.HistoricoVO;
import br.com.ProjetoSpring.models.TransferenciaVO;
import br.com.ProjetoSpring.models.enums.TipoPagamentoEnum;
import br.com.ProjetoSpring.models.enums.TipoTransferenciaEnum;
import br.com.ProjetoSpring.repository.ContaRepository;
import br.com.ProjetoSpring.repository.HistoricoRepository;
import br.com.ProjetoSpring.repository.TransferenciaRepository;
import br.com.ProjetoSpring.services.TransferenciaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.time.*;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private TransferenciaRepository repositoryTransferencia;
    private ContaRepository repositoryConta;
    private HistoricoRepository repositoryHistorico;

    public TransferenciaServiceImpl(TransferenciaRepository repositoryTransferencia, ContaRepository repositoryConta, HistoricoRepository repositoryHistorico) {
        this.repositoryTransferencia = repositoryTransferencia;
        this.repositoryConta = repositoryConta;
        this.repositoryHistorico = repositoryHistorico;
    }

    /**
     * Verifica o tipo de pagamento
     * @param idConta
     * @param pagamento
     * @param tipoPagamentoEnum
     * @return transferencias realizadas
     */
    @Override
    public List<TransferenciaVO> pagamento(Long idConta, Object pagamento, TipoPagamentoEnum tipoPagamentoEnum) {

        List<TransferenciaVO> lista = new ArrayList<TransferenciaVO>();
        Optional<ContaVO> contaOptional = this.repositoryConta.findById(idConta);

        HistoricoVO historico = new HistoricoVO();

        // Verifica se existe conta
        if (contaOptional.isPresent()) {

            switch (tipoPagamentoEnum) {
                case VISTA:
                    lista.add(pagamentoAVista(contaOptional, (PagamentoAVistaDTO) pagamento));
                    // Cria historico para extracao
                    historico.setJuros(0.0);
                    historico.setNomeCliente(((PagamentoAVistaDTO) pagamento).getResponsavel());
                    historico.setNumeroParcelas(1);
                    historico.setValorParcela(((PagamentoAVistaDTO) pagamento).getValor());
                    break;
                case PARCELADO:
                    lista = pagamentoParcelado(contaOptional, (PagamentoParceladoDTO) pagamento);
                    // Cria historico para extracao
                    historico.setJuros(((PagamentoParceladoDTO) pagamento).getJuros());
                    historico.setNomeCliente(((PagamentoParceladoDTO) pagamento).getResponsavel());
                    historico.setNumeroParcelas(((PagamentoParceladoDTO) pagamento).getNumeroParcela());
                    historico.setValorParcela(((PagamentoParceladoDTO) pagamento).getValorParcela());
                    break;
            }

            // Verifica todos os debitos do mes vigente de parcela
            Calendar dataInicioPagamento = Calendar.getInstance();
            dataInicioPagamento.set(Year.now().getValue(), LocalDate.now()
                    .getMonthValue() - 1, contaOptional.get().getDiaDoMesFatura(), 0, 0, 0);

            Calendar dataFimPagamento = Calendar.getInstance();
            dataFimPagamento.set(Year.now().getValue(), LocalDate.now()
                    .getMonthValue(), contaOptional.get().getDiaDoMesFatura(), 0, 0, 0);

            BigDecimal totalDebitosAteUltimaFatura =
                    this.repositoryTransferencia.debitosParcela(dataInicioPagamento, dataFimPagamento, idConta)
                            .stream()
                            .map(TransferenciaVO::getValor)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

            historico.setDataTransacao(dataInicioPagamento);
            historico.setConta(contaOptional.get());

            // Realiza atualizacao do saldo da conta
            BigDecimal saldo = contaOptional.get().getLimite().subtract(totalDebitosAteUltimaFatura);
            contaOptional.get().setSaldo(saldo);

            this.repositoryHistorico.save(historico);
            this.repositoryConta.save(contaOptional.get());

        } else {

            TransferenciaVO transferenciaVO = new TransferenciaVO();
            transferenciaVO.setMensagem("Conta não existe!");

        }
        return lista;
    }

    /**
     * Realiza transferencia de pagamento a vista
     * @param contaOptional
     * @param pagamentoAVistaDTO
     * @return
     */
    @Override
    public TransferenciaVO pagamentoAVista(Optional<ContaVO> contaOptional, PagamentoAVistaDTO pagamentoAVistaDTO) {
        TransferenciaVO transferenciaVO = new TransferenciaVO();
        transferenciaVO.setDataInsercao(LocalDate.now());
        transferenciaVO.setTipo(TipoTransferenciaEnum.DEBITO.getTipo());
        transferenciaVO.setResponsavel(pagamentoAVistaDTO.getResponsavel());
        transferenciaVO.setConta(contaOptional.get());

        calculoPagamento(transferenciaVO, pagamentoAVistaDTO.getValor(), pagamentoAVistaDTO.getValor());
        Calendar dataLimitePagamento = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        // Verifica se o mes atual ja passou da data limite da fatura
        if (MonthDay.now().getDayOfMonth() >= contaOptional.get().getDiaDoMesFatura()) {
            dataLimitePagamento.set(Year.now().getValue(), LocalDate.now()
                    .getMonthValue(), contaOptional.get().getDiaDoMesFatura(), 0, 0, 0);
            transferenciaVO.setDataLimitePagamento(dataLimitePagamento);
        } else {
            dataLimitePagamento.set(Year.now().getValue(), LocalDate.now()
                    .getMonthValue() - 1, contaOptional.get().getDiaDoMesFatura(), 0, 0, 0);
            transferenciaVO.setDataLimitePagamento(dataLimitePagamento);
        }
        return this.repositoryTransferencia.save(transferenciaVO);
    }

    /**
     * Realiza transferencia de pagamento parcelado
     * @param contaOptional
     * @param pagamentoParceladoDTO
     * @return
     */
    @Override
    public List<TransferenciaVO> pagamentoParcelado(Optional<ContaVO> contaOptional, PagamentoParceladoDTO pagamentoParceladoDTO) {
        List<TransferenciaVO> listaTransferenciasVO = new ArrayList<TransferenciaVO>();
        Boolean transacoesRealizadas = true;
        Integer numeroParcelas;

        // Verifica em que mes fatura entrara
        if (MonthDay.now().getMonthValue() >= contaOptional.get().getDiaDoMesFatura()) {
            numeroParcelas = 1;
        } else {
            numeroParcelas = 0;
        }

        transacoesRealizadas = criaParcelas(contaOptional, pagamentoParceladoDTO, listaTransferenciasVO, transacoesRealizadas, numeroParcelas);

        // Se alguma transacao falhar, zera as transacoes, e as geram por motivos de historico
        if (!transacoesRealizadas) {
            listaTransferenciasVO.stream().forEach(transacoes -> {
                transacoes.setMensagem("Valor de saldo inferior ao solicitado!");
                transacoes.setValor(BigDecimal.ZERO);
            });
        }

        return this.repositoryTransferencia.saveAll(listaTransferenciasVO);
    }

    /**
     * Cria loop nas parcelas, verificando o valor de cada parcela de acordo com os debitos no seu mes
     * , somando com o juros aplicado
     * @param contaOptional
     * @param pagamentoParceladoDTO
     * @param listaTransferenciasVO
     * @param transacoesRealizadas
     * @param numeroParcelas
     * @return
     */
    private Boolean criaParcelas(Optional<ContaVO> contaOptional, PagamentoParceladoDTO pagamentoParceladoDTO, List<TransferenciaVO> listaTransferenciasVO, Boolean transacoesRealizadas, Integer numeroParcelas) {
        List<BigDecimal> parcelas = new ArrayList<>();

        // Cria array das parcelas
        for (int i = 0; i < pagamentoParceladoDTO.getNumeroParcela(); i++) {
            parcelas.add(pagamentoParceladoDTO.getValorParcela());
        }

        // Cria transacao para cada parcela
        for (BigDecimal valorParcela : parcelas) {
            TransferenciaVO transferenciaVO = new TransferenciaVO();
            transferenciaVO.setDataInsercao(LocalDate.now());
            transferenciaVO.setTipo(TipoTransferenciaEnum.DEBITO.getTipo());
            transferenciaVO.setResponsavel(pagamentoParceladoDTO.getResponsavel());
            transferenciaVO.setConta(contaOptional.get());

            // Verifica debitos no mes da parcela
            Calendar dataInicioParcela = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

            dataInicioParcela.set(Year.now().getValue(), LocalDate.now()
                    .getMonthValue() - 1, contaOptional.get().getDiaDoMesFatura(), 0, 0, 0);
            dataInicioParcela.add(Calendar.MONTH, numeroParcelas);

            Calendar dataFimParcela = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            dataFimParcela.set(Year.now().getValue(), LocalDate.now()
                    .getMonthValue(), contaOptional.get().getDiaDoMesFatura(), 0, 0, 0);
            dataFimParcela.add(Calendar.MONTH, numeroParcelas);
            dataFimParcela.add(Calendar.DAY_OF_MONTH, -1);

            transferenciaVO.setDataLimitePagamento(dataInicioParcela);

            // Debitos para pagamento ate a proxima fatura
            List<TransferenciaVO> debitosMesParcela =
                    this.repositoryTransferencia
                            .debitosParcela(dataInicioParcela, dataFimParcela, contaOptional.get().getIdConta());

            // Juros
            float juros = (float) pagamentoParceladoDTO.getJuros().doubleValue() / 100;

            // Parcela mais juros
            BigDecimal parcelaMaisJuros = valorParcela
                    .add(valorParcela
                            .multiply(new BigDecimal(juros))).setScale(2, RoundingMode.HALF_UP);

            // Debitos mais valor da parcela solicitado
            BigDecimal totalDebito = debitosMesParcela
                    .stream()
                    .map(TransferenciaVO::getValor)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .add(parcelaMaisJuros);

            // Calculo de saldo e debito
            if (!calculoPagamento(transferenciaVO, totalDebito, parcelaMaisJuros)) {
                transacoesRealizadas = false;
            }

            numeroParcelas++;
            listaTransferenciasVO.add(transferenciaVO);
        }
        return transacoesRealizadas;
    }

    /**
     * Verifica se o saldo disponivel eh suficiente
     * @param transferenciaVO
     * @param debito
     * @param parcela
     * @return
     */
    private Boolean calculoPagamento(TransferenciaVO transferenciaVO, BigDecimal debito, BigDecimal parcela) {
        BigDecimal saldo = transferenciaVO.getConta().getSaldo().subtract(debito);
        if (saldo.compareTo(new BigDecimal(0)) < 0) {
            transferenciaVO.setValor(new BigDecimal(0));
            transferenciaVO.setMensagem("Valor de saldo inferior ao solicitado!");
            return false;
        } else {
            transferenciaVO.setValor(parcela);
            transferenciaVO.getConta().setSaldo(saldo);
            transferenciaVO.setMensagem("Debitado com sucesso!");
            return true;
        }
    }

}

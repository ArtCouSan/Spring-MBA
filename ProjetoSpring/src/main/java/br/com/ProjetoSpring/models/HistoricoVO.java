package br.com.ProjetoSpring.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Table(name = "TB_HISTORICO")
public class HistoricoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HISTORICO")
    private Integer idHistorico;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CONTA")
    private ContaVO conta;

    @Column(name = "NOME_CLIENTE")
    private String nomeCliente;

    @Column(name = "DATA_TRANSACAO")
    @Temporal(TemporalType.DATE)
    private Calendar dataTransacao;

    @Column(name = "VALOR_PARCELA")
    private BigDecimal valorParcela;

    @Column(name = "NUMERO_PARCELA")
    private Integer numeroParcelas;

    @Column(name = "JUROS")
    private Double juros;

    public HistoricoVO() {
    }

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Calendar getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Calendar dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public Double getJuros() {
        return juros;
    }

    public void setJuros(Double juros) {
        this.juros = juros;
    }

    public ContaVO getConta() {
        return conta;
    }

    public void setConta(ContaVO conta) {
        this.conta = conta;
    }
}

package br.com.ProjetoSpring.models;

import br.com.ProjetoSpring.http_utils.GenericResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "TB_TRANSFERENCIA")
public class TransferenciaVO extends GenericResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRANSFERENCIA")
    private Integer idTransferencia;

    @Column(name = "DATA_INSERCAO")
    private LocalDate dataInsercao;

    @Column(name = "DATA_LIMITE_PAGAMENTO")
    private LocalDate dataLimitePagamento;

    @Column(name = "TIPO_TRANFERENCIA")
    private String tipo;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "RESPONSAVEL")
    private String responsavel;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CONTA")
    private ContaVO conta;

    public Integer getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Integer idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public LocalDate getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(LocalDate dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ContaVO getConta() {
        return conta;
    }

    public void setConta(ContaVO conta) {
        this.conta = conta;
    }

    public LocalDate getDataLimitePagamento() {
        return dataLimitePagamento;
    }

    public void setDataLimitePagamento(LocalDate dataLimitePagamento) {
        this.dataLimitePagamento = dataLimitePagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}

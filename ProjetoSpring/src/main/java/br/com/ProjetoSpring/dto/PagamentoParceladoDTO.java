package br.com.ProjetoSpring.dto;

import java.math.BigDecimal;

public class PagamentoParceladoDTO {

    public BigDecimal valorParcela;
    public Integer numeroParcela;
    public Double juros;
    public String responsavel;

    public PagamentoParceladoDTO() {
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Double getJuros() {
        return juros;
    }

    public void setJuros(Double juros) {
        this.juros = juros;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}

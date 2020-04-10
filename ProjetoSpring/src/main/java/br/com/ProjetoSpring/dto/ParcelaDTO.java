package br.com.ProjetoSpring.dto;

import java.math.BigDecimal;

public class ParcelaDTO {

    private BigDecimal valorParcela;

    public ParcelaDTO() {
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }
}

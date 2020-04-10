package br.com.ProjetoSpring.dto;

import java.math.BigDecimal;

public class PagamentoAVistaDTO {

    public BigDecimal valor;

    public String responsavel;

    public PagamentoAVistaDTO(BigDecimal valor, String responsavel) {
        this.valor = valor;
        this.responsavel = responsavel;
    }

    public PagamentoAVistaDTO() {
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

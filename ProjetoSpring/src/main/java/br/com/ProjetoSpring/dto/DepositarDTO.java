package br.com.ProjetoSpring.dto;

import java.math.BigDecimal;

public class DepositarDTO {

    public BigDecimal credito;

    public String responsavel;

    public DepositarDTO(BigDecimal credito, String responsavel) {
        this.credito = credito;
        this.responsavel = responsavel;
    }

    public DepositarDTO() {
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}

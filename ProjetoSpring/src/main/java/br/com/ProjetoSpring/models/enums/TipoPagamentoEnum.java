package br.com.ProjetoSpring.models.enums;

public enum TipoPagamentoEnum {

    VISTA,
    PARCELADO;

    private String pagamento;

    TipoPagamentoEnum(String pagamento) {
        this.pagamento = pagamento;
    }

    TipoPagamentoEnum() {

    }

    public String getPagamento() {
        return pagamento;
    }

}

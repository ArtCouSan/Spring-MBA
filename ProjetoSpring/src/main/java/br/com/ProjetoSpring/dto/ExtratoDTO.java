package br.com.ProjetoSpring.dto;

import br.com.ProjetoSpring.models.HistoricoVO;
import br.com.ProjetoSpring.models.UsuarioVO;
import com.opencsv.bean.CsvBindAndJoinByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExtratoDTO {

    @CsvBindByName(column = "Nome")
    private String nome;

    @CsvBindByName(column = "Data de transacao")
    private String data;

    @CsvBindByName(column = "Valor")
    private BigDecimal valor;

    @CsvBindByName(column = "Numero de parcelas")
    private Integer parcelas;

    @CsvBindByName(column = "Juros")
    private Double juros;

    @CsvBindByName(column = "Data limite de pagamento")
    private Double dataLimitePagamento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public Double getJuros() {
        return juros;
    }

    public void setJuros(Double juros) {
        this.juros = juros;
    }

    public Double getDataLimitePagamento() {
        return dataLimitePagamento;
    }

    public void setDataLimitePagamento(Double dataLimitePagamento) {
        this.dataLimitePagamento = dataLimitePagamento;
    }

    public ExtratoDTO() {
    }

    public ExtratoDTO parseHistoricoVO(HistoricoVO historicoVO) {
        ExtratoDTO extrato = new ExtratoDTO();
        extrato.setNome(historicoVO.getNomeCliente());
        extrato.setValor(historicoVO.getValorParcela());
        extrato.setParcelas(historicoVO.getNumeroParcelas());
        extrato.setJuros(historicoVO.getJuros());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String data = format.format(historicoVO.getDataTransacao().getTime());
        extrato.setData(data);
        return extrato;
    }

}

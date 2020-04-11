package br.com.ProjetoSpring.models;

import br.com.ProjetoSpring.http_utils.GenericResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TB_CONTA")
public class ContaVO extends GenericResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTA")
    private Long idConta;

    @JsonManagedReference
    @OneToOne(mappedBy = "conta", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private UsuarioVO usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conta")
    private List<HistoricoVO> historico;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conta")
    private List<TransferenciaVO> tranferencias;

    @Column(name = "SALDO")
    private BigDecimal saldo;

    @Column(name = "LIMITE")
    private BigDecimal limite;

    @Column(name = "CONSUMIDO")
    private BigDecimal consumido;

    @Column(name = "DIA_PAGAMENTO_FATURA")
    private Integer diaDoMesFatura;

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public ContaVO() {
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public UsuarioVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public List<TransferenciaVO> getTranferencias() {
        return tranferencias;
    }

    public void setTranferencias(List<TransferenciaVO> tranferencias) {
        this.tranferencias = tranferencias;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public BigDecimal getConsumido() {
        return consumido;
    }

    public void setConsumido(BigDecimal consumido) {
        this.consumido = consumido;
    }

    public Integer getDiaDoMesFatura() {
        return diaDoMesFatura;
    }

    public void setDiaDoMesFatura(Integer diaDoMesFatura) {
        this.diaDoMesFatura = diaDoMesFatura;
    }

    public List<HistoricoVO> getHistorico() {
        return historico;
    }

    public void setHistorico(List<HistoricoVO> historico) {
        this.historico = historico;
    }
}

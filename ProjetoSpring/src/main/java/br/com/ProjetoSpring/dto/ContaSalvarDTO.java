package br.com.ProjetoSpring.dto;

import br.com.ProjetoSpring.models.ContaVO;
import java.math.BigDecimal;

public class ContaSalvarDTO {

    private BigDecimal limite;
    private Integer diaDoMesFatura;

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public Integer getDiaDoMesFatura() {
        return diaDoMesFatura;
    }

    public void setDiaDoMesFatura(Integer diaDoMesFatura) {
        this.diaDoMesFatura = diaDoMesFatura;
    }

    public ContaSalvarDTO() {
    }

    /**
     * Cria conta com valor default de limite
     * @return
     */
    public ContaVO parseContaVO(){
        ContaVO contaVO = new ContaVO();
        contaVO.setConsumido(BigDecimal.ZERO);

        if(this.diaDoMesFatura != null){
            contaVO.setDiaDoMesFatura(this.diaDoMesFatura);
            contaVO.setLimite(this.limite);
            contaVO.setSaldo(this.limite);
        }else {
            contaVO.setDiaDoMesFatura(1);
            contaVO.setLimite(new BigDecimal(4000));
            contaVO.setSaldo(new BigDecimal(4000));
        }

        return contaVO;
    }

}

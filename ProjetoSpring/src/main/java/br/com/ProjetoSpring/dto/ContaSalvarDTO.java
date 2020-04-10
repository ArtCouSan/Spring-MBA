package br.com.ProjetoSpring.dto;

import br.com.ProjetoSpring.models.ContaVO;
import java.math.BigDecimal;

public class ContaSalvarDTO {

    private BigDecimal saldo;

    /**
     * Cria conta com valor default de limite
     * @return
     */
    public ContaVO parseContaVO(){
        ContaVO contaVO = new ContaVO();
        contaVO.setSaldo(BigDecimal.ZERO);
        contaVO.setDiaDoMesFatura(1);
        return contaVO;
    }

}

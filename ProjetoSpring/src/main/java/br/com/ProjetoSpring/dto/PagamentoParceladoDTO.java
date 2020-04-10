package br.com.ProjetoSpring.dto;

import java.math.BigDecimal;
import java.util.List;

public class PagamentoParceladoDTO {

    public List<ParcelaDTO> parcelasDTO;
    public String responsavel;

    public PagamentoParceladoDTO() {
    }

    public List<ParcelaDTO> getParcelasDTO() {
        return parcelasDTO;
    }

    public void setParcelaDTO(List<ParcelaDTO> parcelasDTO) {
        this.parcelasDTO = parcelasDTO;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}

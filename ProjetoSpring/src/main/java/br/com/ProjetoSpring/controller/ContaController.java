package br.com.ProjetoSpring.controller;

import br.com.ProjetoSpring.dto.ExtratoDTO;
import br.com.ProjetoSpring.models.ContaVO;
import br.com.ProjetoSpring.models.HistoricoVO;
import br.com.ProjetoSpring.services.ContaService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/conta")
public class ContaController {

    private ContaService serviceConta;

    public ContaController(ContaService serviceConta) {
        this.serviceConta = serviceConta;
    }

    /**
     * Lista contas
     *
     * @return contas
     */
    @GetMapping()
    public Stream<ContaVO> listarContas() {
        return this.serviceConta.listarContas();
    }

    /**
     * Lista conta pelo id
     *
     * @return conta
     */
    @GetMapping("/{id}")
    public ContaVO pegarContaPeloId(@PathVariable Long id) {
        return this.serviceConta.pegarContaPeloId(id).get();
    }

    /**
     * Gera extrato para download
     *
     * @param id
     */
    @GetMapping("/{id}/extrato")
    public void pegarExtratoPeloId(@PathVariable Long id, HttpServletResponse response) throws Exception {

        //set file name and content type
        String filename = "Extrato.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<ExtratoDTO> writer = new StatefulBeanToCsvBuilder<ExtratoDTO>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(';')
                .withOrderedResults(false)
                .build();

        List<HistoricoVO> listaHistorico = serviceConta.exportarHistorico(id);
        List<ExtratoDTO> listaExtrato = new ArrayList<>();
        ExtratoDTO extratoDTO = new ExtratoDTO();

        for (HistoricoVO historico : listaHistorico) {
            listaExtrato.add(extratoDTO.parseHistoricoVO(historico));
        }

        //write all users to csv file
        writer.write(listaExtrato);

    }
}

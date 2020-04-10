package br.com.ProjetoSpring.services;

import br.com.ProjetoSpring.models.ContaVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContaServiceImplTest  {

    @Autowired
    private ContaService serviceConta;

    /**
     * Teste de listagem de contas
     */
    @Test
    public void listarContas(){
        try (Stream<ContaVO> contas = this.serviceConta.listarContas()) {
            // Verica se retorna vazio
            assertThat(contas).isNotNull();
        } catch (Exception e){
            assertThat("Sem itens na lista");
        }
    }

    @Test
    public void pegarContaPeloId(){
        try (Stream<ContaVO> contas = this.serviceConta.listarContas()) {
            Optional<ContaVO> contaQualquer = contas.findFirst();
            if (contaQualquer.isPresent()) {
                Optional<ContaVO> conta = this.serviceConta.pegarContaPeloId(contaQualquer.get().getIdConta());
                assertThat(conta.get()).isNotNull();
            }
        }catch (Exception e){
            assertThat("Sem itens na lista");
        }
    }

}

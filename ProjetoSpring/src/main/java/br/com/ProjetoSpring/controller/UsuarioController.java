package br.com.ProjetoSpring.controller;

import br.com.ProjetoSpring.dto.UsuarioAlterarDTO;
import br.com.ProjetoSpring.dto.UsuarioSalvarDTO;
import br.com.ProjetoSpring.dto.UsuarioStatusDTO;
import br.com.ProjetoSpring.models.UsuarioVO;
import br.com.ProjetoSpring.services.UsuarioService;
import br.com.ProjetoSpring.utils.FileUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    private UsuarioService serviceUsuario;

    UsuarioController(UsuarioService serviceUsuario) {
        this.serviceUsuario = serviceUsuario;
    }

    @PostMapping("/cadastrar")
    public UsuarioVO cadastrar(@RequestBody UsuarioSalvarDTO usuarioSalvarDTO) {
        return this.serviceUsuario.salvarUsuario(usuarioSalvarDTO);
    }

    @PutMapping("/atualizar")
    public UsuarioVO atualizar(@RequestBody UsuarioAlterarDTO usuarioAlterarDTO) {
        return this.serviceUsuario.alterarUsuario(usuarioAlterarDTO);
    }

    @GetMapping("/{id}")
    public UsuarioVO pegarUsuarioPeloId(@PathVariable Long id) {
        return this.serviceUsuario.pegarUsuarioPeloId(id).get();
    }

    @PostMapping("/alterar-status")
    public UsuarioVO alterarStatus(@RequestBody UsuarioStatusDTO usuarioStatus) {
        return this.serviceUsuario.alterarStatusUsuario(usuarioStatus).get();
    }

    @PostMapping("/uploadUsers")
    public List<UsuarioVO> uploadUsers(@RequestParam("file") MultipartFile multipart) throws IOException {
        File file = FileUtils.convert(multipart);
        BCryptPasswordEncoder ps = new BCryptPasswordEncoder();
        List<UsuarioVO> usuarios = new ArrayList<>();
        Boolean cadastrar = true;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> columns = Arrays.asList(line.split(" "));
                UsuarioSalvarDTO usuarioSalvarDTO = new UsuarioSalvarDTO();
                usuarioSalvarDTO.setNome("");

                for (String coluna: columns) {
                    if (!(coluna.startsWith("-") || coluna.trim().isEmpty())) {
                        cadastrar = true;
                        if (coluna.matches("^[0-9].*$")) {
                            if (coluna.length() == 7) {
                                usuarioSalvarDTO.setNick(coluna);
                            } else {
                                usuarioSalvarDTO.setPassword(coluna);
                            }
                        } else {
                            usuarioSalvarDTO.setNome(usuarioSalvarDTO.getNome().concat(" ").concat(coluna));
                        }
                    } else {
                        cadastrar = false;
                    }
                }

                // Ser usuario foi criado, salva ele
                if (cadastrar) {
                    usuarios.add(this.serviceUsuario.salvarUsuario(usuarioSalvarDTO));
                }

            }
        }
        return usuarios;
    }

}

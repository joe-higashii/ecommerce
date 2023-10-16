package br.com.serratec.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.com.serratec.ecommerce.model.email.Email;
import br.com.serratec.ecommerce.service.EmailService;
import br.com.serratec.ecommerce.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obterTodos() {
        return ResponseEntity.ok(usuarioService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> adicionar(@RequestBody UsuarioRequestDTO usuario) {

        UsuarioResponseDTO usuarioAdicionado = usuarioService.adicionar(usuario);

        return ResponseEntity
                .status(201)
                .body(usuarioAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuario) {

        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizar(id, usuario);

        return ResponseEntity
                .status(200)
                .body(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }

    @GetMapping("/email")
    public ResponseEntity<?> testeEnvioDeEmail() throws MessagingException {

        List<String> destinatarios = new ArrayList<>();
        destinatarios.add("lexfco@gmail.com");
        destinatarios.add("gabsteixeira.21@gmail.com");
        destinatarios.add("mianaaudi@hotmail.com");

        String mensagem = "<h1 style=\"color:red;\"> Fala aí, coleguinhas! \\\\m/@_@\\\\m/ <h1>";

        Email email = new Email("Teste de email", mensagem, "joe", destinatarios);

        emailService.enviar(email);

        return ResponseEntity.status(200).body("Fala aí, candango! \\m/@_@\\m/");
    }

}

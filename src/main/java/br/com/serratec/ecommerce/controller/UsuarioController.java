package br.com.serratec.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.usuario.UsuarioLoginRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioLoginResponseDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.com.serratec.ecommerce.model.email.Email;
import br.com.serratec.ecommerce.service.EmailService;
import br.com.serratec.ecommerce.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/usuarios")

@CrossOrigin("*")

@Tag(name = "/api/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    //#region Swagger
    @Operation(summary = "método para listar todos os usuários cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Usuários não encontrados"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar os usuários"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })
    //#endregion
    public ResponseEntity<List<UsuarioResponseDTO>> obterTodos() {
        return ResponseEntity.ok(usuarioService.obterTodos());
    }

    @GetMapping("/{id}")
    //#region Swagger
    @Operation(summary = "método para buscar usuário pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar o usuário"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })
    //#endregion
    public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obterPorId(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResponseDTO> obterProEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.obterPorEmail(email));
    }

    @PostMapping
    //#region Swagger
    @Operation(summary = "método para adicionar usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário adicionado com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível adicionar o usuário"),
        @ApiResponse(responseCode = "500", description = "Erro ao adicionar o usuário"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    //#endregion
    public ResponseEntity<UsuarioResponseDTO> adicionar(@RequestBody UsuarioRequestDTO usuario) {

        UsuarioResponseDTO usuarioAdicionado = usuarioService.adicionar(usuario);

        return ResponseEntity
                .status(201)
                .body(usuarioAdicionado);
    }

    @PutMapping("/{id}")
    //#region Swagger
    @Operation(summary = "método para atualizar usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível atualizar o usuário"),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar o usuário"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    //#endregion
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuario) {

        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizar(id, usuario);

        return ResponseEntity
                .status(200)
                .body(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    //#region Swagger
    @Operation(summary = "método para deletar usuário")
    @ApiResponses(value = {
        
        @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível deletar o usuário"),
        @ApiResponse(responseCode = "500", description = "Erro ao deletar o usuário"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    //#endregion
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginResponseDTO> logar(@RequestBody UsuarioLoginRequestDTO usuarioLoginRequest) {
        UsuarioLoginResponseDTO usuarioLogado = usuarioService.logar(usuarioLoginRequest.getEmail(),
                usuarioLoginRequest.getSenha());
        return ResponseEntity
                .status(200)
                .body(usuarioLogado);
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

        return ResponseEntity.status(200).body("Fala aí, galera! \\m/@_@\\m/");
    }
}

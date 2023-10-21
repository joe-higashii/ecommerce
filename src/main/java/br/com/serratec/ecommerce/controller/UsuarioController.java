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
        destinatarios.add("eduardopachecogt@hotmail.com");
        destinatarios.add("nathanzero14@gmail.com");

        String mensagem = "<!DOCTYPE html>\r\n" + //
                "<html lang=\"pt-br\">\r\n" + //
                "<head>\r\n" + //
                "    <meta charset=\"UTF-8\">\r\n" + //
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                "    <title>G5 ecommerce</title>\r\n" + //
                "</head>\r\n" + //
                "<body style=\"width: 100%; height: 100%; font-family: Verdana,sans-serif;\">\r\n" + //
                "    <div id=\"emailBody\" style=\"background: #f2f2f2; color: #2f2f2f; width: 80%; max-width: 700px; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); border-radius: 20px; box-shadow: 5px 5px 10px #444;\">\r\n" + //
                "        <h1 style=\"text-align: center;\">Obrigado.</h1>\r\n" + //
                "        <div id=\"container\" style=\"background-color: white; text-align: center; padding: 10px; margin: 5%; border-radius: 10px;\">\r\n" + //
                "            <h2 style=\"text-align: center;\">Olá (nome cliente)</h2>\r\n" + //
                "            <h3>Pedido Efetuado!</h3>\r\n" + //
                "            <p>\r\n" + //
                "                Obrigado por comprar de (nome da loja).\r\n" + //
                "            </p>\r\n" + //
                "            <div box-shadow: 5px 5px 10px #444;>\r\n" + //
                "                <h3 style=\"color: #444; padding-bottom: 10px; border-bottom: 1px solid #2f2f2f; margin: 10px 2%; text-align: left;\">\r\n" + //
                "                    Informações do seu pedido: \r\n" + //
                "                </h3>\r\n" + //
                "                <p>\r\n" + //
                "                    <div style=\"text-align: left; margin: 20px; \">\r\n" + //
                "\r\n" + //
                "                        <div style=\"font-weight: bold; background-color: #444; color: white; padding: 10px;\">\r\n" + //
                "                            ID do pedido: \r\n" + //
                "                            <span style=\"color: white; font-weight: normal;\">(id do pedido)</span>\r\n" + //
                "                        </div>\r\n" + //
                "\r\n" + //
                "                        <div style=\"font-weight: bold; background-color: white; color: #444; padding: 10px;\">\r\n" + //
                "                            Enviar cobrança para: \r\n" + //
                "                            <span style=\"color: #2f2f2f; font-weight: normal;\">(email do usuario)</span>\r\n" + //
                "                        </div>\r\n" + //
                "                        <div style=\"font-weight: bold; background-color: #444; color: white; padding: 10px;\">\r\n" + //
                "                            Data do pedido: \r\n" + //
                "                            <span style=\"color: white; font-weight: normal;\">(data do pedido)</span>\r\n" + //
                "                        </div>\r\n" + //
                "                    </div>\r\n" + //
                "                    \r\n" + //
                "                    <h3 style=\"color: #444; padding-bottom: 10px; border-bottom: 1px solid #2f2f2f; margin: 10px 2%; text-align: left;\">\r\n" + //
                "                        Aqui está o seu pedido: </h3>\r\n" + //
                "                    <div style=\"width: 100%; display: flex; flex-direction: column; align-items: center;\">\r\n" + //
                "                        <table style=\"width: 95%; padding: 10px; border-collapse: collapse;\">\r\n" + //
                "                            <thead style=\"background-color: #444; color: white; border: 1px solid #f2f2f2; width: 100%; height: 100%;\">\r\n" + //
                "                                <tr >\r\n" + //
                "                                    <th>Descrição</th>\r\n" + //
                "                                    <th>Preço</th>\r\n" + //
                "                                </tr>\r\n" + //
                "                            </thead>\r\n" + //
                "                            <tbody>\r\n" + //
                "                                <tr style=\"border: 1px solid #f2f2f2;\">\r\n" + //
                "                                    <td >Produto 1</td>\r\n" + //
                "                                    <td>R$ 19.99</td>\r\n" + //
                "                                </tr>\r\n" + //
                "                                <tr style=\"border: 1px solid #f2f2f2;\">\r\n" + //
                "                                    <td>Produto 2</td>\r\n" + //
                "                                    <td>R$ 29.99</td>\r\n" + //
                "                                </tr>\r\n" + //
                "                                <tr style=\"border: 1px solid #f2f2f2;\">\r\n" + //
                "                                    <td>Produto 3</td>\r\n" + //
                "                                    <td>R$ 14.99</td>\r\n" + //
                "                                </tr>\r\n" + //
                "                            </tbody>\r\n" + //
                "                        </table>\r\n" + //
                "                        <h4 style=\"border-top: 1px solid #2f2f2f; border-bottom: 1px solid #2f2f2f; padding: 20px; text-align: right;\">\r\n" + //
                "                            Total: R$(valor total + (acrescimo - desconto)) BRL</h4>\r\n" + //
                "                    </div>\r\n" + //
                "                </p>\r\n" + //
                "            </div>\r\n" + //
                "        </div>\r\n" + //
                "    </div>\r\n" + //
                "</body>\r\n" + //
                "</html>";

        Email email = new Email("Teste de email", mensagem, "joe", destinatarios);

        emailService.enviar(email);

        return ResponseEntity.status(200).body("Fala aí, galera! \\m/@_@\\m/");
    }
}

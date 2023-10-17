package br.com.serratec.ecommerce.controller;

import java.util.List;

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

import br.com.serratec.ecommerce.model.TipoUsuario;
import br.com.serratec.ecommerce.service.TipoUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tipos-usuarios")
@Tag(name = "/api/tipos-usuarios")


public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping
    @Operation(summary = "método para listar todos os tipos de usuários")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Usuários não encontrados"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar os usuários"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })

    public ResponseEntity<List<TipoUsuario>> obterTodos() {
        
        return ResponseEntity.ok(tipoUsuarioService.obterTodos());
    }

    @GetMapping("/{id}")
    
    public ResponseEntity<TipoUsuario> obterPorId(@PathVariable Long id) {

        return ResponseEntity.ok(tipoUsuarioService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> adicionar(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario titularAdicionado = tipoUsuarioService.adicionar(tipoUsuario);

        return ResponseEntity
                .status(201)
                .body(titularAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> atualizar(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario titularAtualizado = tipoUsuarioService.atualizar(id, tipoUsuario);

        return ResponseEntity
                .status(200)
                .body(titularAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        tipoUsuarioService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }
}

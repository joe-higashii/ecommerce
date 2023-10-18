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

import br.com.serratec.ecommerce.model.TipoLog;
import br.com.serratec.ecommerce.service.TipoLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tipos-logs")
@Tag(name = "/api/tipos-logs")

public class TipoLogController {

    @Autowired
    private TipoLogService tipoLogService;

    @GetMapping
    @Operation(summary = "método para listar todos os tipos de logs cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipos de logs encontrados com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Tipos de logs não encontrados"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar tipos de logs"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })
    public ResponseEntity<List<TipoLog>> obterTodos() {
        return ResponseEntity.ok(tipoLogService.obterTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "método para buscar tipo de log pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Log encontrado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Tipo de log não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar o tipo de log"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })

    public ResponseEntity<TipoLog> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoLogService.obterPorId(id));
    }

    @PostMapping
    @Operation(summary = "método para adicionar tipo de log")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de log adicionado com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível adicionar o tipo de log"),
        @ApiResponse(responseCode = "500", description = "Erro ao adicionar o tipo de log"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<TipoLog> adicionar(@RequestBody TipoLog tipoLog) {
        TipoLog titularAdicionado = tipoLogService.adicionar(tipoLog);

        return ResponseEntity
                .status(201)
                .body(titularAdicionado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "método para atualizar tipo de log")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de log atualizado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível atualizar o tipo de log"),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar o tipo de log"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<TipoLog> atualizar(@PathVariable Long id, @RequestBody TipoLog tipoLog) {
        TipoLog titularAtualizado = tipoLogService.atualizar(id, tipoLog);

        return ResponseEntity
                .status(200)
                .body(titularAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "método para deletar tipo de log")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de log deletado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "Tipo de log não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível deletar o tipo de log"),
        @ApiResponse(responseCode = "500", description = "Erro ao deletar o tipo de log"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        
        tipoLogService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }

}

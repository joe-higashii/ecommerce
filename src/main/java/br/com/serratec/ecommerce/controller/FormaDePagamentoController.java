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

import br.com.serratec.ecommerce.dto.formaDePagamento.FormaDePagamentoRequestDTO;
import br.com.serratec.ecommerce.dto.formaDePagamento.FormaDePagamentoResponseDTO;
import br.com.serratec.ecommerce.service.FormaDePagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/formas-pagamento")
@Tag(name = "/api/formas-pagamento")
public class FormaDePagamentoController {

    @Autowired
    private FormaDePagamentoService formaDePagamentoService;

    @GetMapping
     @Operation(summary = "método para listar todas as formas de pagamento cadastradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Formas de pagamento encontradas com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Formas de pagamento não encontrados"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar as formas de pagamento"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })
    public ResponseEntity<List<FormaDePagamentoResponseDTO>> obterTodos() {
        return ResponseEntity.ok(formaDePagamentoService.obterTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "método para buscar forma de pagamento pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Forma de pagamento encontrado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "forma de pagamento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar a forma de pagamento"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })
    public ResponseEntity<FormaDePagamentoResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(formaDePagamentoService.obterPorId(id));
    }

    @PostMapping
    @Operation(summary = "método para adicionar forma de pagamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Forma de pagamento adicionada com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível adicionar a forma de pagamento"),
        @ApiResponse(responseCode = "500", description = "Erro ao adicionar a forma de pagamento"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<FormaDePagamentoResponseDTO> adicionar(
            @RequestBody FormaDePagamentoRequestDTO formaDePagamento) {

        FormaDePagamentoResponseDTO formaDePagamentoAdicionada = formaDePagamentoService.adicionar(formaDePagamento);

        return ResponseEntity
                .status(201)
                .body(formaDePagamentoAdicionada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "método para atualizar forma de pagamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível atualizar a forma de pagamento"),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar a forma de pagamento"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<FormaDePagamentoResponseDTO> atualizar(@PathVariable Long id,
            @RequestBody FormaDePagamentoRequestDTO formaDePagamento) {

        FormaDePagamentoResponseDTO formaDePagamentoAtualizada = formaDePagamentoService.atualizar(id,
                formaDePagamento);

        return ResponseEntity
                .status(200)
                .body(formaDePagamentoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "método para deletar forma de pagamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Forma de pagamento deletado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível deletar a forma de pagamento"),
        @ApiResponse(responseCode = "500", description = "Erro ao deletar a forma de pagamento"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        formaDePagamentoService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }

}

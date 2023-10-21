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

import br.com.serratec.ecommerce.dto.categoria.CategoriaRequestDTO;
import br.com.serratec.ecommerce.dto.categoria.CategoriaResponseDTO;
import br.com.serratec.ecommerce.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "método para listar todas as categorias cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categorias encontradas com sucesso!"), 
        @ApiResponse(responseCode = "401", description = "É necessário fazer o login"),
        //@ApiResponse(responseCode = "403", description = "Forbidden"), 
        @ApiResponse(responseCode = "404", description = "Categorias não encontradas"),
        //@ApiResponse(responseCode = "500", description = "Erro ao listar as categorias"),
        //@ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })
    public ResponseEntity<List<CategoriaResponseDTO>> obterTodos() {
        
        return ResponseEntity.ok(categoriaService.obterTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "método para buscar categoria pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos"),
        @ApiResponse(responseCode = "403", description = "Forbidden"), 
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar a categoria"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })
    public ResponseEntity<CategoriaResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obterPorId(id));
    }

    @PostMapping
    @Operation(summary = "método para adicionar categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria adicionada com sucesso!"), 
        @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos"),
        @ApiResponse(responseCode = "403", description = "Forbidden"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível adicionar a categoria"),
        @ApiResponse(responseCode = "500", description = "Erro ao adicionar a categoria"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<CategoriaResponseDTO> adicionar(@RequestBody CategoriaRequestDTO categoria) {

        CategoriaResponseDTO categoriaAdicionada = categoriaService.adicionar(categoria);

        return ResponseEntity
                .status(201)
                .body(categoriaAdicionada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "método para atualizar categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos"),
        @ApiResponse(responseCode = "403", description = "Forbidden"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível atualizar a categoria"),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar a categoria"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id,
            @RequestBody CategoriaRequestDTO categoria) {

        CategoriaResponseDTO categoriaAtualizada = categoriaService.atualizar(id, categoria);

        return ResponseEntity
                .status(200)
                .body(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "método para deletar categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria deletada com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"),
        @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),  
        @ApiResponse(responseCode = "404", description = "Não foi possível deletar a categoria"),
        @ApiResponse(responseCode = "500", description = "Erro ao deletar a categoria"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        categoriaService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }
}

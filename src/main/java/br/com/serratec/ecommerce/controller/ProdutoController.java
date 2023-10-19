package br.com.serratec.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.produto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "/api/produtos")
public class ProdutoController {

        @Autowired
        private ProdutoService produtoService;

        @GetMapping
        @Operation(summary = "método para listar todos os produtos cadastrados")
        @ApiResponses(value = {

                @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso!"),
                @ApiResponse(responseCode = "404", description = "Produtos não encontrados"),
                @ApiResponse(responseCode = "500", description = "Erro ao listar os produtos"),
                @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

        })
        public ResponseEntity<List<ProdutoResponseDTO>> obterTodos() {
                return ResponseEntity.ok(produtoService.obterTodos());
        }

        @GetMapping("/{id}")
        @Operation(summary = "método para buscar produto pelo ID")
        @ApiResponses(value = {

                @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso!"),
                @ApiResponse(responseCode = "400", description = "ID não encontrado"),
                @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
                @ApiResponse(responseCode = "500", description = "Erro ao listar o produto"),
                @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

        })
        public ResponseEntity<ProdutoResponseDTO> obterPorId(@PathVariable Long id) {
                return ResponseEntity.ok(produtoService.obterPorId(id));
        }

        @PostMapping
        @PreAuthorize("hasAuthority('admin')")
        @Operation(summary = "método para adicionar produto")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Produto adicionado com sucesso!"),
                        @ApiResponse(responseCode = "404", description = "Não foi possível adicionar o produto"),
                        @ApiResponse(responseCode = "500", description = "Erro ao adicionar o produto"),
                        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

        })
        public ResponseEntity<ProdutoResponseDTO> adicionar(@RequestBody ProdutoRequestDTO produto) {
                ProdutoResponseDTO produtoAdicionado = produtoService.adicionar(produto);

                return ResponseEntity
                                .status(201)
                                .body(produtoAdicionado);
        }

        @PutMapping("/{id}")
        @Operation(summary = "método para atualizar produto")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso!"),
                        @ApiResponse(responseCode = "400", description = "ID não encontrado"),
                        @ApiResponse(responseCode = "404", description = "Não foi possível atualizar o produto"),
                        @ApiResponse(responseCode = "500", description = "Erro ao atualizar o produto"),
                        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

        })
        public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id,
                        @RequestBody ProdutoRequestDTO produto) {

                ProdutoResponseDTO produtoAtualizado = produtoService.atualizar(id, produto);

                return ResponseEntity
                                .status(200)
                                .body(produtoAtualizado);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "método para deletar produto")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso!"),
                        @ApiResponse(responseCode = "400", description = "ID não encontrado"),
                        @ApiResponse(responseCode = "404", description = "Não foi possível deletar o produto"),
                        @ApiResponse(responseCode = "500", description = "Erro ao deletar o produto"),
                        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

        })
        public ResponseEntity<?> deletar(@PathVariable Long id) {
                produtoService.deletar(id);

                return ResponseEntity
                                .status(204)
                                .build();
        }
}

package br.com.serratec.ecommerce.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.pedido.PedidoResponseDTO;
import br.com.serratec.ecommerce.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    //#region swagger
    @Operation(summary = "método para listar todos os pedidos cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedidos encontrados com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Pedidos não encontrados"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar os pedidos"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })
    //#endregion
    public ResponseEntity<List<PedidoResponseDTO>> obterTodos() {

        return ResponseEntity.ok(pedidoService.obterTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    //#region swagger
    @Operation(summary = "método para buscar pedido pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar o pedido"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })
    //#endregion
    public ResponseEntity<PedidoResponseDTO> obterPorId(@PathVariable Long id) {
        
        return ResponseEntity.ok(pedidoService.obterPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    //#region swagger
    @Operation(summary = "método para adicionar pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido adicionado com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível adicionar o pedido"),
        @ApiResponse(responseCode = "500", description = "Erro ao adicionar o pedido"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    //#endregion
    public ResponseEntity<PedidoResponseDTO> adicionar(@RequestBody PedidoRequestDTO pedido) throws MessagingException {

        PedidoResponseDTO titularAdicionado = pedidoService.adicionar(pedido);

        return ResponseEntity
                .status(201)
                .body(titularAdicionado);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    //#region swagger
    @Operation(summary = "método para atualizar pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível atualizar o pedido"),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar o pedido"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    //#endregion
    public ResponseEntity<PedidoResponseDTO> atualizar(@PathVariable Long id, @RequestBody PedidoRequestDTO pedido) {
        
        PedidoResponseDTO titularAtualizado = pedidoService.atualizar(id, pedido);

        return ResponseEntity
                .status(200)
                .body(titularAtualizado);
    }

    @PutMapping("/cancelar/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public ResponseEntity<?> cancelarPedido(@PathVariable Long id) {
        
        pedidoService.cancelarPedido(id);

        return ResponseEntity
                .status(200)
                .build();
    }
}

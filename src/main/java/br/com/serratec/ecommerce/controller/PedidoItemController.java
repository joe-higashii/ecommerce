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
import br.com.serratec.ecommerce.dto.pedidoItem.PedidoItemRequestDTO;
import br.com.serratec.ecommerce.dto.pedidoItem.PedidoItemResponseDTO;
import br.com.serratec.ecommerce.model.PedidoItem;
import br.com.serratec.ecommerce.service.PedidoItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/pedido-itens")
@Tag(name = "/api/pedido-itens")

public class PedidoItemController {

    @Autowired
    private PedidoItemService pedidoItemService;

    @GetMapping
    @Operation(summary = "método para listar todos os Itens do Pedido cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Itens do pedido encontrados com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Itens do pedido não encontrados"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar os itens do pedido"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })
    public ResponseEntity<List<PedidoItem>> obterTodos() {

        return ResponseEntity.ok(pedidoItemService.obterTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "método para buscar item no pedido pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item encontrado com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível encontrar o item no pedido"),
        @ApiResponse(responseCode = "500", description = "Erro ao encontrar o item no pedido"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<PedidoItem> obterPorId(@PathVariable Long id) {

        return ResponseEntity.ok(pedidoItemService.obterPorId(id));
    }

    @PostMapping
    @Operation(summary = "método para adicionar item do pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item adicionado ao pedido com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível adicionar o item no pedido"),
        @ApiResponse(responseCode = "500", description = "Erro ao adicionar o item no pedido"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<PedidoItemResponseDTO> adicionar1(@RequestBody PedidoItemRequestDTO pedidoItem) {
        
        PedidoItemResponseDTO pedidoItemAdicionado = pedidoItemService.adicionar(pedidoItem);

        return ResponseEntity
                .status(201)
                .body(pedidoItemAdicionado);
    }

    // @PostMapping
    // public ResponseEntity<PedidoItemResponseDTO> adicionar(@RequestBody PedidoItemRequestDTO pedidoItem, @RequestParam Long pedidoId, @RequestParam int quantidade) {
        
    //     Pedido pedido = new Pedido();

    //     pedido.setPedidoId(pedidoId);
        
    //     PedidoItemResponseDTO pedidoItemAdicionado = pedidoItemService.adicionar(pedidoItem, pedido, quantidade);

    //     return ResponseEntity
    //             .status(201)
    //             .body(pedidoItemAdicionado);
    // }

    @PutMapping("/{id}")
    @Operation(summary = "método para atualizar titular")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Titular atualizado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível atualizar o titular"),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar o titular"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<PedidoItem> atualizar(@PathVariable Long id, @RequestBody PedidoItem pedidoItem) {
        PedidoItem titularAtualizado = pedidoItemService.atualizar(id, pedidoItem);

        return ResponseEntity
                .status(200)
                .body(titularAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "método para deletar item do pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item deletado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível deletar o item no pedido"),
        @ApiResponse(responseCode = "500", description = "Erro ao deletar o item no pedido"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        pedidoItemService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }
}

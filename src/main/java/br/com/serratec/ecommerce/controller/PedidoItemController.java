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

@RestController
@RequestMapping("/api/pedido-itens")
public class PedidoItemController {

    @Autowired
    private PedidoItemService pedidoItemService;

    @GetMapping
    public ResponseEntity<List<PedidoItem>> obterTodos() {

        return ResponseEntity.ok(pedidoItemService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoItem> obterPorId(@PathVariable Long id) {

        return ResponseEntity.ok(pedidoItemService.obterPorId(id));
    }

    @PostMapping
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
    public ResponseEntity<PedidoItem> atualizar(@PathVariable Long id, @RequestBody PedidoItem pedidoItem) {
        PedidoItem titularAtualizado = pedidoItemService.atualizar(id, pedidoItem);

        return ResponseEntity
                .status(200)
                .body(titularAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        pedidoItemService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }
}

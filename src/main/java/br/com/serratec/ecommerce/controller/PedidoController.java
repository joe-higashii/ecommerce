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

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.pedido.PedidoResponseDTO;
import br.com.serratec.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> obterTodos() {

        return ResponseEntity.ok(pedidoService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> obterPorId(@PathVariable Long id) {
        
        return ResponseEntity.ok(pedidoService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> adicionar(@RequestBody PedidoRequestDTO pedido) {

        PedidoResponseDTO titularAdicionado = pedidoService.adicionar(pedido);

        return ResponseEntity
                .status(201)
                .body(titularAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> atualizar(@PathVariable Long id, @RequestBody PedidoRequestDTO pedido) {
        
        PedidoResponseDTO titularAtualizado = pedidoService.atualizar(id, pedido);

        return ResponseEntity
                .status(200)
                .body(titularAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        
        pedidoService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }
}

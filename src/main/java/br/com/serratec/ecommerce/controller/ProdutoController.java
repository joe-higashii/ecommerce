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

import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

     @GetMapping
    public ResponseEntity<List<Produto>> obterTodos(){
        return ResponseEntity.ok(produtoService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<Produto> adicionar(@RequestBody Produto produto){
        Produto titularAdicionado = produtoService.adicionar(produto);

        return ResponseEntity
            .status(201)
            .body(titularAdicionado);
    }

     @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto){
        Produto titularAtualizado = produtoService.atualizar(id, produto);

        return ResponseEntity
            .status(200)
            .body(titularAtualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        produtoService.deletar(id);
        
        return ResponseEntity
            .status(204)
            .build();
    }
}

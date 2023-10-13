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

import br.com.serratec.ecommerce.model.Categoria;
import br.com.serratec.ecommerce.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> obterTodos(){
        return ResponseEntity.ok(categoriaService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.obterPorId(id));
    }

     @PostMapping
    public ResponseEntity<Categoria> adicionar(@RequestBody Categoria categoria){
        Categoria titularAdicionado = categoriaService.adicionar(categoria);

        return ResponseEntity
            .status(201)
            .body(titularAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria){
        Categoria titularAtualizado = categoriaService.atualizar(id, categoria);

        return ResponseEntity
            .status(200)
            .body(titularAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        categoriaService.deletar(id);
        
        return ResponseEntity
            .status(204)
            .build();
    }

}

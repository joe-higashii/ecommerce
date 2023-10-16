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

@RestController
@RequestMapping("/api/tipos-logs")
public class TipoLogController {

    @Autowired
    private TipoLogService tipoLogService;

    @GetMapping
    public ResponseEntity<List<TipoLog>> obterTodos() {
        return ResponseEntity.ok(tipoLogService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoLog> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoLogService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoLog> adicionar(@RequestBody TipoLog tipoLog) {
        TipoLog titularAdicionado = tipoLogService.adicionar(tipoLog);

        return ResponseEntity
                .status(201)
                .body(titularAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoLog> atualizar(@PathVariable Long id, @RequestBody TipoLog tipoLog) {
        TipoLog titularAtualizado = tipoLogService.atualizar(id, tipoLog);

        return ResponseEntity
                .status(200)
                .body(titularAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        
        tipoLogService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }

}

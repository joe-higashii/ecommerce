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

import br.com.serratec.ecommerce.model.Log;
import br.com.serratec.ecommerce.service.LogService;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping
    public ResponseEntity<List<Log>> obterTodos() {
        return ResponseEntity.ok(logService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Log> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(logService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<Log> adicionar(@RequestBody Log log) {
        Log titularAdicionado = logService.adicionar(log);

        return ResponseEntity
                .status(201)
                .body(titularAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Log> atualizar(@PathVariable Long id, @RequestBody Log log) {
        Log titularAtualizado = logService.atualizar(id, log);

        return ResponseEntity
                .status(200)
                .body(titularAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        logService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }

}

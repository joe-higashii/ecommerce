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

import br.com.serratec.ecommerce.dto.formaDePagamento.FormaDePagamentoRequestDTO;
import br.com.serratec.ecommerce.dto.formaDePagamento.FormaDePagamentoResponseDTO;
import br.com.serratec.ecommerce.service.FormaDePagamentoService;

@RestController
@RequestMapping("/api/formas-pagamento")
public class FormaDePagamentoController {
    
    @Autowired
    private FormaDePagamentoService formaDePagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaDePagamentoResponseDTO>> obterTodos(){
        return ResponseEntity.ok(formaDePagamentoService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaDePagamentoResponseDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(formaDePagamentoService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<FormaDePagamentoResponseDTO> adicionar(@RequestBody FormaDePagamentoRequestDTO  formaDePagamento){

        FormaDePagamentoResponseDTO titularAdicionado = formaDePagamentoService.adicionar(formaDePagamento);

        return ResponseEntity
            .status(201)
            .body(titularAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaDePagamentoResponseDTO> atualizar(@PathVariable Long id, @RequestBody FormaDePagamentoRequestDTO formaDePagamento){

        FormaDePagamentoResponseDTO titularAtualizado = formaDePagamentoService.atualizar(id, formaDePagamento);

        return ResponseEntity
            .status(200)
            .body(titularAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        formaDePagamentoService.deletar(id);
        
        return ResponseEntity
            .status(204)
            .build();
    }

}

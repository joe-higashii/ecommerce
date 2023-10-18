// package br.com.serratec.ecommerce.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// // import org.springframework.web.bind.annotation.PostMapping;
// // import org.springframework.web.bind.annotation.PutMapping;
// // import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import br.com.serratec.ecommerce.model.TipoUsuario;
// import br.com.serratec.ecommerce.service.TipoUsuarioService;

// @RestController
// @RequestMapping("/api/tipos-usuarios")
// public class TipoUsuarioController {

import br.com.serratec.ecommerce.model.TipoUsuario;
import br.com.serratec.ecommerce.service.TipoUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tipos-usuarios")
@Tag(name = "/api/tipos-usuarios")


public class TipoUsuarioController {

// @Autowired
// private TipoUsuarioService tipoUsuarioService;

// @GetMapping
// public ResponseEntity<List<TipoUsuario>> obterTodos() {

// return ResponseEntity.ok(tipoUsuarioService.obterTodos());
// }
  
  @GetMapping
   @Operation(summary = "método para listar tipos de usuários cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipos de usuários encontrados com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Tipos de usuários não encontrados"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar tipos de usuários"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })

    public ResponseEntity<List<TipoUsuario>> obterTodos() {
        
        return ResponseEntity.ok(tipoUsuarioService.obterTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "método para buscar tipo de usuário pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de usuário encontrado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao listar tipo de usuário"),
        @ApiResponse(responseCode = "504", description = "Tempo da consulta esgotado"),

    })
    
    public ResponseEntity<TipoUsuario> obterPorId(@PathVariable Long id) {

// @GetMapping("/{id}")
// public ResponseEntity<TipoUsuario> obterPorId(@PathVariable Long id) {

// return ResponseEntity.ok(tipoUsuarioService.obterPorId(id));
// }

    @PostMapping
    @Operation(summary = "método para adicionar tipo de usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de usuário adicionado com sucesso!"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível adicionar o tipo de usuário"),
        @ApiResponse(responseCode = "500", description = "Erro ao adicionar o tipo de usuário"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    
    public ResponseEntity<TipoUsuario> adicionar(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario titularAdicionado = tipoUsuarioService.adicionar(tipoUsuario);

// // @PostMapping
// // public ResponseEntity<TipoUsuario> adicionar(@RequestBody TipoUsuario
// // tipoUsuario) {
// // TipoUsuario titularAdicionado = tipoUsuarioService.adicionar(tipoUsuario);

// // return ResponseEntity
// // .status(201)
// // .body(titularAdicionado);
// // }

    @PutMapping("/{id}")
    @Operation(summary = "método para atualizar o tipo de usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de usuário atualizado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível atualizar o tipo de usuário"),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar o tipo de usuário"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })
    public ResponseEntity<TipoUsuario> atualizar(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario titularAtualizado = tipoUsuarioService.atualizar(id, tipoUsuario);

// // @PutMapping("/{id}")
// // public ResponseEntity<TipoUsuario> atualizar(@PathVariable Long id,
// // @RequestBody TipoUsuario tipoUsuario) {
// // TipoUsuario titularAtualizado = tipoUsuarioService.atualizar(id,
// // tipoUsuario);

// // return ResponseEntity
// // .status(200)
// // .body(titularAtualizado);
// // }

    @DeleteMapping("/{id}")
    @Operation(summary = "método para deletar o tipo de usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de usuário deletado com sucesso!"), 
        @ApiResponse(responseCode = "400", description = "ID não encontrado"), 
        @ApiResponse(responseCode = "404", description = "Não foi possível deletar o tipo de usuário"),
        @ApiResponse(responseCode = "500", description = "Erro ao deletar o tipo de usuário"),
        @ApiResponse(responseCode = "504", description = "Tempo da operação esgotado"),

    })

    public ResponseEntity<?> deletar(@PathVariable Long id) {
        tipoUsuarioService.deletar(id);

// @DeleteMapping("/{id}")
// public ResponseEntity<?> deletar(@PathVariable Long id) {
// tipoUsuarioService.deletar(id);

// return ResponseEntity
// .status(204)
// .build();
// }
// }

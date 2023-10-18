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

// @Autowired
// private TipoUsuarioService tipoUsuarioService;

// @GetMapping
// public ResponseEntity<List<TipoUsuario>> obterTodos() {

// return ResponseEntity.ok(tipoUsuarioService.obterTodos());
// }

// @GetMapping("/{id}")
// public ResponseEntity<TipoUsuario> obterPorId(@PathVariable Long id) {

// return ResponseEntity.ok(tipoUsuarioService.obterPorId(id));
// }

// // @PostMapping
// // public ResponseEntity<TipoUsuario> adicionar(@RequestBody TipoUsuario
// // tipoUsuario) {
// // TipoUsuario titularAdicionado = tipoUsuarioService.adicionar(tipoUsuario);

// // return ResponseEntity
// // .status(201)
// // .body(titularAdicionado);
// // }

// // @PutMapping("/{id}")
// // public ResponseEntity<TipoUsuario> atualizar(@PathVariable Long id,
// // @RequestBody TipoUsuario tipoUsuario) {
// // TipoUsuario titularAtualizado = tipoUsuarioService.atualizar(id,
// // tipoUsuario);

// // return ResponseEntity
// // .status(200)
// // .body(titularAtualizado);
// // }

// @DeleteMapping("/{id}")
// public ResponseEntity<?> deletar(@PathVariable Long id) {
// tipoUsuarioService.deletar(id);

// return ResponseEntity
// .status(204)
// .build();
// }
// }

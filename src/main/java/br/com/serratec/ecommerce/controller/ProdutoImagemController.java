package br.com.serratec.ecommerce.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.ecommerce.service.ProdutoImagemService;

@RestController
@RequestMapping("/api/imagem")
public class ProdutoImagemController {

	@Autowired
	private ProdutoImagemService produtoService;

	@PostMapping("/{produtoId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> uploadImage(@PathVariable Long produtoId, @RequestParam("imagem") MultipartFile file)
			throws IOException {
		String uploadImage = produtoService.uploadImagem(produtoId, file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/{produtoImagemId}")
	public ResponseEntity<?> downloadImage(@PathVariable Long produtoImagemId) {
		String imageData = produtoService.downloadImagem(produtoImagemId);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}
}

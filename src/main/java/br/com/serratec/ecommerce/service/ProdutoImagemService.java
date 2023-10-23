package br.com.serratec.ecommerce.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.model.ProdutoImagem;
import br.com.serratec.ecommerce.repository.ProdutoImagemRepository;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoImagemService {

    @Autowired
    private ProdutoImagemRepository produtoImageRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // public String uploadImagem(Long produtoId, MultipartFile file) throws IOException {

    //     Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
    //     Produto produto = optionalProduto.get();
    //     ProdutoImagem produtoImagem = produtoImageRepository.save(ProdutoImagem.builder()
    //             .name(file.getOriginalFilename())
    //             .tipo(file.getContentType())
    //             .produto(produto)
    //             .produtoImagem(file.getBytes()[0]).build());
    //     if (produtoImagem != null) {
    //         return "imagem carregada com sucesso: " + file.getOriginalFilename();
    //     } else {
    //         return "Produto com ID " + produtoId + " não encontrado.";
    //     }
    // }

    // public byte[] downloadImagem(Long produtoImagemId) {
    //     Optional<ProdutoImagem> dbProdutoImagem = produtoImageRepository.findById(produtoImagemId);
    //     byte[] imagens = ImagemUtils.descomprimirImagem(new byte[] { dbProdutoImagem.get().getProdutoImagem() });
    //     return imagens;
    // }

    public String uploadImagem(Long produtoId, MultipartFile file) throws IOException {
        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
        Produto produto = optionalProduto.orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        
        byte[] imagemBytes = file.getBytes();
        String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);

        ProdutoImagem produtoImagem = produtoImageRepository.save(ProdutoImagem.builder()
                .name(file.getOriginalFilename())
                .tipo(file.getContentType())
                .produto(produto)
                .produtoImagem(imagemBase64).build());

        if (produtoImagem != null) {
            return "Imagem carregada com sucesso: " + file.getOriginalFilename();
        } else {
            return "Erro ao carregar imagem para o produto com ID " + produtoId + ".";
        }
    }

    public String downloadImagem(Long produtoImagemId) {
        Optional<ProdutoImagem> dbProdutoImagem = produtoImageRepository.findById(produtoImagemId);
        ProdutoImagem produtoImagem = dbProdutoImagem.orElseThrow(() -> new RuntimeException("Imagem não encontrada."));
        String imagemBase64 = produtoImagem.getProdutoImagem();
        byte[] imagemBytes = Base64.getDecoder().decode(imagemBase64);
        return new String(imagemBytes);
    }
}

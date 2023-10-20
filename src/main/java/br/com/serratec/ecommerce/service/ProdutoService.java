package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.type.TrueFalseType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serratec.ecommerce.dto.produto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.model.Log;
import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private ModelMapper mapper;

    public List<ProdutoResponseDTO> obterTodos() {

        List<Produto> produtos = produtoRepository.findAll();

        return produtos
                .stream()
                .map(produto -> mapper.map(produto, ProdutoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO obterPorId(long id) {

        Optional<Produto> optProduto = produtoRepository.findById(id);

        if (optProduto.isEmpty()) {
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return mapper.map(optProduto.get(), ProdutoResponseDTO.class);
    }


    public ProdutoResponseDTO adicionar(ProdutoRequestDTO produtoRequest) {

        Produto produto = mapper.map(produtoRequest, Produto.class);

        produto.setProdutoId((long) 0);
        produto.setAtivo(true);

        produto = produtoRepository.save(produto);

        ProdutoResponseDTO produtoResponse = mapper.map(produto, ProdutoResponseDTO.class);
        try {

            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Log log = new Log(
            "PRODUTO",
            "INSERIR",
            new ObjectMapper().writeValueAsString(""), 
            new ObjectMapper().writeValueAsString(produtoResponse), 
            usuario, 
            null);

            logService.registrarLog(log);

        } catch (Exception e) {

        }

        return produtoResponse;
    }

    public ProdutoResponseDTO atualizar(long id, ProdutoRequestDTO produtoRequest) {

        var produtoEstoque = obterPorId(id);

        Produto produto = mapper.map(produtoRequest, Produto.class);

        produto.setProdutoId(id);

        produto = produtoRepository.save(produto);

        ProdutoResponseDTO produtoResponse = mapper.map(produto, ProdutoResponseDTO.class);

        try {

            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Log log = new Log(
            "PRODUTO",
            "ATUALIZAR",
            new ObjectMapper().writeValueAsString(produtoEstoque), 
            new ObjectMapper().writeValueAsString(produtoResponse), 
            usuario, 
            null);

            logService.registrarLog(log);

        } catch (Exception e) {

        }

        return produtoResponse;
    }

    public ProdutoResponseDTO InativarProduto(Long id) {

        Produto produto = produtoRepository.findById(id).orElseThrow();

        produto.setAtivo(false);

        produto = produtoRepository.save(produto);

        return mapper.map(produto, ProdutoResponseDTO.class);
    }

    public Produto AtivarProduto(Long id) {

        Produto produto = produtoRepository.findById(id).orElseThrow();

        produto.setAtivo(true);

        produto = produtoRepository.save(produto);

        return produto;
    }

    public void deletar(Long id) {
        
        obterPorId(id);

        produtoRepository.deleteById(id);
    }
}

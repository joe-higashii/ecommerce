package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.produto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

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

        produtoRequest.setProdutoId((long) 0);

        Produto produto = mapper.map(produtoRequest, Produto.class);

        produtoRepository.save(produto);

        return mapper.map(produto, ProdutoResponseDTO.class);
    }

    public ProdutoResponseDTO atualizar(long id, ProdutoRequestDTO produtoRequest) {

        obterPorId(id);

        produtoRequest.setProdutoId(id);

        Produto produto = mapper.map(produtoRequest, Produto.class);

        produtoRepository.save(produto);

        return mapper.map(produto, ProdutoResponseDTO.class);
    }

    public void deletar(Long id) {

        obterPorId(id);

        produtoRepository.deleteById(id);
    }
}

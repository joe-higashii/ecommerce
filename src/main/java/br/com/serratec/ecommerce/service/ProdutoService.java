package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serratec.ecommerce.dto.produto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.model.Auditoria;
import br.com.serratec.ecommerce.model.Categoria;
import br.com.serratec.ecommerce.model.ETipoEntidade;
import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuditoriaService auditoriaService;

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

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        produto.setProdutoId((long) 0);

        produto = produtoRepository.save(produto);

        // depois de adicionar gravar a auditoria

        try {

            Auditoria auditoria = new Auditoria(
                    ETipoEntidade.PRODUTO,
                    "CADASTRO",
                    "",
                    new ObjectMapper()
                            .writeValueAsString(produto),
                    usuario);

            auditoriaService.registrarAuditoria(auditoria);
        } catch (Exception e) {

        }

        return mapper.map(produto, ProdutoResponseDTO.class);
    }

    public ProdutoResponseDTO atualizar(long id, ProdutoRequestDTO produtoRequest) {

        var produtoEstoque = obterPorId(id);

        Produto produto = mapper.map(produtoRequest, Produto.class);

        produto.setProdutoId(id);

        produto = produtoRepository.save(produto);

        // Depois de atualizar, gravar a auditoria
        try {

            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Auditoria auditoria = new Auditoria(
                    ETipoEntidade.PRODUTO,
                    "ATUALIZACAO",
                    new ObjectMapper()
                            .writeValueAsString(produtoEstoque),
                    new ObjectMapper()
                            .writeValueAsString(produto),
                    usuario);

            auditoriaService.registrarAuditoria(auditoria);

        } catch (Exception e) {

        }

        return mapper.map(produto, ProdutoResponseDTO.class);
    }

    public void deletar(Long id) {
        
        obterPorId(id);

        produtoRepository.deleteById(id);
    }
}

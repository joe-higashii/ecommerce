package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> obterTodos(){
        return produtoRepository.findAll();
    }

     public Produto obterPorId(long id){
        Optional<Produto> optProduto = produtoRepository.findById(id);

        if(optProduto.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return optProduto.get();
    }

    public Produto adicionar(Produto produto){
        produto.setProdutoId((long) 0);
        return produtoRepository.save(produto);
    }

    public Produto atualizar(long id, Produto produto){

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        produto.setProdutoId(id);
        return produtoRepository.save(produto);
    }

    public void deletar(Long id){
        obterPorId(id);

        produtoRepository.deleteById(id);
    }
    
}

package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Categoria;
import br.com.serratec.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> obterTodos(){
        return categoriaRepository.findAll();
    }


     public Categoria obterPorId(long id){
        Optional<Categoria> optCategoria = categoriaRepository.findById(id);

        if(optCategoria.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return optCategoria.get();
    }

    public Categoria adicionar(Categoria categoria){
        categoria.setCategoriaId((long) 0);
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(long id, Categoria categoria){

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        categoria.setCategoriaId(id);
        return categoriaRepository.save(categoria);
    }

    public void deletar(Long id){
        obterPorId(id);

        categoriaRepository.deleteById(id);
    }
}

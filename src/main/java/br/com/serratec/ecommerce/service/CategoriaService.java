package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.categoria.CategoriaRequestDTO;
import br.com.serratec.ecommerce.dto.categoria.CategoriaResponseDTO;
import br.com.serratec.ecommerce.model.Categoria;
import br.com.serratec.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper mapper;

    public List<CategoriaResponseDTO> obterTodos() {

        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias
                .stream()
                .map(categoria -> mapper.map(categoria, CategoriaResponseDTO.class))
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO obterPorId(long id) {

        Optional<Categoria> optCategoria = categoriaRepository.findById(id);

        if (optCategoria.isEmpty()) {
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return mapper.map(optCategoria.get(), CategoriaResponseDTO.class);
    }

    public CategoriaResponseDTO adicionar(CategoriaRequestDTO categoriaRequest) {
        
        Categoria categoria = mapper.map(categoriaRequest, Categoria.class);
        
        categoria.setCategoriaId((long) 0);

        categoria = categoriaRepository.save(categoria);

        return mapper.map(categoria, CategoriaResponseDTO.class);
    }

    public CategoriaResponseDTO atualizar(long id, CategoriaRequestDTO categoriaRequest) {

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        Categoria categoria = mapper.map(categoriaRequest, Categoria.class);

        categoria.setCategoriaId(id);

        categoria = categoriaRepository.save(categoria);

        return mapper.map(categoria, CategoriaResponseDTO.class);
    }

    public void deletar(Long id) {

        obterPorId(id);
        
        categoriaRepository.deleteById(id);
    }
}

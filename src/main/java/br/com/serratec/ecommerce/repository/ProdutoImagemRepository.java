package br.com.serratec.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.ecommerce.model.ProdutoImagem;

@Repository
public interface ProdutoImagemRepository extends JpaRepository<ProdutoImagem, Long>{

    Optional<ProdutoImagem> findByName(String imageName);
    
}

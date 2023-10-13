package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;


   public List<Usuario> obterTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario obterPorId(long id){
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        if(optUsuario.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return optUsuario.get();
    }

    public Usuario adicionar(Usuario usuario){
        usuario.setUsuarioId((long) 0);
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(long id, Usuario usuario){

        obterPorId(id);

        usuario.setUsuarioId(id);
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id){
        obterPorId(id);

        usuarioRepository.deleteById(id);
    }

}

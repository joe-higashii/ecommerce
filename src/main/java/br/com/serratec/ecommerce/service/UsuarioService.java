package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.Usuario;
// import br.com.serratec.ecommerce.model.email.Email;
import br.com.serratec.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper mapper;

    public List<UsuarioResponseDTO> obterTodos() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios
                .stream()
                .map(usuario -> mapper.map(usuario, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO obterPorId(long id) {

        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        if (optUsuario.isEmpty()) {
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return mapper.map(optUsuario.get(), UsuarioResponseDTO.class);
    }

    public UsuarioResponseDTO adicionar(UsuarioRequestDTO usuarioRequest) {

        Usuario usuario  = mapper.map(usuarioRequest, Usuario.class);

        usuario.setUsuarioId(0l);

        usuarioRepository.save(usuario);

        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    public UsuarioResponseDTO atualizar(long id, UsuarioRequestDTO usuarioRequest) {

        obterPorId(id);

        usuarioRequest.setUsuarioId(id);

        Usuario usuario = mapper.map(usuarioRequest, Usuario.class);

        usuarioRepository.save(usuario);

        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    public void deletar(Long id) {
        obterPorId(id);

        usuarioRepository.deleteById(id);
    }
}

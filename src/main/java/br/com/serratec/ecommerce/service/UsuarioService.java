package br.com.serratec.ecommerce.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.usuario.UsuarioLoginResponseDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.repository.UsuarioRepository;
import br.com.serratec.ecommerce.security.JWTService;

@Service
public class UsuarioService implements CRUDService<UsuarioRequestDTO, UsuarioResponseDTO, Long> {

    private static final String BEARER = "Bearer ";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    // public UsuarioResponseDTO adicionar(UsuarioRequestDTO usuarioRequest) {

    // usuarioRequest.setUsuarioId((long) 0);

    // Usuario usuario = mapper.map(usuarioRequest, Usuario.class);

    // usuarioRepository.save(usuario);

    // return mapper.map(usuario, UsuarioResponseDTO.class);
    // }

    @Override
    public UsuarioResponseDTO adicionar(UsuarioRequestDTO usuarioRequest) {

        Usuario usuario =  mapper.map(usuarioRequest, Usuario.class);
        
        usuario.setUsuarioId(0l);

        // aqui estou criptografando a senha antes de salvar no banco de dados
        String senha = passwordEncoder.encode(usuario.getSenha());

        usuario.setSenha(senha);
        usuario.setUsuarioId(0l);

        usuario = usuarioRepository.save(usuario);

        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    public UsuarioResponseDTO atualizar(long id, UsuarioRequestDTO usuarioRequest) {

        obterPorId(id);

        usuarioRequest.setUsuarioId(id);

        Usuario usuario = mapper.map(usuarioRequest, Usuario.class);

        usuario = usuarioRepository.save(usuario);

        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    public void deletar(Long id) {

        obterPorId(id);

        usuarioRepository.deleteById(id);
    }

    @Override
    public void deletar(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    public UsuarioResponseDTO obterPorEmail(String email) {
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);

        return mapper.map(optUsuario.get(), UsuarioResponseDTO.class);
    }

    public UsuarioLoginResponseDTO logar(String email, String senha) {
        // é aqui que a autenticação acontece dentro do spring automaticamente

        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);

        if(optUsuario.isEmpty()){
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }

        Authentication autenticacao = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, senha, Collections.emptyList()));
        // Aqui eu passo a nova autenteicação para o springSecurity cuidar pra mim
        SecurityContextHolder.getContext().setAuthentication(autenticacao);

        // Crio o token JWT
        String token = BEARER + jwtService.gerarToken(autenticacao);
        // Bearer 4as5d648sad4asd654asd654asd465asd645asd46asd4s4ad65s654ad

        // Pego o usuário dono do token
        UsuarioResponseDTO usuarioResponseDTO = obterPorEmail(email);

        // Crio e devolvo o DTO esperado.
        return new UsuarioLoginResponseDTO(token, usuarioResponseDTO);
    }
}

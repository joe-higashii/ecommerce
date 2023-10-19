package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.tipoUsuario.TipoUsuarioRequestDTO;
import br.com.serratec.ecommerce.dto.tipoUsuario.TipoUsuarioResponseDTO;
import br.com.serratec.ecommerce.model.TipoUsuario;
import br.com.serratec.ecommerce.repository.TipoUsuarioRepository;

@Service
public class TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private ModelMapper mapper;

    public List<TipoUsuarioResponseDTO> obterTodos() {

        List<TipoUsuario> tipos = tipoUsuarioRepository.findAll();

        return tipos
                .stream()
                .map(tipo -> mapper.map(tipo, TipoUsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }

    public TipoUsuarioResponseDTO obterPorId(long id) {

        Optional<TipoUsuario> optTipo = tipoUsuarioRepository.findById(id);

        if (optTipo.isEmpty()) {
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return mapper.map(optTipo.get(), TipoUsuarioResponseDTO.class);
    }


    public TipoUsuarioResponseDTO adicionar(TipoUsuarioRequestDTO tipoUsuarioRequest) {

        TipoUsuario tipoUsuario = mapper.map(tipoUsuarioRequest, TipoUsuario.class);

        tipoUsuario.setTipoUsuarioId((long) 0);
        
        tipoUsuario = tipoUsuarioRepository.save(tipoUsuario);
        
        return mapper.map(tipoUsuario, TipoUsuarioResponseDTO.class); 
    }

    public TipoUsuarioResponseDTO atualizar(long id, TipoUsuarioRequestDTO tipoUsuarioRequest) {

        TipoUsuario tipoUsuario = mapper.map(tipoUsuarioRequest, TipoUsuario.class);

        tipoUsuario.setTipoUsuarioId(id);
        
        tipoUsuario = tipoUsuarioRepository.save(tipoUsuario);

        return mapper.map(tipoUsuario, TipoUsuarioResponseDTO.class);
    }

    public void deletar(Long id) {

        obterPorId(id);

        tipoUsuarioRepository.deleteById(id);
    }
}

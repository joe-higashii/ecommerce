package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.TipoUsuario;
import br.com.serratec.ecommerce.repository.TipoUsuarioRepository;

@Service
public class TipoUsuarioService {

@Autowired
private TipoUsuarioRepository tipoUsuarioRepository;

public List<TipoUsuario> obterTodos() {
return tipoUsuarioRepository.findAll();
}

public TipoUsuario obterPorId(long id) {

Optional<TipoUsuario> optTipo = tipoUsuarioRepository.findById(id);

if (optTipo.isEmpty()) {
throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
}


    public TipoUsuario adicionar(TipoUsuario tipoUsuario) {

        tipoUsuario.setTipoUsuarioId((long) 0);

        return tipoUsuarioRepository.save(tipoUsuario);
    }

return optTipo.get();
}


public TipoUsuario adicionar(TipoUsuario tipoUsuario) {
tipoUsuario.setTipoUsuarioId((long) 0);
return tipoUsuarioRepository.save(tipoUsuario);
}

public TipoUsuario atualizar(long id, TipoUsuario tipoUsuario) {
tipoUsuario.setTipoUsuarioId(id);
return tipoUsuarioRepository.save(tipoUsuario);
}

public void deletar(Long id) {

obterPorId(id);

tipoUsuarioRepository.deleteById(id);
}

}

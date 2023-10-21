package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.TipoLog;
import br.com.serratec.ecommerce.repository.TipoLogRepository;

@Service
public class TipoLogService {
    
    @Autowired
    private TipoLogRepository tipoLogRepository;

    public List<TipoLog> obterTodos(){
        return tipoLogRepository.findAll();
    }

    public TipoLog obterPorId(long id){
        Optional<TipoLog> optTLog = tipoLogRepository.findById(id);

        if(optTLog.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return optTLog.get();
    }

    public TipoLog adicionar(TipoLog tipoLog){
        tipoLog.setTipoLogId((long) 0);
        return tipoLogRepository.save(tipoLog);
    }

    public TipoLog atualizar(long id, TipoLog tipoLog){

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        tipoLog.setTipoLogId(id);
        return tipoLogRepository.save(tipoLog);
    }

    public void deletar(Long id){
        obterPorId(id);

        tipoLogRepository.deleteById(id);
    }
}

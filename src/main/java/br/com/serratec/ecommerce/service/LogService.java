package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Log;
import br.com.serratec.ecommerce.repository.LogRepository;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public List<Log> obterTodos() {
        return logRepository.findAll();
    }

    public Log obterPorId(long id) {
        Optional<Log> optLog = logRepository.findById(id);

        if (optLog.isEmpty()) {
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return optLog.get();
    }

    public Log adicionar(Log log) {
        log.setLogId((long) 0);
        return logRepository.save(log);
    }

    public Log atualizar(long id, Log log) {

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        log.setLogId(id);
        return logRepository.save(log);
    }

    public void deletar(Long id) {
        obterPorId(id);

        logRepository.deleteById(id);
    }

}

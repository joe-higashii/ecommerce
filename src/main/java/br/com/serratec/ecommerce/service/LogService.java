package br.com.serratec.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Log;
import br.com.serratec.ecommerce.repository.LogRepository;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void registrarLog(Log log) {

        this.logRepository.save(log);
    }
}

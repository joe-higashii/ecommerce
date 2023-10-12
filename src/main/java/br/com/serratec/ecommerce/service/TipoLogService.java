package br.com.serratec.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.repository.TipoLogRepository;

@Service
public class TipoLogService {
    private final TipoLogRepository tipoLogRepository;

    @Autowired
    public TipoLogService(TipoLogRepository tipoLogRepository) {
        this.tipoLogRepository = tipoLogRepository;
    }

}

package br.com.serratec.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Auditoria;
import br.com.serratec.ecommerce.repository.AuditoriaRepository;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    public void registrarAuditoria(Auditoria auditoria) {

        this.auditoriaRepository.save(auditoria);
    }
}

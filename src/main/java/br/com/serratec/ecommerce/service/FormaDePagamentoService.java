package br.com.serratec.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.repository.FormaDePagamentoRepository;

@Service
public class FormaDePagamentoService {
    private final FormaDePagamentoRepository formaDePagamentoRepository;

    @Autowired
    public FormaDePagamentoService(FormaDePagamentoRepository formaDePagamentoRepository) {
        this.formaDePagamentoRepository = formaDePagamentoRepository;
    }

}

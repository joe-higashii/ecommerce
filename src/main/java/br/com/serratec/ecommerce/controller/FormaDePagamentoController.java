package br.com.serratec.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.service.FormaDePagamentoService;

@RestController
@RequestMapping("/api/formas-pagamento")
public class FormaDePagamentoController {
    private final FormaDePagamentoService formaDePagamentoService;

    @Autowired
    public FormaDePagamentoController(FormaDePagamentoService formaDePagamentoService) {
        this.formaDePagamentoService = formaDePagamentoService;
    }

}

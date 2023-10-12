package br.com.serratec.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.service.TipoLogService;

@RestController
@RequestMapping("/api/tipos-logs")
public class TipoLogController {
    private final TipoLogService tipoLogService;

    @Autowired
    public TipoLogController(TipoLogService tipoLogService) {
        this.tipoLogService = tipoLogService;
    }

}

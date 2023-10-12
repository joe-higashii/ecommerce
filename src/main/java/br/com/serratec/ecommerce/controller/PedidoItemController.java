package br.com.serratec.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.service.PedidoItemService;

@RestController
@RequestMapping("/api/pedido-itens")
public class PedidoItemController {
    private final PedidoItemService pedidoItemService;

    @Autowired
    public PedidoItemController(PedidoItemService pedidoItemService) {
        this.pedidoItemService = pedidoItemService;
    }

}

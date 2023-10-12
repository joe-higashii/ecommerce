package br.com.serratec.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.repository.PedidoItemRepository;

@Service
public class PedidoItemService {
    private final PedidoItemRepository pedidoItemRepository;

    @Autowired
    public PedidoItemService(PedidoItemRepository pedidoItemRepository) {
        this.pedidoItemRepository = pedidoItemRepository;
    }

}

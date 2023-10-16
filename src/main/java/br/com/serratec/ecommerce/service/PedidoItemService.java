package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.PedidoItem;
import br.com.serratec.ecommerce.repository.PedidoItemRepository;

@Service
public class PedidoItemService {

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    public List<PedidoItem> obterTodos() {
        return pedidoItemRepository.findAll();
    }

    public PedidoItem obterPorId(long id) {
        Optional<PedidoItem> optPedItem = pedidoItemRepository.findById(id);

        if (optPedItem.isEmpty()) {
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return optPedItem.get();
    }

    public PedidoItem adicionar(PedidoItem pedidoiItem) {
        pedidoiItem.setPedItemId((long) 0);
        return pedidoItemRepository.save(pedidoiItem);
    }

    public PedidoItem atualizar(long id, PedidoItem pedidoItem) {

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        pedidoItem.setPedItemId(id);
        return pedidoItemRepository.save(pedidoItem);
    }

    public void deletar(Long id) {
        obterPorId(id);

        pedidoItemRepository.deleteById(id);
    }

}

package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.PedidoItem;
import br.com.serratec.ecommerce.repository.PedidoItemRepository;
import br.com.serratec.ecommerce.repository.PedidoRepository;

@Service
public class PedidoItemService {

    @Autowired
    private PedidoItemRepository pedidoItemRepository;
    private PedidoRepository pedidoRepository;

    public PedidoItemService(PedidoItemRepository pedidoItemRepository, PedidoRepository pedidoRepository) {
        this.pedidoItemRepository = pedidoItemRepository;
        this.pedidoRepository = pedidoRepository;
    }

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

    public PedidoItem adicionar(PedidoItem pedidoiItem, Pedido pedido, int quantidade) {
        pedidoiItem.setQtd(quantidade);
        pedidoiItem.setPedido(pedido);
        double valorTotalItem = pedidoiItem.getVlUn() * quantidade - pedidoiItem.getVlDesc() + pedidoiItem.getVlAcres();
        pedidoiItem.setVlToProd(valorTotalItem);
        PedidoItem novoPedidoItem = pedidoItemRepository.save(pedidoiItem);
        atualizarTotalPedido(pedido, valorTotalItem);
        return novoPedidoItem;
    }

    private void atualizarTotalPedido(Pedido pedido, double valorItem) {
        double novoValorTotal = pedido.getVlTotal() + valorItem;
        pedido.setVlTotal(novoValorTotal);
        pedidoRepository.save(pedido);
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

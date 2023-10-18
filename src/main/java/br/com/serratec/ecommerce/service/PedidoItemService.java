package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.pedidoItem.PedidoItemRequestDTO;
import br.com.serratec.ecommerce.dto.pedidoItem.PedidoItemResponseDTO;
import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.PedidoItem;
import br.com.serratec.ecommerce.repository.PedidoItemRepository;
import br.com.serratec.ecommerce.repository.PedidoRepository;

@Service
public class PedidoItemService {

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Autowired
    private ModelMapper mapper;

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

    @Transactional
    public PedidoItemResponseDTO adicionar(PedidoItemRequestDTO pedidoItemRequest) {

        PedidoItem pedido = adicionarPedidoItem(pedidoItemRequest);

        PedidoRequestDTO pedidoRequest = pedidoItemRequest.getPedido();

        pedidoItemRequest.setId(pedido.getPedItemId());

        pedidoRequest.setPedidoItem(pedidoItemRequest);

        return mapper.map(pedido, PedidoItemResponseDTO.class);
    }

    public PedidoItem adicionarPedidoItem(PedidoItemRequestDTO pedidoItemRequest) {

        PedidoItem pedidoItem = mapper.map(pedidoItemRequest, PedidoItem.class);

        pedidoItem.setPedItemId((long) 0);

        pedidoItem = pedidoItemRepository.save(pedidoItem);

        return pedidoItem;
    }

    public PedidoItem adicionar1(PedidoItem pedidoiItem) {

        pedidoiItem.setPedItemId((long) 0);

        return pedidoItemRepository.save(pedidoiItem);
    }    

    // public PedidoItemResponseDTO adicionar1(PedidoItemRequestDTO pedidoItemRequest, PedidoRequestDTO pedidoRequest, int quantidade) {

    //     PedidoItem pedidoItem = mapper.map(pedidoItemRequest, PedidoItem.class);
    //     Pedido pedido = mapper.map(pedidoRequest, Pedido.class);

    //     pedidoItem.setQtd(quantidade);
        
    //     pedidoItem.setPedido(pedido);
        
    //     double valorTotalItem = pedidoItem.getVlUn() * quantidade - pedidoItem.getVlDesc() + pedidoItem.getVlAcres();
        
    //     pedidoItem.setVlToProd(valorTotalItem);
        
    //     PedidoItem novoPedidoItem = pedidoItemRepository.save(pedidoItem);
        
    //     atualizarTotalPedido(pedido, valorTotalItem);
        
    //     return mapper.map(novoPedidoItem, PedidoItemResponseDTO.class) ;
    // }

    // private void atualizarTotalPedido(Pedido pedido, double valorItem) {

    //     double novoValorTotal = pedido.getVlTotal() + valorItem;

    //     pedido.setVlTotal(novoValorTotal);
        
    //     pedidoRepository.save(pedido);
    // }

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

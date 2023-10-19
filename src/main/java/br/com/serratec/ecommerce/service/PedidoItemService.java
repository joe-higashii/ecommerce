package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.pedidoItem.PedidoItemResponseDTO;
import br.com.serratec.ecommerce.model.PedidoItem;
import br.com.serratec.ecommerce.repository.PedidoItemRepository;
// import br.com.serratec.ecommerce.repository.PedidoRepository;

@Service
public class PedidoItemService {

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Autowired
    private ModelMapper mapper;

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

    public PedidoItemResponseDTO adicionar(PedidoItem pedidoItem) {

        pedidoItem.setPedItemId(0l);

        double valorTotalItem = pedidoItem.getVlUn() * pedidoItem.getQtd() -
        (pedidoItem.getVlDesc() + pedidoItem.getVlAcres());

        pedidoItem.setVlToProd(valorTotalItem);

        pedidoItem = pedidoItemRepository.save(pedidoItem);

        PedidoItemResponseDTO pedidoItemResponse = mapper.map(pedidoItem, PedidoItemResponseDTO.class);

        return pedidoItemResponse;
    }
    
    public PedidoItem atualizar(long id, PedidoItem pedidoItem) {

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        pedidoItem.setPedItemId(id);

        pedidoItem = pedidoItemRepository.save(pedidoItem);

        return pedidoItem;
    }

    public void deletar(Long id) {

        obterPorId(id);

        pedidoItemRepository.deleteById(id);
    }
}

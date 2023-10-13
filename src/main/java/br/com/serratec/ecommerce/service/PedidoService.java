package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> obterTodos(){
        return pedidoRepository.findAll();
    }

    public Pedido obterPorId(long id){
        Optional<Pedido> optPedido = pedidoRepository.findById(id);

        if(optPedido.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return optPedido.get();
    }

    public Pedido adicionar(Pedido pedido){
        pedido.setPedidoId((long) 0);
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizar(long id, Pedido pedido){

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        pedido.setPedidoId(id);
        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id){
        obterPorId(id);

        pedidoRepository.deleteById(id);
    }
    // Implemente métodos de serviço conforme necessário
}

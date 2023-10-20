package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.pedido.PedidoResponseDTO;
import br.com.serratec.ecommerce.model.Log;
import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.PedidoItem;

import br.com.serratec.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private LogService logService;

    @Autowired
    private ModelMapper mapper;

    public List<PedidoResponseDTO> obterTodos() {

        List<Pedido> pedidos = pedidoRepository.findAll();

        return pedidos
                .stream()
                .map(pedido -> mapper.map(pedido, PedidoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public PedidoResponseDTO obterPorId(long id) {

        Optional<Pedido> optPedido = pedidoRepository.findById(id);

        if (optPedido.isEmpty()) {
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return mapper.map(optPedido.get(), PedidoResponseDTO.class);
    }

    public PedidoResponseDTO adicionar(PedidoRequestDTO pedidoRequest) {

        //pega os itens
        List<PedidoItem> listaSalvaProdutos = pedidoRequest
                .getItens()
                .stream()
                .map(item -> mapper
                .map(item, PedidoItem.class)).collect(Collectors.toList());

        Pedido pedido = adicionarPedido(pedidoRequest);

        pedido.setItens(listaSalvaProdutos);
        
        List<PedidoItem> itensCadastrados = itemsPedido(pedido);
        
        pedido.setItens(itensCadastrados);

        PedidoResponseDTO pedidoResponse = mapper.map(pedido, PedidoResponseDTO.class);

        try {
            
            Log log = new Log(
            "Pedido",
            "CADASTRO",
            "",
            new ObjectMapper().writeValueAsString(pedido),pedido.getUsuario(),
            new Date());

            logService.registrarLog(log);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return pedidoResponse;
    }

    public Pedido adicionarPedido(PedidoRequestDTO pedidoRequest) {

        Pedido pedido = mapper.map(pedidoRequest, Pedido.class);

        pedido.setDtPedido(new Date());

        pedido.setPedidoId(0l);

        pedido = pedidoRepository.save(pedido);

        return pedido;
    }

    public List<PedidoItem> itemsPedido(Pedido pedido) {

        List<PedidoItem> adicionadas = new ArrayList<>();


        for (PedidoItem pedidoItem : pedido.getItens()) {

            pedidoItem.setPedido(pedido);

            pedidoItemService.adicionar(pedidoItem);

            adicionadas.add(pedidoItem);
        }
        
        return adicionadas;
    }

    public PedidoResponseDTO atualizar(long id, PedidoRequestDTO pedidoRequest) {

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        Pedido pedido = mapper.map(pedidoRequest, Pedido.class);

        pedido.setPedidoId(id);

        pedidoRepository.save(pedido);

        return mapper.map(pedido, PedidoResponseDTO.class);
    }

    public void deletar(Long id) {

        obterPorId(id);

        pedidoRepository.deleteById(id);
    }

    // Implemente métodos de serviço conforme necessário
}

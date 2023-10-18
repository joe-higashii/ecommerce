package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.pedido.PedidoResponseDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;
import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

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

        Pedido pedido = adicionarPedido(pedidoRequest);
        
        UsuarioRequestDTO usuarioRequest = pedidoRequest.getUsuario();

        pedidoRequest.setId(pedido.getPedidoId());

        pedidoRequest.setUsuario(usuarioRequest);

        return mapper.map(pedidoRequest, PedidoResponseDTO.class);
    }

    @Transactional
    public Pedido adicionarPedido(PedidoRequestDTO pedidoRequest) {

        Pedido pedido = mapper.map(pedidoRequest, Pedido.class);

        pedido.setPedidoId(0l);

        pedidoRepository.save(pedido);

        return pedido;
    }

    public PedidoResponseDTO atualizar(long id, PedidoRequestDTO pedidoRequest) {

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        pedidoRequest.setId(id);

        Pedido pedido = mapper.map(pedidoRequest, Pedido.class);

        pedidoRepository.save(pedido);

        return mapper.map(pedido, PedidoResponseDTO.class);
    }

    public void deletar(Long id) {

        obterPorId(id);

        pedidoRepository.deleteById(id);
    }

    // Implemente métodos de serviço conforme necessário
}

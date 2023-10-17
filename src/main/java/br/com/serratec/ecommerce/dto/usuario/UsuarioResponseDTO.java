package br.com.serratec.ecommerce.dto.usuario;


import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import java.util.Date;

public class UsuarioResponseDTO extends UsuarioBaseDTO {
    
    private PedidoRequestDTO pedido;

    public PedidoRequestDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoRequestDTO pedido) {
        this.pedido = pedido;
    }
}

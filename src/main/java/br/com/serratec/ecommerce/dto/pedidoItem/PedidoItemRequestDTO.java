package br.com.serratec.ecommerce.dto.pedidoItem;

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;

public class PedidoItemRequestDTO extends PedidoItemBaseDTO {

    private PedidoRequestDTO pedido;

    public PedidoRequestDTO getPedido() {
        return pedido;
    }

    public void setPedidoRequest(PedidoRequestDTO pedido) {
        this.pedido = pedido;
    }
}

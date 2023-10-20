package br.com.serratec.ecommerce.dto.pedidoItem;

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;

public class PedidoItemResponseDTO extends PedidoItemRequestDTO {

    private PedidoRequestDTO pedidoRequest;

    public PedidoRequestDTO getPedidoRequest() {
        return pedidoRequest;
    }

    public void setPedidoRequest(PedidoRequestDTO pedidoRequest) {
        this.pedidoRequest = pedidoRequest;
    }
}

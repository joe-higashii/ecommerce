package br.com.serratec.ecommerce.dto.pedido;

public class PedidoResponseDTO extends PedidoRequestDTO {

    private Long pedidoId;

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }
}

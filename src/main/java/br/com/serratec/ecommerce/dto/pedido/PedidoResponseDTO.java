package br.com.serratec.ecommerce.dto.pedido;

public class PedidoResponseDTO extends PedidoRequestDTO {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

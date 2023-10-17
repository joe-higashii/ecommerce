package br.com.serratec.ecommerce.dto.pedido;

import br.com.serratec.ecommerce.dto.pedidoItem.PedidoItemRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;

public class PedidoRequestDTO extends PedidoBaseDTO {

    private PedidoItemRequestDTO pedidoItem;
    private UsuarioRequestDTO usuario;

    public PedidoItemRequestDTO getPedidoItem() {
        return pedidoItem;
    }

    public void setPedidoItem(PedidoItemRequestDTO pedidoItem) {
        this.pedidoItem = pedidoItem;
    }

    public UsuarioRequestDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioRequestDTO usuario) {
        this.usuario = usuario;
    }
}

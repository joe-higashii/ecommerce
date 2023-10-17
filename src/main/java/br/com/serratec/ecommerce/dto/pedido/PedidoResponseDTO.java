package br.com.serratec.ecommerce.dto.pedido;

import java.util.Date;

import br.com.serratec.ecommerce.dto.pedidoItem.PedidoItemRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;

public class PedidoResponseDTO extends PedidoBaseDTO {

    private PedidoItemRequestDTO pedidoItem;
    private UsuarioRequestDTO usuario;
    private Date dtPedido;

// #region Getter's and Setter's

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

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }
// #endregion
}

package br.com.serratec.ecommerce.dto.pedido;

import java.util.Date;

public class PedidoResponseDTO extends PedidoBaseDTO {

    // #region Getter's and Setter's
    private Date dtPedido;

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }
    // #endregion
}

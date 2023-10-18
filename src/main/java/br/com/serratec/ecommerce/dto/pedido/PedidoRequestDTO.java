package br.com.serratec.ecommerce.dto.pedido;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.serratec.ecommerce.dto.pedidoItem.PedidoItemRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;

public class PedidoRequestDTO extends PedidoBaseDTO {

    private PedidoItemRequestDTO pedidoItem;

    
    @JsonBackReference
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

    private String nrPedido;
    private double vlTotal;
    private double descTotal;
    private double acresTotal;
    private String obs;
    private boolean cancelado;

    // #region GEtters and Setters
    public String getNrPedido() {
        return nrPedido;
    }

    public void setNrPedido(String nrPedido) {
        this.nrPedido = nrPedido;
    }

    public double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(double vlTotal) {
        this.vlTotal = vlTotal;
    }

    public double getDescTotal() {
        return descTotal;
    }

    public void setDescTotal(double descTotal) {
        this.descTotal = descTotal;
    }

    public double getAcresTotal() {
        return acresTotal;
    }

    public void setAcresTotal(double acresTotal) {
        this.acresTotal = acresTotal;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
    // #endregion
}

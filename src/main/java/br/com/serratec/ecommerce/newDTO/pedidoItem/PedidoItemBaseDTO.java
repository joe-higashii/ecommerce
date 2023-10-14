package br.com.serratec.ecommerce.newDTO.pedidoItem;

import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.Produto;

public abstract class PedidoItemBaseDTO {

    private Long pedItemId;
    private double vlUn;
    private int qtd;
    private double vlDesc;
    private double vlAcres;
    private double vlToProd;
    private Produto produto;
    private Pedido pedido;

//#region Getter's and Setter's    
    public Long getPedItemId() {
        return pedItemId;
    }

    public void setPedItemId(Long pedItemId) {
        this.pedItemId = pedItemId;
    }

    public double getVlUn() {
        return vlUn;
    }

    public void setVlUn(double vlUn) {
        this.vlUn = vlUn;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getVlDesc() {
        return vlDesc;
    }

    public void setVlDesc(double vlDesc) {
        this.vlDesc = vlDesc;
    }

    public double getVlAcres() {
        return vlAcres;
    }

    public void setVlAcres(double vlAcres) {
        this.vlAcres = vlAcres;
    }

    public double getVlToProd() {
        return vlToProd;
    }

    public void setVlToProd(double vlToProd) {
        this.vlToProd = vlToProd;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
//#endregion
}

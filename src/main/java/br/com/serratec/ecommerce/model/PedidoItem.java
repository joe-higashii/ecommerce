package br.com.serratec.ecommerce.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PedidoItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedItemId;
    private double vlUn;
    private int qtd;
    private double vlDesc;
    private double vlAcres;
    private double vlToProd;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private List<Pedido> pedidos;

    // #region Getters and Setters

    public PedidoItem(Long pedItemId, double vlUn, int qtd, double vlDesc, double vlAcres, double vlToProd,
            Produto produto, List<Pedido> pedidos) {
        this.pedItemId = pedItemId;
        this.vlUn = vlUn;
        this.qtd = qtd;
        this.vlDesc = vlDesc;
        this.vlAcres = vlAcres;
        this.vlToProd = vlToProd;
        this.produto = produto;
        this.pedidos = pedidos;
    }

    public PedidoItem() {

    }

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

    public List<Pedido> getPedido() {
        return pedidos;
    }

    public void setPedido(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

// #endregion
}

package br.com.serratec.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class PedidoItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedItemId;
    private double valorUnitario;
    private int quantidade;
    private double desconto;
    private double valorTotalItem;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public PedidoItem(Long pedItemId, double valorUnitario, int quantidade, double desconto, double valorTotalItem,
            Produto produto, Pedido pedido) {
        this.pedItemId = pedItemId;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.desconto = desconto;
        this.valorTotalItem = valorTotalItem;
        this.produto = produto;
        this.pedido = pedido;
    }

    public PedidoItem() {}

    public Long getPedItemId() {
        return pedItemId;
    }

    public void setPedItemId(Long pedItemId) {
        this.pedItemId = pedItemId;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorTotalItem() {
        return valorTotalItem;
    }

    public void setValorTotalItem(double valorTotalItem) {
        this.valorTotalItem = valorTotalItem;
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

//#region Getter's and Setter's
    
    
// #endregion
}

package br.com.serratec.ecommerce.dto.pedidoItem;

import br.com.serratec.ecommerce.model.Produto;

public class PedidoItemRequestDTO {

    private Long pedItemId;
    private double valorUnitario;
    private int quantidade;
    private double desconto;
    private double valorTotalItem;
    private Produto produto;

// #region Getter's and Setter's

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

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
    
// #endregion    
}

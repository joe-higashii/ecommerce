package br.com.serratec.ecommerce.dto;

public class PedidoItemDTO {
    private Long pedItemId;
    private double vlUn;
    private int qtd;
    private double vlDesc;
    private double vlAcres;
    private double vlToProd;
    private ProdutoDTO produto;

    // #region Constructors

    public PedidoItemDTO(Long pedItemId, double vlUn, int qtd, double vlDesc, double vlAcres, double vlToProd,
            ProdutoDTO produto) {
        this.pedItemId = pedItemId;
        this.vlUn = vlUn;
        this.qtd = qtd;
        this.vlDesc = vlDesc;
        this.vlAcres = vlAcres;
        this.vlToProd = vlToProd;
        this.produto = produto;
    }

    // #region Getters and Setters

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

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    // #endregion
}
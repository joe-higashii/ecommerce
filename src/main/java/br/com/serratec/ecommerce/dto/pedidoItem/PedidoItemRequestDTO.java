package br.com.serratec.ecommerce.dto.pedidoItem;

import br.com.serratec.ecommerce.model.Produto;

public class PedidoItemRequestDTO {

    private Long id;
    private double vlUn;
    private int qtd;
    private double vlDesc;
    private double vlAcres;
    private double vlToProd;
    private Produto produto;

//#region Getter's and Setter's

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
//#endregion 

}

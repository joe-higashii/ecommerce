package br.com.serratec.ecommerce.dto.produto;

import br.com.serratec.ecommerce.model.Categoria;

public abstract class ProdutoRequestDTO {
    
    private String codProd;
    private String prodNome;
    private int qtdEst;
    private double precoVenda;
    private String obs;
    private Categoria categoria;

//#region Getter's and Setter's   

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getProdNome() {
        return prodNome;
    }

    public void setProdNome(String prodNome) {
        this.prodNome = prodNome;
    }

    public int getQtdEst() {
        return qtdEst;
    }

    public void setQtdEst(int qtdEst) {
        this.qtdEst = qtdEst;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
//#endregion
}

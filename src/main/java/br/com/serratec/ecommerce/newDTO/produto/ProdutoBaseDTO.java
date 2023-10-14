package br.com.serratec.ecommerce.newDTO.produto;

import br.com.serratec.ecommerce.model.Categoria;

public abstract class ProdutoBaseDTO {

    private Long produtoId;
    private String codProd;
    private String prodNome;
    private int qtdEst;
    private double precoVenda;
    private String obs;
    private boolean ativo;
    private Categoria categoria;

//#region Getter's and Setter's    
    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
//#endregion
}

package br.com.serratec.ecommerce.dto;

public class ProdutoDTO {
    private Long produtoId;
    private String codProd;
    private String prodNome;
    private int qtdEst;
    private double precoVenda;
    private String obs;
    private boolean ativo;
    private CategoriaDTO categoria;

    // #region Constructors

    public ProdutoDTO(Long produtoId, String codProd, String prodNome, int qtdEst, double precoVenda, String obs,
            boolean ativo, CategoriaDTO categoria) {
        this.produtoId = produtoId;
        this.codProd = codProd;
        this.prodNome = prodNome;
        this.qtdEst = qtdEst;
        this.precoVenda = precoVenda;
        this.obs = obs;
        this.ativo = ativo;
        this.categoria = categoria;
    }

    // #region Getters and Setters

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

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    // #endregion
}
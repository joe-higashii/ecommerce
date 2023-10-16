package br.com.serratec.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produtoId;

    @Column(nullable = false, unique = true)
    private String codProd;

    @Column(nullable = false)
    private String prodNome;

    private int qtdEst;

    @Column(nullable = false, unique = true)
    private double precoVenda;

    private String obs;

    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // #region Constructors

    public Produto(Long produtoId, String codProd, String prodNome, int qtdEst, double precoVenda, String obs,
            boolean ativo, Categoria categoria) {
        this.produtoId = produtoId;
        this.codProd = codProd;
        this.prodNome = prodNome;
        this.qtdEst = qtdEst;
        this.precoVenda = precoVenda;
        this.obs = obs;
        this.ativo = ativo;
        this.categoria = categoria;
    }

    public Produto() {

    }

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

// #endregion
}

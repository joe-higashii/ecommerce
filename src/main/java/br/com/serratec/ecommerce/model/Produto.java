package br.com.serratec.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Long produtoId;

    @Column(nullable = false, unique = true)
    private String codProduto;

    @Column(nullable = false)
    private String NomeProduto;

    @Column(nullable = false)
    private int quantidadeEstoque;

    @Column(nullable = false)
    private double precoVenda;

    private String obs;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonBackReference
    private Categoria categoria;

    @OneToOne(mappedBy = "produto")
    @JsonManagedReference
    private ProdutoImagem produtoImagem;

    // #region Constructors

    public Produto(Long produtoId, String codProduto, String nomeProduto, int quantidadeEstoque, double precoVenda,
            String obs, boolean ativo, Categoria categoria) {
        this.produtoId = produtoId;
        this.codProduto = codProduto;
        this.NomeProduto = nomeProduto;
        this.quantidadeEstoque = quantidadeEstoque;
        this.precoVenda = precoVenda;
        this.obs = obs;
        this.ativo = ativo;
        this.categoria = categoria;
    }

    public Produto() {
    }

    // #region Getters and Setters

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getNomeProduto() {
        return NomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        NomeProduto = nomeProduto;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
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

    public ProdutoImagem getProdutoImagem() {
        return produtoImagem;
    }

    public void setProdutoImagem(ProdutoImagem produtoImagem) {
        this.produtoImagem = produtoImagem;
    }
    // #endregion
}

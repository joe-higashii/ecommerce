package br.com.serratec.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;

    @Column(nullable = false, unique = true)
    private String codCat;

    @Column(nullable = false)
    private String descricao;
    
    @OneToMany(mappedBy = "categoria")
    @JsonBackReference
    private List<Produto> produtos;

    private boolean ativo;

    public Categoria(Long categoriaId, String codCat, String descricao, boolean ativo) {
        this.categoriaId = categoriaId;
        this.codCat = codCat;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public Categoria() {}

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCodCat() {
        return codCat;
    }

    public void setCodCat(String codCat) {
        this.codCat = codCat;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    } 
    // #endregion
}

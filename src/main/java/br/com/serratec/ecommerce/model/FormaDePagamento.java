package br.com.serratec.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FormaDePagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pgtId;

    @Column(nullable = false, unique = true)
    private String codPgt;

    @Column(nullable = false)
    private String descricao;
    
    private boolean ativo;

    // #region Constructors

    public FormaDePagamento(Long pgtId, String codPgt, String descricao, boolean ativo) {
        this.pgtId = pgtId;
        this.codPgt = codPgt;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public FormaDePagamento() {

    }

    // #region Getters and Setters

    public Long getPgtId() {
        return pgtId;
    }

    public void setPgtId(Long pgtId) {
        this.pgtId = pgtId;
    }

    public String getCodPgt() {
        return codPgt;
    }

    public void setCodPgt(String codPgt) {
        this.codPgt = codPgt;
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

    // #endregion

}

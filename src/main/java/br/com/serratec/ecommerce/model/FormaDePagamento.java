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
    @Column(name = "forma_pagamento_id")
    private Long pgtId;

    @Column(nullable = false, unique = true)
    private String codPagamento;

    @Column(nullable = false)
    private String descricao;

    private boolean ativo;

    public FormaDePagamento(Long pgtId, String codPagamento, String descricao, boolean ativo) {
        this.pgtId = pgtId;
        this.codPagamento = codPagamento;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public FormaDePagamento() {}

    public Long getPgtId() {
        return pgtId;
    }

    public void setPgtId(Long pgtId) {
        this.pgtId = pgtId;
    }

    public String getCodPgt() {
        return codPagamento;
    }

    public void setCodPgt(String codPagamento) {
        this.codPagamento = codPagamento;
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
}

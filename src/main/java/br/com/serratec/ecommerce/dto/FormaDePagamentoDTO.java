package br.com.serratec.ecommerce.dto;

public class FormaDePagamentoDTO {
    private Long pgtId;
    private String codPagamento;
    private String descricao;
    private boolean ativo;

    // #region Constructors

    public FormaDePagamentoDTO(Long pgtId, String codPagamento, String descricao, boolean ativo) {
        this.pgtId = pgtId;
        this.codPagamento = codPagamento;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    // #region Getters and Setters

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

    // #endregion
}
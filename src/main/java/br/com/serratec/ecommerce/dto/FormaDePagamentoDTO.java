package br.com.serratec.ecommerce.dto;

public class FormaDePagamentoDTO {
    private Long pgtId;
    private String codPgt;
    private String descricao;
    private boolean ativo;

    // #region Constructors

    public FormaDePagamentoDTO(Long pgtId, String codPgt, String descricao, boolean ativo) {
        this.pgtId = pgtId;
        this.codPgt = codPgt;
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
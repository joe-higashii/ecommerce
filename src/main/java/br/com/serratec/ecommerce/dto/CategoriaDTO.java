package br.com.serratec.ecommerce.dto;

public class CategoriaDTO {
    private Long categoriaId;
    private String codCategoria;
    private String descricao;

    private boolean ativo;

    // #region Constructors

    public CategoriaDTO(Long categoriaId, String codCategoria, String descricao, boolean ativo) {
        this.categoriaId = categoriaId;
        this.codCategoria = codCategoria;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    // #region Getters and Setters

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCodCat() {
        return codCategoria;
    }

    public void setCodCat(String codCategoria) {
        this.codCategoria= codCategoria;
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
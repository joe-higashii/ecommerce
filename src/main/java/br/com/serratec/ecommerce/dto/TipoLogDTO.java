package br.com.serratec.ecommerce.dto;

public class TipoLogDTO {
    private Long tipoLogId;
    private String descricao;

    // #region Constructors

    public TipoLogDTO(Long tipoLogId, String descricao) {
        this.tipoLogId = tipoLogId;
        this.descricao = descricao;
    }

    // #region Getters and Setters

    public Long getTipoLogId() {
        return tipoLogId;
    }

    public void setTipoLogId(Long tipoLogId) {
        this.tipoLogId = tipoLogId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // #endregion

}
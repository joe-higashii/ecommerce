package br.com.serratec.ecommerce.newDTO.tipoLog;

public abstract class TipoLogBaseDTO {

    private Long tipoLogId;
    private String descricao;

//#region Getter's and Setter's   
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
//#endregion
}

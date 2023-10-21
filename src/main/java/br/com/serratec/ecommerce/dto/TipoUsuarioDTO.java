package br.com.serratec.ecommerce.dto;

public class TipoUsuarioDTO {
    private Long tipoUsuarioId;
    private String tipoUsu;

    // #region Constructors

    public TipoUsuarioDTO(Long tipoUsuarioId, String tipoUsu) {
        this.tipoUsuarioId = tipoUsuarioId;
        this.tipoUsu = tipoUsu;
    }

    // #region Getters and Setters

    public Long getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(Long tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public String getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(String tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

    // #endregion
}
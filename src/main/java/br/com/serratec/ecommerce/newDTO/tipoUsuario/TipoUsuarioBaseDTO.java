package br.com.serratec.ecommerce.newDTO.tipoUsuario;

public abstract class TipoUsuarioBaseDTO {

    private Long tipoUsuarioId;
    private String tipoUsu;

//#region Getter's and Setter's   
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
//#endregion
}

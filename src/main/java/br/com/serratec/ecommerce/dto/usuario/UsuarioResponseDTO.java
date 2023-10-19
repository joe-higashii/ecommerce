package br.com.serratec.ecommerce.dto.usuario;


public class UsuarioResponseDTO extends UsuarioRequestDTO {

    private long usuarioId;
    private boolean ativo;

//#region Getter's and Setter's    

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
//#endregion
}

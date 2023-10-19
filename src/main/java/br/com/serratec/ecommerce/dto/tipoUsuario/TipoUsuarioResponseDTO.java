package br.com.serratec.ecommerce.dto.tipoUsuario;

public class TipoUsuarioResponseDTO extends TipoUsuarioRequestDTO {
    
    private Long tipoUsuarioId;

    public Long getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(Long tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }
}

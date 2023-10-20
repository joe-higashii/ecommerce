package br.com.serratec.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_usuario_id")
    private Long tipoUsuarioId;

    @Column(nullable = false)
    private String tipoUsuario;

    public TipoUsuario(Long tipoUsuarioId, String tipoUsuario) {
        this.tipoUsuarioId = tipoUsuarioId;
        this.tipoUsuario = tipoUsuario;
    }

    public TipoUsuario() {}

    public Long getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(Long tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public String getTipoUsu() {
        return tipoUsuario;
    }

    public void setTipoUsu(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
//#endregion
}

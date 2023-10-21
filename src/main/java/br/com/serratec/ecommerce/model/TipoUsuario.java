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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(tipoUsuario);
    }

    public boolean isCliente() {
        return "cliente".equalsIgnoreCase(tipoUsuario);
    }
//#endregion
}

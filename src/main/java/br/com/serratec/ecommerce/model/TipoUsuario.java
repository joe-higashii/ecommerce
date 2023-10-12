package br.com.serratec.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoUsuarioId;
    private String tipoUsu;

    // #region Constructors

    public TipoUsuario(Long tipoUsuarioId, String tipoUsu) {
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

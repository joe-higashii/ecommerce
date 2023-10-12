package br.com.serratec.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoLogId;
    private String descricao;

    // #region Constructors

    public TipoLog(Long tipoLogId, String descricao) {
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

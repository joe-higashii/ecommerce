package br.com.serratec.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoLogId;

    @Column(nullable = false)
    private String descricao;

    public TipoLog(Long tipoLogId, String descricao) {
        this.tipoLogId = tipoLogId;
        this.descricao = descricao;
    }

    public TipoLog() {}

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

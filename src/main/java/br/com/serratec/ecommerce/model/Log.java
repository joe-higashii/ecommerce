package br.com.serratec.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;
    @Column(nullable = false)
    private String tabela;
    @Column(nullable = false)
    private String tipoLogId;
    @Column(nullable = false)
    private Date dataAlteracao;
    @Column(nullable = false)
    private String vlOrig;
    @Column(nullable = false)
    private String vlAtual;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    public Log(Long logId, String tabela, String tipoLogId, Date dataAlteracao, String vlOrig, String vlAtual,
            Usuario usuario) {
        this.logId = logId;
        this.tabela = tabela;
        this.tipoLogId = tipoLogId;
        this.dataAlteracao = dataAlteracao;
        this.vlOrig = vlOrig;
        this.vlAtual = vlAtual;
        this.usuario = usuario;
    }

    // #region Getters and Setters
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getTipoLogId() {
        return tipoLogId;
    }

    public void setTipoLogId(String tipoLogId) {
        this.tipoLogId = tipoLogId;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getVlOrig() {
        return vlOrig;
    }

    public void setVlOrig(String vlOrig) {
        this.vlOrig = vlOrig;
    }

    public String getVlAtual() {
        return vlAtual;
    }

    public void setVlAtual(String vlAtual) {
        this.vlAtual = vlAtual;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }
    // #endregion
}

package br.com.serratec.ecommerce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;
    private String tipoLogId;
    private Date dataAlteracao;
    private double vlOrig;
    private double vlAtual;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // #region Constructors

    public Log(Long logId, String tipoLogId, Date dataAlteracao, double vlOrig, double vlAtual, Usuario usuario) {
        this.logId = logId;
        this.tipoLogId = tipoLogId;
        this.dataAlteracao = dataAlteracao;
        this.vlOrig = vlOrig;
        this.vlAtual = vlAtual;
        this.usuario = usuario;
    }

    public Log() {

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

    public double getVlOrig() {
        return vlOrig;
    }

    public void setVlOrig(double vlOrig) {
        this.vlOrig = vlOrig;
    }

    public double getVlAtual() {
        return vlAtual;
    }

    public void setVlAtual(double vlAtual) {
        this.vlAtual = vlAtual;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // #endregion

}

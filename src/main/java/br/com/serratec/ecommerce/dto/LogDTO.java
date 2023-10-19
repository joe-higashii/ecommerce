package br.com.serratec.ecommerce.dto;

import java.util.Date;

public class LogDTO {
    private Long logId;
    private String tipoLogId;
    private Date dataAlteracao;
    private double vlOrig;
    private double vlAtual;
    private UsuarioDTO usuario;

    // #region Constructors

    public LogDTO(Long logId, String tipoLogId, Date dataAlteracao, double vlOrig, double vlAtual, UsuarioDTO usuario) {
        this.logId = logId;
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

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    // #endregion

}
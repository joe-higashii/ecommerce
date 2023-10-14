package br.com.serratec.ecommerce.newDTO.log;

import java.util.Date;

import br.com.serratec.ecommerce.model.Usuario;

public abstract class LogBaseDTO {
    
    private Long logId;
    private String tipoLogId;
    private Date dataAlteracao;
    private double vlOrig;
    private double vlAtual;
    private Usuario usuario;

//#region Getter's and Setter's    

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

//#endregion
}

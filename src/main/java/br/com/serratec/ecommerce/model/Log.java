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
    private String acao;

    private String vlOrig;

    @Column(nullable = false)
    private String vlAtual;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false)
    private Date dataAlteracao;

    public Log(String tabela, String acao, String vlOrig, String vlAtual, Usuario usuario, Date dataAlteracao) {
        this.tabela = tabela;
        this.acao = acao;
        this.vlOrig = vlOrig;
        this.vlAtual = vlAtual;
        this.usuario = usuario;
        this.dataAlteracao = new Date();
    }

// #region Getters and Setters

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
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

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
// #endregion
}

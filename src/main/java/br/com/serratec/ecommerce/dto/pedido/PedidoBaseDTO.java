package br.com.serratec.ecommerce.dto.pedido;

import java.util.Date;

import br.com.serratec.ecommerce.model.FormaDePagamento;

public abstract class PedidoBaseDTO {

    private Long id;
    private String nrPedido;
    private Date dtPedido;
    private double vlTotal;
    private double descTotal;
    private double acresTotal;
    private String obs;
    private boolean cancelado;
    private FormaDePagamento formaDePagamento;

    // #region Getter's and Setter's

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNrPedido() {
        return nrPedido;
    }

    public void setNrPedido(String nrPedido) {
        this.nrPedido = nrPedido;
    }

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }

    public double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(double vlTotal) {
        this.vlTotal = vlTotal;
    }

    public double getDescTotal() {
        return descTotal;
    }

    public void setDescTotal(double descTotal) {
        this.descTotal = descTotal;
    }

    public double getAcresTotal() {
        return acresTotal;
    }

    public void setAcresTotal(double acresTotal) {
        this.acresTotal = acresTotal;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }
    // #endregion
}

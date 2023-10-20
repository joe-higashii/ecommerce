package br.com.serratec.ecommerce.dto;

import java.util.Date;
import java.util.List;

public class PedidoDTO {
    private Long pedidoId;
    private String nrPedido;
    private Date dtPedido;
    private double vlTotal;
    private double descTotal;
    private double acresTotal;
    private String obs;
    private boolean cancelado;
    private FormaDePagamentoDTO formaPagamento;
    private UsuarioDTO usuario;
    private List<PedidoItemDTO> itens;

    // #region Constructors

    public PedidoDTO(Long pedidoId, String nrPedido, Date dtPedido, double vlTotal, double descTotal, double acresTotal,
            String obs, boolean cancelado, FormaDePagamentoDTO formaPagamento, UsuarioDTO usuario,
            List<PedidoItemDTO> itens) {
        this.pedidoId = pedidoId;
        this.nrPedido = nrPedido;
        this.dtPedido = dtPedido;
        this.vlTotal = vlTotal;
        this.descTotal = descTotal;
        this.acresTotal = acresTotal;
        this.obs = obs;
        this.cancelado = cancelado;
        this.formaPagamento = formaPagamento;
        this.usuario = usuario;
        this.itens = itens;
    }

    // #region Getters and Setters

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
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

    public FormaDePagamentoDTO getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaDePagamentoDTO formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<PedidoItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItemDTO> itens) {
        this.itens = itens;
    }

    // #endregion
}
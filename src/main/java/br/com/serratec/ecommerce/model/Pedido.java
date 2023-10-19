package br.com.serratec.ecommerce.model;

import java.util.Date;
import java.util.List;

// import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(nullable = false, unique = true)
    private String nrPedido;

    @Column(nullable = false)
    private Date dtPedido;

    private double vlTotal;
    private double descTotal;
    private double acresTotal;
    private String obs;
    private boolean cancelado;

    // @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id")
    private FormaDePagamento formaPagamento;

    // @JsonManagedReference
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonManagedReference
    // @JsonBackReference
    @OneToMany(mappedBy = "pedido")
    private List<PedidoItem> itens;

    public Pedido(Long pedidoId, String nrPedido, Date dtPedido, double vlTotal, double descTotal, double acresTotal,
            String obs, boolean cancelado, FormaDePagamento formaPagamento, Usuario usuario, List<PedidoItem> itens) {
        this.pedidoId = pedidoId;
        this.nrPedido = nrPedido;
        this.dtPedido = new Date();
        this.vlTotal = vlTotal;
        this.descTotal = descTotal;
        this.acresTotal = acresTotal;
        this.obs = obs;
        this.cancelado = cancelado;
        this.formaPagamento = formaPagamento;
        this.usuario = usuario;
        this.itens = itens;
    }

    public Pedido() {
        this.dtPedido = new Date();
    }

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

    public FormaDePagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaDePagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItem> itens) {
        this.itens = itens;
    }

    // #endregion
}

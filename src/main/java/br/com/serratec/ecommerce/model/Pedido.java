package br.com.serratec.ecommerce.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(nullable = false, unique = true)
    private String codPedido;

    @Column(nullable = false)
    private Date dataPedido;

    private double valorTotal;
    private double desconto;
    private double acrescimo;
    private String obs;
    private boolean cancelado;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id")
    private FormaDePagamento formaPagamento;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonManagedReference
    @OneToMany(mappedBy = "pedido")
    private List<PedidoItem> itens;

    public Pedido(Long pedidoId, String codPedido, Date dataPedido, double valorTotal, double desconto,    double acrescimo, String obs, boolean cancelado, FormaDePagamento formaPagamento, Usuario usuario, List<PedidoItem> itens
    ) {
        this.pedidoId = pedidoId;
        this.codPedido = codPedido;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
        this.acrescimo = acrescimo;
        this.obs = obs;
        this.cancelado = cancelado;
        this.formaPagamento = formaPagamento;
        this.usuario = usuario;
        this.itens = itens;
    }

    public Pedido() {}

//#region Getter's and Setter's  

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(String codPedido) {
        this.codPedido = codPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(double acrescimo) {
        this.acrescimo = acrescimo;
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

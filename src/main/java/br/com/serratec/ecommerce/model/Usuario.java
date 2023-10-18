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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private long usuarioId;

    @Column(nullable = false)
    private String codUsu;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private boolean ativo;

    @Column(nullable = false)
    private Date dtCadastro;

    @JsonManagedReference
    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "tipo_usuario_id")
    private TipoUsuario tipoUsuario;

    @JsonManagedReference
    // @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    public Usuario(long usuarioId, String codUsu, String nome, String email, String senha, String telefone,
            boolean ativo, Date dtCadastro, TipoUsuario tipoUsuario, List<Pedido> pedidos) {
        this.usuarioId = usuarioId;
        this.codUsu = codUsu;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.ativo = ativo;
        this.dtCadastro = new Date();
        this.tipoUsuario = tipoUsuario;
        this.pedidos = pedidos;
    }

    public Usuario() {
        this.dtCadastro = new Date();
    }

// #region Getters and Setters

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
// #endregion
}

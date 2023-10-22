package br.com.serratec.ecommerce.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
// import java.util.stream.Collectors;

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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

    // #region propriedades
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "usuario_id")
    private long usuarioId;

    @Column(nullable = false)
    private String codUsuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private Date dtCadastro;

    
    @Column(nullable = false)
    private boolean ativo;

    // @JsonManagedReference
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "tipo_usuario_id")
    private TipoUsuario tipoUsuario;

    @JsonManagedReference
    //@JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private transient List<Pedido> pedidos;

    public Usuario(long usuarioId, String codUsuario, String nome, String email, String senha, String telefone,
            boolean ativo, Date dtCadastro, TipoUsuario tipoUsuario, List<Pedido> pedidos) {
        this.usuarioId = usuarioId;
        this.codUsuario = codUsuario;
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
        return codUsuario;
    }

    public void setCodUsu(String codUsuario) {
        this.codUsuario = codUsuario;
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

    public boolean isAdmin() {
        return tipoUsuario != null && tipoUsuario.isAdmin();
    }

    public boolean isCliente() {
        return tipoUsuario != null && "cliente".equalsIgnoreCase(tipoUsuario.getTipoUsuario());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // Adicione a role 'USER' para todos os usuários
    
        if (isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // Adicione a role 'ADMIN' se o usuário for 'admin'
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE")); // Adicione a role 'CLIENTE' se o usuário não for 'admin'
        }
    
        return authorities;
    }  

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { // essa conta não expira?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // essa conta não pode ser bloqueada?
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // essa autorização não expira?
        return true;
    }

    @Override
    public boolean isEnabled() { // esta conta está ativa?
        return true;
    }

    // #endregion
}

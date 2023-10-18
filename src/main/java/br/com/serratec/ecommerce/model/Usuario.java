package br.com.serratec.ecommerce.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

    // #region propriedades
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarioId")
    private Long usuarioId;

    @Column(nullable = false, unique = true)
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

    // @ManyToOne
    // @JoinColumn(name = "tipoUsuarioid")
    @Column(nullable = false)
    private TipoUsuario tipoUsuario;

    // #region Constructors

    public Usuario(Long usuarioId, String codUsu, String nome, String email, String senha, String telefone,
            boolean ativo, Date dtCadastro, TipoUsuario tipoUsuario) {
        this.usuarioId = usuarioId;
        this.codUsu = codUsu;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.ativo = ativo;
        this.dtCadastro = new Date();
        this.tipoUsuario = tipoUsuario;
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

    // #region UserDetails

    // Daqui pra baixo é implementação do UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

package br.com.serratec.ecommerce.dto;

import java.util.Date;

public class UsuarioDTO {
    private Long usuarioId;
    private String codUsu;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private boolean ativo;
    private Date dtCadastro;
    private TipoUsuarioDTO tipoUsuario;

    // #region Constructors

    public UsuarioDTO(Long usuarioId, String codUsu, String nome, String email, String senha, String telefone,
            boolean ativo, Date dtCadastro, TipoUsuarioDTO tipoUsuario) {
        this.usuarioId = usuarioId;
        this.codUsu = codUsu;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.ativo = ativo;
        this.dtCadastro = dtCadastro;
        this.tipoUsuario = tipoUsuario;
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

    public TipoUsuarioDTO getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioDTO tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    // #endregion
}
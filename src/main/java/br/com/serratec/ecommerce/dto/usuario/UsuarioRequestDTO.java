package br.com.serratec.ecommerce.dto.usuario;

import java.util.List;

import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.TipoUsuario;

public class UsuarioRequestDTO {
    
    private String codUsuario;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private TipoUsuario tipoUsuario;
    private List<Pedido> pedidos;

// #region Getter's and Setter's

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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
// #endregion
}

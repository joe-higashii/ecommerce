package br.com.serratec.ecommerce.dto.usuario;

import java.util.List;

import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.TipoUsuario;

public class UsuarioResponseDTO {

    private long usuarioId;
    private String codUsu;
    private String nome;
    private String email;
    private String telefone;
    private TipoUsuario tipoUsuario;
    private List<Pedido> pedidos;

//#region Getter's and Setter's    

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
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
//#endregion
}

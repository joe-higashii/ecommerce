package br.com.serratec.ecommerce.dto.usuario;

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;

public class UsuarioRequestDTO extends UsuarioBaseDTO {

    private String codUsu;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private boolean ativo;
    
//#region Getter's and Setter's      
    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

public class UsuarioRequestDTO extends UsuarioBaseDTO {

    private PedidoRequestDTO pedido;

    public PedidoRequestDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoRequestDTO pedido) {
        this.pedido = pedido;
    }
}

package br.com.serratec.ecommerce.dto.pedido;

import java.util.List;

import br.com.serratec.ecommerce.dto.pedidoItem.PedidoItemRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;

public class PedidoRequestDTO extends PedidoBaseDTO {

    private List<PedidoItemRequestDTO> itens;
    private UsuarioRequestDTO usuario;

// #region Getter's and Setter's

    public List<PedidoItemRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItemRequestDTO> itens) {
        this.itens = itens;
    }

    public UsuarioRequestDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioRequestDTO usuario) {
        this.usuario = usuario;
    }

// #endregion
}

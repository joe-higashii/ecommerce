package br.com.serratec.ecommerce.dto.produto;

import br.com.serratec.ecommerce.model.ProdutoImagem;

public class ProdutoResponseDTO extends ProdutoRequestDTO {

    private Long produtoId;
    private boolean ativo;
    private ProdutoImagem produtoImagem;

    // #region Getter's and Setter's

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public ProdutoImagem getProdutoImagem() {
        return produtoImagem;
    }

    public void setProdutoImagem(ProdutoImagem produtoImagem) {
        this.produtoImagem = produtoImagem;
    }
    // #endregion
}

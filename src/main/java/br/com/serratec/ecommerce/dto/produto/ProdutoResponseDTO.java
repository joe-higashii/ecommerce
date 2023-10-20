package br.com.serratec.ecommerce.dto.produto;

public class ProdutoResponseDTO extends ProdutoRequestDTO {
    
    private Long produtoId;
    private boolean ativo;

//#region Getter's and Setter's  

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
//#endregion
}

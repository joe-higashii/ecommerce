package br.com.serratec.ecommerce.dto.categoria;

import java.util.List;

import br.com.serratec.ecommerce.model.Produto;

public class CategoriaRequestDTO {

    private String codCat;
    private String descricao; 
    private List<Produto> produtos;

//#region Getter's and Setter's 

    public String getCodCat() {
        return codCat;
    }

    public void setCodCat(String codCat) {
        this.codCat = codCat;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
//#endregion
}

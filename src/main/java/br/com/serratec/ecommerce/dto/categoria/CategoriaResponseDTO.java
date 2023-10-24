package br.com.serratec.ecommerce.dto.categoria;

public class CategoriaResponseDTO extends CategoriaRequestDTO {

    private Long categoriaId;
    private boolean ativo;

 //#region Getter's and Setter's 

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
//#endregion
}

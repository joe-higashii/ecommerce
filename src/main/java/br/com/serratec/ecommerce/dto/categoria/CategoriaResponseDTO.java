package br.com.serratec.ecommerce.dto.categoria;

public class CategoriaResponseDTO extends CategoriaRequestDTO {

    private Long categoriaId;

 //#region Getter's and Setter's 

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
//#endregion
}

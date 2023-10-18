package br.com.serratec.ecommerce.dto.categoria;

public class CategoriaResponseDTO {

    private Long categoriaId;
    private String codCat;
    private String descricao;

    public String getCodCat() {
        return codCat;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
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
}

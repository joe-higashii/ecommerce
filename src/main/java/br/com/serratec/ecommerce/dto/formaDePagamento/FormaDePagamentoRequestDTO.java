package br.com.serratec.ecommerce.dto.formaDePagamento;

public class FormaDePagamentoRequestDTO {

    private String codPagamento;
    private String descricao;


//#region Getter's and Setter's

    public String getCodPgt() {
        return codPagamento;
    }

    public void setCodPgt(String codPagamento) {
        this.codPagamento = codPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
//#endregion
}

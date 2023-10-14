package br.com.serratec.ecommerce.newDTO.formaDePagamento;

public abstract class FormaDePagamentoBaseDTO {
    
    private Long pgtId;
    private String codPgt;
    private String descricao;
    private boolean ativo;

//#region Getter's and Setter's

    public Long getPgtId() {
        return pgtId;
    }

    public void setPgtId(Long pgtId) {
        this.pgtId = pgtId;
    }

    public String getCodPgt() {
        return codPgt;
    }

    public void setCodPgt(String codPgt) {
        this.codPgt = codPgt;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
//#endregion    
}

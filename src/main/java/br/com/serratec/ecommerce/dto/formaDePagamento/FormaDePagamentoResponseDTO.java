package br.com.serratec.ecommerce.dto.formaDePagamento;

public class FormaDePagamentoResponseDTO extends FormaDePagamentoRequestDTO {

    private Long PagamentoId;
    private boolean ativo;

    public Long getPagamentoId() {
        return PagamentoId;
    }

    public void setPagamentoId(Long getPagamentoId) {
        this.PagamentoId = getPagamentoId;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}

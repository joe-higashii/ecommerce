package br.com.serratec.ecommerce.dto.formaDePagamento;

public class FormaDePagamentoResponseDTO extends FormaDePagamentoRequestDTO {

    private Long PagamentoId;

    public Long getPagamentoId() {
        return PagamentoId;
    }

    public void setPagamentoId(Long getPagamentoId) {
        this.PagamentoId = getPagamentoId;
    }
}

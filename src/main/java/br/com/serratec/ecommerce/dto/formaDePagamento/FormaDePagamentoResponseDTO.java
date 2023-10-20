package br.com.serratec.ecommerce.dto.formaDePagamento;

public class FormaDePagamentoResponseDTO extends FormaDePagamentoRequestDTO {

    private Long pgtId;

    public Long getPgtId() {
        return pgtId;
    }

    public void setPgtId(Long pgtId) {
        this.pgtId = pgtId;
    }
}

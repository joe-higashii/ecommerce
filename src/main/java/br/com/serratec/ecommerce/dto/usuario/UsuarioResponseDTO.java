package br.com.serratec.ecommerce.dto.usuario;

import java.util.Date;

public class UsuarioResponseDTO extends UsuarioBaseDTO {

    private Date dtCadastro;

    public Date getDataCadastro() {
        return dtCadastro;
    }

    public void setDataCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }
}

package br.com.serratec.ecommerce.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorDataHora {

    public static String converterDateParaDataHora(Date data) {
        return new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(data);
    }
}
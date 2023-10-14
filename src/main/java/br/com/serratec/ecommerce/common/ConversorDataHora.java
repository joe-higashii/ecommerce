package br.com.serratec.ecommerce.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorDataHora {

    public static String converterDateParaDataHora(Date data) {
        return new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(data);
    }
}

// 03/10/2023 20:59:32
// 2023-10-03T20:59:23
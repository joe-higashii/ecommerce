package br.com.serratec.ecommerce.model;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class EmailHtmlConteudo {
    
    public void salvarConteudoHtml(String conteudoHtml) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/templates/index.html"))) {
            writer.write(conteudoHtml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package br.com.serratec.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtoImagem")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoImagem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long produtoImagemId;

    private String name;

    private String tipo;
    @Column(name = "produtoImagem", length = 10000000)
    private String produtoImagem;

    @OneToOne
    @JoinColumn(name = "produto_id")
    @JsonBackReference
    private Produto produto;
}

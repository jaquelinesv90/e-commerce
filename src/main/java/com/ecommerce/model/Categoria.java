package com.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="categoria")
public class Categoria {

    /*@GeneratedValue(strategy = GenerationType.TABLE)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tabela")
    @TableGenerator(name = "tabela", table = "hibernate_sequences",
            pkColumnName = "sequence_name",
            pkColumnValue = "categoria",
            valueColumnName = "next_val",
            initialValue = 0,
            allocationSize = 50)*/

    //@GeneratedValue(strategy = GenerationType.AUTO)
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    //@SequenceGenerator(name= "seq", sequenceName="sequencia_chave_primaria",initialValue=10)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    //Exemplo de auto-relacionamento
    @ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPaiId;

    @OneToMany(mappedBy = "categoriaPai") //no mappedBy a gente coloca o atributo da classe que está na lista
    private List<Categoria> categorias;  // onde o JPA vai buscar os metadados

    //mapeando a volta do relacionamento com produto
    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;
}

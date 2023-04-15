package com.ecommerce.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.ecommerce.listener.GenericoListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners({GenericoListener.class})
@Entity
@Table(name="Produto")
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable  = false)
    private LocalDateTime dataUltimaAtualizacao;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    // Produto é o owner da relação
    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    //mapeamento de lista de tipo básico
    // as tags são guardadas em uma tabela, é possivle customizar
    // essa tabela com a anotação @CollectionTable
    @ElementCollection
    @CollectionTable(name="produto_tag",
                joinColumns = @JoinColumn(name="produto_id"))
    @Column(name="tag")
    private List<String> tags;


    //mapeamento de um embeddable- classe embutida
    @ElementCollection
    @CollectionTable(name = "produto_atributo",
            joinColumns=@JoinColumn(name="produto_id"))
    private List<Atributo> atributos;


}

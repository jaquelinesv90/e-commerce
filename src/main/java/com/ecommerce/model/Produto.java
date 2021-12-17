package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="Produto")
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal preco;


}

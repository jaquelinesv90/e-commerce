package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categoria {

    @Id
    private Integer id;

    private String nome;

    private Integer categoriaPaiId;


}

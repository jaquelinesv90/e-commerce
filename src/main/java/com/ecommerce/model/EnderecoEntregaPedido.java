package com.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

// Objeto embutido - esta classe é embutivel em outra classe - embutir em nossas entidades
// tipos que não são entidades
@Getter
@Setter
@Embeddable
public class EnderecoEntregaPedido {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;


}

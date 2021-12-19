package com.ecommerce.model;

import javax.persistence.Embeddable;

// Objeto embutido - esta classe é embutivel em outra classe
@Embeddable
public class EnderecoEntregaPedido {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;


}

package com.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "pedido_id")
    private Integer pedidoId;

    @ManyToOne
    @JoinColumn(name ="produtoId_id")
    private Produto produtoId;

    @Column(name="preco_produto")
    private BigDecimal precoProduto;

    private Integer quantidade;


}

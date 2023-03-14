package com.ecommerce.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

// representa o identificador do nosso item_pedido
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoId implements Serializable {

    // anotação do lombok
    @EqualsAndHashCode.Include
    private Integer pedidoId;

    @EqualsAndHashCode.Include
    private Integer produtoId;
}

package com.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="pedido")
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // o JoinColumn não é obrigatório
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente clienteId;

    @Column(name="data_pedido")
    private LocalDateTime dataPedido;

    @Column(name="data_conclusao")
    private LocalDateTime dataConclusao;

    @Column(name="nota_fiscal_id")
    private Integer notaFiscalId;

    private BigDecimal total;

    private StatusPedido status;

    // as colunas que estão nesta classe(EnderecoEntregaPedido) fazem parte da tabela pedido
    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

}

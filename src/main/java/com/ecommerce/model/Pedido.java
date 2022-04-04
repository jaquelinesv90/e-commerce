package com.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @Column(name="data_pedido")
    private LocalDateTime dataPedido;

    @Column(name="data_conclusao")
    private LocalDateTime dataConclusao;

    @Column(name="nota_fiscal_id")
    private Integer notaFiscalId;

    private BigDecimal total;

    private StatusPedido status;

    //mapeando a volta, pagamentoCartao é owner da relação(não é obrigatório,mapear a volta)
    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamento;

    // as colunas que estão nesta classe(EnderecoEntregaPedido) fazem parte da tabela pedido
    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

}

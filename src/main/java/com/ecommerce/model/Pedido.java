package com.ecommerce.model;

import com.ecommerce.listener.GenericoListener;
import com.ecommerce.listener.GerarNotaFiscalListener;
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
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
@Entity
@Table(name="pedido")
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*  o JoinColumn não é obrigatório
        optional = false, se toda vez que eu for salvar
        um pedido eu preciso de um cliente eu adiciono esta
        anotação.
     */
    @ManyToOne(optional=false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @Column(name="data_criação")
    private LocalDateTime dataCriacao;

    @Column(name="data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name="data_conclusao")
    private LocalDateTime dataConclusao;

    //mapeando a volta, notaFiscal é o owner da relação
    @OneToOne(mappedBy="pedido")
    private NotaFiscal notaFiscal;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    //mapeando a volta, pagamentoCartao é owner da relação(não é obrigatório,mapear a volta)
    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamento;

    // as colunas que estão nesta classe(EnderecoEntregaPedido) fazem parte da tabela pedido
    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    public boolean isPago(){
        return StatusPedido.PAGO.equals(status);
    }

    //@PrePersist
    //@PreUpdate
    public void calcularTotal(){
        if(itens != null){
            total = itens.stream().map(ItemPedido::getPrecoProduto)
                    .reduce(BigDecimal.ZERO,BigDecimal::add);
        }
    }
    @PrePersist
    public void aoPersistir(){
        dataCriacao = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar(){
        dataUltimaAtualizacao = LocalDateTime.now();
        calcularTotal();
    }

}
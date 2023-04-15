package com.ecommerce.mapeamentoavancado;

import com.ecommerce.EntityManagerTest;
import com.ecommerce.model.Atributo;
import com.ecommerce.model.Cliente;
import com.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class ElementCollection extends EntityManagerTest {

    @Test
    public void aplicarTags(){
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class,1);
        produto.setTags(Arrays.asList("ebook","livro-digital"));
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificação = entityManager.find(Produto.class, produto.getId());
    }

    @Test
    public void aplicarAtributos(){
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class,1);
        produto.setAtributos(Arrays.asList(new Atributo("tela","320x600")));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificação = entityManager.find(Produto.class, produto.getId());
    }

    @Test
    public void aplicarContato(){
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class,1);
        cliente.setContatos(Collections.singletonMap("email","fernando@email.com"));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals("fernando@email.com",clienteVerificacao.getContatos().get("email"));
    }
}

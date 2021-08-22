package com.ecommerce.iniciandocomjpa;

import com.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest{

    @Test
    public void inserirOPrimeiroObjeto(){
        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Camera Canon");
        produto.setDescricao("A melhor definicao");
        produto.setPreco(new BigDecimal(5000));

        //abrindo a transação
        entityManager.getTransaction().begin();
        //quando a gente chama o persist ele joga o objeto na memória para gerenciar
        entityManager.persist(produto);

        entityManager.getTransaction().commit();
        //limpando a memória
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class,produto.getId());
        Assert.assertNotNull(produtoVerificacao);
    }

    @Test
    public void removerObjeto(){
        //é necessário buscar o objeto na base antes da exclusão,pois,
        //se somente criar uma instancia de objeto e tentar excluir vai
        //gerar um erro dizendo que o objeto não é detached.
        // Produto produto = new Produto();
        //logo depois que o método find for executado ele vai montar nossa
        // entidade jogar para a memória do entityManager(para poder ser gerenciado)
        Produto produto = entityManager.find(Produto.class,3);
        produto.setId(3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();
        // o entityManager remove da base de dados e da memória dele.
        // entityManager.clear(); Não é necessario na assercao para operacao de remocao
        Produto produtoVerificacao = entityManager.find(Produto.class,3);
        Assert.assertNull(produtoVerificacao);
    }

    @Test
    public void atualizarObjeto(){
        // ESTA É APENAS UMA FORMA DE FAZER ATUALIZAÇÃO COM MERGE
        //Todos os atributos do objeto tem que ser preenchidos, para que a atualização
        //seja coerente.Caso eu queira atualizar somente por exemplo o nome, e não colocar
        //valor nos outros campos o entityManager vai entender que vc quer deixar esses
        // campos não preenchidos como nulo.Para resolver isso ou o usuario passa os valores(os mesmos valores)
        //e voce atribui nos outros campos ou busca da base de dados pelo id.
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("kindle paperwhite");
        produto.setDescricao("Conheça o novo Kindle");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        //o clear aqui é necessário porque a instancia de produto(uma cópia) vai ser colocada
        // na memória do entityManager(para ele gerenciar)
        entityManager.clear();

        // se a gente executar o find sem fazer o clear ele vai executar o find e pegar
        // da memória, como a gente quer pegar da base de dados, então a gente executa o clear
        Produto produtoVerificacao = entityManager.find(Produto.class,3);
        Assert.assertEquals("Kindle paperWhite",produtoVerificacao.getNome());

    }

    @Test
    public void atualizarObjetoGerenciado(){
        //Quando eu faço o find ele busca na base e constrói a instancia
        // do produto,coloca como objeto gerenciado na memória do
        // entityManager e depois ele retorna e atribui para nossa variável.
        Produto produto = entityManager.find(Produto.class,1);

        entityManager.getTransaction().begin();
        produto.setNome("kindle paperwhite 2 Geracao");
        entityManager.getTransaction().commit();
    }

    @Test
    public void inserirObjetoComMerge(){
        Produto produto = new Produto();

        produto.setId(4);
        produto.setNome("Microfone Rode Videmic");
        produto.setDescricao("A melhor qualidade de som");
        produto.setPreco(new BigDecimal(1000));

        //abrindo a transação
        entityManager.getTransaction().begin();
        //Aqui a gente vai utilizar o método merge para persistir
        entityManager.merge(produto);

        entityManager.getTransaction().commit();
        //limpando a memória
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class,produto.getId());
        Assert.assertNotNull(produtoVerificacao);
    }


    /* persiste: somente para persistir não é possível atualizar, outra coisa é ele
        pega a instancia da entidade'ali produtoMerge', o valor dela e coloca na memória
        do entityManager, para ela ser gerenciada.
    */

    /* merge: persiste e atualiza, outra coisa ele pega a instancia 'produtoMerge' e
       a cópia ele joga para o entityManager gerenciar.
     */
    @Test
    public void mostrarDiferencaPersistMerge(){
        Produto produtoPersist = new Produto();

        produtoPersist.setId(5);
        produtoPersist.setNome("Smartphone One Plus");
        produtoPersist.setDescricao("O processador mais rápido");
        produtoPersist.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPersist);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Produto produtoVerificaoPersist = entityManager.find(Produto.class,produtoPersist.getId());
        Assert.assertNotNull(produtoVerificaoPersist);


        Produto produtoMerge = new Produto();

        produtoMerge.setId(5);
        produtoMerge.setNome("Notebook Dell");
        produtoMerge.setDescricao("O melhor da Categoria");
        produtoMerge.setPreco(new BigDecimal(2000));

        // adicionar essa linha, pois ele devolve a cópia como retorno da chamada dele.
        produtoMerge = entityManager.merge(produtoMerge);
        // setar o nome não funcionará pois esta instancia não é gerenciada, não faz efeito essa alteração
        produtoMerge.setNome("Notebook Dell 2");

        entityManager.getTransaction().begin();
        entityManager.merge(produtoMerge);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacaoMerge = entityManager.find(Produto.class,produtoMerge.getId());
        Assert.assertNotNull(produtoVerificacaoMerge);
    }
}

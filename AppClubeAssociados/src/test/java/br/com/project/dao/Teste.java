/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.project.dao;

import br.com.project.model.Associado;
import br.com.project.model.Bar;
import br.com.project.model.BarProduto;
import br.com.project.model.Clube;
import br.com.project.model.Comanda;
import br.com.project.model.Dependente;
import br.com.project.model.Mensalidade;
import br.com.project.model.Produto;
import br.com.project.model.enumeration.EnumParentesco;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class Teste {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        
        Clube clube = new Clube();
        clube.setCnpj("654115");
        clube.setId(1);
        clube.setLocalidade("Juiz de Fora");
        clube.setNome("Clube Associados");
        clube.setResponsavel("Marcos andré");
        
        new ClubeDAO().inserir(clube);
        
        Date dt = new Date(System.currentTimeMillis());
       
        Mensalidade mensalidade = new Mensalidade();
        
        mensalidade.setValor(50);
        mensalidade.setDtVencimento(dt);
        
        Associado associado = new Associado();
        associado.setAgencia(6115);
        associado.setBanco("Banco do brasil");
        associado.setClubeParticipante(clube);
        associado.setConta(5411);
        associado.setCpf("158741");
        associado.setDataNascimento(dt);
        associado.setEndereco("endereco teste");
        associado.setId(9);
        associado.setNome("teste");
        associado.setTelefone("545115");
        associado.setRg("541");
        associado.setMensalidade(mensalidade);
        
        new AssociadoDAO().inserir(associado);
        
        Dependente dependente = new Dependente();
        
        dependente.setCpf("5112");
        dependente.setDataNascimento(dt);
        dependente.setEndereco("endereco teest");
        dependente.setId(1);
        dependente.setNome("dependente teste");
        dependente.setParente(EnumParentesco.filho);
        dependente.setResponsavel(associado);
        dependente.setRg("5451");
        dependente.setTelefone("8545151");
        
        new DependenteDAO().inserir(dependente);
        
        Bar bar = new Bar();
        
        bar.setClube(clube);
        bar.setId(2);
        bar.setResponsavel("marcos");
        
        new BarDAO().inserir(bar);
        
        ArrayList<Produto> produtos = new ArrayList<>();
        
        Produto produto = new Produto();
        
        produto.setEhPerecivel(true);
        produto.setId(5);
        produto.setMarca("JACK DANIELS");
        produto.setNome("WHISKEY JACK DANIELS 500ML");
        produto.setPreco(2.50);
        produto.setValidade(dt);
        
        new ProdutoDAO().inserir(produto);
        
        BarProduto barProduto = new BarProduto();
        
        barProduto.setId_bar(bar);
        barProduto.setId_produto(produto);
        barProduto.setNomeProduto(produto);
        
        new BarProdutoDAO().inserir(barProduto);
        
        Comanda comanda = new Comanda();
        
        produtos.add(produto);
        
        comanda.setData_atendimento(LocalDateTime.MIN);
        comanda.setId(2);
        comanda.setId_bar(bar);
        comanda.setId_cliente(associado);
//        comanda.setValor(mensalidade);
//        comanda.setProdutosConsumidos(produtos);
        
        new ComandaDAO().inserir(comanda);
        
    }
}

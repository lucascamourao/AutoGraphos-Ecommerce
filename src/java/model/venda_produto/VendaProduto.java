package model.venda_produto;

import model.produto.Produto;
import model.venda.Venda;

/**
 *
 * @author Guilherme Sousa Lopes & Lucas Cabral Amador Mourão
 *
 * Classe de entidade que representa a relação entre venda e produto
 */
public class VendaProduto {

    private Venda venda;
    private Produto produto;
    private double preco;
    private int quantidade;
     
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

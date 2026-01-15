package model.venda_produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static config.Config.*;
import model.produto.Produto;
import model.venda.Venda;

/**
 *
 * @author Guilherme Sousa Lopes & Lucas Cabral Amador Mourão
 *
 * Classe que implementa o padrão DAO para a entidade venda_produto
 */
public class VendaProdutoDAO {

    public boolean inserir(int vendaId, int produtoId, double preco, int quantidade) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);

            PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO venda_produto (venda_id, produto_id, preco, quantidade) VALUES (?, ?, ?, ?)"
            );

            preparedStatement.setInt(1, vendaId);
            preparedStatement.setInt(2, produtoId);
            preparedStatement.setDouble(3, preco);
            preparedStatement.setInt(4, quantidade);

            sucesso = (preparedStatement.executeUpdate() == 1);

            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return sucesso;
    }

    public List<VendaProduto> obterPorVenda(int vendaId) {
        List<VendaProduto> resultado = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);

            PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT venda_id, produto_id, preco, quantidade FROM venda_produto WHERE venda_id = ?"
            );
            preparedStatement.setInt(1, vendaId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                VendaProduto vp = new VendaProduto();

                Venda venda = new Venda();
                venda.setId(resultSet.getInt("venda_id"));

                Produto produto = new Produto();
                produto.setId(resultSet.getInt("produto_id"));

                vp.setVenda(venda);
                vp.setProduto(produto);
                vp.setPreco(resultSet.getDouble("preco"));
                vp.setQuantidade(resultSet.getInt("quantidade"));

                resultado.add(vp);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        return resultado;
    }

    public boolean remover(int vendaId, int produtoId) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM venda_produto WHERE venda_id = ? AND produto_id = ?");
            preparedStatement.setInt(1, vendaId);
            preparedStatement.setInt(2, produtoId);
            sucesso = (preparedStatement.executeUpdate() == 1);

            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return sucesso;
    }
}

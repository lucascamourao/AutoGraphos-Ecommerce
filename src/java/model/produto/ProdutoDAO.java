package model.produto;


import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import static config.Config.*;

import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import model.categoria.Categoria;
import model.categoria.CategoriaDAO;

/**
 *
 * @author Guilherme Sousa Lopes & Lucas Cabral Amador Mourão
 */
public class ProdutoDAO {

    private static final String UPLOAD_DIRETORIO = "C:\\Users\\guilh\\Documents\\upload";

    /**
     * Método utilizado para obter todos os produtos existentes
     *
     * @return
     */
    public List<Produto> obterTodos() {
        List<Produto> produtos = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, preco, foto, quantidade, categoria_id FROM produto ORDER BY descricao ASC");
            ResultSet resultSet = preparedStatement.executeQuery();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setFoto(resultSet.getString("foto"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                Categoria categoria = categoriaDAO.obter(resultSet.getInt("categoria_id"));
                produto.setCategoria(categoria);
                produtos.add(produto);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        return produtos;
    }

    /**
     * Método utilizado para obter todos os produtos em estoque
     *
     * @return
     */
    public List<Produto> obterTodosEmEstoque() {
        List<Produto> produtos = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, preco, foto, quantidade, categoria_id FROM produto WHERE quantidade > 0 ORDER BY descricao ASC");
            ResultSet resultSet = preparedStatement.executeQuery();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setFoto(resultSet.getString("foto"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                Categoria categoria = categoriaDAO.obter(resultSet.getInt("categoria_id"));
                produto.setCategoria(categoria);
                produtos.add(produto);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        return produtos;
    }

    /**
     * Método utilizado para obter todos os produtos que estão faltando em
     * estoque
     *
     * @return
     */
    public List<Produto> obterProdutosFaltantesEmEstoque() {
        List<Produto> produtos = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, preco, foto, quantidade, categoria_id FROM produto WHERE quantidade = 0 ORDER BY descricao ASC");
            ResultSet resultSet = preparedStatement.executeQuery();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setFoto(resultSet.getString("foto"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                Categoria categoria = categoriaDAO.obter(resultSet.getInt("categoria_id"));
                produto.setCategoria(categoria);
                produtos.add(produto);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        return produtos;
    }

    /**
     * Método utilizado para obter um produto pelo identificador
     *
     * @param id
     * @return
     */
    public Produto obter(int id) {
        Produto produto = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, preco, foto, quantidade, categoria_id FROM produto WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            while (resultSet.next()) {
                produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setFoto(resultSet.getString("foto"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                Categoria categoria = categoriaDAO.obter(resultSet.getInt("categoria_id"));
                produto.setCategoria(categoria);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        return produto;
    }

    /**
     * Método para inserir um novo produto
     *
     * @param descricao
     * @param preco
     * @param foto
     * @param quantidade
     * @param categoriaId
     * @return
     */
    public boolean inserir(String descricao, double preco, Part foto, int quantidade, int categoriaId) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto (descricao, preco, foto, quantidade, categoria_id) VALUES (?, ?, NULL, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, descricao);
            preparedStatement.setDouble(2, preco);
            preparedStatement.setInt(3, quantidade);
            preparedStatement.setInt(4, categoriaId);
            sucesso = (preparedStatement.executeUpdate() == 1);
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            long produtoId = 0;
            if (resultSet.next()) {
                produtoId = resultSet.getLong(1);
            }
            resultSet.close();
            preparedStatement.close();
            if (sucesso && foto != null && foto.getSize() > 0) {
                String produtoFotoNomeArquivo = foto.getSubmittedFileName();
                produtoFotoNomeArquivo = produtoId + produtoFotoNomeArquivo.substring(produtoFotoNomeArquivo.lastIndexOf("."));
                File produtoFotoArquivo = new File(new File(UPLOAD_DIRETORIO), produtoFotoNomeArquivo);
                try (InputStream input = foto.getInputStream()) {
                    Files.copy(input, produtoFotoArquivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                Statement statement = connection.createStatement();
                sucesso = statement.executeUpdate("UPDATE produto SET foto = '" + produtoFotoArquivo.getName() + "' WHERE id = " + produtoId) == 1;
                statement.close();
            }
            if (sucesso) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.close();
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            return false;
        }
        return sucesso;
    }

    /**
     * Método para atualizar um produto existente
     *
     * @param descricao
     * @param preco
     * @param foto
     * @param quantidade
     * @param categoriaId
     * @param id
     * @return
     */
    public boolean atualizar(String descricao, double preco, Part foto, int quantidade, int categoriaId, int id) {
        Produto produto = obter(id);
        if (produto == null) {
            return false;
        }
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto SET descricao = ?, preco = ?, foto = NULL, quantidade = ?, categoria_id = ? WHERE id = ?");
            preparedStatement.setString(1, descricao);
            preparedStatement.setDouble(2, preco);
            preparedStatement.setInt(3, quantidade);
            preparedStatement.setInt(4, categoriaId);
            preparedStatement.setInt(5, id);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            if (sucesso) {
                // se tem uma nova foto a ser atualizada
                if (foto != null && foto.getSize() > 0) {
                    // se tem uma foto antiga, remover
                    if (produto.getFoto() != null && produto.getFoto().trim().length() > 0) {
                        File produtoFotoArquivo = new File(new File(UPLOAD_DIRETORIO), produto.getFoto());
                        if (produtoFotoArquivo.exists()) {
                            sucesso = produtoFotoArquivo.delete();
                        }
                    }
                    // atualiza a nova foto
                    String produtoFotoNomeArquivo = foto.getSubmittedFileName();
                    produtoFotoNomeArquivo = id + produtoFotoNomeArquivo.substring(produtoFotoNomeArquivo.lastIndexOf("."));
                    File produtoFotoArquivo = new File(new File(UPLOAD_DIRETORIO), produtoFotoNomeArquivo);
                    try (InputStream input = foto.getInputStream()) {
                        Files.copy(input, produtoFotoArquivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                    Statement statement = connection.createStatement();
                    sucesso = statement.executeUpdate("UPDATE produto SET foto = '" + produtoFotoArquivo.getName() + "' WHERE id = " + id) == 1;
                    statement.close();
                } else {
                    // se tem uma foto antiga, remover
                    if (produto.getFoto() != null && produto.getFoto().trim().length() > 0) {
                        File produtoFotoArquivo = new File(new File(UPLOAD_DIRETORIO), produto.getFoto());
                        if (produtoFotoArquivo.exists()) {
                            sucesso = produtoFotoArquivo.delete();
                        }
                    }
                    Statement statement = connection.createStatement();
                    sucesso = statement.executeUpdate("UPDATE produto SET foto = NULL WHERE id = " + id) == 1;
                    statement.close();
                }
            }
            if (sucesso) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.close();
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            return false;
        }
        return sucesso;
    }

    /**
     * Método para remover um produto existente
     *
     * @param id
     * @return
     */
    public boolean remover(int id) {
        Produto produto = obter(id);
        if (produto == null) {
            return false;
        }
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto WHERE id = ?");
            preparedStatement.setInt(1, id);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            if (sucesso && produto.getFoto() != null && produto.getFoto().trim().length() > 0) {
                File produtoFotoArquivo = new File(new File(UPLOAD_DIRETORIO), produto.getFoto());
                if (produtoFotoArquivo.exists()) {
                    sucesso = produtoFotoArquivo.delete();
                }
            }
            if (sucesso) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return sucesso;
    }

}
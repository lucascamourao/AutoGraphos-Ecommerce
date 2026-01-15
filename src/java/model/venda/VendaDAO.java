package model.venda;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import static config.Config.*;
import java.sql.Timestamp;
import model.usuario.Usuario;

/**
 *
 * @author Guilherme Sousa Lopes & Lucas Cabral Amador Mourão
 *
 * Classe que implementa o padrão DAO para a entidade venda
 */
public class VendaDAO {

    /**
     * Método utilizado para obter todas as categorias existentes
     *
     * @return
     */
    public List<Venda> obterTodas() {
        List<Venda> resultado = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, data_hora FROM venda");
            while (resultSet.next()) {
                Venda venda = new Venda();
                venda.setId(resultSet.getInt("id"));
                venda.setDataHora(resultSet.getTimestamp("data_hora"));
                resultado.add(venda);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        return resultado;
    }

    /**
     * Método utilizado para obter uma venda existente
     *
     * @param id
     * @return
     */
    public Venda obter(int id) {
        Venda venda = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, data_hora FROM venda WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                venda = new Venda();
                venda.setId(resultSet.getInt("id"));
                venda.setDataHora(resultSet.getTimestamp("data_hora"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        return venda;
    }

    /**
     * Método utilizado para inserir uma nova venda
     *
     * @param data_hora
     * @param userId
     * @return
     */
    public int inserir(Timestamp data_hora, int userId) {
        int vendaId = -1;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO venda (data_hora, usuario_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, data_hora);
            preparedStatement.setInt(2, userId);
 
            int linhasAfetadas = preparedStatement.executeUpdate();
            if (linhasAfetadas == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    vendaId = generatedKeys.getInt(1);
                }
                generatedKeys.close();
            }
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(); // <<< ESSENCIAL
            return -1;
        }
        return vendaId;
    } 

    /**
     * Método utilizado para atualizar uma categoria existente
     *
     * @param data_hora
     * @param id
     * @return
     */
    public boolean atualizar(Timestamp data_hora, int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE venda SET data_hora = ? WHERE id = ?");
            preparedStatement.setTimestamp(1, data_hora);
            preparedStatement.setInt(2, id);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return sucesso;
    }

    /**
     * Método utilizado para remover uma categoria existente
     *
     * @param id
     * @return
     */
    public boolean remover(int id) {
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM venda WHERE id = ?");
            preparedStatement.setInt(1, id);
            sucesso = (preparedStatement.executeUpdate() == 1);
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return sucesso;
    }



    public List<Venda> obterPorUsuario(int usuarioId) {
        List<Venda> resultado = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, data_hora FROM venda WHERE usuario_id = ? ORDER BY data_hora DESC"
            );
            preparedStatement.setInt(1, usuarioId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Venda venda = new Venda();
                venda.setId(resultSet.getInt("id"));
                venda.setDataHora(resultSet.getTimestamp("data_hora"));
                resultado.add(venda);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return resultado;
    }
    

    public List<Venda> obterTodasRelatorio() {
        List<Venda> resultado = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                "SELECT v.id, v.data_hora, u.id AS user_id, u.nome, u.endereco, u.email, u.login, u.senha, u.administrador " +
                "FROM venda v " +
                "JOIN usuario u ON v.usuario_id = u.id"
            );
            while (resultSet.next()) {
                Venda venda = new Venda();
                venda.setId(resultSet.getInt("id"));
                venda.setDataHora(resultSet.getTimestamp("data_hora"));

                Usuario u = new Usuario();
                u.setId(resultSet.getInt("user_id"));
                u.setNome(resultSet.getString("nome"));
                u.setEndereco(resultSet.getString("endereco"));
                u.setEmail(resultSet.getString("email"));
                u.setLogin(resultSet.getString("login"));
                u.setSenha(resultSet.getString("senha"));
                u.setAdministrador(resultSet.getBoolean("administrador"));

                venda.setUsuario(u);

                resultado.add(venda);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return resultado;
    }
    
}

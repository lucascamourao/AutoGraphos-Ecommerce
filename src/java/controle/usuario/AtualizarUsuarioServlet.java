package controle.usuario;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.usuario.UsuarioDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Servlet que possui a ação de atualizar um usuário existente
 */
public class AtualizarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.atualizar(nome, endereco, email, login, senha, id);
        if (sucesso) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Logout");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("mensagem", "Não foi possível atualizar o usuário");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("principal.jsp");
            requestDispatcher.forward(request, response);
        }
    }

}

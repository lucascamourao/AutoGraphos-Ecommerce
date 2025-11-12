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
 * Servlet que possui a ação de remover um usuário existente
 */
public class RemoverUsuarioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.remover(id);
        if (sucesso) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Logout");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("mensagem", "Não foi possível remover o usuário");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("principal.jsp");
            requestDispatcher.forward(request, response);
        }
    }

}

package controle.usuario;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.usuario.Usuario;
import model.usuario.UsuarioDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Servlet que possui a ação de identificar um usuário
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.obter(login);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            Cookie c = new Cookie("smdecommerce.login", login);
            c.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(c);
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuario);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/account-info.jsp");
            requestDispatcher.forward(request, response);
        } else {
            /**request.setAttribute("mensagem", "Login ou senha incorreta");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);**/
            response.sendRedirect(request.getContextPath() + "/Inicio");
        }
    }

}

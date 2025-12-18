package controle.inicio;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.produto.Produto;
import model.produto.ProdutoDAO;

/**
 * 
 * @author Leonardo Oliveira Moreira
 * 
 * Servlet que possui a ação de carregar a página inicial
 */
public class InicioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getAttribute("mensagem") != null) {
            request.setAttribute("mensagem", request.getAttribute("mensagem"));
        }
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.obterTodosEmEstoque();
        request.setAttribute("produtos", produtos);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}

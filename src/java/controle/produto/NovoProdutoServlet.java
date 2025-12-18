package controle.produto;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.categoria.Categoria;
import model.categoria.CategoriaDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Servlet que possui a ação de exibir o formulário para inserir um novo produto
 */
public class NovoProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.obterTodas();
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("/pages/new-product.jsp").forward(request, response);
    }

}

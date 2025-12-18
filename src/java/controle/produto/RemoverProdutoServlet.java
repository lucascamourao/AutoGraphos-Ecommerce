package controle.produto;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.produto.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Servlet que possui a ação de remover um produto existente
 */
public class RemoverProdutoServlet extends HttpServlet {
  
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProdutoDAO produtoDAO = new ProdutoDAO();
        boolean sucesso = produtoDAO.remover(id);
        request.setAttribute("mensagem", sucesso ? "Registro removido com sucesso" : "Não foi possível remover o registro");
        request.getRequestDispatcher("ListarProduto").forward(request, response);
    }

}

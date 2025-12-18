package controle.produto;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.produto.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Servlet que possui a ação de atualizar um produto existente
 */
public class AtualizarProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        Part foto = request.getPart("foto");
        int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
        ProdutoDAO produtoDAO = new ProdutoDAO();
        boolean sucesso = produtoDAO.atualizar(descricao, preco, foto, quantidade, categoriaId, id);
        request.setAttribute("mensagem", sucesso ? "Registro atualizado com sucesso" : "Não foi possível atualizar o registro");
        request.getRequestDispatcher("ListarProduto").forward(request, response);
    }
}

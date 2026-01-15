package controle.inicio;

import config.Constantes;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.carrinho.CarrinhoCompras;
import model.carrinho.CarrinhoItem;
import model.produto.Produto;
import model.produto.ProdutoDAO;
import utils.Utils;

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
        
        /* obtenção dos itens do carrinho de compras */
        Cookie cookie = Utils.obterCookieCarrrinhoCompras(request);
        if (cookie == null) {
            cookie = new Cookie(Constantes.COOKIE_CARRINHO_COMPRAS_CHAVE, "");
            response.addCookie(cookie);
        }
        List<CarrinhoItem> itensCarrinhoCompras = CarrinhoCompras.obter(cookie);
        request.setAttribute("itensCarrinhoCompras", itensCarrinhoCompras);
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}

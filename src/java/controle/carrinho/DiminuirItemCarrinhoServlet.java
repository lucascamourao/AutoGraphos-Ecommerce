package controle.carrinho;

import config.Constantes;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.carrinho.CarrinhoCompras;
import utils.Utils;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle para implementar a ação de adicionar um item no carrinho
 * de compras
 */
public class DiminuirItemCarrinhoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int produtoId = Integer.parseInt(request.getParameter("produtoId"));

        Cookie cookie = Utils.obterCookieCarrrinhoCompras(request);
        if (cookie == null) {
            cookie = new Cookie(Constantes.COOKIE_CARRINHO_COMPRAS_CHAVE, "");
        }

        cookie = CarrinhoCompras.diminuirItem(produtoId, cookie);
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio");
        dispatcher.forward(request, response);
    }
}

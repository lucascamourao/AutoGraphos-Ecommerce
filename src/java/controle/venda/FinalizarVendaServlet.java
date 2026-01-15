package controle.venda;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import model.carrinho.CarrinhoCompras;
import model.carrinho.CarrinhoItem;
import model.produto.Produto;
import model.produto.ProdutoDAO;
import model.usuario.Usuario;
import model.venda.VendaDAO;
import model.venda_produto.VendaProdutoDAO;


import static config.Constantes.COOKIE_CARRINHO_COMPRAS_CHAVE;

public class FinalizarVendaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Cookie cookieCarrinho = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (COOKIE_CARRINHO_COMPRAS_CHAVE.equals(c.getName())) {
                    cookieCarrinho = c;
                    break;
                }
            }
        }

        List<CarrinhoItem> itensCarrinho = CarrinhoCompras.obter(cookieCarrinho);

        if (itensCarrinho == null || itensCarrinho.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/Inicio");
            return;
        }

        ProdutoDAO produtoDAO = new ProdutoDAO();

        for (CarrinhoItem item : itensCarrinho) {
            Produto produtoBanco = produtoDAO.obter(item.getProduto().getId());

            if (produtoBanco == null ||
                item.getQuantidade() > produtoBanco.getQuantidade()) {

                request.setAttribute(
                    "mensagem",
                    "Quantidade solicitada ultrapassa o estoque do produto: "
                    + item.getProduto().getDescricao()
                );
                request.getRequestDispatcher("/Inicio")
                       .forward(request, response);
                return;
            }
        }

        VendaDAO vendaDAO = new VendaDAO();
        Timestamp agora = new Timestamp(System.currentTimeMillis());

        int vendaId = vendaDAO.inserir(agora, usuario.getId());

        if (vendaId == -1) {
            request.setAttribute("mensagem", "Erro ao finalizar a venda.");
            request.getRequestDispatcher("/Inicio")
                   .forward(request, response);
            return;
        }
        VendaProdutoDAO vendaProdutoDAO = new VendaProdutoDAO();


        for (CarrinhoItem item : itensCarrinho) {

            Produto produto = produtoDAO.obter(item.getProduto().getId());

            int quantidadeComprada = item.getQuantidade();
            double precoTotal = produto.getPreco() * quantidadeComprada;

            boolean sucesso = vendaProdutoDAO.inserir(
                vendaId,
                produto.getId(),
                precoTotal,
                quantidadeComprada
            );

            if (!sucesso) {
                request.setAttribute(
                    "mensagem",
                    "Erro ao registrar produtos da venda."
                );
                request.getRequestDispatcher("/Inicio")
                       .forward(request, response);
                return;
            }

            int novaQuantidade =
                produto.getQuantidade() - quantidadeComprada;

            produtoDAO.atualizarQuantidade(
                produto.getId(),
                novaQuantidade
            );
        }

        if (cookieCarrinho != null) {
            cookieCarrinho.setValue("");
            cookieCarrinho.setPath(request.getContextPath());
            cookieCarrinho.setMaxAge(0);
            response.addCookie(cookieCarrinho);
        }

        response.sendRedirect(request.getContextPath() + "/Inicio");
    }
}

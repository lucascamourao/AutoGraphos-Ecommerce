package controle.venda;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.venda.VendaDAO;

public class DeletarVendaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int vendaId = Integer.parseInt(request.getParameter("vendaId"));
        VendaDAO vendaDAO = new VendaDAO();
        boolean sucesso = vendaDAO.remover(vendaId);
        request.setAttribute("mensagem", sucesso ? "Venda removida com sucesso." : "Não foi possível remover a venda.");
        request.getRequestDispatcher("/pages/ver-compras-admin-page.jsp").forward(request, response);
    }
}

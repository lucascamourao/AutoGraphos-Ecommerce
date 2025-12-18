package controle.produto;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import model.produto.Produto;
import model.produto.ProdutoDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Servlet que possui a ação de mostrar a foto de um produto existente
 */
public class MostrarFotoProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.obter(id);
        File foto = new File("C:\\Users\\guilh\\Documents\\upload\\" + produto.getFoto());
        String mimeType = getServletContext().getMimeType(foto.getName());
        response.setContentType(mimeType);
        response.setContentLengthLong(foto.length());
        try (FileInputStream fis = new FileInputStream(foto); OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
    
}

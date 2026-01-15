package controle.venda;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.usuario.Usuario;
import model.venda.Venda;
import model.venda.VendaDAO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class RelatorioComprasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dataInicioStr = request.getParameter("dataInicio");
        String dataFimStr = request.getParameter("dataFim");

        if (dataInicioStr == null || dataFimStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datas não informadas");
            return;
        }

        Timestamp dataInicio = Timestamp.valueOf(dataInicioStr + " 00:00:00");
        Timestamp dataFim = Timestamp.valueOf(dataFimStr + " 23:59:59");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_compras_periodo.pdf\"");

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.showText("Relatório de Compras por Cliente");
                contentStream.newLine();
                contentStream.showText("Período: " + dataInicioStr + " até " + dataFimStr);
                contentStream.newLine();
                contentStream.newLine();

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);

                VendaDAO vendaDAO = new VendaDAO();

                List<Venda> todasVendas = vendaDAO.obterTodasRelatorio();
                Map<Integer, Integer> contadorCompras = new HashMap<>();
                Map<Integer, Usuario> usuariosMap = new HashMap<>();

                for (Venda v : todasVendas) {
                    Timestamp dataVenda = v.getDataHora();
                    if (dataVenda.after(dataInicio) && dataVenda.before(dataFim)) {
                        Usuario u = v.getUsuario();
                        if (u != null) {
                            int userId = u.getId();
                            contadorCompras.put(userId, contadorCompras.getOrDefault(userId, 0) + 1);
                            usuariosMap.putIfAbsent(userId, u);
                        }
                    }
                }
                List<Map.Entry<Integer, Integer>> listaOrdenada = contadorCompras.entrySet().stream()
                        .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                        .toList();

                for (Map.Entry<Integer, Integer> entry : listaOrdenada) {
                    int userId = entry.getKey();
                    int qtdCompras = entry.getValue();
                    Usuario u = usuariosMap.get(userId);

                    if (u != null) {
                        contentStream.showText("ID Cliente: " + u.getId() + " - Nome: " + u.getNome() +
                                " - Total de Compras: " + qtdCompras);
                        contentStream.newLine();
                    }
                }
                contentStream.endText();
            }
            document.save(response.getOutputStream());
        }
    }
}
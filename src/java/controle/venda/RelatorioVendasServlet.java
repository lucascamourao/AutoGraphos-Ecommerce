package controle.venda;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.venda.Venda;
import model.venda.VendaDAO;
import model.venda_produto.VendaProduto;
import model.venda_produto.VendaProdutoDAO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class RelatorioVendasServlet extends HttpServlet {

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
        response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_valor_por_dia.pdf\"");

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.showText("Relatório de Valor Recebido por Dia");
                contentStream.newLine();
                contentStream.showText("Período: " + dataInicioStr + " até " + dataFimStr);
                contentStream.newLine();
                contentStream.newLine();

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);

                VendaDAO vendaDAO = new VendaDAO();
                VendaProdutoDAO vendaProdutoDAO = new VendaProdutoDAO();

                List<Venda> todasVendas = vendaDAO.obterTodas();
                Map<String, Double> totalPorDia = new TreeMap<>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                for (Venda v : todasVendas) {
                    Timestamp dataVenda = v.getDataHora();
                    if (!dataVenda.before(dataInicio) && !dataVenda.after(dataFim)) {
                        List<VendaProduto> itens = vendaProdutoDAO.obterPorVenda(v.getId());
                        double somaVenda = 0;
                        for (VendaProduto vp : itens) {
                            somaVenda += vp.getPreco();
                        }
                        String dia = sdf.format(dataVenda);
                        totalPorDia.put(dia, totalPorDia.getOrDefault(dia, 0.0) + somaVenda);
                    }
                }
                for (Map.Entry<String, Double> entry : totalPorDia.entrySet()) {
                    contentStream.showText("Data: " + entry.getKey() + " - Total Recebido: R$ " + String.format("%.2f", entry.getValue()));
                    contentStream.newLine();
                }
                contentStream.endText();
            }
            document.save(response.getOutputStream());
        }
    }
}

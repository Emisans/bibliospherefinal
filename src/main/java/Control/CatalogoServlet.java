package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Dao.ProdottoDao;
import Model.Prodotto;

import java.io.IOException;
import java.util.List;

@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ProdottoDao dao = new ProdottoDao();
            List<Prodotto> prodotti = dao.findAll();

            // DEBUG
            System.out.println("📦 Prodotti trovati: " + prodotti.size());
            for (Prodotto p : prodotti) {
                System.out.println("➡️ " + p.getNome());
            }

            request.setAttribute("prodotti", prodotti);
            request.getRequestDispatcher("jsp/catalogo.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(); // Log completo per debugging
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel caricamento del catalogo");
        }
    }
}

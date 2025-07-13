package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Dao.ProdottoDao;
import Model.Carrello;
import Model.Prodotto;

import java.io.IOException;

@WebServlet("/AggiungiCarrello")
public class AggiungiCarrelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        ProdottoDao dao = new ProdottoDao();
        Prodotto p = dao.findById(id);

        if (p != null) {
            HttpSession session = request.getSession();
            Carrello carrello = (Carrello) session.getAttribute("carrello");
            if (carrello == null) {
                carrello = new Carrello();
                session.setAttribute("carrello", carrello);
            }
            carrello.aggiungi(p, quantita);
        }
        response.sendRedirect("CatalogoServlet");
    }
}


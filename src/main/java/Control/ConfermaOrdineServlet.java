package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Dao.OrdineDao;
import Model.Carrello;
import Model.Utente;

import java.io.IOException;

@WebServlet("/ConfermaOrdine")
public class ConfermaOrdineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        String email = utente.getEmail();

        if (carrello != null && !carrello.isEmpty()) {
            OrdineDao dao = new OrdineDao();
            int ordineId = dao.salvaOrdine(carrello, email);
            carrello.svuota();

            request.setAttribute("ordineId", ordineId);
            request.getRequestDispatcher("jsp/confermaOrdine.jsp").forward(request, response);
        } else {
            response.sendRedirect("CarrelloServlet");
        }
    }
}

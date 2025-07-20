package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.Carrello;

import java.io.IOException;

@WebServlet("/RimuoviCarrello")
public class RimuoviCarrelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            Carrello carrello = (Carrello) session.getAttribute("carrello");
            if (carrello != null) {
                carrello.rimuovi(id);
                System.out.println("🗑️ Prodotto rimosso dal carrello. ID = " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Errore nel parsing dell'ID.");
            e.printStackTrace();
        }

        response.sendRedirect("CarrelloServlet");
    }
}

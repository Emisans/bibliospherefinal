package Control;

import model.Carrello;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/RimuoviCarrello")
public class RimuoviCarrelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello != null) {
            carrello.rimuovi(id);
        }
        response.sendRedirect("CarrelloServlet");
    }
}
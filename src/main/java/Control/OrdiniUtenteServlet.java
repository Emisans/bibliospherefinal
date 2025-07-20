package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Dao.OrdineDao;
import Model.Ordine;
import Model.Utente;

import java.io.IOException;
import java.util.List;

@WebServlet("/OrdiniUtenteServlet")
public class OrdiniUtenteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        String email = utente.getEmail();
        OrdineDao dao = new OrdineDao();
        List<Ordine> ordini = dao.trovaOrdiniPerEmail(email);

        request.setAttribute("ordini", ordini);
        request.getRequestDispatcher("jsp/ordini.jsp").forward(request, response);
    }
}
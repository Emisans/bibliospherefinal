package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Dao.OrdineDao;
import Model.Ordine;

import java.io.IOException;
import java.util.List;

@WebServlet("/OrdiniUtenteServlet")
public class OrdiniUtenteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if (email == null) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }
        OrdineDao dao = new OrdineDao();
        List<Ordine> ordini = dao.trovaOrdiniPerEmail(email);
        request.setAttribute("ordini", ordini);
        request.getRequestDispatcher("jsp/ordini.jsp").forward(request, response);
    }
}
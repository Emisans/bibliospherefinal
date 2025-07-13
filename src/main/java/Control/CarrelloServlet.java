package Control;

import model.Carrello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }
        request.setAttribute("carrello", carrello);
        request.getRequestDispatcher("jsp/carrello.jsp").forward(request, response);
    }
}
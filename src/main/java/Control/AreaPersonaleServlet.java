package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/AreaPersonale")
public class AreaPersonaleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("utente") != null) {
            request.getRequestDispatcher("jsp/areapersonale.jsp").forward(request, response);
        } else {
            response.sendRedirect("jsp/login.jsp");
        }
    }
}

package Control;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalida la sessione
        HttpSession session = request.getSession(false); // false = non creare nuova se non esiste
        if (session != null) {
            session.invalidate();
        }

        // Redirect alla pagina di login
        response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
    }
}
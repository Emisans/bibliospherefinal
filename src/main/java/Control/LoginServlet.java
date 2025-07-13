package Control;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.mindrot.jbcrypt.BCrypt;

import Dao.UtenteDAO;
import Model.Utente;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Utente utente = UtenteDAO.doRetrieveByUsername(username);

            if (utente != null && BCrypt.checkpw(password, utente.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("utente", utente);
                response.sendRedirect("areapersonale.jsp");
            } else {
                request.setAttribute("errore", "Username o password errati.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore interno durante il login.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}


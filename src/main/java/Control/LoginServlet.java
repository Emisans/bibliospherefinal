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

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UtenteDAO utenteDao = new UtenteDAO();
            Utente utente = utenteDao.doRetrieveByEmail(email);

            if (utente != null && BCrypt.checkpw(password, utente.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("utente", utente);
                session.setAttribute("ruolo", utente.getRuolo());

                if ("admin".equals(utente.getRuolo())) {
                    response.sendRedirect("admin/adminHome.jsp");
                } else {
                    response.sendRedirect("home.jsp");
                }

            } else {
                request.setAttribute("erroreLogin", "Email o password non validi");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("errore.jsp");
        }
    }
}

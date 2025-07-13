package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.UtenteDao;
import model.Utente;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UtenteDao dao = new UtenteDao();

        try {
            Utente utente = dao.cercaUtentePerEmail(email);
            if (utente != null && BCrypt.checkpw(password, utente.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("utenteLoggato", utente);
                session.setAttribute("email", utente.getEmail());
                response.sendRedirect("jsp/areapersonale.jsp");
            } else {
                request.setAttribute("error", "Email o password errata!");
                request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Errore di connessione al database.");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }
    }
}



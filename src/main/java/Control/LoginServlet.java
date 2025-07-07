package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UtenteDao;
import model.Utente;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String error = null;

        UtenteDao dao = new UtenteDao();
        try {
            Utente utente = dao.cercaUtentePerEmail(email);

            if (utente != null && BCrypt.checkpw(password, utente.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("utenteLoggato", utente);

                response.sendRedirect("jsp/areaPersonale.jsp");
                return;
            } else {
                error = "Email o password errata!";
            }
        } catch (SQLException e) {
            error = "Errore di connessione al database.";
            e.printStackTrace();
        }

        request.setAttribute("error", error);
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
    }
}

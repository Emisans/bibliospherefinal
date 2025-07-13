package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;

import model.Utente;
import dao.UtenteDao;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String conferma = request.getParameter("confirm");

        if (!password.equals(conferma)) {
            response.sendRedirect("jsp/registrazione.jsp?errore=password");
            return;
        }

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        Utente utente = new Utente(nome, email, username, hashed);
        UtenteDao dao = new UtenteDao();

        try {
            if (dao.emailEsistente(email)) {
                response.sendRedirect("jsp/registrazione.jsp?errore=emailEsistente");
                return;
            }
            dao.salvaUtente(utente);
            response.sendRedirect("CatalogoServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Errore nel salvataggio dell'utente");
        }
    }
}

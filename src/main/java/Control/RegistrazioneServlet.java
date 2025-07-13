package Control;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.mindrot.jbcrypt.BCrypt;

import Dao.UtenteDAO;
import Model.Utente;

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            String passwordCifrata = BCrypt.hashpw(password, BCrypt.gensalt());

            Utente nuovo = new Utente();
            nuovo.setNome(nome);
            nuovo.setEmail(email);
            nuovo.setUsername(username);
            nuovo.setPassword(passwordCifrata);

            boolean success = UtenteDAO.doSave(nuovo);

            if (success) {
                request.setAttribute("messaggio", "Registrazione completata con successo!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("errore", "Errore durante la registrazione.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore interno del server.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.jsp");
            dispatcher.forward(request, response);
        }
    }
}
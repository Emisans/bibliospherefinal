package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.mindrot.jbcrypt.BCrypt;

import Dao.UtenteDAO;
import Model.Utente;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("🟡 Tentativo login");
        System.out.println("👉 Username inserito: " + username);
        System.out.println("👉 Password inserita: " + password);

        try {
            Utente utente = UtenteDAO.doRetrieveByUsername(username);

            if (utente != null && BCrypt.checkpw(password, utente.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("utente", utente);
                session.setAttribute("email", utente.getEmail());

                System.out.println("✅ Login riuscito per " + username + " (ruolo: " + utente.getRuolo() + ")");

                if ("admin".equals(utente.getRuolo())) {
                    response.sendRedirect("jsp/admin/dashboard.jsp"); // puoi cambiare la destinazione
                } else {
                    response.sendRedirect("jsp/areapersonale.jsp");
                }

            } else {
                System.out.println("❌ Login fallito per: " + username);
                request.setAttribute("errore", "Username o password errati.");
                request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore interno durante il login.");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }
    }
}

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

        System.out.println("🟡 Tentativo login");
        System.out.println("👉 Username inserito: " + username);
        System.out.println("👉 Password inserita: " + password);

        try {
            Utente utente = UtenteDAO.doRetrieveByUsername(username);

            if (utente != null) {
                System.out.println("✅ Utente trovato nel DB");
                System.out.println("🔐 Password nel DB (cifrata): " + utente.getPassword());
                boolean match = BCrypt.checkpw(password, utente.getPassword());
                System.out.println("🔎 Verifica corrispondenza password: " + match);

                if (match) {
                    HttpSession session = request.getSession();
                    session.setAttribute("utente", utente);
                    System.out.println("✅ Login riuscito per: " + username);
                    response.sendRedirect("jsp/areapersonale.jsp");
                    return;
                }
            }

            System.out.println("❌ Login fallito per: " + username);
            request.setAttribute("errore", "Username o password errati.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/login.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore interno durante il login.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}

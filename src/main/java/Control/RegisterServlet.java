package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import dao.UtenteDao;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String conferma = request.getParameter("confirm");

        Utente utente = new Utente(nome, email, username, password);

        UtenteDao dao = new UtenteDao();
        try {
            dao.salvaUtente(utente);
            System.out.println("Utente salvato nel database!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nel salvataggio dell'utente!");
        }

        response.sendRedirect("jsp/registrazione-successo.jsp");
    }
}


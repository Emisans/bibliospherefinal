package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProdottoDao;
import model.Prodotto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        ProdottoDao dao = new ProdottoDao();
        try {
            List<Prodotto> prodotti = dao.findAll();
            request.setAttribute("prodotti", prodotti);
            request.getRequestDispatcher("jsp/catalogo.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Errore nel caricamento dei prodotti");
        }
    }
}

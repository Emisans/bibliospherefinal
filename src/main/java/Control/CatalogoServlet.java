package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Dao.ProdottoDao;
import Model.Prodotto;

import java.io.IOException;
import java.util.List;

@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProdottoDao dao = new ProdottoDao();
        List<Prodotto> prodotti = dao.findAll();
        request.setAttribute("prodotti", prodotti);
        request.getRequestDispatcher("jsp/catalogo.jsp").forward(request, response);
    }
}

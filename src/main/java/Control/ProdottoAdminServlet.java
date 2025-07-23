package Control;

import Dao.ProdottoDao;
import Model.Prodotto;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ProdottoAdminServlet")
public class ProdottoAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String azione = request.getParameter("azione");
        ProdottoDao prodottoDao = new ProdottoDao();

        try {
            if ("Aggiungi".equals(azione)) {
                String titolo = request.getParameter("titolo");
                String autore = request.getParameter("autore");
                String nome = request.getParameter("nome");
                String descrizione = request.getParameter("descrizione");
                double prezzo = Double.parseDouble(request.getParameter("prezzo"));
                double iva = Double.parseDouble(request.getParameter("iva"));
                int quantita = Integer.parseInt(request.getParameter("quantita"));

                Prodotto p = new Prodotto();
                p.setTitolo(titolo);
                p.setAutore(autore);
                p.setNome(nome);
                p.setDescrizione(descrizione);
                p.setPrezzo(prezzo);
                p.setIva(iva);
                p.setQuantita(quantita);

                prodottoDao.insert(p);
                request.setAttribute("messaggio", "✅ Prodotto aggiunto con successo.");

            } else if ("Modifica".equals(azione)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Prodotto daModificare = prodottoDao.doRetrieveById(id);
                request.setAttribute("prodottoDaModificare", daModificare);

            } else if ("Aggiorna".equals(azione)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String titolo = request.getParameter("titolo");
                String autore = request.getParameter("autore");
                String nome = request.getParameter("nome");
                String descrizione = request.getParameter("descrizione");
                double prezzo = Double.parseDouble(request.getParameter("prezzo"));
                double iva = Double.parseDouble(request.getParameter("iva"));
                int quantita = Integer.parseInt(request.getParameter("quantita"));

                Prodotto aggiornato = new Prodotto();
                aggiornato.setId(id);
                aggiornato.setTitolo(titolo);
                aggiornato.setAutore(autore);
                aggiornato.setNome(nome);
                aggiornato.setDescrizione(descrizione);
                aggiornato.setPrezzo(prezzo);
                aggiornato.setIva(iva);
                aggiornato.setQuantita(quantita);

                prodottoDao.update(aggiornato);
                request.setAttribute("messaggio", "✅ Prodotto aggiornato con successo.");

            } else if ("Cancella".equals(azione)) {
                int id = Integer.parseInt(request.getParameter("id"));
                prodottoDao.delete(id);
                request.setAttribute("messaggio", "🗑️ Prodotto eliminato.");

            }

            List<Prodotto> prodotti = prodottoDao.doRetrieveAll();
            request.setAttribute("prodotti", prodotti);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/admin/gestioneProdotti.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("errore500.jsp");
        }
    }
}


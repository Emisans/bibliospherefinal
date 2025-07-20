package Control;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {}

    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean isAdmin = session != null && "admin".equals(session.getAttribute("ruolo"));

        if (!isAdmin) {
            res.sendRedirect(req.getContextPath() + "/errore403.jsp"); // pagina accesso negato
            return;
        }

        chain.doFilter(request, response);
    }
}

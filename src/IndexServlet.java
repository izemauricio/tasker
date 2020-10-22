import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// mapeia a rota index.html para este servlet que redirenciona para o arquivo login.html
@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws IOException, ServletException {
	        RequestDispatcher view = req.getRequestDispatcher("/login.html");
	        view.forward(req, resp);    
	    }

}
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// mapeia a rota /LoginServlet para este servlet que processa GET e POST
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if ((username.equals("Bilbo") && password.equals("Baggins"))
				|| (username.equals("Frodo") && password.equals("Hobbit"))
				|| (username.equals("Gandalf") && password.equals("Wizard"))
				|| (username.equals("Sauron") && password.equals("Evil"))) {

			HttpSession session = req.getSession(true);
			session.setMaxInactiveInterval(60 * 5);
			session.setAttribute("username", username);

			try {
				// req.getRequestDispatcher("TaskerServlet").include(req, res);
				res.sendRedirect("TaskerServlet");
			} catch (IOException e) {
			}
		} else {
			try {
				res.sendRedirect("login.html?error=1");
			} catch (IOException e) {
			}
		}

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException {
	}

}
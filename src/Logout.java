import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Logout")
public class Logout extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException {

		HttpSession session = req.getSession(false);

		if (session == null) {
			try {
				res.sendRedirect("login.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				session.invalidate();
				res.sendRedirect("login.html?error=2");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.swing.text.html.HTMLDocument.Iterator;

@WebServlet("/TaskerServlet")
public class TaskerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, Task> database = new HashMap<Integer, Task>();

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		if (session == null) {
			res.sendRedirect("login.html");
		} else {
			res.setContentType("text/html;charset=UTF-8");

			PrintWriter out = res.getWriter();
			
			String username = (String) session.getAttribute("username");
			String task = req.getParameter("task");
			String color = req.getParameter("color");
			String date = req.getParameter("date");
			String filter = (String) req.getAttribute("filter");
			String todayDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());

			String html = "";

			System.out.println("DADOS DO FORM: "+ username+" "+ task+" "+ color+" "+ date);
			
			if (task != null && color != null && date != null) {
				int id = getId();
				database.put(id, new Task(id, username, task, color, date));
				res.sendRedirect("TaskerServlet");
			} else {
				html = "<!DOCTYPE html>\n"
						+ "<html>\n"
						+ "\n"
						+ "<head>\n"
						+ "	<link rel=\"stylesheet\" href=\"tasker.css\" type=\"text/css\" />\n"
						+ "	<script type=\"text/javascript\" src=\"tasker.js\"></script>\n"
						+ "	<title>Task List</title>\n"
						+ "</head>\n"
						+ "\n"
						+ "<body>\n"
						+ "	<div id=\"TitleBox\">TASKER</div>\n"
						+ "\n"
						+ "	<div id=\"UserBox\">\n"
						+ "		<div id=\"item1\">"
						+ username
						+ "</div>\n"
						+ "		<div id=\"item2\"><a href=\"Logout\">Logout</a></div>\n"
						+ "	</div>\n"
						+ "\n"
						+ "	<div id=\"TaskAddBox\">\n"
						+ "		<form action=\"TaskerServlet\" method=\"POST\">\n"
						+ "			<div id=\"item\"><input name=\"task\" type=\"text\" value=\"Task\" onfocus=\"taskFocus(this)\" onfocusout=\"taskFocusOut(this)\"/></div>\n"
						+ "			<div id=\"item\"><input name=\"color\" type=\"color\" /></div>\n"
						+ "			<div id=\"item\"><input id=\"datee\" name=\"date\" type=\"date\" value=\""
						+ todayDate
						+ "\"/></div>\n"
						+ "			<div id=\"item\"><td><input type=\"submit\" onclick=\"return ssubmit(this);\" value=\"ADD\"></div>\n"
						+ "		</form>\n" + "	</div>\n" + "\n"
						+ "	<div id=\"TitleBox2\">\n" + "	</div>";

				if (filter != null && filter.equals("1")) {
					html += "<div id=\"Incomplete\"><a href=\"TaskerServlet?filter=0\">Show all</a></div>\n";
				} else {
					html += "<div id=\"Incomplete\"><a href=\"TaskerServlet?filter=1\">Show only incompletes</a></div>\n";
				}

				html += "<div id=\"TaskListBox\">\n" + "		<table>\n" + "\n"
						+ "			<tr>\n" + "				<th>TASK</th>\n"
						+ "				<th>COLOR</th>\n" + "				<th>DUE</th>\n"
						+ "				<th>COMPLETED</th>\n" + "				<th></th>\n"
						+ "			</tr>";

				for (Map.Entry pair : database.entrySet()) {

					if (((Task) pair.getValue()).getUsername().equals(username)) {

						task = ((Task) pair.getValue()).getTask();
						color = ((Task) pair.getValue()).getColor();
						date = ((Task) pair.getValue()).getDate();

						String completed = "*";

						Calendar record = ((Task) pair.getValue()).getCalendar();
						Calendar today = Calendar.getInstance();

						if (today.compareTo(record) > 0) {
							completed = "yes";
						} else {
							completed = "no";
						}

						if (filter != null && filter.equals("1")) {
							if (!completed.equals("no")) {
								continue;
							}
						}

						html += "<tr>\n"
								+ "				<td>"
								+ task
								+ "</td>\n"
								+ "				<td><input name=\"color\" type=\"color\" disabled value=\""
								+ color + "\" /></td>\n"
								+ "				<td id=\"due\">" + date + "</td>\n"
								+ "				<td>" + completed + "</td>\n"
								+ "				<td><a href=\"TaskerServlet?delete="
								+ pair.getKey() + "\">X</a></td>\n"
								+ "			</tr>";
					}
				}

				html += "</table>\n" + "	</div>\n" + "\n" + "</body>\n"
						+ "</html>";
			}

			out.print(html);
		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException {

		String id = req.getParameter("delete");
		String filter = req.getParameter("filter");

		if (filter != null && filter.equals("1")) {
			req.setAttribute("filter", "1");
		} else {
			req.setAttribute("filter", "0");
		}

		if (id == null) {
			try {
				doPost(req, res);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				database.remove(new Integer(id));
			} catch (Exception e) {
			}

			try {
				res.sendRedirect("TaskerServlet");
			} catch (IOException e) {
			}
		}
	}

	private int getId() {
		int id = 0;

		while (database.get(id) != null) {
			id++;
		}

		return id;
	}
}
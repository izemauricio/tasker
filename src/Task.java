import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Task {

	int id;
	String username;
	String task;
	Color color;
	Calendar calendar;

	public String getTask() {
		return task;
	}

	public String getUsername() {
		return username;
	}

	public String getColor() {
		return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(),
				color.getBlue());
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(calendar.getTime());
	}

	public Task(int pid, String pusername, String ptask, String pcolor,
			String pdate) {
		id = pid;
		username = pusername;
		task = ptask;
		color = Color.decode(pcolor);
		calendar = new GregorianCalendar(new Integer(pdate.split("-")[0]),
				new Integer(pdate.split("-")[1]) - 1, new Integer(
						pdate.split("-")[2]));
	}
}

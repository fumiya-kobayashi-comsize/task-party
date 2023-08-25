package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.MakeListDAO;
import model.dao.TaskSelectDAO;
import model.entity.TaskBean;
import model.entity.UserBean;


/**
 * 従業員のカレンダー早見表を制御するためのServlet
 * @author Arakawa
 *
 */
@WebServlet("/EmployeeCalendarServlet")
public class EmployeeCalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MakeListDAO listDAO = new MakeListDAO();
		List<UserBean> userList = null;
		try {
			userList = listDAO.selectAllUser();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		List<boolean[]> isEmptyTaskWeekLists = new ArrayList<>();
		LocalDate date = LocalDate.now();
		for (UserBean user : userList) {
			TaskSelectDAO selectDAO = new TaskSelectDAO();
			List<TaskBean> usersTaskList = null;
			try {
				usersTaskList = selectDAO.selectProgressTask(user.getUserId());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			boolean [] isEmptyTaskWeekList = new boolean[7];
			for(int i = 0; i < 7; i++) {
				isEmptyTaskWeekList[i] = isTaskEmpty(usersTaskList, date.plusDays(i));
			}
			isEmptyTaskWeekLists.add(isEmptyTaskWeekList);
		}

		HttpSession session = request.getSession();
		session.setAttribute("user_list", userList);
		session.setAttribute("is_empty_list", isEmptyTaskWeekLists);
		session.setAttribute("date", date);
		RequestDispatcher rd = request.getRequestDispatcher("employee-calendar.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MakeListDAO listDAO = new MakeListDAO();
		List<UserBean> userList = null;
		try {
			userList = listDAO.selectAllUser();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		List<boolean[]> isEmptyTaskWeekLists = new ArrayList<>();
		LocalDate date = (LocalDate)session.getAttribute("date");
		String dateChange = request.getParameter("date_change");
		if(dateChange.equals("next")) {
			if(ChronoUnit.DAYS.between(LocalDate.now(), date) < 28) date = date.plusDays(7);
		}else if(dateChange.equals("prev")){
			if(!date.equals(LocalDate.now())) date = date.minusDays(7);
		}
		for (UserBean user : userList) {
			TaskSelectDAO selectDAO = new TaskSelectDAO();
			List<TaskBean> usersTaskList = null;
			try {
				usersTaskList = selectDAO.selectProgressTask(user.getUserId());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			boolean [] isEmptyTaskWeekList = new boolean[7];
			for(int i = 0; i < 7; i++) {
				isEmptyTaskWeekList[i] = isTaskEmpty(usersTaskList, date.plusDays(i));
			}
			isEmptyTaskWeekLists.add(isEmptyTaskWeekList);
		}


		session.setAttribute("user_list", userList);
		session.setAttribute("is_empty_list", isEmptyTaskWeekLists);
		session.setAttribute("date", date);
		RequestDispatcher rd = request.getRequestDispatcher("employee-calendar.jsp");
		rd.forward(request, response);
	}

	/**
	 * 指定した日付にユーザーの着手中タスクが存在するか判定するメソッド
	 * @param userId
	 * @param checkDate
	 * @return boolean
	 */
	private boolean isTaskEmpty(List<TaskBean> usersTaskList, LocalDate checkDate) {
		boolean isEmpty = true;

		for (TaskBean usersTask : usersTaskList) {
			if(usersTask.getStartDate() == null && usersTask.getLimitDate() == null) {
				isEmpty = false;
				break;
			}
			if(usersTask.getStartDate() == null) {
				if(usersTask.getLimitDate().isBefore(checkDate)) {
					continue;
				}
				isEmpty = false;
				break;
			}
			if(usersTask.getLimitDate() == null) {
				if(usersTask.getStartDate().isAfter(checkDate)) {
					continue;
				}
				isEmpty = false;
				break;
			}


			if(usersTask.getStartDate().isAfter(checkDate) ||
					usersTask.getLimitDate().isBefore(checkDate)) {
				continue;
			}

			isEmpty = false;
			break;
		}
		return isEmpty;

	}

}

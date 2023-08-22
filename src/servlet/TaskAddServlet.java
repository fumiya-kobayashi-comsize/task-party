package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.MakeListDAO;
import model.dao.TaskInsertDAO;
import model.dao.TaskSelectCurrentUserDAO;
import model.dao.TaskSelectDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * Servlet implementation class TaskAddServlet
 */
@WebServlet("/TaskAddServlet")
public class TaskAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<CategoryBean> categoryList = null;
		List<UserBean> userList = null;
		List<StatusBean> statusList = null;

		MakeListDAO listDAO = new MakeListDAO();

		try {
			categoryList = listDAO.selectAllCategory();
			userList = listDAO.selectAllUser();
			statusList = listDAO.selectAllStatus();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("userList", userList);
		session.setAttribute("statusList", statusList);

		RequestDispatcher rd = request.getRequestDispatcher("add-task.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		TaskInsertDAO insertDAO = new TaskInsertDAO();
		TaskSelectCurrentUserDAO currentUserDAO = new TaskSelectCurrentUserDAO();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("user_id");
		int count = 0;
		int currentUsersLimit = 0;
		LocalDate localDate = null;
		LocalDate startDate = null;
		//　　　送られてきたdateをlocaldateへ変換
		if (!request.getParameter("startDate").equals("")) {
			startDate = LocalDate.parse(request.getParameter("startDate"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		if (!request.getParameter("limitDate").equals("")) {
			localDate = LocalDate.parse(request.getParameter("limitDate"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		TaskBean insertTask = new TaskBean();
		insertTask.setTaskName(request.getParameter("taskName"));
		insertTask.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		insertTask.setStartDate(startDate);
		insertTask.setLimitDate(localDate);
		insertTask.setUserId(request.getParameter("userId"));
		insertTask.setStatusCode(request.getParameter("statusCode"));
		insertTask.setMemo(request.getParameter("memo"));


		//タスクを追加可能なら追加
		if (canInsertTask(insertTask)) {
			try {
				count = insertDAO.insertTask(insertTask);
				currentUsersLimit = currentUserDAO.selectCurrentUsersTask(userId);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		session.setAttribute("current_users_limit", currentUsersLimit);

		if (count == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("add-task-error.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("add-task-success.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * タスクを挿入可能か判定するメソッド
	 * @author Arakawa
	 * @param insertTask
	 * @return boolean
	 */
	private boolean canInsertTask(TaskBean insertTask) {
		TaskSelectDAO selectDAO = new TaskSelectDAO();
		List<TaskBean> usersTaskList = null;
		try {
			//タスクを追加しようとしたユーザーの着手中のタスク一覧を取得
			usersTaskList = selectDAO.selectProgressTask(insertTask.getUserId());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//タスクが重複していないか判定
		if(usersTaskList == null) return true;
		if(insertTask.getStartDate() == null && insertTask.getLimitDate() == null) return false;
		for (TaskBean usersTask : usersTaskList) {
			if (usersTask.getStartDate() == null && usersTask.getLimitDate() == null) {
				return false;
			}
			if(insertTask.getStartDate() == null) {
				if(usersTask.getStartDate() == null) return false;
				if (usersTask.getStartDate().isAfter(insertTask.getLimitDate())) {
					continue;
				}
				return false;
			}
			if(insertTask.getLimitDate() == null) {
				if(usersTask.getLimitDate() == null) return false;
				if (usersTask.getLimitDate().isBefore(insertTask.getStartDate())) {
					continue;
				}
				return false;
			}
			if (usersTask.getStartDate() == null) {
				if (usersTask.getLimitDate().isBefore(insertTask.getStartDate())) {
					continue;
				}
				return false;
			}
			if (usersTask.getLimitDate() == null) {
				if (usersTask.getStartDate().isAfter(insertTask.getLimitDate())) {
					continue;
				}
				return false;
			}

			if (usersTask.getStartDate().isAfter(insertTask.getLimitDate()) ||
					usersTask.getLimitDate().isBefore(insertTask.getStartDate())) {
				continue;
			}
			return false;
		}

		return true;
	}

}

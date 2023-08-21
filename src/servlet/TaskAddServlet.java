package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		TaskSelectDAO selectDAO = new TaskSelectDAO();
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
		TaskBean taskBean = new TaskBean();
		taskBean.setTaskName(request.getParameter("taskName"));
		taskBean.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		taskBean.setStartDate(startDate);
		taskBean.setLimitDate(localDate);
		taskBean.setUserId(request.getParameter("userId"));
		taskBean.setStatusCode(request.getParameter("statusCode"));
		taskBean.setMemo(request.getParameter("memo"));

		List<TaskBean> usersTaskList = new ArrayList<>();
		boolean canInsertTask = true;

		try {
			//タスクを追加しようとしたユーザーの着手中のタスク一覧を取得
			usersTaskList = selectDAO.selectProgressTask(taskBean.getUserId());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//タスク登録期間が被っていたらcanInsertTaskにfalseを代入
		for (TaskBean usersTask : usersTaskList) {
			if (usersTask.getStartDate() == null && usersTask.getLimitDate() == null) {
				canInsertTask = false;
				break;
			}
			if (usersTask.getStartDate() == null) {
				if(taskBean.getStartDate() != null) {
					if (usersTask.getLimitDate().isBefore(taskBean.getStartDate())) {
						continue;
					}
				}
				canInsertTask = false;
				break;
			}
			if (usersTask.getLimitDate() == null) {
				if(taskBean.getLimitDate() != null) {
					if (usersTask.getStartDate().isAfter(taskBean.getLimitDate())) {
						continue;
					}
				}
				canInsertTask = false;
				break;
			}

			if(taskBean.getStartDate() != null && taskBean.getLimitDate() != null) {
				if (usersTask.getStartDate().isAfter(taskBean.getStartDate()) ||
						usersTask.getLimitDate().isBefore(taskBean.getLimitDate())) {
					continue;
				}
			}

			canInsertTask = false;
			break;
		}

		//タスクを追加可能なら追加
		if (canInsertTask) {
			try {
				count = insertDAO.insertTask(taskBean);
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

}

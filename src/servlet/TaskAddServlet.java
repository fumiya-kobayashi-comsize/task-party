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
 * タスクの追加を制御するServlet
 * @author Negami Arakawa
 *
 */
@WebServlet("/TaskAddServlet")
public class TaskAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


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
		int insertCount = 0;
		int currentUsersLimit = 0;
		LocalDate localDate = null;
		LocalDate startDate = null;
		String memo =null;
		// 送られてきたdateをlocaldateへ変換
		if (!request.getParameter("startDate").equals("")) {
			startDate = LocalDate.parse(request.getParameter("startDate"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		if (!request.getParameter("limitDate").equals("")) {
			localDate = LocalDate.parse(request.getParameter("limitDate"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		if(!request.getParameter("memo").equals("")) {
			memo=request.getParameter("memo");
		}
		TaskBean insertTask = new TaskBean();
		insertTask.setTaskName(request.getParameter("taskName"));
		insertTask.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		insertTask.setStartDate(startDate);
		insertTask.setLimitDate(localDate);
		insertTask.setUserId(request.getParameter("userId"));
		insertTask.setStatusCode(request.getParameter("statusCode"));
		insertTask.setMemo(memo);

		// タスクが追加可能なら追加
		if (canInsertTask(insertTask)) {
			try {
				insertCount = insertDAO.insertTask(insertTask);
				currentUsersLimit = currentUserDAO.selectCurrentUsersTask(userId);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		session.setAttribute("current_users_limit", currentUsersLimit);

		if (insertCount == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("add-task-error.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("add-task-success.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * 引数にしたタスクと、同ユーザーの既存タスクの期間を比較し、挿入可能か判定するメソッド
	 * @param insertTask
	 * @return boolean
	 */
	private boolean canInsertTask(TaskBean insertTask) {
		// 追加するタスクが未着手もしくは完了なら挿入可能
		if(insertTask.getStatusCode().equals("00") || insertTask.getStatusCode().equals("99")) return true;

		TaskSelectDAO selectDAO = new TaskSelectDAO();
		List<TaskBean> usersTaskList = null;
		try {
			// タスクを追加しようとしたユーザーの着手中のタスク一覧を取得
			usersTaskList = selectDAO.selectProgressTask(insertTask.getUserId());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// タスクが重複していないか判定
		if(usersTaskList.isEmpty()) return true;
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

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
import model.dao.TaskSelectCurrentUserDAO;
import model.dao.TaskSelectDAO;
import model.dao.TaskUpdateDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.TaskShowBean;
import model.entity.UserBean;

/**
 * Servlet implementation class TaskEditServlet
 */
/**
 * タスクの編集を制御するServlet
 * @author Arakawa
 *
 */
@WebServlet("/TaskEditServlet")
public class TaskEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		TaskShowBean task = new TaskShowBean();
		List<CategoryBean> categoryList = null;
		List<UserBean> userList = null;
		List<StatusBean> statusList = null;
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		TaskSelectDAO dao = new TaskSelectDAO();
		MakeListDAO mldao = new MakeListDAO();
		try {
			task = dao.selectTaskShow(taskId);
			categoryList = mldao.selectAllCategory();
			userList = mldao.selectAllUser();
			statusList = mldao.selectAllStatus();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(task.getMemo() == null) {
			task.setMemo("");
		}
		HttpSession session = request.getSession();
		session.setAttribute("task", task);
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("userList", userList);
		session.setAttribute("statusList", statusList);
		RequestDispatcher rd = request.getRequestDispatcher("edit-task.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		TaskBean updateTask = new TaskBean();
		updateTask.setTaskId(Integer.parseInt(request.getParameter("task_id")));
		updateTask.setTaskName(request.getParameter("task_name"));
		updateTask.setCategoryId(Integer.parseInt(request.getParameter("category_id")));
		LocalDate startDate = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("user_id");
		int currentUsersLimit =0;


		if(!request.getParameter("start_date").equals("")) {
			 startDate = LocalDate.parse(request.getParameter("start_date"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		updateTask.setStartDate(startDate);
		LocalDate localDate = null;
		if(!request.getParameter("limit_date").equals("")) {
			 localDate = LocalDate.parse(request.getParameter("limit_date"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		updateTask.setLimitDate(localDate);
		updateTask.setUserId(request.getParameter("user_id"));
		updateTask.setStatusCode(request.getParameter("status_code"));
		updateTask.setMemo(request.getParameter("memo"));

		TaskSelectDAO selectDao = new TaskSelectDAO();
		TaskUpdateDAO dao = new TaskUpdateDAO();
		TaskSelectCurrentUserDAO currentUserDAO =new TaskSelectCurrentUserDAO();
		int updateCount = 0;
		// 更新可能ならタスクを更新する
		if(canUpdateTask(updateTask)) {
			try {
				if(!updateTask.equals(selectDao.selectTask(updateTask.getTaskId()))) {
					updateCount = dao.updateTask(updateTask);
					currentUsersLimit=currentUserDAO.selectCurrentUsersTask(userId);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		if (updateCount != 0) {
			TaskShowBean taskShow = new TaskShowBean();
			try {
				taskShow = selectDao.selectTaskShow(updateTask.getTaskId());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("task", taskShow);
			session.setAttribute("current_users_limit",currentUsersLimit );
			RequestDispatcher rd = request.getRequestDispatcher("edit-task-success.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("edit-task-error.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * 更新するタスクと同ユーザーの既存タスクの期間を比較し、更新可能か判定するメソッド
	 * @param updateTask
	 * @return boolean
	 */
	private boolean canUpdateTask(TaskBean updateTask) {
		TaskSelectDAO selectDAO = new TaskSelectDAO();
		List<TaskBean> usersTaskList = null;
		try {
			// タスクを更新しようとしたユーザーの着手中のタスク一覧(更新タスクを除く)を取得
			usersTaskList = selectDAO.selectOtherProgressTask(updateTask.getUserId(), updateTask.getTaskId());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// タスクが重複していないか判定
		if(usersTaskList == null) return true;
		if(updateTask.getStartDate() == null && updateTask.getLimitDate() == null) return false;
		for (TaskBean usersTask : usersTaskList) {
			if (usersTask.getStartDate() == null && usersTask.getLimitDate() == null) {
				return false;
			}
			if(updateTask.getStartDate() == null) {
				if(usersTask.getStartDate() == null) return false;
				if (usersTask.getStartDate().isAfter(updateTask.getLimitDate())) {
					continue;
				}
				return false;
			}
			if(updateTask.getLimitDate() == null) {
				if(usersTask.getLimitDate() == null) return false;
				if (usersTask.getLimitDate().isBefore(updateTask.getStartDate())) {
					continue;
				}
				return false;
			}
			if (usersTask.getStartDate() == null) {
				if (usersTask.getLimitDate().isBefore(updateTask.getStartDate())) {
					continue;
				}
				return false;
			}
			if (usersTask.getLimitDate() == null) {
				if (usersTask.getStartDate().isAfter(updateTask.getLimitDate())) {
					continue;
				}
				return false;
			}

			if (usersTask.getStartDate().isAfter(updateTask.getLimitDate()) ||
					usersTask.getLimitDate().isBefore(updateTask.getStartDate())) {
				continue;
			}
			return false;
		}

		return true;
	}

}

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
 * タスク編集Servlet
 * @author arakawa
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
			task = dao.selectTask(taskId);
			categoryList = mldao.selectAllCategory();
			userList = mldao.selectAllUser();
			statusList = mldao.selectAllStatus();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
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
		TaskBean task = new TaskBean();
		task.setTaskId(Integer.parseInt(request.getParameter("task_id")));
		task.setTaskName(request.getParameter("task_name"));
		task.setCategoryId(Integer.parseInt(request.getParameter("category_id")));
		LocalDate localDate = null;
		if(!request.getParameter("limit_date").equals("")) {
			 localDate = LocalDate.parse(request.getParameter("limit_date"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		task.setLimitDate(localDate);
		task.setUserId(request.getParameter("user_id"));
		task.setStatusCode(request.getParameter("status_code"));
		task.setMemo(request.getParameter("memo"));

		TaskUpdateDAO dao = new TaskUpdateDAO();
		int updateCount = 0;
		try {
			updateCount = dao.updateItem(task);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if (updateCount != 0) {
			TaskShowBean taskShow = new TaskShowBean();
			TaskSelectDAO selectDao = new TaskSelectDAO();
			try {
				taskShow = selectDao.selectTask(task.getTaskId());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			request.setAttribute("task", taskShow);
			RequestDispatcher rd = request.getRequestDispatcher("edit-task-success.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("edit-task-error.jsp");
			rd.forward(request, response);
		}
	}

}

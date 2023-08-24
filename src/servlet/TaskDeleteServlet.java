package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CommentDeleteAllDAO;
import model.dao.TaskDeleteDAO;
import model.dao.TaskSelectCurrentUserDAO;
import model.dao.TaskSelectDAO;
import model.entity.TaskShowBean;

/**
 * タスクの削除を制御するサーブレット
 * @author Koseki
 */
@WebServlet("/TaskDeleteServlet")
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("task_id"));
		TaskSelectDAO dao = new TaskSelectDAO();

		try {
			TaskShowBean task = dao.selectTaskShow(id);
			HttpSession session = request.getSession();
			session.setAttribute("task", task);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("delete-task-confirm.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		TaskDeleteDAO dao = new TaskDeleteDAO();
		CommentDeleteAllDAO commentDelete = new CommentDeleteAllDAO();
		TaskSelectCurrentUserDAO currentUserDAO =new TaskSelectCurrentUserDAO();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("user_id");
		int currentUsersLimit =0;
		int processNumber = 0; //処理件数

		try {
			//処理
			commentDelete.deleteAllComment(Integer.parseInt(request.getParameter("task_id"))) ;
			processNumber = dao.deleteTask(Integer.parseInt(request.getParameter("task_id")));
			currentUsersLimit=currentUserDAO.selectCurrentUsersTask(userId);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		session.setAttribute("current_users_limit",currentUsersLimit );
		//処理件数で遷移先を
		if(processNumber>0) {
			//成功画面
			RequestDispatcher rd = request.getRequestDispatcher("delete-task-success.jsp");
			rd.forward(request, response);
		} else {
			//失敗画面
			RequestDispatcher rd = request.getRequestDispatcher("delete-task-error.jsp");
			rd.forward(request, response);
		}
	}
}

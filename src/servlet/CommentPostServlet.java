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

import model.dao.CommentPostDAO;
import model.dao.TaskSelectDAO;
import model.entity.CommentBean;
import model.entity.TaskShowBean;

/**
 * Servlet implementation class CommentPostServlet
 */
@WebServlet("/CommentPostServlet")
public class CommentPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentPostServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		TaskShowBean task = new TaskShowBean();
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		TaskSelectDAO selectDAO = new TaskSelectDAO();

		try {
			task = selectDAO.selectTaskShow(taskId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("task", task);

		RequestDispatcher rd = request.getRequestDispatcher("add-comment.jsp");
		rd.forward(request, response);
	}

	/**
	 * コメント投稿servlet
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CommentPostDAO postDAO = new CommentPostDAO();
		CommentBean commnetBean = new CommentBean();
		HttpSession session = request.getSession();
		TaskShowBean task = (TaskShowBean) session.getAttribute("task_show");
		int count = 0;

		commnetBean.setTaskId(task.getTaskId());
		commnetBean.setCommentUser(session.getAttribute("userId").toString());
		commnetBean.setCommentContent(request.getParameter("comment"));

		try {
			count = postDAO.postComment(commnetBean);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (count == 1) {
			RequestDispatcher rd = request.getRequestDispatcher("add-comment-success.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("add-comment-error.jsp");
			rd.forward(request, response);
		}
	}

}

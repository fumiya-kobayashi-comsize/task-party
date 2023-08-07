package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentDeleteDAO;

/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/CommentDeleteServlet")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int count = 0;
		CommentDeleteDAO deleteDAO = new CommentDeleteDAO();
		int commentId = Integer.parseInt(request.getParameter("comment_id"));

		try {
			count = deleteDAO.deleteComment(commentId);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (count == 1) {
			RequestDispatcher rd = request.getRequestDispatcher("delete-comment-success.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("delete-comment-error.jsp");
			rd.forward(request, response);
		}
	}

}

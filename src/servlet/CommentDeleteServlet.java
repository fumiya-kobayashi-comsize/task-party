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

import model.dao.CommentDeleteDAO;


/**
 * @author Negami
 *
 */
@WebServlet("/CommentDeleteServlet")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CommentDeleteDAO deleteDAO = new CommentDeleteDAO();

		String userId= request.getParameter("user_id");
		int commentId=Integer.parseInt(request.getParameter("comment_id"));
		int count = 0;
		HttpSession session = request.getSession();

		if(userId.equals(session.getAttribute("user_id"))) {
		try {
			count = deleteDAO.deleteComment(commentId);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
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

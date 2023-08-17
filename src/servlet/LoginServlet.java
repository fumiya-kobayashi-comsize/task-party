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

import model.dao.UserDAO;
import util.PasswordUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		UserDAO userDAO = new UserDAO();

		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");
		String safetyPassword = PasswordUtil.getSafetyPassword(password, userId);

//		ユーザーが存在するかを判定するための変数
		boolean isMatch = false;
//		セッションに名前をあげるための変数
		String userName = null;
//		ユーザがロックされているかを判定するための変数
		int locked = 0;
//		ユーザのログイン試行回数
		int attempt = 0;
//		管理者の判定
		boolean admin = false;

		try {
			isMatch = userDAO.matchUser(userId, safetyPassword);
			if(isMatch) {
				userName = userDAO.selectUser(userId);
				admin = userDAO.adminJudge(userId);
			} else {
				attempt = userDAO.loginAttempt(userId);
				locked = userDAO.isLocked(userId, attempt);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("user_name", userName);
		session.setAttribute("user_id", userId);
		request.setAttribute("locked", locked);
		session.setAttribute("admin", admin);
		if (isMatch) {
			RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

	}

}

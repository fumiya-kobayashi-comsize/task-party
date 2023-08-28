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

import model.dao.TaskSelectCurrentUserDAO;
import model.dao.UserDAO;
import util.PasswordUtil;


/**
 * @author Negami Koseki
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		TaskSelectCurrentUserDAO currentUserDAO =new TaskSelectCurrentUserDAO();

		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");
		String safetyPassword = PasswordUtil.getSafetyPassword(password, userId);
		int currentUsersLimit =0;

//		ユーザーが存在するかを判定するための変数
		boolean isMatch = false;
//		セッションに名前をあげるための変数
		String userName = null;
//		ユーザのログイン試行回数
		int attempt = 0;
//		管理者の判定
		boolean admin = false;
//		ユーザーがロックされているか判定
		boolean isLocked = false;

		try {
//			ユーザーIDが存在するか、パスワードが合っているか
			isMatch = userDAO.matchUser(userId, safetyPassword);
//			ユーザーがロックされているか
			isLocked = userDAO.isLocked(userId);

//			ユーザーの着手しているタスクの期限を取得
			currentUsersLimit=currentUserDAO.selectCurrentUsersTask(userId);

//			ユーザーID、パスワードが合っている場合
			if(isMatch) {
//				ユーザー名を取得
				userName = userDAO.selectUser(userId);
//				管理者であるかの判定
				admin = userDAO.adminJudge(userId);

//			IDパスいずれか、もしくは両方が間違えている場合
			} else {
//				ユーザーIDからログイン試行回数を取得
				attempt = userDAO.getLoginAttempt(userId);
//				試行回数をインクリメント、一定以上でロック
				userDAO.updateAttempt(userId, attempt);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("user_name", userName);
		session.setAttribute("user_id", userId);
		session.setAttribute("current_users_limit",currentUsersLimit );
		request.setAttribute("isLocked", isLocked);
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

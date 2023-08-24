package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AdminUnlockUserDAO;

/**
 * 管理者によるロックされているユーザーの解除を制御をするサーブレット
 * @author Koseki
 */
@WebServlet("/AdminUnlockUser")
public class AdminUnlockUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		AdminUnlockUserDAO dao = new AdminUnlockUserDAO();
		try {
			dao.unlockUser(request.getParameter("unlock"));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("AdminLockedUserListServlet");
		rd.forward(request, response);
	}
}

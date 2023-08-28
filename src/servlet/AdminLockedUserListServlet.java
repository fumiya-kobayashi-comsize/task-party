package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AdminLockedUserListDAO;
import model.entity.UserBean;

/**
 * 管理者によるロックされたユーザーのリストを制御するサーブレット
 * @author Koseki
 */
@WebServlet("/AdminLockedUserList")
public class AdminLockedUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<UserBean> lockedUserList = null;
		AdminLockedUserListDAO dao = new AdminLockedUserListDAO();
		try {
			lockedUserList = dao.lockedUserList();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		request.setAttribute("lockedUserList", lockedUserList);
		RequestDispatcher rd = request.getRequestDispatcher("admin-locked-user-list.jsp");
		rd.forward(request, response);
	}
}

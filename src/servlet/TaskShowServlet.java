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
import javax.servlet.http.HttpSession;

import model.dao.TaskSelectAllDAO;
import model.entity.TaskShowBean;

/**
 *	タスクの全表示を制御するサーブレット
 * @author Koseki
 *
 */
@WebServlet("/TaskShowServlet")
public class TaskShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<TaskShowBean> taskList = null;

		TaskSelectAllDAO dao = new TaskSelectAllDAO();

		try {
			taskList = dao.SelectAll();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("taskList", taskList);

		RequestDispatcher rd = request.getRequestDispatcher("show-task.jsp");
		rd.forward(request, response);
	}

}

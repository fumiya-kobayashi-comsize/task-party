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

import model.dao.TaskSelectAllDAO;
import model.entity.TaskShowBean;

/**
 * Servlet implementation class TaskShowServlet
 */
@WebServlet("/TaskShowServlet")
public class TaskShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<TaskShowBean> taskList = null;

		TaskSelectAllDAO dao = new TaskSelectAllDAO();

		try {
			taskList = dao.SelectAll();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("taskList", taskList);

		RequestDispatcher rd = request.getRequestDispatcher("show-task.jsp");
		rd.forward(request, response);
	}

}

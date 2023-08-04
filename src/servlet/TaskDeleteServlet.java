package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskDeleteDAO;

/**
 * Servlet implementation class TaskDeleteServlet
 */
@WebServlet("/TaskDeleteServlet")
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDeleteServlet() {
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

		TaskDeleteDAO dao = new TaskDeleteDAO();
		int processNumber = 0; //処理件数

		try {
			//処理
			processNumber = dao.deleteTask(Integer.parseInt(request.getParameter("taskId")));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
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

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

import model.dao.CommentBrowseDAO;
import model.dao.TaskSelectDAO;
import model.entity.CommentBean;
import model.entity.TaskShowBean;

/**
 * タスクに付随するコメントの表示を制御するサーブレット
 * @author Koseki
 */
@WebServlet("/CommentBrowseServlet")
public class CommentBrowseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//魔法の呪文
		request.setCharacterEncoding("UTF-8");

		List<CommentBean> commentList = null;
		TaskShowBean taskShow = new TaskShowBean();

		//DAO有効化
		CommentBrowseDAO commentDAO = new CommentBrowseDAO();
		TaskSelectDAO taskDAO = new TaskSelectDAO();

		//タスクの選択とそのタスクのコメントリスト取得
		try {
			commentList = commentDAO.TaskComment(Integer.parseInt(request.getParameter("task_id")));
			taskShow = taskDAO.selectTaskShow(Integer.parseInt(request.getParameter("task_id")));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("comment_list", commentList);
		session.setAttribute("task_show", taskShow);


		RequestDispatcher rd = request.getRequestDispatcher("show-comment.jsp");
		rd.forward(request, response);
	}

}

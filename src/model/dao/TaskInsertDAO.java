package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * タスク登録と既存のカテゴリネーム、ユーザーネーム、ステータスネームを取得
 * @author 根上
 *
 */
public class TaskInsertDAO {

	/**
	 * @author 根上
	 * @return カテゴリー情報リスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<CategoryBean> selectAllCategory() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM m_category";

		List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				int categoryId = res.getInt("category_id");
				String categoryName = res.getString("category_name");

				CategoryBean categoryBean = new CategoryBean();
				categoryBean.setCategoryId(categoryId);
				categoryBean.setCategoryName(categoryName);

				categoryList.add(categoryBean);
			}
		}
		return categoryList;
	}

	/**
	 *
	 *@author 根上
	 * @return ユーザー情報リスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<UserBean> selectAllUser() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM m_user";

		List<UserBean> userList = new ArrayList<>();
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				String userId = res.getString("user_id");
				String userName = res.getString("user_name");

				UserBean userBean = new UserBean();
				userBean.setUserId(userId);
				userBean.setUserName(userName);

				userList.add(userBean);
			}
		}
		return userList;
	}

	/**
	 * @author 根上
	 * @returnステータス情報リスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<StatusBean> selectAllStatus() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM m_status";

		List<StatusBean> statusList = new ArrayList<>();
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				String statusCode = res.getString("status_code");
				String statusName = res.getString("status_name");

				StatusBean statusBean = new StatusBean();
				statusBean.setStatusCode(statusCode);
				statusBean.setStatusName(statusName);

				statusList.add(statusBean);
			}
		}
		return statusList;
	}

	/**
	 * タスク登録メソッド
	 * @author 根上
	 * @param taskBean
	 * @return	insert成否判定のためのcount
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int insertTask(TaskBean taskBean) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO t_task (task_name,category_id,limit_date,user_id,status_code,memo) VALUES(?,?,?,?,?,?)";
		int count = 0;

		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO t_task ");
		sb.append(" (task_name,category_id ");
		if (taskBean.getLimitDate() != null) {
			sb.append(" ,limit_date ");
		}
		sb.append(" ,user_id,status_code ");
		if (!taskBean.getMemo().equals("")) {
			sb.append(",memo");
		}
		sb.append(") VALUES(?,?");
		if (taskBean.getLimitDate() != null) {
			sb.append(",?");
		}
		sb.append(",?,?");
		if (!taskBean.getMemo().equals("")) {
			sb.append(",?");
		}
		sb.append(")");
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, taskBean.getTaskName());
			pstmt.setInt(2, taskBean.getCategoryId());

			if (taskBean.getLimitDate() != null) {
				pstmt.setDate(3, Date.valueOf(taskBean.getLimitDate()));
			} else {
				pstmt.setDate(3, null);
			}
			pstmt.setString(4, taskBean.getUserId());
			pstmt.setString(5, taskBean.getStatusCode());
			pstmt.setString(6, taskBean.getMemo());

			count = pstmt.executeUpdate();
		}

		return count;
	}
}

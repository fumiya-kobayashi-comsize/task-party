package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.UserBean;

public class MakeListDAO {
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
}

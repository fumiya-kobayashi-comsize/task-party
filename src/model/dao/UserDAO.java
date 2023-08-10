package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ログイン判定をするメソッドを格納
 * @author 根上
 *
 *
 */
public class UserDAO {

	/**
	 * ユーザーIDとパスワードが合致しているかを判定するメソッド
	 * @author 根上
	 * @param userBean
	 * @return 変数match  内容true or false
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean matchUser(String userId, String password) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM m_user WHERE user_id=? AND password=?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				return true;
			}
			return false;
		}
	}

	/**
	 * 入力されたIDと関連するユーザーネームを抽出するメソッド
	 * @author 根上
	 * @param userId
	 * @return	UserBean
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String selectUser(String userId) throws SQLException, ClassNotFoundException {
		String sql = "SELECT user_name FROM m_user WHERE user_id=?";
		String userName = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, userId);
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				userName = res.getString("user_name");
			}
		}
		return userName;
	}

}

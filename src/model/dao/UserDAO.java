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
			if (res.next()) {
				if (res.getBoolean("is_locked")) {
					return false;
				} else if (res.getInt("login_attempts") <= 5) {
					return true;
				}
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
			if (res.next()) {
				userName = res.getString("user_name");
			}
		}
		return userName;
	}

	/**
	 * 存在するユーザーのログイン試行回数をインクリメントする
	 * 一定回数以上でロック
	 * @author koseki
	 * @param isMatch
	 * @return locked
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int updateAttempt(String userId, int attempt) throws SQLException, ClassNotFoundException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(" m_user ");
		sb.append("SET ");
		sb.append(" login_attempts = login_attempts + 1 ");
		if (attempt >= 6) {
			sb.append(" , is_locked = true ");
		}
		sb.append("WHERE user_id = ? ");
		String sql = sb.toString();

		//ユーザがロックされているかの判定
		int result = 0;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		}
		return result;
	}

	/**
	 * ログイン試行回数を取得
	 * @param userId
	 * @return ログイン試行回数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int getLoginAttempt(String userId) throws SQLException, ClassNotFoundException {
		String sql = "SELECT login_attempts FROM m_user WHERE user_id = ?";
		int attempt = 0;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, userId);
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				attempt = res.getInt("login_attempts");
			}
		}
		return attempt;
	}

	/**
	 * 管理者を判定
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean adminJudge(String userId) throws SQLException, ClassNotFoundException {
		String sql = "SELECT is_admin FROM m_user WHERE user_id = ? ";
		boolean admin = false;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, userId);
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				admin = res.getBoolean("is_admin");
			}
		}
		return admin;
	}

	public boolean isLocked(String userId) throws SQLException, ClassNotFoundException {
		String sql = "SELECT is_locked FROM m_user WHERE user_id = ? ";
		boolean lock = false;

		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, userId);
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				lock = res.getBoolean("is_locked");
			}
		}
		return lock;
	}
}

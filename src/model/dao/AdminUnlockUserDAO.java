package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ロックされたユーザーをアンロックするための
 * データベースに接続するDAO
 * @author Koseki
 *
 */
public class AdminUnlockUserDAO {

	/**
	 * 管理者がロックされているユーザーをアンロックするためのメソッド
	 * @return ロックが解除されたかを確認するための変数 0 or 1
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int unlockUser(String userId) throws SQLException, ClassNotFoundException {
		int res = 0;
		String sql = "UPDATE m_user SET login_attempts = 0, is_locked = false WHERE user_id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, userId);
			res = pstmt.executeUpdate();
		}
		return res;
	}
}

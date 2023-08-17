package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.UserBean;

/**
 *
 * @author koseki
 *
 */
public class AdminLockedUserListDAO {

	public List<UserBean> lockedUserList() throws SQLException, ClassNotFoundException{
		String sql = "SELECT * FROM m_user WHERE is_locked = true ";

		List<UserBean> lockedUserList = new ArrayList<UserBean>();
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				String userId = res.getString("user_id");
				String userName = res.getString("user_name");
				int attempt = res.getInt("login_attempts");
				boolean locked = res.getBoolean("is_locked");


				UserBean userBean = new UserBean();
				userBean.setUserId(userId);
				userBean.setUserName(userName);
				userBean.setAtpt(attempt);
				userBean.setLocked(locked);

				lockedUserList.add(userBean);
			}
		}
		return lockedUserList;
	}
}

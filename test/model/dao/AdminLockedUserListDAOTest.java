package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.UserBean;

class AdminLockedUserListDAOTest {

	@Test
	void test() {
		List<UserBean> userList = new ArrayList<UserBean>();
		AdminLockedUserListDAO dao = new AdminLockedUserListDAO();

		try {
			userList = dao.lockedUserList();

			for(UserBean user : userList) {
				assertTrue(user.getLocked());
			}
		} catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

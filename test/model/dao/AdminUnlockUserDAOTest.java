package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserBean;

class AdminUnlockUserDAOTest {

	@Test
	void test1UnlockUser() {
		int count = 0;
		UserBean user = new UserBean();
		user.setUserId("user");
		user.setPass("5a63524297fbbf5df0f2f10ff13fba9a19168b9d7a3a4e76fddc81e12f46b2f1");
		user.setUserName("一般ユーザー");
		user.setAdmin(false);
		user.setAtpt(6);
		user.setLocked(true);
		AdminUnlockUserDAO dao = new AdminUnlockUserDAO();

		try {
			count = dao.unlockUser(user.getUserId());
		} catch (SQLException | ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		assertEquals(1, count);

	}

	@Test
	void test2UnlockUser() {
		int count = 0;
		UserBean user = new UserBean();
		user.setUserId("admin");
		user.setPass("3f74691ad7292c5f0ee29cc8fba0b03463e3b1c4a1d368838dbe11b925d244c4");
		user.setUserName("管理者ユーザー");
		user.setAdmin(true);
		user.setAtpt(0);
		user.setLocked(false);
		AdminUnlockUserDAO dao = new AdminUnlockUserDAO();

		try {
			count = dao.unlockUser(user.getUserId());
		} catch (SQLException | ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		assertEquals(1, count);

	}
}

package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import util.PasswordUtil;

/**
 * idとpasswordが合致を判定するメソッドのテストクラス
 */
public class UserDAOTest {

	@Test
	void matchUser1() {

		boolean match = false;
		UserDAO userDAO = new UserDAO();
		PasswordUtil hash = new PasswordUtil();

		String userId = "admin";
		String userPassword = hash.getSafetyPassword("password", userId);
		try {
			for (int i = 0; i < 6; i++) {
				match = userDAO.matchUser(userId, userPassword);
				assertTrue(match);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void matchUser2() {

		boolean match = false;
		UserDAO userDAO = new UserDAO();
		PasswordUtil hash = new PasswordUtil();

		String userId = "user";
		String userPassword = hash.getSafetyPassword("1234567", userId);
		try {
			for (int i = 0; i < 6; i++) {
				int attempt = userDAO.getLoginAttempt(userId);
				userDAO.updateAttempt(userId, attempt);
				match = userDAO.matchUser(userId, userPassword);
				assertFalse(match);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void matchUser3() {

		boolean match = false;
		UserDAO userDAO = new UserDAO();
		PasswordUtil hash = new PasswordUtil();

		String userId = "admin";
		String userPassword = hash.getSafetyPassword("password", userId);
		String wrongPassword = hash.getSafetyPassword("123456", userId);
		try {
			for (int i = 0; i < 5; i++) {
				int attempt = userDAO.getLoginAttempt(userId);
				userDAO.updateAttempt(userId, attempt);
				match = userDAO.matchUser(userId, wrongPassword);
			}
			for (int i = 0; i < 6; i++) {
				int attempt = userDAO.getLoginAttempt(userId);
				userDAO.updateAttempt(userId, attempt);
				match = userDAO.matchUser(userId, userPassword);
				assertFalse(match);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void matchUserFailure() {

		boolean match = false;
		UserDAO userDAO = new UserDAO();

		String userId = "";
		String userPassword = "";
		try {
			for (int i = 0; i < 6; i++) {
				match = userDAO.matchUser(userId, userPassword);
				assertFalse(match);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	void selectUser() {

		UserDAO userDAO = new UserDAO();

		String name = null;
		String userId = "admin";
		try {
			name = userDAO.selectUser(userId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロックd
			e.printStackTrace();
		}
		assertEquals("管理者ユーザー", name);
	}

	@Test
	void selectUserFailure() {

		UserDAO userDAO = new UserDAO();

		String name = null;
		String userId = "";
		try {
			name = userDAO.selectUser(userId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロックd
			e.printStackTrace();
		}
		assertNull(name);
	}

	@Test
	void lockedUser() {

		boolean match = false;
		UserDAO userDAO = new UserDAO();
		PasswordUtil hash = new PasswordUtil();

		String userId = "user";
		String userPassword = hash.getSafetyPassword("password", userId);
		try {
			for (int i = 0; i < 6; i++) {
				match = userDAO.matchUser(userId, userPassword);
				assertFalse(match);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void updateUserAttempt() {

		int count = 0;
		int attempt = 0;
		UserDAO dao = new UserDAO();

		String userId = "user";

		try {
			for (int i = 0; i < 6; i++) {
				attempt = dao.getLoginAttempt(userId);
				count = dao.updateAttempt(userId, attempt);
				assertEquals(1, count);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void adminJudge1() {

		boolean result = false;
		UserDAO dao = new UserDAO();

		String userId = "admin";

		try {
			result = dao.adminJudge(userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertTrue(result);
	}

	@Test
	void adminJudge2() {

		boolean result = false;
		UserDAO dao = new UserDAO();

		String userId = "user";

		try {
			dao.adminJudge(userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertFalse(result);
	}

	@Test
	void isLocked1() {

		boolean result = false;
		UserDAO dao = new UserDAO();

		String userId = "admin";

		try {
			dao.isLocked(userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertFalse(result);
	}

	@Test
	void isLocked2() {

		boolean result = false;
		UserDAO dao = new UserDAO();

		String userId = "user";

		try {
			dao.isLocked(userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertFalse(result);
	}

	@Test
	void getAttempt() {
		int result = 0;
		UserDAO dao = new UserDAO();

		String userId = "aaa";

		try {
			result = dao.getLoginAttempt(userId);
		} catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(0, result);
	}

	@Test
	void isLockedFalse() {
		boolean result = false;
		UserDAO dao = new UserDAO();

		String userId = "aaa";

		try {
			result = dao.isLocked(userId);
		} catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		assertFalse(result);
	}

	@Test
	void adminJudgeFalse() {
		boolean result = false;
		UserDAO dao = new UserDAO();

		String userId = "aaa";

		try {
			result = dao.adminJudge(userId);
		} catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		assertFalse(result);
	}
}

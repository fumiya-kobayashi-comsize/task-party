package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

/**
 * idとpasswordが合致を判定するメソッドのテストクラス
 */
public class UserDAOTest {

	@Test
	void matchUser() {

		boolean match = false;
		UserDAO userDAO = new UserDAO();

		String userId="admin";
		String userPassword="password";
		try {
			match = userDAO.matchUser(userId, userPassword);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertTrue(match);
	}

	@Test
	void matchUserFailure() {

		boolean match = false;
		UserDAO userDAO = new UserDAO();

		String userId="";
		String userPassword="";
		try {
			match = userDAO.matchUser(userId, userPassword);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		assertFalse(match);
	}
	@Test
	void selectUser() {

		UserDAO userDAO = new UserDAO();

		String name = null;
		String userId="admin";
		try {
			name = userDAO.selectUser(userId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロックd
			e.printStackTrace();
		}
		assertEquals("テストユーザー", name);
	}
	@Test
	void selectUserFailure(){

		UserDAO userDAO = new UserDAO();

		String name = null;
		String userId="";
		try {
			name = userDAO.selectUser(userId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロックd
			e.printStackTrace();
		}
		assertNull(name);
	}
}

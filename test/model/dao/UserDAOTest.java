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
	void matchUser() {

		boolean match = false;
		UserDAO userDAO = new UserDAO();
		PasswordUtil hash = new PasswordUtil();

		String userId="admin";
		String userPassword=hash.getSafetyPassword("password", userId);
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
		assertEquals("管理者ユーザー", name);
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

package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TaskSelectCurrentUserDAOTest {

	@Test
	void test1() {
		int result = 0;
		TaskSelectCurrentUserDAO dao = new TaskSelectCurrentUserDAO();

		try {
			result = dao.selectCurrentUsersTask("admin");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(0, result);
	}

	@Test
	void test2() {
		int result = 0;
		TaskSelectCurrentUserDAO dao = new TaskSelectCurrentUserDAO();

		try {
			result = dao.selectCurrentUsersTask("user");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(7, result);
	}

}

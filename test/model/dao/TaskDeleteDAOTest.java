package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TaskDeleteDAOTest {

	@Test
	void testDeleteTask() {
		int count = 0;
		TaskDeleteDAO dao = new TaskDeleteDAO();
		try {
			count = dao.deleteTask(3);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(1, count);
	}

}

package model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.TaskShowBean;

class TaskSelectDAOTest {

	@Test
	void testSelectTaskShow() {
		TaskSelectDAO dao = new TaskSelectDAO();
		TaskShowBean ts = new TaskShowBean();
		try {
			ts = dao.selectTaskShow(1);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(1, ts.getTaskId());
		assertEquals("サンプルタスク", ts.getTaskName());
	}

	@Test
	void testSelectTask() {
		fail("まだ実装されていません");
	}

}

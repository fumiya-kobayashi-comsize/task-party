package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.TaskShowBean;

class TaskSelectAllDAOTest {

	@Test
	void testSelectAll() {
		List<TaskShowBean> taskList = new ArrayList<TaskShowBean>();
		TaskSelectAllDAO dao = new TaskSelectAllDAO();

		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			taskList = dao.SelectAll();
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("task_db.t_task.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);

			//読み込み行
			String line;

			//読み込み行数の管理
			int i = 0;

			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {
				//先頭行は列名
				if (i != 0) {
					//カンマで分割した内容を配列に格納する
					String[] data = line.split(",");
					assertEquals(Integer.parseInt(data[0]), taskList.get(i - 1).getTaskId());
					assertEquals(data[1], taskList.get(i - 1).getTaskName());
					assertEquals(data[2], taskList.get(i - 1).getCategoryName());
					assertEquals(data[3], taskList.get(i - 1).getLimitDate());
					assertEquals(data[4], taskList.get(i - 1).getUserName());
					assertEquals(data[5], taskList.get(i - 1).getStatusName());
					assertEquals(data[6], taskList.get(i - 1).getMemo());
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

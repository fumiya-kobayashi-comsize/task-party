package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;
import model.entity.TaskShowBean;

class TaskSelectDAOTest {

	@Test
	void testSelectTaskShow1() {
		TaskSelectDAO dao = new TaskSelectDAO();
		TaskShowBean ts = new TaskShowBean();
		try {
			ts = dao.selectTaskShow(1);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(1, ts.getTaskId());
		assertEquals("未着手サンプルタスク", ts.getTaskName());
		assertEquals("新商品A:開発プロジェクト", ts.getCategoryName());
		assertEquals(LocalDate.of(2023, 9, 1), ts.getStartDate());
		assertEquals(LocalDate.of(2023, 9, 11), ts.getLimitDate());
		assertEquals("管理者ユーザー", ts.getUserName());
		assertEquals("未着手", ts.getStatusName());
		assertEquals("サンプルメモ", ts.getMemo());
	}
	@Test
	void testSelectTaskShow2() {

		TaskSelectDAO dao = new TaskSelectDAO();
		TaskShowBean ts = new TaskShowBean();
		try {
			ts = dao.selectTaskShow(7);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(7, ts.getTaskId());
		assertEquals("userタスク2-50", ts.getTaskName());
		assertEquals("新商品A:開発プロジェクト", ts.getCategoryName());
		assertEquals(null, ts.getStartDate());
		assertEquals(null, ts.getLimitDate());
		assertEquals("一般ユーザー", ts.getUserName());
		assertEquals("着手", ts.getStatusName());
		assertEquals("memo", ts.getMemo());
	}

	@Test
	void testSelectTask1() {
		TaskSelectDAO dao = new TaskSelectDAO();
		TaskBean task = new TaskBean();
		try {
			task = dao.selectTask(1);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(1, task.getTaskId());
		assertEquals("未着手サンプルタスク", task.getTaskName());
		assertEquals(1, task.getCategoryId());
		assertEquals(LocalDate.of(2023, 9, 1), task.getStartDate());
		assertEquals(LocalDate.of(2023, 9, 11), task.getLimitDate());
		assertEquals("admin", task.getUserId());
		assertEquals("00", task.getStatusCode());
		assertEquals("サンプルメモ", task.getMemo());
	}

	@Test
	void testSelectTask2() {
		TaskSelectDAO dao = new TaskSelectDAO();
		TaskBean task = new TaskBean();
		try {
			task = dao.selectTask(8);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertEquals(8, task.getTaskId());
		assertEquals("adminタスク2-50", task.getTaskName());
		assertEquals(1, task.getCategoryId());
		assertEquals(null, task.getStartDate());
		assertEquals(null, task.getLimitDate());
		assertEquals("admin", task.getUserId());
		assertEquals("50", task.getStatusCode());
		assertEquals("MEMO", task.getMemo());
	}


	@Test
	void selectProgressTask1() {
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		TaskSelectDAO dao = new TaskSelectDAO();

		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			taskList = dao.selectProgressTask("admin");
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("admin着手中タスク一覧.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);

			//読み込み行
			String line;

			//読み込み行数の管理
			int i = 0;

			//LocalDateへの変換
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {
				//先頭行は列名
				if (i != 0) {
					//カンマで分割した内容を配列に格納する
					String[] data = line.split(",");
					assertEquals(Integer.parseInt(data[0]), taskList.get(i - 1).getTaskId());
					assertEquals(data[1], taskList.get(i - 1).getTaskName());
					assertEquals(Integer.parseInt(data[2]), taskList.get(i - 1).getCategoryId());
					if(Objects.toString(taskList.get(i - 1).getStartDate(), "") != "") {
						assertEquals(LocalDate.parse(data[3], dtf), taskList.get(i - 1).getStartDate());
					}
					if(Objects.toString(taskList.get(i - 1).getLimitDate(), "") != "") {
						assertEquals(LocalDate.parse(data[4], dtf), taskList.get(i - 1).getLimitDate());
					}
					assertEquals(data[5], taskList.get(i - 1).getUserId());
					assertEquals(data[6], taskList.get(i - 1).getStatusCode());
					assertEquals(data[7], taskList.get(i - 1).getMemo());
				}
				i++;
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

	@Test
	void selectProgressTask2() {
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		TaskSelectDAO dao = new TaskSelectDAO();

		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			taskList = dao.selectProgressTask("user");
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("user着手中タスク一覧.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);

			//読み込み行
			String line;

			//読み込み行数の管理
			int i = 0;

			//LocalDateへの変換
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {
				//先頭行は列名
				if (i != 0) {
					//カンマで分割した内容を配列に格納する
					String[] data = line.split(",");
					assertEquals(Integer.parseInt(data[0]), taskList.get(i - 1).getTaskId());
					assertEquals(data[1], taskList.get(i - 1).getTaskName());
					assertEquals(Integer.parseInt(data[2]), taskList.get(i - 1).getCategoryId());
					if(Objects.toString(taskList.get(i - 1).getStartDate(), "") != "") {
						assertEquals(LocalDate.parse(data[3], dtf), taskList.get(i - 1).getStartDate());
					}
					if(Objects.toString(taskList.get(i - 1).getLimitDate(), "") != "") {
						assertEquals(LocalDate.parse(data[4], dtf), taskList.get(i - 1).getLimitDate());
					}
					assertEquals(data[5], taskList.get(i - 1).getUserId());
					assertEquals(data[6], taskList.get(i - 1).getStatusCode());
					assertEquals(data[7], taskList.get(i - 1).getMemo());
				}
				i++;
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

	@Test
	void selectProgressTaskAll() {
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		TaskSelectDAO dao = new TaskSelectDAO();

		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			taskList = dao.selectProgressTask();
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("着手中タスク一覧.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);

			//読み込み行
			String line;

			//読み込み行数の管理
			int i = 0;

			//LocalDateへの変換
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {
				//先頭行は列名
				if (i != 0) {
					//カンマで分割した内容を配列に格納する
					String[] data = line.split(",");
					assertEquals(Integer.parseInt(data[0]), taskList.get(i - 1).getTaskId());
					assertEquals(data[1], taskList.get(i - 1).getTaskName());
					assertEquals(Integer.parseInt(data[2]), taskList.get(i - 1).getCategoryId());
					if(Objects.toString(taskList.get(i - 1).getStartDate(), "") != "") {
						assertEquals(LocalDate.parse(data[3], dtf), taskList.get(i - 1).getStartDate());
					}
					if(Objects.toString(taskList.get(i - 1).getLimitDate(), "") != "") {
						assertEquals(LocalDate.parse(data[4], dtf), taskList.get(i - 1).getLimitDate());
					}
					assertEquals(data[5], taskList.get(i - 1).getUserId());
					assertEquals(data[6], taskList.get(i - 1).getStatusCode());
					assertEquals(data[7], taskList.get(i - 1).getMemo());
				}
				i++;
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

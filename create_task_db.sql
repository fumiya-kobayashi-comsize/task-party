/* Javaサーブレット基礎 演習問題用SQL */
/* DB作成 */
DROP DATABASE IF EXISTS task_db;
CREATE DATABASE task_db CHARACTER SET utf8 COLLATE utf8_general_ci;

/* AUTOCOMMIT 無効 */
SET AUTOCOMMIT = 0;

/*ユーザマスタ作成*/
CREATE TABLE task_db.m_user
(
	user_id VARCHAR(24) PRIMARY KEY NOT NULL,
	password VARCHAR(64) NOT NULL,
	user_name VARCHAR(20) UNIQUE NOT NULL,
	update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

/*カテゴリーマスタ作成*/
CREATE TABLE task_db.m_category
(
	category_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	category_name VARCHAR(20) UNIQUE NOT NULL,
	update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

/*ステータスマスタ作成*/
CREATE TABLE task_db.m_status
(
	status_code CHAR(2) PRIMARY KEY NOT NULL,
	status_name VARCHAR(20) UNIQUE NOT NULL,
	update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

/*タスクテーブル作成*/
CREATE TABLE task_db.t_task
(
	task_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	task_name VARCHAR(50) NOT NULL,
	category_id INT NOT NULL,
	limit_date DATE,
	user_id VARCHAR(24) NOT NULL,
	status_code CHAR(2) NOT NULL,
	memo VARCHAR(100),
	create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	FOREIGN KEY(category_id) REFERENCES task_db.m_category(category_id),
	FOREIGN KEY(user_id) REFERENCES task_db.m_user(user_id),
	FOREIGN KEY(status_code) REFERENCES task_db.m_status(status_code)
);

/*ステータスマスタ INSERT*/
INSERT INTO task_db.m_status(status_code, status_name) VALUES ('00', '未着手');
INSERT INTO task_db.m_status(status_code, status_name) VALUES ('50', '着手');
INSERT INTO task_db.m_status(status_code, status_name) VALUES ('99', '完了');

/*カテゴリマスタ INSERT*/
INSERT INTO task_db.m_category (category_name) VALUES ('新商品A:開発プロジェクト');
INSERT INTO task_db.m_category (category_name) VALUES ('既存商品B:改良プロジェクト');

/*ユーザマスタ INSERT*/
INSERT INTO task_db.m_category VALUES ('admin', 'password', 'テストユーザー');

commit;
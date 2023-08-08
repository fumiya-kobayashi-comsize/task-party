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

/*コメントテーブル作成*/
CREATE TABLE task_db.t_comment
(
	comment_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	task_id INT NOT NULL,
	user_id VARCHAR(24) NOT NULL,
	comment VARCHAR(100) NOT NULL,
	update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	FOREIGN KEY(task_id) REFERENCES task_db.t_task(task_id),
	FOREIGN KEY(user_id) REFERENCES task_db.m_user(user_id)
);

/*ステータスマスタ INSERT*/
INSERT INTO task_db.m_status(status_code, status_name) VALUES ('00', '未着手');
INSERT INTO task_db.m_status(status_code, status_name) VALUES ('50', '着手');
INSERT INTO task_db.m_status(status_code, status_name) VALUES ('99', '完了');

/*カテゴリマスタ INSERT*/
INSERT INTO task_db.m_category (category_name) VALUES ('新商品A:開発プロジェクト');
INSERT INTO task_db.m_category (category_name) VALUES ('既存商品B:改良プロジェクト');

/*ユーザマスタ INSERT*/
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('admin', 'password', 'テストユーザー');

/*タスクテーブル INSERT*/
INSERT INTO task_db.t_task(task_name, category_id, limit_date, user_id, status_code, memo) VALUES ('サンプルタスク', 1, '2023-09-01', 'admin', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code) VALUES ('サンプルタスク2', 2, 'admin', '99');

/*コメントテーブル INSERT*/
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (2,'admin','コメント');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (1,'admin','コメントコメント');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (1,'admin','コメントコメントコメント');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (1,'admin','コメントコメントコメントコメント');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (2,'admin','コメントコメントコメントコメントコメント');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (2,'admin','コメントコメントコメントコメントコメントコメント');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (2,'admin','コメントコメントコメントコメントコメントコメントコメント');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (1,'admin','コメントコメントコメントコメントコメントコメントコメントコメント');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (2,'admin','コメントコメントコメントコメントコメントコメントコメントコメントコメント');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (2,'admin','コメントコメントコメントコメントコメントコメントコメントコメントコメントコメント');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (1,'admin','コメント1');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (1,'admin','コメント2');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (1,'admin','コメント3');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (2,'admin','コメント4');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (2,'admin','コメント5');
INSERT INTO task_db.t_comment(task_id, user_id, comment) VALUES (1,'admin','コメント6');


commit;
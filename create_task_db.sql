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
	is_admin BOOLEAN DEFAULT FALSE NOT NULL,
	login_attempts INT DEFAULT 0 NOT NULL,
	is_locked BOOLEAN DEFAULT FALSE NOT NULL,
	lock_datetime TIMESTAMP,
	update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

/* このテーブルには以下の列が含まれています：
user_id: ユーザID。主キーとして指定され、NOT NULL制約があります。
password: ユーザのパスワードを格納します。NOT NULL制約があります。
user_name: ユーザ名。一意性が必要なため、UNIQUE制約があります。NOT NULL制約もあります。
is_admin: ユーザが管理者権限を持つかどうかを示すBOOLEAN型の列です。デフォルト値はFALSEです。
login_attempts: ユーザがログインを試みた回数を格納する列です。デフォルト値は0です。
is_locked: ログイン試行回数が一定回数以上の場合に、ユーザがロックされたかどうかを示すBOOLEAN型の列です。デフォルト値はFALSEです。
lock_datetime: ユーザがロックされた時刻を記録するTIMESTAMP型の列です。
update_datetime: レコードの更新時刻を記録するTIMESTAMP型の列で、デフォルト値はCURRENT_TIMESTAMPです。

ログインの際には、入力されたユーザ名とパスワードを照合し、存在するか確認した後、is_lockedがTRUEでないことと、
login_attemptsが一定回数未満であることを確認してログインを許可します。
パスワードが間違っていた場合、login_attemptsをインクリメントします。管理者権限を持つユーザはis_admin列で判定できます。*/


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
	start_date DATE,
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
INSERT INTO task_db.m_user(user_id, password, user_name,is_admin) VALUES ('admin', '3f74691ad7292c5f0ee29cc8fba0b03463e3b1c4a1d368838dbe11b925d244c4', '管理者ユーザー',true);
INSERT INTO task_db.m_user(user_id, password, user_name, is_locked) VALUES ('user', '5a63524297fbbf5df0f2f10ff13fba9a19168b9d7a3a4e76fddc81e12f46b2f1', '一般ユーザー', true);
/*INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('', '', '');*/

/*タスクテーブル INSERT*/
INSERT INTO task_db.t_task(task_name, category_id, start_date, limit_date, user_id, status_code, memo) VALUES ('未着手サンプルタスク', 1,'2023-09-01', '2023-09-11', 'admin', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, start_date, user_id, status_code) VALUES ('サンプルタスク2', 2, '2023-08-01','admin', '99');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('着手中サンプルタスク', 1, 'admin', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, start_date, limit_date, user_id, status_code, memo) VALUES ('着手中サンプルタスク', 1,'2023-08-10', '2023-08-30', 'user', '50', 'サンプルメモ');

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
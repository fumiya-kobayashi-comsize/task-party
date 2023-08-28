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
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('arakawa', '50c5893dcda5ed2bf08ab55ef33048d272f625ef68c707112d9c8bfca286ab36', '荒川');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('negami', '4da3747fd7664e08e226bf14e5eb3664f7f26836318102f8f64aa047dc483220', '根上');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('koseki', '38aa1bea65c021c6416fe6a080e49f5b5fc8441a2ae90b3178addc7274627220', '小関');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('test1', 'f225891f2a21a91dad0e986bf11b7e90888ca6b503d4eaeaed37c44deb2a4dd5', 'test1');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('test2', '19f4fb995da16a6e4ced78dab16eff176c7925ca20ef9fe86b371aafcab5bc66', 'test2');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('test3', '8763602da8158d729a5c76c42d2e03d3f49b158a9da798557ab195151f09583c', 'test3');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('test4', '55e93c62af920b89b49961c594464666a6edb2a31c38c6123accd55bf78a12f0', 'test4');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('test5', '904584fe8ff8c9f5192309fad5741017796e99e0269f1b256bda188bc072ea98', 'test5');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user1', 'd2f935418213eeecbc1e3638815d9aba3a308cec399866478efe9bd850186b16', 'user1');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user2', '60c0bf07d1b05abd07fbc6ae70d0c835644a48263e560d7234866c57fe7f5f7a', 'user2');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user3', '545186a955aaef2e3447cca3aeb1e730ed2357b94e2cc291a70c5444364fb6d8', 'user3');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user4', '66fd8a6e8c9e36c935dd33cabc7d5b0d02c68b75dc20ff5cdb2d000c370e35dd', 'user4');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user5', 'a9a8921985582bbb550f686a407b71eac7c769e6a2346478e490590b0945bf68', 'user5');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user6', '22060885a327a42ccaf8584c5800d25de9def116279c4bb4a9648a513b8e1c32', 'user6');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user7', '5854103b011eea2e12aa0f15f3d38e8c8a51fb1f4cabe727721fe89100ee8fdd', 'user7');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user8', 'f71c51085efc5af96ca7bd27ac525c1509d70fa2f47d5ff79140fbf5b2810b43', 'user8');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user9', '673335bcb20d75042e1c12b88ebe1399938c5e57719a4c2c2950fe7be1508cdd', 'user9');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user10', '8925ab429bd418992d6b686be25b7c6eddc56cccc93ffc71d318e4cba5dd1f7c', 'user10');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user11', '2a21de06905a62f1428144b988aaeb45ab8010d085cdf4be319eb3b2098e15e8', 'user11');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user12', 'a914245c60603813431ec61865ad4f9336204b79444fe5deacd58708a3e26648', 'user12');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user13', 'b76770917af559a42d07fb12cc76f3a589b63813cf017417d69228a63422fe85', 'user13');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user14', 'cb3f76fdcc2097207e68ebef19c2c089d4ce33f96f7bea284be23d27183c64c4', 'user14');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user15', '23f0440d5f08fee8016769d302c26502eba80f8e919f94abc8370c47a4f92fa3', 'user15');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user16', 'af42c827d6aba07bb8e250db7e048503dbabec34a8b184310af0e9f19d0c9897', 'user16');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user17', '980a0b5643298be2e2f6f921320c120413818cfc83e3243ebb7264924b9accd9', 'user17');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user18', '2be30abbe53b900d23af2477f84139a3003499b6f0b81025a82331506f3f73e3', 'user18');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user19', '4682f537847a645716e8accfe9cd1c199ab2b4edd904008ab1d4fad0a8156f45', 'user19');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user20', 'cc622aa61ca386143592af7854fedf2e66461cc281252e95088a882702f209f5', 'user20');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user21', 'ff47e2ebe2f9cb4c1288ce2012b7f7fb22adf2cb6431014d59af305dafcc5ac8', 'user21');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user22', '95752c7ee8082c29eb8646ecf117215f36ae432d733a5006a419ad15cb6e9341', 'user22');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user23', 'b247da4295f9f4d3ef81efa3b66b85c2d4042fc946ba7b4b98607a8dda3f1537', 'user23');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user24', '9cdc1637bdd7797bde9f9392e29a3a7794f7457f9077fe0b0a9477326093151a', 'user24');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user25', '2cc2534b2fcb8d74730a1162ea09a8bf88a4614284b2f4ba8bba677256f312c1', 'user25');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user26', '58242304f4834594ecc500c1c533059135d48dd79e4d040ab63a10d7b1eccc5e', 'user26');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user27', '84efecde18daab602818ba079a653b1bdfd50567489ec4afdc540232c71ee443', 'user27');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user28', 'cf21bcd7bb648d9ab5e29a50fb33be0e6a629a470ab15aa134d5d3d35302aeec', 'user28');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user29', 'f5fb6e04daf08a2c993bdaf92947b3dd0c12db593b0dd8f32546766a934cd243', 'user29');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user30', '2ac8cdb7f7cf0a0007e5a7662206944c5c0d6a664da859fdbb1e2acc3230c87c', 'user30');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user31', '8a2b6a046c26b3b47f4d1558d5c3562b0344e14c661b19dca57f37d0974b790f', 'user31');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user32', '624b6374b5949ebb0f6e2da119809bdeaac487e6eec48877e48a7c3057207bd6', 'user32');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user33', '9314838a8d0e94c5a7eafa4982de99cb7be874881784f33094d69d829b6538ce', 'user33');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user34', '35222e3f55d3409398a2d61364460a492741b3cc2ba6378e839ef6d273f1e1bc', 'user34');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user35', '03332fecddeeeab9159d33bfabbb2e42c8b875c5f82530d623e94ca661c0f02d', 'user35');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user36', 'e81ccecec710b1291d0685828f5863ae2fc63811011bf5e6d4359d33d6ea70c0', 'user36');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user37', '18ead03db6e1f1ca42042d38ce113afdd8a8aeeedaef2ef01b09b156e0b13de7', 'user37');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user38', '2b9a80ee781880846acd192358d526b73e462f653291a3a7a602604e4f6f859d', 'user38');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user39', '2b39355197d1e679a1be07ef04956384aa226d216846f5ab03f42185f69d5341', 'user39');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user40', 'ce79a5d1045a629940117890f9549dfaa8929017e9d28663623869105c52a9cf', 'user40');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user41', '6fcbadfd5e9cefa9a1e65f0f1cf2dc388d9a117a94004dd4471587518124f20e', 'user41');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user42', '7c1a22cf882b64999d60540ba871e94320643e2871bd64cc44dc2a6a48000bc0', 'user42');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user43', 'be4017c60fb719f9ee6cd1352156daa5496a990174a2f49b22637caff9f7d508', 'user43');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user44', 'b57960eaa5413aa8cb31b7c86fb11080f15bfa399f38e653462749a80e29bb36', 'user44');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user45', '5038d876cdc82537cfc5116907ebc720f93173a3d29c0cb6bd27dde4068c439b', 'user45');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user46', 'bb07c0a7eab5cbfd4475f55a1938ca71ae1a66c0fc4795ff880abbddbb130149', 'user46');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user47', 'a481aca3d3cd3672b42c3757e89cacd5b1062771833cf76e98ba6d386447c0a7', 'user47');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user48', '6b06e711fd98e1c0e6cfc94bd9a61730e13e3d8fe4255955b1b24b47244a336e', 'user48');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user49', '9df7067d549d91d4f043f718ed5db7cf9887ccfe4eb78615f0421d2537cc6742', 'user49');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user50', '292bec07aaf9bcd76937e8d72424d404ecd648af82fc4dd2da409a86dfc9ef30', 'user50');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user51', '2f85df39ca3205ee45f2baa879a32e5d99801f9edc51e92e56bd787a6b0958b3', 'user51');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user52', 'ca2a2c6207e2fffe0880af77219fb98d8994fa916f07c6b685c8231106df4d56', 'user52');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user53', '05bf6141c1d5138c2e7b938f1bd7cd039b48d55c8c2537545c3db7beff7b3355', 'user53');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user54', '69bc7cddea3df4b5119b021a36918b927affe355885b72620070e8b7c42c67b6', 'user54');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user55', '493eb71cbab3031c9bb848ba98e677802b82214262ebc916e1d07236063bf5a6', 'user55');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user56', '225c6e434532293f66b313a43c54b16243efe938600a7bf8538c7c674a540195', 'user56');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user57', '7f73093eac19c90c7e0e725e9f40700a06b711a3262a836b4a9eb9595ba68b5c', 'user57');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user58', 'dfe4694340d2c2241f2d8330a7cfeb84f83258f68206bbbb87d7b3a503e301e4', 'user58');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user59', '56921190575ecfca578e2753cb21829caad44c6720b43e14ad3e2085490d4eb8', 'user59');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user60', '3a095030dd7042e824652751842df63382394fc73f5bc9a22e71580897ed67e4', 'user60');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user61', '9eed1e4eac95769849e9610cdadd4227086c9ef7dd20db1a9b28e64a6c192587', 'user61');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user62', '6229ec51ce0c5938cc98c61a4f4ebd100ce7fe1058a5dcb1216cb3e6168c0742', 'user62');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user63', 'a1b90f3fdee1ba059531d645189941623367af44b23b9c1681012afa4370aee0', 'user63');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user64', 'ead4b33b125b3eac0cf585305fb2504e4b6ae238351ffc4724f52f1ac89f58c2', 'user64');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user65', 'e1b765f9d5f3f2395a105166d37b1b529293071e12010bb3b6bb5388d146cf63', 'user65');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user66', 'ef12c130ceee4b218dd5edf0e987fc0541bf6ab3e8568fadae6ff914b4616be3', 'user66');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user67', '702edab32ea5dd944cc3722902e2a110e2dd7375f4a240e829387863a2aa1568', 'user67');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user68', '49db1ac873e8561704f06967e12f8c83a08db361131e607dd7f28653773f5023', 'user68');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user69', '66236a441481b81ffcd1fb90da0c3561d00fcf724537d16c5a5196ffdf59ccfd', 'user69');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user70', '0fc81c7799aac8147db437f6f77fb56a57efd1b25e8fa4b51a102944a3cb2a92', 'user70');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user71', 'f4d09434d2bf03500542df4d0555b49c7e46ec9323f14f51d12d2db50b3e5a96', 'user71');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user72', 'd8917ceaf874b24051b62a358bd74927120f3e226f72be60a2812bdbda8e92d5', 'user72');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user73', '5b63bfb25383e4d1c106878e0bdf4d08895753d842f2da87593e26c47baf0cd9', 'user73');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user74', 'b8188631b01983a9ae2520d3d2d7b41acd05f0794a4be0c91756fb0e7eaf23a0', 'user74');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user75', 'e92023ab16adda53da38f29f6e4aaabf866710f0b964e04b2f07ca48d75e493d', 'user75');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user76', 'b3e2791f56510d7186f05c700ac4cdcc557df649f03612130aa9db3bb0bec0a6', 'user76');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user77', 'fa1384b6176495d3bb31a856badd4564d3cca1f2428bf4aff4905e9443343d7b', 'user77');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user78', 'ecd59d32169110bac72f55afc86ab88884119c8dfbe30aa4508a033f676ea941', 'user78');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user79', '43fc86bedbca947e90e603f2cd6dc0c6fd04410b2bad40735c868543217d9bc6', 'user79');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user80', 'cbcefa0f1edacb9a239a7e4d53898f5de061c174167c9368d940510460b7a24e', 'user80');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user81', '2759e3270e8a6f03666fd963d677836ad84e16b1cfbc5aa33733cf08c06381ac', 'user81');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user82', '0c83cf799d37ee2c42b8ce03cad362b95929420def7af254c09cf2c7f2701ac8', 'user82');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user83', 'b23db1780ab9603fa3a3d19c29d208e8c963ef5c04e52994155d703e24bc012d', 'user83');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user84', '69f2f8b661e459197c5a39fc7d50cfe32299f4fd8ff8ffd44ca27200db87bbad', 'user84');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user85', 'b01f26e1713d8ed94388716f073f93a8dca115da2781cc71eb747d93b47a47f8', 'user85');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user86', '836fd1928a61008fda9056c597ff658e276114f5ce025cd232bd15e858211fb9', 'user86');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user87', '83b190ad950155f7deeba58f44bdeaedebbb2bc568fb7b45dfb090c04b304f32', 'user87');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user88', '9fa1c75f5db0f1e56580a47913ca1e880158357ff7a7bb4361a5b8d782fdc19d', 'user88');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user89', '25c6aaf29e733887f2575e5333a3216c9f50ebbf2891236da54345a5bf89844f', 'user89');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user90', 'a4115958899e678fbab52f44efbf097a85bdb2d3e75235eeaa877984c2362811', 'user90');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user91', 'e85e5edcca736aae175b7c52f2644aafdebc38543fff39882194168cf6eb11dc', 'user91');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user92', 'd8cea4a91b7c0ba9c6489aa7f7501e30a86c640537cb017c2640a73f4eb3a478', 'user92');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user93', '20597a87f603948754c7db59333b54f483b4794e470a5793dd5e510eacdf5a05', 'user93');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user94', '37691ff6cbb0e4aa12da6787d981cb33575d55f8e4e0588a1b65b896ac375af8', 'user94');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user95', 'cf78ecd710607f6d67575abe38365624aefbf30bc1a63442014585117ea1c321', 'user95');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user96', 'cdc6f9479f3511dfb412be68e3747875aa32daf32ef042610d1a720fdd5e9f49', 'user96');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user97', '5ff1c1651868505c938a604258d3597e5bb9a52066912d870475d74ad20660d9', 'user97');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user98', '54943812cca8a654e3cb4245f1c68708d2e3fb786270e0dcb20fb76b05b2255d', 'user98');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user99', 'b22b23cbe4a2aaca6e6acf1edace83785d962f0e21164efcf9fe0c4c077e45c7', 'user99');
INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('user100', '49c56e664923eea9a3dd22421f70146f6468f25adca38ffd41bd0ef938eefd88', 'user100');

/*INSERT INTO task_db.m_user(user_id, password, user_name) VALUES ('', '', '');*/

/*タスクテーブル INSERT*/
INSERT INTO task_db.t_task(task_name, category_id, start_date, limit_date, user_id, status_code, memo) VALUES ('未着手サンプルタスク', 1, '2023-09-01', '2023-09-11', 'admin', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, start_date, user_id, status_code) VALUES ('サンプルタスク2', 2, '2023-08-01','admin', '99');
INSERT INTO task_db.t_task(task_name, category_id, start_date, limit_date, user_id, status_code, memo) VALUES ('着手中サンプルタスク', 1, '2023-08-01', '2023-08-31', 'admin', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code) VALUES ('着手中サンプルタスク', 1, 'user', '50');
INSERT INTO task_db.t_task(task_name, category_id, start_date, limit_date, user_id, status_code, memo) VALUES ('test1タスク50', 1,'2023-08-24', '2023-08-26', 'test1', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, limit_date, user_id, status_code) VALUES ('test2タスク50', 1, '2023-08-31', 'test2', '50');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('test3タスク50', 1, 'test3', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code) VALUES ('test4タスク50', 1, 'test4', '50');


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
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user1タスク', 1, 'user1', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user2タスク', 1, 'user2', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user3タスク', 1, 'user3', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user4タスク', 1, 'user4', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user5タスク', 1, 'user5', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user6タスク', 1, 'user6', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user7タスク', 1, 'user7', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user8タスク', 1, 'user8', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user9タスク', 1, 'user9', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user10タスク', 1, 'user10', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user11タスク', 1, 'user11', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user12タスク', 1, 'user12', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user13タスク', 1, 'user13', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user14タスク', 1, 'user14', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user15タスク', 1, 'user15', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user16タスク', 1, 'user16', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user17タスク', 1, 'user17', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user18タスク', 1, 'user18', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user19タスク', 1, 'user19', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user20タスク', 1, 'user20', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user21タスク', 1, 'user21', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user22タスク', 1, 'user22', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user23タスク', 1, 'user23', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user24タスク', 1, 'user24', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user25タスク', 1, 'user25', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user26タスク', 1, 'user26', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user27タスク', 1, 'user27', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user28タスク', 1, 'user28', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user29タスク', 1, 'user29', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user30タスク', 1, 'user30', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user31タスク', 1, 'user31', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user32タスク', 1, 'user32', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user33タスク', 1, 'user33', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user34タスク', 1, 'user34', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user35タスク', 1, 'user35', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user36タスク', 1, 'user36', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user37タスク', 1, 'user37', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user38タスク', 1, 'user38', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user39タスク', 1, 'user39', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user40タスク', 1, 'user40', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user41タスク', 1, 'user41', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user42タスク', 1, 'user42', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user43タスク', 1, 'user43', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user44タスク', 1, 'user44', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user45タスク', 1, 'user45', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user46タスク', 1, 'user46', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user47タスク', 1, 'user47', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user48タスク', 1, 'user48', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user49タスク', 1, 'user49', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user50タスク', 1, 'user50', '50', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user51タスク', 1, 'user51', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user52タスク', 1, 'user52', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user53タスク', 1, 'user53', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user54タスク', 1, 'user54', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user55タスク', 1, 'user55', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user56タスク', 1, 'user56', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user57タスク', 1, 'user57', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user58タスク', 1, 'user58', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user59タスク', 1, 'user59', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user60タスク', 1, 'user60', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user61タスク', 1, 'user61', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user62タスク', 1, 'user62', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user63タスク', 1, 'user63', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user64タスク', 1, 'user64', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user65タスク', 1, 'user65', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user66タスク', 1, 'user66', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user67タスク', 1, 'user67', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user68タスク', 1, 'user68', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user69タスク', 1, 'user69', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user70タスク', 1, 'user70', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user71タスク', 1, 'user71', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user72タスク', 1, 'user72', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user73タスク', 1, 'user73', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user74タスク', 1, 'user74', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user75タスク', 1, 'user75', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user76タスク', 1, 'user76', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user77タスク', 1, 'user77', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user78タスク', 1, 'user78', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user79タスク', 1, 'user79', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user80タスク', 1, 'user80', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user81タスク', 1, 'user81', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user82タスク', 1, 'user82', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user83タスク', 1, 'user83', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user84タスク', 1, 'user84', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user85タスク', 1, 'user85', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user86タスク', 1, 'user86', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user87タスク', 1, 'user87', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user88タスク', 1, 'user88', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user89タスク', 1, 'user89', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user90タスク', 1, 'user90', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user91タスク', 1, 'user91', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user92タスク', 1, 'user92', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user93タスク', 1, 'user93', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user94タスク', 1, 'user94', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user95タスク', 1, 'user95', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user96タスク', 1, 'user96', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user97タスク', 1, 'user97', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user98タスク', 1, 'user98', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user99タスク', 1, 'user99', '00', 'サンプルメモ');
INSERT INTO task_db.t_task(task_name, category_id, user_id, status_code, memo) VALUES ('user100タスク', 1, 'user100', '00', 'サンプルメモ');


commit;
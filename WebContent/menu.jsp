<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menu</title>
</head>
<body>
	<div align="center">
		<h1 >メニュー画面</h1>
		<hr>
		ようこそ！
		<form action="TaskAddServlet" method="GET">
			<input type="submit" value="タスク登録" >
		</form>
		<br>
			<!--actionにサ
			ーブレットURLを入れる  -->
		<form action="TaskShowServlet" method="POST">
			<input type="submit" value="タスク表示">
		</form>
		<br>
		<form action="logout.jsp" method="POST">
			<input type="submit" value="ログアウト">
		</form>
	</div>
</body>
</html>
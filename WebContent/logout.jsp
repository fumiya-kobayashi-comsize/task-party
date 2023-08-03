<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>
</head>
<body>
	<div align="center">
		<h1>ログアウト画面</h1><hr>
		お疲れさまでした
		<%session.invalidate();%>
		<h1>ログアウトしました</h1>
		<form action="login.jsp" method="POST">
			<input type="submit" value="ログイン画面へ">
		</form>
	</div>
</body>
</html>
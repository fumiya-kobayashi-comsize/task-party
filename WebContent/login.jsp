<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<div align="center">
		<h1>ログイン画面</h1>
		<hr>
		<h1>ユーザーIDとパスワードを入力してください。</h1>
		<% int locked = 0;
		if((Integer)request.getAttribute("locked") != null){
			locked = (Integer)request.getAttribute("locked");
		}
			if(locked > 0){
			%>
			ログインに失敗しました。
		<%} else {%>
		<%} %>
		<form action="LoginServlet" method="post">
			ユーザーID<input type="text" name="user_id"><br>
			パスワード<input type="text" name="password"><br>
			<input type="submit" value="ログイン">
			<input type="reset" value="取消">
		</form>
	</div>
</body>
</html>
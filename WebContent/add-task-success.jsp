<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.TaskShowBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add task success</title>
</head>
<body>
<% TaskShowBean task=(TaskShowBean)session.getAttribute("taskShow"); %>
	<div align="center">
		<h1>タスクの登録に成功しました</h1><hr>
	<h3>次のタスクを登録しました</h3>
<br>
	<form action="menu.jsp" method="POST">
		<input type="submit" value="メニューへ">
	</form>
	</div>
</body>
</html>
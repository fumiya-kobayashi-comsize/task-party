<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.entity.TaskShowBean"%>
<%@page import="java.util.List"%>
<%@page import="model.entity.CommentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add comment success</title>
</head>
<body>
	<h1>コメントの追加に成功しました。</h1>

	<form action="show-task.jsp" method="POST">
		<input type="submit" value="メニューへ">
	</form>
</body>
</html>
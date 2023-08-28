<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% int commentId=Integer.parseInt(request.getParameter("comment_id")); %>
<% String userId=request.getParameter("user_id"); %>
	<h1>コメント削除確認</h1><hr>
	このコメントを削除しますか?

	<form action="CommentDeleteServlet"method="POST">
		<input type="submit" value="はい">
		<input type="hidden" name="comment_id" value=<%=commentId %>>
		<input type="hidden" name="user_id" value=<%=userId %>>
	</form>
	<form action="show-comment.jsp"method="POST">
		<input type="submit" value="いいえ">
	</form>
</body>
</html>
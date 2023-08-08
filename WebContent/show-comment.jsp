<%@page import="model.entity.TaskShowBean"%>
<%@page import="java.util.List"%>
<%@page import="model.entity.CommentBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%List<CommentBean> commentList = (List<CommentBean>)session.getAttribute("comment_list"); %>
<%TaskShowBean task = (TaskShowBean)session.getAttribute("task_show"); %>
<h3>コメント</h3>
<hr>
	<form action="menu.jsp" method="POST">
		<input type="submit" value="メニューへ">
	</form>
<table>
	<tr>
		<th>タスク名</th>
		<th>カテゴリ情報</th>
		<th>期限</th>
		<th>担当者情報</th>
		<th>ステータス情報</th>
		<th>メモ</th>
	</tr>
	<tr>
		<td><%=task.getTaskName() %></td>
		<td><%=task.getCategoryName() %></td>
		<td>
			<%if(task.getLimitDate()!=null){ %>
				<%=task.getLimitDate() %>
			<% }%>
		</td>

		<td><%=task.getUserName() %></td>
		<td><%=task.getStatusName() %></td>
		<td><%=task.getMemo() %></td>
	</tr>
</table>
<hr>
<form action="CommentPostServlet"method="GET">
	<input type="hidden" name="task_id" value= <%=task.getTaskId() %>>
	<input type="submit" value="コメント追加">
</form>
<form action="delete-comment-confirm.jsp "method="POST">
<table>
	<%for(CommentBean comment : commentList){ %>
	<tr>
		<th>コメント投稿者</th>
		<th>コメント</th>
		<th>投稿日時</th>

	</tr>
	<tr>
		<td><%=comment.getCommentUser() %></td>
		<td><%=comment.getCommentContent() %></td>
		<td><%=comment.getCommentDate() %></td>
		<td><input type="submit" value="削除"><input type="hidden" name="comment_id" value=<%=comment.getCommentId() %>><td>
	</tr>
	<%} %>
</table>

</form>
</body>
</html>
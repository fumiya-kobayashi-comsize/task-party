<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>タスク登録画面</h1>
		<hr>
		<form action="TaskAddServlet" method="POST">
			<table  border="1">
				<tr>
					<td>タスク名</td>
					<td><input type="text" name="taskName" maxlength="50"></td>
				</tr>
				<tr>
					<td>カテゴリ名</td>
					<td><!--カテゴリーネームをdbから取得しプルダウン表示  --></td>
				</tr>
				<tr>
					<td>期限</td>
					<td><input type="date" name="createDatetime"></td>
				</tr>
				<tr>
					<td>担当者情報</td>
					<td><!--user nameをdbから取得しプルダウン表示  --></td>
				</tr>
				<tr>
					<td>ステータス</td>
					<td><!--statusnameをdbから取得しプルダウン表示  --></td>
				</tr>
				<tr>
					<td>メモ</td>
					<td><input type="text" name="memo" maxlength="100"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
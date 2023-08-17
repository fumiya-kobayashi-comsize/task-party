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
		<h1>管理者用画面</h1>
		<hr>
		<table>
			<tr>
				<th>
					<form action="AdminLockedUserList" method="POST">
						<input type="submit" value="ロックされたユーザ一覧">
					</form>
				</th>
				</tr>
				<tr>
				<th>
					<form action="menu.jsp">
						<input type="submit" value="メニューに戻る">
					</form>
				</th>
			</tr>
		</table>
	</div>
</body>
</html>
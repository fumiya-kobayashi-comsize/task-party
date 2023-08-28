<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.CategoryBean,model.entity.UserBean,model.entity.StatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%List<CategoryBean> categoryList=(List)session.getAttribute("categoryList");
	List<UserBean> userList=(List)session.getAttribute("userList");
	List<StatusBean> statusList=(List)session.getAttribute("statusList");%>
	<div align="center">
		<h1>タスク登録画面</h1>
		<hr>
		<form action="TaskAddServlet" method="POST">
			<table border="1">
				<tr>
					<td>タスク名</td>
					<td><input type="text" name="taskName" maxlength="50" required></td>
				</tr>
				<tr>
					<td>カテゴリ名</td>
					<td>

					<select name="categoryId"required>
						<%for(CategoryBean category:categoryList ){ %>
							<option value=<%=category.getCategoryId() %>><%=category.getCategoryName() %></option>
						<%} %>
					</select >
					</td>
				</tr>
				<tr>
					<td>開始日</td>
					<td><input type="date" name="startDate" v-bind:min=today id="start_day"max="9999-12-31"></td>
				</tr>

				<tr>
					<td>期限</td>
					<td><input type="date" name="limitDate" v-bind:min=today id="limit_day"max="9999-12-31"></td>
				</tr>
				<tr>
					<td>担当者情報</td>
					<td>
					<select name="userId" required>
						<%for(UserBean user:userList ){ %>
							<option value=<%=user.getUserId() %>><%=user.getUserName() %></option>
						<%} %>
					</select>
					</td>
				</tr>
				<tr>
					<td>ステータス</td>
					<td>
						<select name="statusCode" required>
						<%for(StatusBean status:statusList ){ %>
							<option value=<%=status.getStatusCode() %>><%=status.getStatusName() %></option>
						<%} %>
					</select>
					</td>
				</tr>
				<tr>
					<td>メモ</td>
					<td><input type="text" name="memo" maxlength="100"></td>
				</tr>
			</table>
			<input type="submit" value="登録" onclick="return dayCon()" >
		</form>
		<form action="menu.jsp" method="POST">
			<input type="submit" value="メニューへ">
		</form>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js">
	</script>
	<script type="text/javascript">
	var vue = new Vue({
		  el: '#start_day',
		  data: {
		    today: ''
		  },
		  created: function() {
		    let todaySet = new Date();
		    let YYYY = todaySet.getFullYear();
		    var MM = ('00' + (todaySet.getMonth() + 1)).slice(-2);
		    var DD = ('00' + todaySet.getDate()).slice(-2);
		    this.today = YYYY + '-' + MM + '-' + DD
		  },
		})
	var vue = new Vue({
		  el: '#limit_day',
		  data: {
		    today: ''
		  },
		  created: function() {
		    let todaySet = new Date();
		    let YYYY = todaySet.getFullYear();
		    var MM = ('00' + (todaySet.getMonth() + 1)).slice(-2);
		    var DD = ('00' + todaySet.getDate()).slice(-2);
		    this.today = YYYY + '-' + MM + '-' + DD
		  },
		})

	function dayCon(){
		  var val_from = document.getElementsByName("startDate")[0].value;
		  var val_to = document.getElementsByName("limitDate")[0].value;

		  if(val_from != "" && val_to != ""){

		    // 追記ここから --------------------------------------------
		    // 漢字の「年」と「月」を「/」に変換、「日」を削除する
		    val_from = val_from.replace("年","/");
		    val_from = val_from.replace("月","/");
		    val_from = val_from.replace("日","");

		    val_to = val_to.replace("年","/");
		    val_to = val_to.replace("月","/");
		    val_to = val_to.replace("日","");
		    // 追記ここまで --------------------------------------------

		    // 日付オブジェクトを生成
		    var fromDate = new Date(val_from);
		    var toDate = new Date(val_to);
		    // 開始日と終了日の差を計算
		    var judge = (toDate - fromDate);

		    if(judge < 0){
		      alert("終了日には開始日以降の日付を指定してください。");
		      return false;
		    }else{
		      return true;
		    }
		  }else{
		    return true;
		  }
		}
	</script>
</body>
</html>
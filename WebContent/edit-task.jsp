<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List, model.entity.TaskShowBean, model.entity.CategoryBean,
    		model.entity.UserBean, model.entity.StatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集画面</title>
</head>
<body>
	<%
		List<CategoryBean> categoryList=(List)session.getAttribute("categoryList");
		List<UserBean> userList=(List)session.getAttribute("userList");
		List<StatusBean> statusList=(List)session.getAttribute("statusList");
		TaskShowBean task = (TaskShowBean)session.getAttribute("task");
	%>
	<h2>タスク編集画面</h2>
<hr>
	<h3>次のタスクを編集します。</h3>
<br>
	<form action="TaskEditServlet" method="POST">
		<table>
			<tr>
				<th>タスク名</th>
				<td><input type="text" name="task_name"
					value="<%=task.getTaskName() %>" maxlength="50" required></td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><select name="category_id">
				<%
					for(CategoryBean category : categoryList){
						String categoryName = category.getCategoryName();
						if(categoryName.equals(task.getCategoryName())){
				%>
				<option value = "<%=category.getCategoryId() %>" selected><%=categoryName %></option>
				<%
						}else{
				%>
				<option value = "<%=category.getCategoryId() %>"         ><%=categoryName %></option>
				<%
						}
					}
				%>
				</select></td>
			</tr>
			<tr>
				<th>開始日</th>
				<td><input type="date" name="start_date" v-bind:min=today id="start_day"max="9999-12-31" value ="<%=task.getStartDate()%>" ></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><input type="date" name="limit_date" v-bind:min=today id="limit_day"max="9999-12-31" value = "<%=task.getLimitDate()%>"   ></td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><select name="user_id">
				<%
					for(UserBean user:userList){
						String userName = user.getUserName();
						if(userName.equals(task.getUserName())){
				%>
				<option value = "<%=user.getUserId() %>" selected><%=userName %></option>
				<%
						}else{
				%>
				<option value = "<%=user.getUserId() %>"><%=userName %></option>
				<%
						}
					}
				%>
				</select></td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td><select name="status_code">
				<%
					for(StatusBean status:statusList){
						String statusName = status.getStatusName();
						if(statusName.equals(task.getStatusName())){
				%>
				<option value = "<%=status.getStatusCode() %>" selected><%=statusName %></option>
				<%
						}else{
				%>
				<option value = "<%=status.getStatusCode() %>" ><%=statusName %></option>
				<%
						}
					}
				%>
				</select></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><input type="text" name="memo"
					value="<%=task.getMemo() %>" maxlength="100"></td>
			</tr>
		</table>
		<table>
		<tr>
			<td>
					<input type = "hidden" name = "task_id" value = <%=task.getTaskId() %>>
					<input type = "submit" value = "変更する" onclick="return dayCon()">
			</td>
		</tr>
	</table>
	</form>
	<form action="menu.jsp" method="POST">
		<input type="submit" value="メニューへ">
	</form>

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
			  var val_from = document.getElementsByName("start_date")[0].value;
			  var val_to = document.getElementsByName("limit_date")[0].value;

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
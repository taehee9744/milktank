<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method="post" action="DispatcherServlet">
<input type="hidden" name="command" value="insert">
<table border=1>
	<tr>
		<td>제목 : </td>
		<td><input type="text" name="title"> </td>
	</tr>
	<tr>
		<td>내용 : </td>
		<td><textarea rows="10" cols="20" name="detail"></textarea>
		</td>
	</tr>
</table>
<input type="submit" value="등록">
<input type="reset" value="취소">
</form>

</body>
</html> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
          
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function maincancel(){
	location.href="DispatcherServlet?command=allview";
}
function content_submit(){
	var create=document.createForm;
	if(create.title.value==""){
		alert("제목을 입력하세요");
		create.title.focus();
		return false;
	}else	if(create.content.value==""){
		alert("내용을 입력하세요");
		create.content.focus();
		return false;
	}
	create.submit();
}
</script>
</head>
<body>

<font size=6 color=blue>글쓰기</font><hr>

<form method="post" name="createForm" action="DispatcherServlet?command=insert" enctype="multipart/form-data">
<!-- <input type="hidden" name="command" value="insert"> -->
Section<select name="section">
		<option value="1">sports</option>
		<option value="2">fashion</option>
		<option value="3">movie</option>
		<option value="4">travle</option>
		<option value="5">food</option>
		<option value="6">game</option>
		<option value="7">it</option>
		<option value="8">car</option>
		<option value="9">love</option>
		<option value="10">tv</option>
		</select>
Magazine<select name="magazine"><option value="1">ffuck</option></select><br>
제목 : <input type="text" name="title"><br>
파일 : <input type="file" name="file"/><br/>
<br><textarea rows="30" cols="80" name="content"></textarea><br><br>
<input type="image"  src="img/confirm.gif" border="0" value="확인" onclick="return content_submit()">
<input type="image"  src="img/cancel.gif" border="0" value="작성취소" onclick="maincancel()">
</form>
</body>
</html>
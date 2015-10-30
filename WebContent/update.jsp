<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

function update_check(){
	var r=document.updateForm;
	if(r.title.value==""){
		alert("제목을 입력하세요");
		r.title.focus();
		return false;
	}else	if(r.content.value==""){
		alert("내용을 입력하세요");
		r.content.focus();
		return false;
	}
	create.submit();
}
</script>
</head>
<body>

<font size=6 color=blue>글쓰기</font><hr>

<form method="post" name="updateForm" action="DispatcherServlet">
<input type="hidden" name="command" value="updatecontent">
글 번호 <input name ="pno" type="text" value="${requestScope.content.p_no }" readonly="readonly">
작성자 <input name="uid"type="text" value="${requestScope.content.uno }"readonly="readonly"><br>
Section<select name="section">
<c:choose>
<c:when test="${requestScope.content.section==1 }">
<option value="1" selected="selected">sports</option>
<option value="2">fashion</option>
<option value="3">movie</option>
</c:when>
<c:when test="${requestScope.content.section==2 }">
<option value="fashion" selected="selected">fashion</option>
<option value="1">sports</option>
<option value="3">movie</option>
</c:when>
<c:when test="${requestScope.content.section==3 }">
<option value="movie" selected="selected">movie</option>
<option value="1">sports</option>
<option value="2">fashion</option>
</c:when>
</c:choose>

</select>
Magazine<select name="magazine">
<c:choose>
<c:when test="${requestScope.content.magazine==1 }">
<option value="1" selected="selected">ffuck</option>
</c:when>
</c:choose>
</select><br>
제목 : <input type="text" name="title" value="${requestScope.content.title }"><br>
<br><textarea rows="30" cols="80" name="content" >${requestScope.content.content }</textarea><br><br>
<input type="image"  src="img/confirm.gif" border="0" value="확인" onclick="return content_submit()">
<input type="image"  src="img/cancel.gif" border="0" value="작성취소" onclick="maincancel()">
</form>
</body>
</html>
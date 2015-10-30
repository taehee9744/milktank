<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.MemberVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function logout(){
	var flag = confirm("로그아웃 하시겠습니까?");
	if(flag){
		location.href="DispatcherServlet?command=logout";
	}else{
		location.href="index.jsp"
	}
}

</script>
</head>
<body>
<center><h1>메인화면</h1></center>
<hr>

<%
	MemberVO vo = (MemberVO)session.getAttribute("login");
	if(vo==null){
%>
<a href="register.jsp">회원가입</a>
<a href="login.jsp">로그인</a>
<a href="DispatcherServlet?command=allview">전체글보기</a>

<%
	}else{
%>
<%=vo.getId() %>님 로그인 중 입니다.<br>
<a href="insert.jsp">글쓰기</a>
<a href="DispatcherServlet?command=allview">전체글보기</a>
<input type="button" value="로그아웃" onclick="logout()">
<%
	}
%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.MemberVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/httpRequest.js"></script>
<script type="text/javascript">

	/* window.onload = function(){
		friendCheck();
		friendCheckResult();
	} 
 */
   <%MemberVO vo = (MemberVO)session.getAttribute("login");%>
function friendCheck(){
	var r_id = "<%=vo.getId()%>";
	var param = "command=friendCheck&r_id="+r_id;
	sendRequest("${pageContext.request.contextPath}/DispatcherServlet",param,friendCheckResult,"POST");
}
function friendCheckResult(){
	if(httpRequest.readyState==4){
		if(httpRequest.status==200){
			var friendcheck = eval("("+httpRequest.responseText+")");
			if(friendcheck.fcheck){
				alert("친구 요청이 있습니다.");
			}else{
				alert("환영합니다!!");
			}
		}
	}
}

</script>
</head>
<body>
	<input type="button" value="친구 요청 확인 " onclick="return friendCheck()">
	로그인성공!
	<a href="index.jsp">메인으로가기</a>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="model.MemberVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
<%MemberVO vo = (MemberVO)session.getAttribute("login");%>
function approve(){
	location.href="DispatcherServlet?command=fapprove&s_id=${requestScope.friendconfirm.id}&r_id=<%=vo.getId()%>";
}

function reject(){
	
}
</script>
</head>
<body>

${requestScope.friendconfirm.id }
<input type="button" value="수락" onclick="return approve()">
<input type="button" value="거절" onclick="return reject()">
</body>
</html>
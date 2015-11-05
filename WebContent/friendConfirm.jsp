<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="model.MemberVO" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
 <script type="text/javascript">
<%MemberVO vo = (MemberVO)session.getAttribute("login");%>
function approve(id){

location.href="DispatcherServlet?command=fapprove&s_id="+id+"&r_id=<%=vo.getId()%>";
}
 function reject(id){
	location.href="DispatcherServlet?command=freject&s_id="+id+"&r_id=<%=vo.getId()%>";
} 
</script> 
</head>
<body>
<form name="frlist">
<c:set var="i" value="0"></c:set>
<c:forEach items="${requestScope.friendconfirm }" var="friendc">
<%-- <c:set var="i" value="${i+1 }"></c:set> --%>
<%-- <input type="hidden" name="f_id${i}" value="${friendc.id }"> --%>
<input type="hidden" name="f_id" value="${friendc.id }">

${friendc.id }

<input type="button" value="수락" onclick="return approve('${friendc.id}')">
<input type="button" value="거절" onclick="return reject('${friendc.id}')"><br>
</c:forEach>
</form>

<a href ="index.jsp">메인으로</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.1.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/httpRequest.js"></script>
<script type="text/javascript">
	function idcheck(){
		var userid = document.getElementById("userId").value;
		var param = "command=idcheck&id="+userid;
		sendRequest("${pageContext.request.contextPath}/DispatcherServlet", 
				param, idcheckResult, "POST");		
	}
	function idcheckResult(){
		if(httpRequest.readyState==4){
			if(httpRequest.status==200){
				var idcheck = eval("(" + httpRequest.responseText + ")");
				if(idcheck.check){
					alert("사용가능한 아이디입니다.");
				}else{
					alert("이미 사용중인 아이디입니다.");
				}
				
			}
		}
	}
	
	
</script>
</head>
<body>

<div style="width:450px; border: 3px solid black;">
<center><h1><b>회원가입</b></h1></center>
<hr>
<form method="post" action="DispatcherServlet">
<input type="hidden" name="command" value="register">
<ul>
	<table align="center">
		<tr align="left">
			<th bgcolor=#FFFFE0><li>아이디</li></th>
			<td>
				<input type="text" name="id" id="userId">
				<input type="button" name="button" value="확인" onclick="idcheck()">
			</td>
		</tr>
		<tr align="left">
			<th bgcolor=#FFFFE0><li>비밀번호</li></th>
			<td>
				<input type="password" name="pass">
			</td>
		</tr>
		<tr align="left">
			<th bgcolor=#FFFFE0><li>성별</li></th>
			<td>
				<input type="radio" name="gender" value="male">남
				<input type="radio" name="gender" value="female">여
			</td>
		</tr>
		<tr align="left">
			<th bgcolor=#FFFFE0><li>생년월일</li></th>
			<td>
				<input type="text" name="birth" size="5">
			</td>
		</tr>
		<tr align="left">
			<th bgcolor=#FFFFE0><li>지역</li></th>
			<td>
				<select name="local">
				<option value="02">서울</option>
				<option value="051">부산</option>
				<option value="031">경기도</option>
				</select>
			</td>
		</tr>
		<tr align="left">
			<th bgcolor=#FFFFE0><li>관심사</li></th>
			<td>
				<input type="checkbox" name="i1" value="sports">스포츠
				<input type="checkbox" name="i1" value="fashion">패션
				<input type="checkbox" name="i1" value="movie">영화
			</td>
		</tr>
		<tr>
			<td></td>
			<td align="center">
				<input type="submit" value="등록">
				<input type="reset" value="취소">
			</td>
		</tr>
		
	</table>
</ul>
</form>

</body>
</html>
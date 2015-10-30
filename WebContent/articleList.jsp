<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
[
<c:forEach var="a" items="${data }" varStatus="status">
<c:if test="${!status.first }">,</c:if>
{num:${a.r_no }, pno:${a.p_no }, uid:'${a.u_id }', content:'${a.r_content }'}
</c:forEach>
]
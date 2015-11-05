<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    

[
<c:forEach var="a" items="${magazine }" varStatus="status">
<c:if test="${!status.first }">,</c:if>
{magazine:'${a.m_name }'}
</c:forEach>
]
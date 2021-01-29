<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>

<script>
	alert('메일 인증이 안된 아이디 입니다. 인증후 진행해 주세요.')
	location.href = "${root}user/login?mailfail=true"
</script>
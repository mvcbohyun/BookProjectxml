<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>

<script>
	alert('가입이 완료되었습니다.\n 메일 인증 후 로그인 하시기 바랍니다.')
	location.href = "${root}user/login"
</script>
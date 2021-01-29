<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>
<script>
	alert('메일 인증이 안된 아이디 입니다. 메일 인증을 하고 진행하시기 바랍니다.')
	location.href = '${root}user/mailchk'
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <c:set var='root' value="${pageContext.request.contextPath }/"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미니 프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<!-- 상단 메뉴 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<form:form action="${root}user/modify_pro" method="post" modelAttribute="modifyUserBean">
					<form:hidden path="UserIdExist"/>
						<div class="form-group">
							<form:label path="User_Name">이름</form:label>
							<form:input path="User_Name" class='form-control' readonly="true"/>
						</div>
						<div class="form-group">
							<form:label path="User_Id">아이디</form:label>
							<div class="input-group">
								<form:input path="User_Id" class='form-control' readonly="true"/>
							</div>
						</div>
							<div class="form-group">
							<form:label path="User_Pw">비밀번호</form:label>
							<form:password path="User_Pw" class='form-control'/>
							<form:errors path='User_Pw' style='color:red'/>
						</div>
						<div class="form-group">
							<form:label path="User_Pw2">비밀번호 확인</form:label>
							<form:password path="User_Pw2" class='form-control'/>
							<form:errors path='User_Pw2' style='color:red'/>
						</div>
						
						<div class="form-group">
							<form:label path="User_mail">메일</form:label>
							<div class="input-group">
								<form:input path="User_mail" class='form-control' readonly="true"/>
							</div>
						</div>
						<div class="form-group">
							<form:label path="User_CellPhone">핸드폰 번호</form:label>
							<div class="input-group">
								<form:input path="User_CellPhone" class='form-control'/>
							</div>
						</div>
						<div class="form-group">
							<form:label path="User_mail">성별</form:label><br/>
							<form:radiobutton path="User_Gender" value="0" label="남자" checked="checked" />
							<form:radiobutton path="User_Gender" value="1" label="여자"/>
						</div>
						
						
						<div class="form-group">
							<div class="text-right">
								<form:button class='btn btn-primary'>회원가입</form:button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>









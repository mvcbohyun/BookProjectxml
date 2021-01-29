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
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<script type="text/javascript">

	function remailchk(){
		
	var User_mail =$("#User_mail").val()
	var User_Id =$("#User_Id_chk").val()
	
	if(User_mail.length==0){
		alert('메일을 입력해주세요')
		return
	}
	if(User_mail.indexOf("@")<1){
		alert('메일 형식이 잘못되었습니다.')
		return
	}
	
	$.ajax({
		url : '${root}user/remailchk/'+User_mail+"/"+User_Id,
		type : 'get',
		dataType : 'text',
		success : function(result){
			if(result.trim()== 'true'){
				alert('인증메일 보내기 완료 .')
				
				
			}else{
				alert('인증메일 보내기 실패.')
				
			}
		}
	})
	
	}
</script>
<body>

<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<c:if test="${fail ==true}">
						<div class="alert alert-danger">
							<h3>로그인 실패</h3>
							<p>아이디 비밀번호를 확인해주세요</p>
						</div>
					</c:if>
					<c:if test="${mailfail ==true}">
						<div class="alert alert-danger">
							<h3>메일인증</h3>
							<div class="form-group">
							<input name="User_Id_chk" id="User_Id_chk" class='form-control'/>
								<div class="input-group">
									<input name="User_mail" id="User_mail" class='form-control'/>
									<div class="input-group-append">
									<input type="button"  class='btn btn-primary' onclick="remailchk()" value="메일인증"></input>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<form:form action="${root }user/login_pro" method='post' modelAttribute="tempLoginUserBean">
						<div class="form-group">
							<form:label path="User_Id">아이디</form:label>
							<form:input path="User_Id" class='form-control'/>
							<form:errors path='User_Id' style='color:red'/>
						</div>
						<div class="form-group">
							<form:label path="User_Pw">비밀번호</form:label>
							<form:password path="User_Pw" class='form-control'/>
							<form:errors path='User_Pw' style='color:red'/>
						</div>
						<div class="form-group text-right">
							<form:button class='btn btn-primary'>로그인</form:button>
							<a href="${root }user/join" class="btn btn-danger">회원가입</a>
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








    
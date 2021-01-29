<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value='${pageContext.request.contextPath }/' />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미니 프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<script type="text/javascript">
	function Commentinsert(){
	var memo =$("#memo").val()
	var commentId =$("#commentId").val()
	var boardcode =$("#boardcode").val()
	
	if(memo.length == 0){
		alert('댓글을 입력해주세요.')
		return 
	}
	$.ajax({
		url : '${root}comment/commentwrite/'+ memo +"/"+boardcode+"/"+ commentId,
		type : 'get',
		dataType : 'text',
		success : function(result){
			if(result.trim()== 'true'){
				alert('댓글 입력 완료.')
				$('textarea').val('');
				 getCommentList();
			}else{
				$('textarea').val('');
				alert('댓글 입력 실패.')
			}
		}
	})
	
}
	
	/**
	 * 초기 페이지 로딩시 댓글 불러오기
	 */
	$(function(){
	    
	    getCommentList();
	    
	});
	 
	/**
	 * 댓글 불러오기(Ajax)
	 */
	function getCommentList(){
	    
	    $.ajax({
	        type:'GET',
	        url : '${root}comment/commentread/${content_idx}',
	        dataType : "json",
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
	        success : function(data){
	            
	            var html = "";
	         
	            if(data.length > 0){
	                 $(data).each(function () {
	                    html += "<li data-code='" + this.code + "' class='replyLi'>"
	                    html +=  "<p  id ='commentname'><strong>" +this.commentname + "</strong></p>"
	                    html +=  "<p class='memo'>" + this.memo + "</p>"
	                    html += "</li><hr/>";
	                  
	                })
	                
	            } else {
	                
	                html += "<div>";
	                html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
	                html += "</table></div>";
	                html += "</div>";
	                
	            }
	            $("#commentList").html(html);
	            
	        },
	        error:function(request,status,error){
	            
	       }
	        
	    });
	}
	
	
	


	
	


</script>
<body>

	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<div class="container" style="margin-top: 100px">
		<div class="row">
			
			<div class="col-sm-12">
			<div class="card shadow">
				<div class="card-body">
					<div class="form-group">
						<label for="board_writer_name">작성자</label> <input type="text"
							id="board_writer_name" name="board_writer_name"
							class="form-control"
							value="${readContentBean.content_writer_name }"
							disabled="disabled" />
					</div>
					<div class="form-group">
						<label for="board_date">작성날짜</label> <input type="text"
							id="board_date" name="board_date" class="form-control"
							value="${readContentBean.addDate }" disabled="disabled" />
					</div>
					<div class="form-group">
						<label for="board_subject">제목</label> <input type="text"
							id="board_subject" name="board_subject" class="form-control"
							value="${readContentBean.content_subject }" disabled="disabled" />
					</div>
					<div class="form-group">
						<label for="board_content">내용</label>
						<textarea id="board_content" name="board_content"
							class="form-control" rows="10" style="resize: none"
							disabled="disabled">${readContentBean.content_text }</textarea>
					</div>
					<c:if test="${readContentBean.content_file !=null }">
						<div class="form-group">
							<label for="board_file">첨부 이미지</label> <img
								src="${root }upload/${readContentBean.content_file}"
								width="100%" />
						</div>
					</c:if>
					<div class="form-group">
						<div class="text-right">
							<a href="${root }board/main?info_idx=${info_idx}"
								class="btn btn-primary">목록보기</a>
							<c:if
								test="${loginUserBean.user_idx ==readContentBean.content_writer_idx}">
								<a
									href="${root }board/modify?info_idx=${info_idx}&content_idx=${content_idx}"
									class="btn btn-info">수정하기</a>
								<a
									href="${root }board/delete?info_idx=${info_idx}&content_idx=${content_idx}"
									class="btn btn-danger">삭제하기</a>
							</c:if>
						</div>
					</div>
				
				</div>
				</div>
				<div class="card" id="comments">
					<div class="card-body">
						<!-- 댓글 작성 창 -->
						<form>
							<div class="form-group">
								<label>댓글 작성</label>
								<textarea class="form-control" id="memo" name="memo" rows="3"></textarea>
							</div>
							<input type="hidden" id="commentId" name="commentId"
								value="${loginUserBean.user_idx}"> <input type="hidden"
								id="boardcode" name="boardcode" value="${content_idx}">
							<button type="button" class="btn btn-primary"
								id="comment-create-btn" onclick="Commentinsert()">등록</button>
						</form>



						<!--<ul class="list-unstyled"> <li class="media mt-4" id="comment" style="background-color:silver"> -->
						<ul id="commentList">
						</ul>
						<!-- </li> </ul>-->
					</div>
				</div>

				
			</div>
		</div>
		</div>




		<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>

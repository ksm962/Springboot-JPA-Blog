<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
글 번호:<span id="id"><i>${board.id }</i> </span>
작성자:<span ><i>${board.name.username}</i></span>
<br/>
		<div class="form-group">
			<label for="title">Title</label> 
			<h3>${board.title }</h3>
		</div>

		<div class="form-group">
			<label for="content">Content:</label>
			<div>${board.content }</div>
		</div>
		
		<c:if test = "${board.name.id == principal.name.id }">
		<a href ="/board/${board.id }/updateForm" class ="btn btn-warning"> 수정</a>
		<button id = "btn-delete" class = "btn btn-danger"> 삭제</button>
		</c:if>
		<button class = "btn btn-secondary" onclick ="history.back()"> 돌아가기</button>

</div>


<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>



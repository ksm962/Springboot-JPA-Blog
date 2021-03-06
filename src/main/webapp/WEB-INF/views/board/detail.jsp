<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	글 번호:<span id="id"><i>${board.id }</i> </span> 작성자:<span><i>${board.name.username}</i></span> <br />
	<div class="form-group">
		<label for="title">Title</label>
		<h3>${board.title }</h3>
	</div>

	<div class="form-group">
		<label for="content">Content:</label>
		<div>${board.content }</div>
	</div>
	<c:if test="${board.name.id == principal.name.id }">
		<a href="/board/${board.id }/updateForm" class="btn btn-warning"> 수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>


	<div class="card">
		<form>
			<input type="hidden" id="nameid"  value="${principal.name.id }" />
			<input type="hidden" id="boardid"  value="${board.id }" />
			<div class="card-body">
				<textarea id="reply-content" class="form-control" row="1"></textarea>
			</div>
			<div class="card-footer">
				<button type="button" class="btn btn-primary" id="btn-reply-save">등록</button>
			</div>
	</div>
	</form>


	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="reply--box" class="list-group">
			<c:forEach var="reply" items="${board.replys}">
				<li id="reply--1" class="list-group-item d-flex justify-content-between">
					<div>${reply.content }</div>
					<div class="d-flex">
						<div class="font-italic">작성자 :${reply.name.username } &nbsp;</div>
						<button class="badge">삭제</button>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>


<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>



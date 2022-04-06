<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container">
	<form>
		<input type="hidden" value="${principal.name.id}" id="id">
		<div class="form-group">
			<label for="username">Username:</label> <input type="text" value="${principal.name.username}" class="form-control" placeholder="Enter username" id="username">
		</div>

		<c:if test="${empty principal.name.oauth }">
			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
			</div>
		</c:if>
		<div class="form-group">
			<label for="email">Email:</label> <input type="email" value="${principal.name.email }" class="form-control" placeholder="Enter email" id="email"  readonly>
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">회원수정완료</button>
</div>
<script src="/js/name.js"></script>

<%@ include file="../layout/footer.jsp"%>



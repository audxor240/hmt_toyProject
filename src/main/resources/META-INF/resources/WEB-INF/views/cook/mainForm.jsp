<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<div style="margin-left:8px;">
		<a  class="btn btn-primary"  href="/cook/cookWriteForm" >작성</a>
	</div>
	<c:forEach var="cook" items="${cooks.content}">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${cook.cook_name}</h4>
				<a href="/cook/${cook.id }" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>
	<ul class="pagination justify-content-center">
		<!-- justify-content-center : flex 가운데 정렬 -->
		<c:choose>
			<c:when test="${cooks.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${cooks.number-1}">이전</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${cooks.number-1}">이전</a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${cooks.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${cooks.number+1}">다음</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${cooks.number+1}">다음</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
<%@ include file="../layout/footer.jsp"%>
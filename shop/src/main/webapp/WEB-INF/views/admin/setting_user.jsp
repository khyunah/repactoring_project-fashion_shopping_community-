<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="left_nav.jsp"%>

<div class="admin-container">

<div class="container">

	<div style="height: 50px;"></div>
	<h2>회원정보 관리</h2>
	<div style="height: 20px;"></div>

	<hr />

	<div class="setting-header-container">

		<div class="d-flex">

			<div class="form-group mr-2 columnBox">
				<select class="form-control " id="sel1"
					onchange="chooseUserColumn(this)">
					<option>선택</option>
					<option id="ID">ID</option>
					<option id="USERNAME">USERNAME</option>
					<option id="NAME">NAME</option>
					<option id="ADDRESS">ADDRESS</option>
					<option id="OAUTH">OAUTH</option>
				</select>
				<c:if test="${column eq 'OAUTH'}">
					<c:choose>
						<c:when test="${keyword == 'KAKAO'}">
							<select class="form-control oauthSelectBox"
								onchange="chooseOauth(this)">
								<option>선택</option>
								<option>ORIGIN</option>
								<option selected>KAKAO</option>
							</select>
						</c:when>
						<c:otherwise>
							<select class="form-control oauthSelectBox"
								onchange="chooseOauth(this)">
								<option>선택</option>
								<option selected>ORIGIN</option>
								<option>KAKAO</option>
							</select>
						</c:otherwise>
					</c:choose>
				</c:if>
			</div>

			<div>
				<form class="form-inline" action="/admin/user/select" method="get" onsubmit="return checkColumn()">
					<input type="text" class="form-control" name="keyword" value="${keyword}" placeholder="검색어를 입력해주세요." id="keyword" />
					<input type="hidden" id="column" name="column" value="${column}">
					<button type="submit" class="btn btn-dark ml-2">검색</button>
				</form>
			</div>

		</div>

		<div class="setting-user-btn-box">
			<a href="/admin/user/select?keyword=&column=" class="btn btn-dark">전체조회</a>
			<button type="button" class="btn btn-success"
				onclick="admin.userChangeRole()">권한설정</button>
			<button type="button" class="btn btn-danger"
				onclick="admin.userDelete()">삭제</button>
		</div>

	</div>

	<div style="height: 70px;"></div>

	<table class="table table-dark table-hover">
		<%@ include file="admin_user_list.jsp"%>
	</table>

</div>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

		<div class="order-complete-wrap">
			<div class="order-complete-box">
					<p class="p1">사용자에 의해 주문이 취소되었습니다.</p>
					<p class="p2">다시 시도해 주세요</p>
			</div>
			
				<button style="float: right; margin-right: 190px;" onclick="location.href='/'" class="btn btn-dark">홈으로</button>			
				<button style="float: right; margin-right: 5px;" onclick="history.back()" class="btn btn-dark">장바구니</button>
			</div>
</body>
</html>
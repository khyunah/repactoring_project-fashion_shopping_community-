<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>


	<div class="order-complete-wrap">
			<div class="order-complete-box">
					<p class="p1">주문이 완료되었습니다.</p>
					<p class="p2">${principal.user.id} 고객님께서 ${pageTokenInfo.approvedAt}에 주문하신 내역입니다.</p>
			</div>
			<div class="imgBox">
				<img class="gradient-line" alt="" src="/image/gradient_line.png">
			</div><!-- imgBox -->
			<div class="order-complete-row order-complete-row1">
					<h3>주문상품 내역</h3>
			
	<table>
<!-- 	<table border="1" cellspacing="0" cellpadding="20px" id="adD_tbl" class="adD_tbl"> -->
		<thead class="tbl-head">
			<tr>
				<th>상품정보</th>
				<th>수량</th>
				<th>결제 방법</th>
				<th>구매가</th>
			</tr>
		</thead>
		
		<tbody>
		
				<tr>
					<td>${pageTokenInfo.itemName}</td>
					<td>${pageTokenInfo.quantity}개</td>
					<td>${pageTokenInfo.paymentMethodType}</td>
					<td>${formatPrice} 원</td>
				</tr>
		
		</tbody>
		
	</table>
	<button id="order-complete-home-button" type="button" class="btn btn-dark" onclick="location.href='/'">홈으로</button>
	</div><!-- order-complete-row1 -->		 
	</div> <!-- order-complete-wrap -->
		
</body>

</html>


	
</body>
</html>
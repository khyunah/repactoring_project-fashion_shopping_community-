<%@page import="java.text.NumberFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

	<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">상품 상세 보기</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      <div style = "">
      <p style="font-weight: bold; margin-top: 0px; margin-bottom:0px; font-size: 17px;  font-color: #333;">주문번호</p>
      <p class = "orderNumberModal" style="text-indent: 2px; margin-top: 0px; font-color: #333;"></p>
      </div>
        <table>
		<thead class="tbl-head">
			<tr> 
				<th>상품목록</th>
			</tr>
		</thead>
		
		<tbody>

			<tr>
				<td class = "orderItemModal"></td>
			</tr>

		</tbody>
		
		</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


	<div class="receipt-container">
			<div class="order-complete-row order-complete-row1">
					<h3>주문내역</h3>

	<table>
		<thead class="tbl-head">
			<tr> 
				<th style="display:none;">orderItem</th>
				<th>상품명</th>
				<th>주문번호</th>
				<th>주문일</th>
				<th>구매가</th>
				<th>주소</th>
				<th>상품 상세보기</th>
			</tr>
		</thead>
		

		<tbody>
		<c:forEach items="${purchaseHistoryGroupList}" var="purchaseHistory">
			<tr>
			<td class="orderItem" style="display:none;">
				<c:forEach items="${purchaseHistoryList}" var="history">
					<c:if test="${purchaseHistory.tid eq history.tid}">
					
					${history.item.name}<br/>
						
					</c:if>
				</c:forEach>
			</td>
				<td>${purchaseHistory.itemName}</td>
				<td class = "orderNumber">${purchaseHistory.tid}</td>
				<td>${purchaseHistory.createdAt}</td>
				<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${purchaseHistory.total}"/>원</td>
				<td>${purchaseHistory.user.address}</td>
				<td><button class="btn text-white detailBtn" style="background-color: #453675;" data-bs-toggle="modal" data-bs-target="#exampleModal">
					  상세보기
					</button>
				</td>
			</tr>
		</c:forEach>
		</tbody>
		
	</table>

	
	</div><!-- order-complete-row1 -->		 
	</div> <!-- order-complete-wrap -->
						
</body>
<script>
$('.detailBtn').on('click', function(){
	let orderNumber = $(this).parent().parent().find('.orderNumber').html();
	$('.orderNumberModal').html(orderNumber);
	let orderItem=$(this).parent().parent().find('.orderItem').html();
	$('.orderItemModal').html(orderItem);
})

</script>

</html>
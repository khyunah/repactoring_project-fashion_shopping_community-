<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="left_nav.jsp"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.0/moment.min.js"></script>
<style>
.admin-container {
	padding-top: 50px;
}

.chart-category-container {
	display: flex;
	justify-content: flex-end;
}

.c-container {
	margin-bottom: 70px;
	width: 100%;
	padding-top: 5px;
	border-top: 1px solid black;
	border-bottom: 1px solid black;
}

.c-h-container {
	margin-bottom: 20px;
	width: 100%;
	padding-top: 5px;
	border-top: 1px solid black;
}

.chart-container {
	width: 100%;
	height: 330px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.chart-mini-container {
	width: 100%;
	height: 270px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.chart-text-container {
	margin-top: 20px;
	margin-right: 30px;
	min-width: 180px;
	text-align: center;
	align-self: flex-start;
}

.chart-today {
	font-size: 23px;
	font-family: 'Gowun Dodum', sans-serif;
	font-weight: bold;
}

.chart-text {
	font-size: 16px;
	font-family: 'Gowun Dodum', sans-serif;
	font-weight: bold;
}

.chart-third-text {
	font-size: 16px;
	font-family: 'Gowun Dodum', sans-serif;
	font-weight: bold;
	text-align: center;
	margin-top: 15px;
	margin-bottom: 10px;
}

.chart-result-container {
	width: 500px;
}

.chart-second-result-container {
	width: 300px;
}

.chart-p {
	text-align: center;
	margin-bottom: 20px;
	font-family: 'Gowun Dodum', sans-serif;
	font-weight: bold;
	font-size: 18px;
}

.chart-table-title {
	margin-bottom: 20px;
	font-family: 'Gowun Dodum', sans-serif;
	font-weight: bold;
	font-size: 25px;
}

.second-chart-div {
	width: 300px;
}

.chart-heart-top5-div,
.chart-reply-top5-div {
	width: 400px;
}

.chart-board-div {

}

.chart-heart-title-td {
	max-width: 300px;
	display: inline-block;
    width: 280px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

</style>

<div class="admin-container">

	<div class="container">
		<div class="chart-category-container">
			<a href="/admin/graph-join" type="button" class="btn btn-outline-dark">회원 통계</a> 
			<a href="/admin/graph-sales" type="button" class="btn btn-outline-dark">상품 통계</a> 
			<a href="/admin/graph-community" type="button" class="btn btn-outline-dark">SNS 통계</a>
		</div>
		
		<p class="chart-table-title">커뮤니티 게시글</p>
		<div class="c-container">
			<div class="chart-container">
				<div class="chart-text-container">
					<p class="chart-today">${today}</p>

					<table class="table table-bordered table-sm">
						<thead class="chart-text">
							<tr>
								<th>기간</th>
								<th>게시글 수</th>
							</tr>
						</thead>
						<tbody class="chart-text">
							<tr>
								<td>오늘</td>
								<td>${todayList.count}</td>
							</tr>
							<tr>
								<td>한주</td>
								<td>${weekList.count}</td>
							</tr>
							<tr>
								<td>한달</td>
								<td id="month-count"></td>
							</tr>
							<tr>
								<td>총</td>
								<td>${totalList.count}</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="chart-board-div">
					<p class="chart-p">한달 게시글 수</p>
					<div>
						<canvas id="chart-1" width="900" height="290"></canvas>
					</div>
				</div>
			</div>
		</div>

		<p class="chart-table-title">좋아요 Top 5</p>
		<div class="c-h-container">
			<div class="chart-mini-container">
				<div class="chart-text-container">

					<table class="table table-bordered table-sm">
						<thead class="chart-text">
							<tr>
								<th>NO</th>
								<th>제목</th>
								<th>회원ID</th>
								<th>좋아요 수</th>
							</tr>
						</thead>
						<tbody class="chart-text chart-heart-tbody"></tbody>
					</table>
				</div>
				<div class="chart-heart-top5-div">
					<p class="chart-p">금주 좋아요 Top 5</p>
					<div>
						<canvas id="chart-4"></canvas>
					</div>
				</div>
			</div>
		</div>
		
		<p class="chart-table-title">댓글 수 Top 5</p>
		<div class="c-container">
			<div class="chart-mini-container">
				<div class="chart-text-container">

					<table class="table table-bordered table-sm">
						<thead class="chart-text">
							<tr>
								<th>NO</th>
								<th>제목</th>
								<th>회원ID</th>
								<th>댓글 수</th>
							</tr>
						</thead>
						<tbody class="chart-text chart-reply-tbody"></tbody>
					</table>
				</div>
				<div class="chart-reply-top5-div">
					<p class="chart-p">금주 댓글 Top 5</p>
					<div>
						<canvas id="chart-5"></canvas>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</div>
<script src="/js/chart_community.js"></script>

</body>
</html>

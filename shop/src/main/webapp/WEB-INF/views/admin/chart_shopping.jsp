<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="left_nav.jsp"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.0/moment.min.js"></script>
<style>

td {
	max-width: 135px;
	word-break: break-word;
}
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

.chart-container {
	width: 100%;
	height: 330px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.chart-text-container {
	margin-top: 10px;
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

.chart-top-income {
	width: 400px;
}

.chart-top-count {
	width: 320px;
}

.chart-top {
	width: 300px;
}

.chart-top5-user-income {
	width: 400px;
}

.chart-top5-user-count {
	width: 300px;
}

.chart-category-income {
	width: 450px;
}

.chart-category-count {
	width: 300px;
}

</style>


<div class="admin-container">

	<div class="container">
		<div class="chart-category-container">
			<a href="/admin/graph-join" type="button" class="btn btn-outline-dark">?????? ??????</a> 
			<a href="/admin/graph-sales" type="button" class="btn btn-outline-dark">?????? ??????</a> 
			<a href="/admin/graph-community" type="button" class="btn btn-outline-dark">SNS ??????</a>
		</div>

		<p class="chart-table-title">?????? ?????? ??????</p>
		<div class="c-container">
			<div class="chart-container">
				<div class="chart-text-container">
					<p class="chart-today">${today}</p>

					<table class="table table-bordered table-sm">
						<thead class="chart-text">
							<tr>
								<th>??????</th>
								<th>?????? ??????</th>
								<th>?????????</th>
							</tr>
						</thead>
						<tbody class="chart-text">
							<tr>
								<td>??????</td>
								<td id="today-income"></td>
								<td id="today-count"></td>
							</tr>
							<tr>
								<td>??????</td>
								<td id="week-income"></td>
								<td id="week-count"></td>
							</tr>
							<tr>
								<td>???</td>
								<td id="total-income">${totalIncome}</td>
								<td id="total-count">${totalList.totalCount}</td>
							</tr>
						</tbody>
					</table>

				</div>

				<div class="chart-result-container" style="margin-right: 50px;">
					<p class="chart-p">?????? ?????? ??????</p>
					<div>
						<canvas id="myChart"></canvas>
					</div>
				</div>
				<div class="chart-second-result-container">
					<div class="second-chart-div">
						<p class="chart-p">?????? ?????? ??????</p>
						<canvas id="second-chart"></canvas>
					</div>
				</div>
			</div>
		</div>

		<p class="chart-table-title">?????? ?????? ?????? Top 5</p>
		<div class="c-container">
			<div class="chart-container">
				<div class="chart-text-container">

					<table class="table table-bordered table-sm">
						<thead class="chart-text">
							<tr>
								<th>??????</th>
								<th>?????????</th>
								<th>?????? ??????</th>
								<th>?????????</th>
							</tr>
						</thead>
						<tbody class="chart-text chart-top5-item"></tbody>
					</table>

				</div>
				<div class="chart-top-income" style="margin-right: 50px;">
					<p class="chart-p">?????? Top 5 ?????? ?????? ??????</p>
					<div>
						<canvas id="chart-4"></canvas>
					</div>
				</div>
				<div class="chart-top-count">
					<p class="chart-p">?????? Top 5 ?????? ?????? ??????</p>
					<canvas id="chart-5"></canvas>
				</div>
			</div>
		</div>

		<p class="chart-table-title">????????? ???????????? Top 5</p>
		<div class="c-container">
			<div class="chart-container">
				<div class="chart-text-container">

					<table class="table table-bordered table-sm">
						<thead class="chart-text ">
							<tr>
								<th>??????</th>
								<th>??????ID</th>
								<th>?????? ??????</th>
								<th>?????????</th>
							</tr>
						</thead>
						<tbody class="chart-text chart-top5-user"></tbody>
					</table>

				</div>

				<div class="chart-top5-user-income" style="margin-right: 50px;">
					<p class="chart-p">????????? ?????? Top 5 ????????? ?????? ??????</p>
					<div>
						<canvas id="chart-6"></canvas>
					</div>
				</div>
				<div class="chart-top5-user-count">
					<p class="chart-p">????????? ?????? Top 5 ????????? ?????? ??????</p>
					<canvas id="chart-7"></canvas>
				</div>
			</div>
		</div>
		
		<p class="chart-table-title">????????? ?????? ??????????????? ?????? ??????</p>
		<div class="c-container">
			<div class="chart-container">
				<div class="chart-text-container">

					<table class="table table-bordered table-sm">
						<thead class="chart-text">
							<tr>
								<th>??????</th>
								<th>????????????</th>
								<th>?????? ??????</th>
								<th>?????????</th>
							</tr>
						</thead>
						<tbody class="chart-text chart-category"></tbody>
					</table>

				</div>

				<div class="chart-category-income" style="margin-right: 50px;">
					<p class="chart-p">????????? ??????????????? ?????? ??????</p>
					<div>
						<canvas id="chart-8"></canvas>
					</div>
				</div>
				<div class="chart-category-count">
					<p class="chart-p">????????? ??????????????? ?????? ??????</p>
					<canvas id="chart-9"></canvas>
				</div>
			</div>

		</div>
	
	</div>
</div>
<script src="/js/chart_shopping.js"></script>

</body>
</html>

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
	display: flex;
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
}

.chart-text-container {
	width: 150px;
	margin-right: 30px;
	text-align: center;
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
	width: 250px;
}

.third-canvas-div {
	width: 200px;
}

.admin-null-info {
	font-family: 'Gowun Dodum', sans-serif;
	font-weight: bold;
	text-align: center;
}

</style>

<div class="admin-container">

	<div class="container">
		<div class="chart-category-container">
			<a href="/admin/graph-join" type="button" class="btn btn-outline-dark">회원 통계</a>
			<a href="/admin/graph-sales" type="button" class="btn btn-outline-dark">상품 통계</a>
			<a href="/admin/graph-community" type="button" class="btn btn-outline-dark">SNS 통계</a>
		</div>

		<p class="chart-table-title">회원 가입자 수 통계</p>
		<div class="c-container">
			<div class="chart-container">
				<div class="chart-text-container">
					<p class="chart-today">${today}</p>
					<table class="table table-bordered table-sm">
					    <thead class="chart-text">
					      <tr>
					        <th>기간</th>
					        <th>가입자 수</th>
					      </tr>
					    </thead>
					    <tbody class="chart-text">
					      <tr>
					        <td>오늘</td>
					        <td id="today-count"></td>
					      </tr>
					      <tr>
					        <td>한주</td>
					        <td id="week-count"></td>
					      </tr>
					      <tr>
					        <td>총</td>
					        <td id="total-count">${totalCount.joinCount}</td>
					      </tr>
					    </tbody>
					</table>
				</div>
				<div class="chart-result-container" style="margin-right: 50px;">
					<p class="chart-p">금주 일자별 가입자 수</p>
					<div>
						<canvas id="myChart"></canvas>
					</div>
				</div>
				<div class="chart-second-result-container">
					<div class="second-chart-div">
					<p class="chart-p">OAuth별 오늘 가입자</p>
						<canvas id="second-chart"></canvas>
					</div>
					<div class="third-canvas-div">
						<p class="chart-third-text">OAuth별 총 가입자</p>
						<canvas id="third-chart"></canvas>
					</div>
				</div>
			</div>
		</div>
		
		<p class="chart-table-title">오늘 가입한 회원</p>
		<div class="setting-user-btn-box" style="display: flex; justify-content: flex-end; margin-bottom: 10px;">
			<a href="/admin/user/select?keyword=&column=" class="btn btn-dark">전체조회</a>
			<button type="button" class="btn btn-success"
				onclick="admin.userChangeRole()">권한설정</button>
			<button type="button" class="btn btn-danger"
				onclick="admin.userDelete()">삭제</button>
		</div>
		
		<table class="table table-bordered table-hover">
			<%@ include file="admin_user_list.jsp"%>
		</table>

	</div>
</div>
<script src="/js/chart_user.js"></script>
<script>
function clickList(target) {
	let list = target.parentNode;
	let trs = list.getElementsByTagName("tr");

	// 선택안된 
	var backColor = "white";
	var textColor = "black";
	// 선택 된
	var orgBColor = "#cbcbcb";
	var orgTColor = "black";

	let th_value = [];

	for (var i = 0; i < trs.length; i++) {
		if (trs[i] != target) {
			trs[i].style.backgroundColor = backColor;
			trs[i].style.color = textColor;
		} else {
			trs[i].style.backgroundColor = orgBColor;
			trs[i].style.color = orgTColor;
			var td = trs[i].getElementsByTagName("td");
			for (let j = 0; j < td.length; j++) {
				th_value[j] = td[0].innerText;
			}
		}
	}
	var id = th_value[0];
	$("#admin-object-id").text(id);
}
</script>

</body>
</html>
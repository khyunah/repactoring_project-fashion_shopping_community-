var salesWeek = {
	labels: [],
	dataSets: [],

	weekData: function() {
		$.ajax({
			type: 'GET',
			url: '/admin/graph/sales-result/week',
			contentType: 'application/json',
			dataType: 'json'
		}).done(function(response) {
			if(response != null){
				$.each(response, function(index, obj) {
				salesWeek.labels.push(obj.salesDate);
				salesWeek.dataSets.push(obj.totalIncome);
				countSets.push(obj.totalCount);
			});
			checkData();
			salesWeek.setDataToInput();
			renderLine('myChart', dateRange, '판매 금액', resultData, 'rgba(255, 0, 0, 0.65)');
			randerBar('second-chart', dateRange, '판매 수량', countList, weekBackColor, weekBorderColor);
			}
			
		}).fail(function() {
			console.log("실패");
		});
	}, 
	
	setDataToInput: function (){
		// 오늘 판매금액, 판매량
		$("#today-income").text(formatOneIncome(resultData[6]));
		$("#today-count").text(countList[6]);
	
		// 금주 판매금액, 판매량
		var weekIncome = 0;
		$.each(salesWeek.dataSets, function(index, count) {
			weekIncome += count;
		});
		weekIncome = formatOneIncome(weekIncome);
		
		var weekCount = 0;
		$.each(countSets, function(index, income){
			weekCount += income;
		});
		
		$("#week-income").text(weekIncome);
		$("#week-count").text(weekCount);
	}, 

};

function formatOneIncome(income){
	return new Intl.NumberFormat('ko-KR').format(income);
}

// 화면에 뿌려줄 날짜 
var dateRange = [];
// 0값을 넣어주기 위한 비교 날짜 리스트 
var checkDate = [];
// 연산을 통한 결과 금액 리스트 
var resultData = [];

// 통신을 통해 얻어온 countList
var countSets = [];
// 날짜별로 정리한 countList 
var countList = [];

function checkData() {

	for (var i = 6; i >= 0; i--) {
		dateRange.push(moment().subtract(i, 'd').format("MM-DD"));
		checkDate.push(moment().subtract(i, 'd').format("YYYY-MM-DD"));
	}

	var index = 0;
	for (var i = 0; i < checkDate.length; i++) {

		if (salesWeek.labels[index] == checkDate[i]) {
			resultData.push(salesWeek.dataSets[index]);
			countList.push(countSets[index]);
			index++;
		} else {
			resultData.push(0);
			countList.push(0);
		}
	}
}

var salesItem = {
	labels: [],
	dataSets: [],

	weekData: function() {
		$.ajax({
			type: 'GET',
			url: '/admin/graph/sales-result/item',
			contentType: 'application/json',
			dataType: 'json'
		}).done(function(response) {
			if(response != null){
				$.each(response, function(index, obj) {
				salesItem.labels.push(obj.name);
				salesItem.dataSets.push(obj.totalIncome);
				countSets.push(obj.totalCount);
			});
			salesItem.setDataToInput();
			randerBar('chart-4', salesItem.labels, '판매 금액', salesItem.dataSets, topBackColor, topBorderColor);
			randerBar('chart-5', salesItem.labels, '판매 수량', countSets, topBackColor, topBorderColor);
			}
			
		}).fail(function() {
			console.log("실패");
		});
	}, 
	
	setDataToInput: function (){
		var formatIncome = numberFormatter(salesItem.dataSets);
		
		$.each(salesItem.labels, function(index, name){
			$(".chart-top5-item").append(
				`<tr class="chart-top5-item-row-${index + 1}">
			        <td>${index + 1}</td>
			        <td>${name}</td>
			      </tr>`
			);
		});
		$.each(formatIncome, function(index, income){
			$(`.chart-top5-item-row-${index + 1}`).append(
				`<td>${income}</td>`
			);
		});
		$.each(countSets, function(index, count){
			$(`.chart-top5-item-row-${index + 1}`).append(
				`<td>${count}</td>`
			)
		});
	}
};

var top5UserLabels = [];
var top5UserIncomeData = [];
var top5UsercountData = [];

var top5User = {
	userData: function() {
		$.ajax({
			type: 'GET',
			url: '/admin/graph/sales-result/user',
			contentType: 'application/json',
			dataType: 'json'
		}).done(function(response) {
			if(response != null){
				$.each(response, function(index, obj) {
				console.log('유저 네임 : ' + obj.totalIncome);
				top5UserLabels.push(obj.name);
				top5UserIncomeData.push(obj.totalIncome);
				top5UsercountData.push(obj.totalCount);
			});
			top5User.setDataToInput();
			top5User.subName();
			
			randerBar('chart-6', top5UserLabels, '구매 금액', top5UserIncomeData, top5UserBackColor, top5UserBorderColor);
			randerBar('chart-7', top5UserLabels, '구매 수량', top5UsercountData, top5UserBackColor, top5UserBorderColor);
			}
			
		}).fail(function() {
			console.log("실패");
		});
	}, 
	
	setDataToInput: function (){
		var formatIncome = numberFormatter(top5UserIncomeData);
		
		$.each(top5UserLabels, function(index, name){
			$(".chart-top5-user").append(
				`<tr class="chart-top5-user-row-${index + 1}">
			        <td>${index + 1}</td>
			        <td>${name}</td>
			      </tr>`
			);
		});
		$.each(formatIncome, function(index, income){
			$(`.chart-top5-user-row-${index + 1}`).append(
				`<td>${income}</td>`
			);
		});
		$.each(top5UsercountData, function(index, count){
			$(`.chart-top5-user-row-${index + 1}`).append(
				`<td>${count}</td>`
			)
		});
	},
	
	subName: function(){
		$.each(top5UserLabels, function(index, name){
			top5UserLabels[index] = name.substring(0, 12);
		});
	}
};


var categoryLabels = [];
var incomeData = [];
var countData = [];

var category = {
	categoryData: function() {
		$.ajax({
			type: 'GET',
			url: '/admin/graph/sales-result/category',
			contentType: 'application/json',
			dataType: 'json'
		}).done(function(response) {
			if(response != null){
				$.each(response, function(index, obj) {
				categoryLabels.push(obj.name);
				incomeData.push(obj.totalIncome);
				countData.push(obj.totalCount);
			});
			category.checkCategory();
			category.setDataToInput();

			randerBar('chart-8', categoryLabels, '판매 금액', incomeData, categoryBackColor, categoryBorderColor);
			randerBar('chart-9', categoryLabels, '판매 수량', countData, categoryBackColor, categoryBorderColor);
			}
			
		}).fail(function() {
			console.log("실패");
		});
	}, 
	
	setDataToInput: function (){
		var formatIncome = numberFormatter(incomeData);
		$.each(categoryLabels, function(index, name){
			$(".chart-category").append(
				`<tr class="chart-category-row-${index + 1}">
			        <td>${index + 1}</td>
			        <td>${name}</td>
			      </tr>`
			);
		});
		$.each(formatIncome, function(index, income){
			$(`.chart-category-row-${index + 1}`).append(
				`<td>${income}</td>`
			);
		});
		$.each(countData, function(index, count){
			$(`.chart-category-row-${index + 1}`).append(
				`<td>${count}</td>`
			)
		});
	}, 
	
	checkCategory: function (){
				
		var categoryList = [
			'SHIRTS', 'PANTS', 'ACCESSORY', 'SHOES', 'SKIRT', 'ONEPIECE', 'OUTER'
		];
		
		for(var i = 0; i < categoryList.length; i++){
			for(var j = 0; j < categoryLabels.length; j++){
				if(categoryList[i] == categoryLabels[j]){
					categoryList[i] = 'OK';
				}
			}
		}
		
		$.each(categoryList, function(index, category){
			if(category != 'OK'){
				categoryLabels.push(category);
				incomeData.push(0);
				countData.push(0);
			}
		});
	}
};

function numberFormatter(priceList){
	var formatPriceList = [];
	$.each(priceList, function(index, price){
		formatPriceList.push(new Intl.NumberFormat('ko-KR').format(price));
		console.log(formatPriceList[index]);
	});
	return formatPriceList;
}

var weekBackColor = [
	'rgba(255, 99, 132, 0.7)', 
	'rgba(54, 162, 235, 0.7)',
	'rgba(255, 206, 86, 0.7)',
	'rgba(75, 192, 192, 0.7)',
	'rgba(153, 102, 255, 0.7)',
	'rgba(255, 159, 64, 0.7)'
];

var weekBorderColor = [
	'rgba(255, 99, 132, 1)', 
	'rgba(54, 162, 235, 1)',
	'rgba(255, 206, 86, 1)',
	'rgba(75, 192, 192, 1)',
	'rgba(153, 102, 255, 1)',
	'rgba(255, 159, 64, 1)'
];

var topBackColor = [
	'rgba(255, 0, 0, 0.7)',
	'rgba(255, 86, 0, 0.7)',
	'rgba(255, 190, 0, 0.7)',
];

var topBorderColor = [
	'rgba(255, 0, 0, 1)',
	'rgba(255, 86, 0, 1)',
	'rgba(255, 190, 0, 1)',
];

var top5UserBackColor = [
	'rgba(0, 185, 0, 0.7)',
	'rgba(176, 190, 0, 0.7)',
	'rgba(255, 190, 0, 0.7)'
];

var top5UserBorderColor = [
	'rgba(0, 185, 0, 1)',
	'rgba(176, 190, 0, 1)',
	'rgba(255, 190, 0, 1)'
];

var categoryBackColor = [
	'rgba(0, 0, 255, 0.8)',
	'rgba(0, 154, 255, 0.7)',
	'rgba(136, 214, 255, 0.7)'
];

var categoryBorderColor = [
	'rgba(0, 0, 255, 1)',
	'rgba(0, 154, 255, 1)',
	'rgba(136, 214, 255, 1)'
];

function randerBar(context, labels, label, data, backColor, borderColor) {
	new Chart($(`#${context}`), {
		type: 'bar',
		data: {
			labels: labels,
			datasets: [{
				label: label,
				fill: false,
				data: data,
				backgroundColor: backColor,
				borderColor: borderColor,
				borderWidth: 1
			}]
		},
		options: {
			responsive: true,
			scales: {
				yAxes: [{
					ticks: {
						beginAtZero: true
					}
				}]
			},
			legend: {
				display: false
			}
		}
	});
};

function renderLine(context, labels, label, data, color) {
	new Chart($(`#${context}`), {
		type: 'line',
		data: {
			labels: labels,
			datasets: [{
				label: label,
				data: data,
				fill: false,
				borderWidth: 1,
				lineTension: 0,
				backgroundColor: color,
				borderColor: color
			}],
		},
		options: {
			responsive: true,
			scales: {
				xAxes: [{
					ticks: {
						maxTicksLimit: 7,
						fontSize: 13
					}
				}],
				yAxes: [{
					ticks: {
						min: 0,
						fontSize: 13
					}
				}],
			},
			legend: {
				display: false
			}
		}
	});
}

salesWeek.weekData();
salesItem.weekData();
top5User.userData();
category.categoryData();

var today = new Date();
var date = new Date();
var selectedCell;
var realMonth = date.getMonth()+1;
var realToDay = date.getDate();
var selectedMonth = null;
var selectedDate = null;
function buildCalendar(){
	var row = null
	var cnt = 0;
	function exchangeToPossibleDay(cnt){
		result = cnt % 7;
		result -= 1;
		if (result == -1) {
			result = 6;
		}
		return result; 
	}

	var calendarTable = document.getElementById("calendar");
	var calendarTableTitle = document.getElementById("calendarTitle");
	calendarTableTitle.innerHTML = today.getFullYear()+"년"+(today.getMonth()+1)+"월";
  
	var firstDate = new Date(today.getFullYear(), today.getMonth(), 1);
	var lastDate = new Date(today.getFullYear(), today.getMonth()+1, 0);
	while(calendarTable.rows.length > 2){
		calendarTable.deleteRow(calendarTable.rows.length -1);
	}
	row = calendarTable.insertRow();
	for(i = 0; i < firstDate.getDay(); i++){
		cell = row.insertCell();
		cnt += 1;
	}

	row1 = calendarTable.insertRow();

	for(i = 1; i <= lastDate.getDate(); i++){
		noCount = 0;
		etp = exchangeToPossibleDay(cnt)*1;
		if (nowMonth == realMonth && i <= realToDay) {
			noCount +=1;
		} else if (nowMonth > realMonth && i > realToDay) {
			noCount +=1;
		} else if (possibleDay[etp] == 0){
			noCount +=1;
		}
		cell = row.insertCell();
  		cnt += 1;
		cell.setAttribute('id', i);
		cell.innerHTML = i;
		cell.align = "center";
		nowMonth = today.getMonth()+1;
		monthEquals = thisMonth(nowMonth, realMonth);
		cell.onclick = function(){
			clickedYear = today.getFullYear();
			clickedMonth = ( 1 + today.getMonth() );
    		clickedDate = this.getAttribute('id');

    		clickedDate = clickedDate >= 10 ? clickedDate : '0' + clickedDate;
    		clickedMonth = clickedMonth >= 10 ? clickedMonth : '0' + clickedMonth;
    		clickedYMD = clickedYear + "-" + clickedMonth + "-" + clickedDate;

    		opener.document.getElementById("date").value = clickedYMD;
    		self.close();
		}

		if (cnt % 7 == 1) {
			cell.innerHTML = "<font color=#F79DC2>" + i + "</font>";
		}
	
		if (cnt % 7 == 0){
			cell.innerHTML = "<font color=skyblue>" + i + "</font>";
			row = calendar.insertRow();
		}
		
	}

	if(cnt % 7 != 0){
		for(i = 0; i < 7 - (cnt % 7); i++){
			cell = row.insertCell();
		}
	}
	
	function thisMonth(todayMonth, dateMonth){
		if(todayMonth*1 == dateMonth*1){
			return 0;
		}
		return 1;
	}

}

function prevCalendar(){
	if (today.getMonth() < realMonth){
		alert("예약은 금일기준 다음날부터 30일 이후까지만 가능합니다.");	
		return false;
	}
	today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());
	buildCalendar();
}

function nextCalendar(){
	if(today.getMonth()+1 == (realMonth + 1)){
		alert("예약은 금일기준 다음날부터 30일 이후까지만 가능합니다.");
		return false;
	}
	today = new Date(today.getFullYear(), today.getMonth()+1, today.getDate());
	buildCalendar();
}
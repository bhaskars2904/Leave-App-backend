function validate(startDate, endDate, item){
		    	if(!startDate){
		    		//display start date error
		    		// this.$.endDate.invalid = true;
		    		return "101";
		    	}
		    	//startDate < today
		    	if(!currentDateValidator(startDate)){

		    		//display error in start date
		    		// this.$.startDate.invalid = true;
		    		return "101";
		    	}
		    	// endDate && endDate<startDate
		    	if(endDate && !differenceOfDateValidator(startDate, endDate)){
		    		//endDate smaller error
		    		// this.$.endDate.invalid = true;
		    		return "201";
		    	}
		    	if(item.base.length<=0){
		    		return "301";
		    	}

		    	return "200";
		    }

function currentDateValidator(date){
		    	var currDate = new Date();
		    	var date = new Date(date);
		    	var timeDiff = date.getTime()-currDate.getTime();
		    	var absoluteTimeDiff = Math.abs(timeDiff);
		    	var DaysDiff = Math.floor(absoluteTimeDiff/(1000*3600*24));
		    	if(DaysDiff === 0 || timeDiff>0){return true;}
		    	else{return false;}
		    }

function differenceOfDateValidator(firstDate, lastDate){
		    	var date1 = new Date(firstDate);
		    	var date2 = new Date(lastDate);
		    	var timeDiff = date2.getTime()-date1.getTime();
		    	if(timeDiff>=0){return true;}
		    	else{return false;}
		    }

function countDays(firstDate, lastDate){
				firstDate = new Date(firstDate);
		    	lastDate = new Date(lastDate);
		    	let timeDiff = firstDate.getTime()-lastDate.getTime();
		    	let absoluteTimeDiff = Math.abs(timeDiff);
		    	let DaysDiff = Math.floor(absoluteTimeDiff/(1000*3600*24));
		    	DaysDiff = DaysDiff+1;
		    	console.log("num weekends = "+countWeekeends(firstDate,lastDate));
		    	var weekends = countWeekeends(firstDate,lastDate);
		    	if(!isNaN(weekends)){
		    	var days = DaysDiff - weekends;
		    	console.log("days = "+days+", DaysDiff = "+DaysDiff+", weekends = "+weekends);}
//		    	console.log("days = "+days);
		    	return DaysDiff;
			}

function countWeekeends(datebegin, dateend){
    var count = 0;
    let firstDate = new Date(datebegin);
    let lastDate = new Date(dateend);
    while(isWeekend(firstDate)){
        count++;
        firstDate.setDate(firstDate.getDate() + 1);
    }
    while(isWeekend(lastDate)){
        count++;
        lastDate.setDate(lastDate.getDate() - 1);
    }
    let timeDiff = firstDate.getTime()-lastDate.getTime();
    let absoluteTimeDiff = Math.abs(timeDiff);
    let DaysDiff = Math.floor(absoluteTimeDiff/(1000*3600*24));
    count = count + Math.floor(DaysDiff/7)*2;
    return count;
}

function isWeekend(date){
    if(date.getDay()==6 || date.getDay()==0){
        return true;
    }else{
        return false;
    }
}
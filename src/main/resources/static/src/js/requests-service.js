let RequestService = (function(){



    function getRequestsForTable(data){
        let requests = [];
        Object.keys(data).forEach(function(key){
            let item = data[key];
//            item.startDate = item.startDate.slice(0,10);
//            item.endDate = item.endDate.slice(0,10);
            let request = {
                leaveId : item.leaveId,
                startDate : item.startDate,
                endDate : item.endDate,
                description : item.descr,
                employeeid : item.empId,
                employeename : item.fname,
                status : item.status,
                leaveleft : item.leaveLeft,
                leavedays : item.numLeaveDays
            }
            requests = requests.concat(request);
        })
        return requests;
    }

    return {
        getRequestsForTable : getRequestsForTable
    };
})()
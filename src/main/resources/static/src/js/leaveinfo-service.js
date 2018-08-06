let LeaveInfoService = (function(){

//    function getLeaveInfoService(data){
//        return data.approverList;
//    }

    function getLeaveInfoService(data){
        let approverInfos = [];
        let approverInfo = data.approverList.map(function(item){
            return {
                Approver : item.fname+' '+item.lname,
                empId : item.empId,
                Mail : item.mail
            };
        })
        approverInfos = approverInfos.concat(approverInfo);
        return approverInfos;
    }

    return {
        getLeaveInfoService : getLeaveInfoService
    };
})()
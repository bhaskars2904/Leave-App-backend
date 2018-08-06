let LeaveService = (function () {

    function getLeavesForTable(data) {
        let leaves = [];

        data.forEach(function(item) {
            let leave = item.approverDetailList.map(function (subitem) {
                return {
                    leaveId: item.leaveId,
                    startDate: item.startDate,
                    endDate: item.endDate,
                    description: item.descr,
                    Approver: subitem.employeeDetail.fname + ' ' + subitem.employeeDetail.lname,
                    ApproverMail: subitem.employeeDetail.mail,
                    Status: subitem.status
                };
            });

            leaves = leaves.concat(leave);
        });

        return leaves;
    }

    

    return {
        getLeavesForTable: getLeavesForTable
    };
})()
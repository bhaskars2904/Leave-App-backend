function getApproversArray(selected){
    var approvers = []
    selected.forEach(function(item){
        approvers.push(item.empId);
    })
    return approvers;
}
package com.bhaskar.dao;

import com.bhaskar.model.ApproverLeaveDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApproverLeaveDao{

    List<ApproverLeaveDetail> getApproverLeaveDetails(String uname);
}

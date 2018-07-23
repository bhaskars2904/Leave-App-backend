package com.bhaskar.dao.impl;

import com.bhaskar.dao.MixedDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Repository
public class MixedDaoImpl implements MixedDao {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getEmployeeDetails(String uname) {
        if(uname!=null){
            Query nativeQuery = entityManager.createNativeQuery("select e.fname, e.lname, e.mail from employee e where e.uname = ?1");//"+"'"+uname+"'");
            nativeQuery.setParameter("1", uname);
            List<Object> object = nativeQuery.getResultList();
            return object;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getLeaves(String uname) {
        if(uname!=null){
            String str = "select alev.leave_id, alev.start_date, alev.end_date, alev.descr, alev.approver_id, alev.status,a.fname\n" +
                    "from\n" +
                    "\t(select tlev.leave_id, tlev.start_date, tlev.end_date, tlev.descr, tlev.emp_id, tlev.num_leave_days, ls.status, ls.approver_id\n" +
                    "\tfrom\n" +
                    "\t\t(select l.leave_id, l.start_date, l.end_date, l.descr, l.emp_id, l.num_leave_days \n" +
                    "\t\t from leave l \n" +
                    "\t\t where l.emp_id in\n" +
                    "\t\t (select emp_id from employee where uname=?1)\n" +
                    "\t\t ) tlev\n" +
                    "\t left outer join leave_status ls\n" +
                    "\t on ls.leave_id = tlev.leave_id) alev\n" +
                    "left outer join employee a\n" +
                    "on alev.approver_id = a.emp_id;";
            Query nativeQuery = entityManager.createNativeQuery(str);
            nativeQuery.setParameter("1", uname);
            List<Object> object = nativeQuery.getResultList();
            return object;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getRequests(String uname) {
        if(uname!=null){
            String str = "select tlb.start_date,tlb.leave_id, tlb.end_date, tlb.descr, tlb.num_leave_days, tlb.emp_id, tlb.status, tlb.leave_left, e.fname\n" +
                    "from\n" +
                    "\t(select tlsa.leave_id, tlsa.start_date, tlsa.end_date, tlsa.descr, tlsa.num_leave_days, tlsa.emp_id, tlsa.status, lb.leave_left\n" +
                    "\tfrom \n" +
                    "\t\t(select l.start_date, l.end_date, l.descr, l.num_leave_days, tls.emp_id,tls.status, l.leave_id \n" +
                    "\t\tfrom\n" +
                    "\t\t\t(select ls.leave_status_id, ls.leave_id, ls.emp_id, ls.status\n" +
                    "\t\t\tfrom leave_status ls\n" +
                    "\t\t\twhere ls.approver_id in \n" +
                    "\t\t\t(select emp_id from employee where uname=?1)) tls\n" +
                    "\t\tleft outer join leave l\n" +
                    "\t\ton l.leave_id = tls.leave_id) tlsa\n" +
                    "\tleft outer join leave_balance lb\n" +
                    "\ton tlsa.emp_id=lb.emp_id)tlb\n" +
                    "left outer join employee e\n" +
                    "on tlb.emp_id = e.emp_id;";
            Query nativeQuery = entityManager.createNativeQuery(str);
            nativeQuery.setParameter("1", uname);
            List<Object> object = nativeQuery.getResultList();
            return object;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getLeaveInfo(String uname) {
        if(uname!=null){
            String str = "select tinfo.man_id, tinfo.leave_left, e.fname, e.lname\n" +
                    "from\n" +
                    "\t(select appr.man_id, appr.emp_id, lb.leave_left\n" +
                    "\tfrom \n" +
                    "\t\t(select em.man_id, em.emp_id \n" +
                    "\t\tfrom emp_manager em\n" +
                    "\t\twhere em.emp_id in (select emp_id from employee where uname=?1)) appr\n" +
                    "\tleft outer join leave_balance lb\n" +
                    "\ton appr.emp_id = lb.emp_id) tinfo\n" +
                    "left outer join employee e\n" +
                    "on tinfo.man_id = e.emp_id;";
            Query nativeQuery = entityManager.createNativeQuery(str);
            nativeQuery.setParameter("1", uname);
            List<Object> object = nativeQuery.getResultList();
            return object;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int getEmpId(String uname) {
        if(uname!=null){
            String str = "select e.emp_id from employee e where e.uname=?1";
            Query nativeQuery = entityManager.createNativeQuery(str);
            nativeQuery.setParameter("1", uname);
            List<Object> object = nativeQuery.getResultList();
            System.out.println("object is "+object.get(0));
            return (int)object.get(0);
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int countLeaveTable(){
        String str = "select count (*) from leave";
        Query nativeQuery = entityManager.createNativeQuery(str);
        List<Object> object = nativeQuery.getResultList();
        Integer index = ((BigInteger)object.get(0)).intValue();
        return index;
    }

    @Override
    public int countLeaveStatusTable() {
        String str = "select count (*) from leave_status";
        Query nativeQuery = entityManager.createNativeQuery(str);
        List<Object> object = nativeQuery.getResultList();
        Integer index = ((BigInteger)object.get(0)).intValue();
        return index;
    }

    @Override
    @Transactional
    public void changeStatus(int approverId, int leaveId) {
        System.out.println("change status executed appid = "+approverId+", leaveid = "+leaveId);
        String str = "update leave_status set status=\'confirmed\' where approver_id = "+approverId+" and leave_id = "+leaveId;
        int nativeQuery = entityManager.createNativeQuery(str).executeUpdate();
    }


}

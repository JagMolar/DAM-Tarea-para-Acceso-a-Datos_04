package hibernateoracle;
// Generated 12-ene-2022 11:32:24 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * Emp generated by hbm2java
 */
public class Emp  implements java.io.Serializable {


     private short empno;
     private Dept dept;
     private String ename;
     private String job;
     private Short mgr;
     private Date hiredate;
     private BigDecimal sal;
     private BigDecimal comm;

    public Emp() {
    }

	
    public Emp(short empno, Dept dept) {
        this.empno = empno;
        this.dept = dept;
    }
    public Emp(short empno, Dept dept, String ename, String job, Short mgr, Date hiredate, BigDecimal sal, BigDecimal comm) {
       this.empno = empno;
       this.dept = dept;
       this.ename = ename;
       this.job = job;
       this.mgr = mgr;
       this.hiredate = hiredate;
       this.sal = sal;
       this.comm = comm;
    }
   
    public short getEmpno() {
        return this.empno;
    }
    
    public void setEmpno(short empno) {
        this.empno = empno;
    }
    public Dept getDept() {
        return this.dept;
    }
    
    public void setDept(Dept dept) {
        this.dept = dept;
    }
    public String getEname() {
        return this.ename;
    }
    
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getJob() {
        return this.job;
    }
    
    public void setJob(String job) {
        this.job = job;
    }
    public Short getMgr() {
        return this.mgr;
    }
    
    public void setMgr(Short mgr) {
        this.mgr = mgr;
    }
    public Date getHiredate() {
        return this.hiredate;
    }
    
    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }
    public BigDecimal getSal() {
        return this.sal;
    }
    
    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }
    public BigDecimal getComm() {
        return this.comm;
    }
    
    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }




}



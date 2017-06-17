package com.zjht.inventory.stock.entity;

import java.util.Date;

/**
 * Created by longshuzhen on 2016/10/27.
 */
public class Employee {
    /**
      * 全参构造
      *
      * @param id
      * @param code               员工ID
      * @param name               员工名称
      * @param position           员工职位
      * @param companyCode       所属公司
      * @param supperCode        上级名称
      * @param departmentCode    所属部门
      * @param positionLevel     职位等级
      * @param mail              邮箱
      * @param mobile            手机号
      * @param tel               座机
      * @param qq                QQ号
      * @param sex               性别
      * @param identification    身份证号
      * @param regDate           入职时间
      * @param createBy          创建人
      * @param updateBy          更新人
      * @param createTime        创建时间
      * @param updateTime        更新时间
      * @param status            状态：0-删除；1-启用
      * @param ts01              数据库同步时间戳
      * @param turnPositiveDate  转正日期
      * @param employeeStatus    员工在职情况：0-试用；1-正式；2-离职；
      * @param certificateType   证件类型
      * @param reportTo          汇报对象
      * @param employmentType    用工性质
      * @param probationMonth    试用期时长
      * @param attendanceAward   考勤奖
      * @param positionType      岗位性质 1-营运岗 0-非营运岗 2-业务岗 3-技术岗
      * @param jobConnector      工作交接人，取值本表CODE列值
     ***/

    private Number id;
    private String code;
    private String name;
    private String position;
    private String companyCode;
    private String supperCode;
    private String departmentCode;
    private String positionLevel;
    private String mail;
    private String mobile;
    private String tel;
    private String qq;
    private String sex;
    private String identification;
    private Date regDate;
    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;
    private String status;
    private String ts01;
    private Date turnPositiveDate;
    private String employeeStatus;
    private String certificateType;
    private String reportTo;
    private String employmentType;
    private String probationMonth;
    private Number attendanceAward;
    private String positionType;
    private String jobConnector;

    public Employee() {
    }

    public Employee(Number id, String code, String name, String position, String companyCode, String supperCode, String departmentCode, String positionLevel, String mail, String mobile, String tel, String qq, String sex, String identification, Date regDate, String createBy, String updateBy, Date createTime, Date updateTime, String status, String ts01, Date turnPositiveDate, String employeeStatus, String certificateType, String reportTo, String employmentType, String probationMonth, Number attendanceAward, String positionType, String jobConnector) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.position = position;
        this.companyCode = companyCode;
        this.supperCode = supperCode;
        this.departmentCode = departmentCode;
        this.positionLevel = positionLevel;
        this.mail = mail;
        this.mobile = mobile;
        this.tel = tel;
        this.qq = qq;
        this.sex = sex;
        this.identification = identification;
        this.regDate = regDate;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.ts01 = ts01;
        this.turnPositiveDate = turnPositiveDate;
        this.employeeStatus = employeeStatus;
        this.certificateType = certificateType;
        this.reportTo = reportTo;
        this.employmentType = employmentType;
        this.probationMonth = probationMonth;
        this.attendanceAward = attendanceAward;
        this.positionType = positionType;
        this.jobConnector = jobConnector;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSupperCode() {
        return supperCode;
    }

    public void setSupperCode(String supperCode) {
        this.supperCode = supperCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(String positionLevel) {
        this.positionLevel = positionLevel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTs01() {
        return ts01;
    }

    public void setTs01(String ts01) {
        this.ts01 = ts01;
    }

    public Date getTurnPositiveDate() {
        return turnPositiveDate;
    }

    public void setTurnPositiveDate(Date turnPositiveDate) {
        this.turnPositiveDate = turnPositiveDate;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getReportTo() {
        return reportTo;
    }

    public void setReportTo(String reportTo) {
        this.reportTo = reportTo;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getProbationMonth() {
        return probationMonth;
    }

    public void setProbationMonth(String probationMonth) {
        this.probationMonth = probationMonth;
    }

    public Number getAttendanceAward() {
        return attendanceAward;
    }

    public void setAttendanceAward(Number attendanceAward) {
        this.attendanceAward = attendanceAward;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getJobConnector() {
        return jobConnector;
    }

    public void setJobConnector(String jobConnector) {
        this.jobConnector = jobConnector;
    }
}

package com.neuedu.springboot.model.bean;

import java.util.Date;

/**
 * 住院表实体类
 * @Author:mwy
 * @Date 2023/2/16 17:14
 */
public class Hospitalization {

    //主键
    private Integer id;
    //病人账号
    private String userAccount;
    //病人姓名
    private String userName;
    //诊断医生账号
    private String docAccount;
    //诊断医生姓名
    private String docName;
    //诊断医生类型
    private String docType;
    //疾病
    private String disease;
    //入住时间
    private Date inTime;
    //入住时长
    private Integer continuedTime;
    //状态
    private String status;
    //缴费金额
    private Integer payMoney;
    //1同意住院0不同意住院
    private Integer isIn;
    //病历表Id
    private Integer registerId;
    //挂号科室类型
    private String deptType;
    //挂号科室分类
    private String deptCategoryName;
    //入住病房名称
    private String wardName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDocAccount() {
        return docAccount;
    }

    public void setDocAccount(String docAccount) {
        this.docAccount = docAccount;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Integer getContinuedTime() {
        return continuedTime;
    }

    public void setContinuedTime(Integer continuedTime) {
        this.continuedTime = continuedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Integer payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getIsIn() {
        return isIn;
    }

    public void setIsIn(Integer isIn) {
        this.isIn = isIn;
    }

    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getDeptCategoryName() {
        return deptCategoryName;
    }

    public void setDeptCategoryName(String deptCategoryName) {
        this.deptCategoryName = deptCategoryName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
}

package com.neuedu.springboot.model.bean;

import java.lang.reflect.Array;

/**
 * @Author:mwy
 * @Date 2023/2/4 18:51
 */
public class Register {
    private Integer id;
    private String userAccount;
    private String userName;
    private String docAccount;
    private String docName;
    private String registerDepartment;
    private String docType;
    private String userDescription;
    private String docDiagnostic;
    private String drug;
    private String status;
    private String deptName;
    private String deptCategoryName;
    private String disease;
    private String isHosAdvice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAcount(String userAccount) {
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

    public String getRegisterDepartment() {
        return registerDepartment;
    }

    public void setRegisterDepartment(String registerDepartment) {
        this.registerDepartment = registerDepartment;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getDocDiagnostic() {
        return docDiagnostic;
    }

    public void setDocDiagnostic(String docDiagnostic) {
        this.docDiagnostic = docDiagnostic;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCategoryName() {
        return deptCategoryName;
    }

    public void setDeptCategoryName(String deptCategoryName) {
        this.deptCategoryName = deptCategoryName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getIsHosAdvice() {
        return isHosAdvice;
    }

    public void setIsHosAdvice(String isHosAdvice) {
        this.isHosAdvice = isHosAdvice;
    }
}

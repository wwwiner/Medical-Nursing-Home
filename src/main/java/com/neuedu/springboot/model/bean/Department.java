package com.neuedu.springboot.model.bean;

import java.util.Date;

/**
 * @Author:mwy
 * @Date 2023/2/1 23:31
 */
public class Department {
    private Integer id;
    private String deptCode;
    private String deptName;
    private String deptCategoryName;
    private String deptType;
    private String createName;
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
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

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

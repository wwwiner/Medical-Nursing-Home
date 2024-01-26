package com.neuedu.springboot.model.bean;

import java.util.List;

/**
 * @Author:mwy
 * @Date 2023/2/2 11:31
 */
public class DepartmentPageDto {
    //总行数
    private int totalRow;
    //总页数
    private int totalPageNum;
    //上一页
    private int preNum;
    //下一页
    private int nextNum;
    //当前页
    private int pageNum;
    //每页显示最大行数
    private int maxPageNum;
    //开始查询记录数
    private int beginNum;
    //业务数据
    private List list;

    //科室名称
    private String deptName;
    //科室分类
    private String deptCategoryName;
    //科室类型
    private String deptType;

    //用户姓名
    private String realName;
    //用户分类
    private String userType;

    //药品姓名
    private String drugName;
    //药品分类
    private String drugType;

    //医生姓名
    private String docName;
    //状态
    private String status;

    //用户账号
    private String userAccount;
    //用户账号
    private String userName;

    //病房类型
    private String wardType;

    //资讯标题
    private String bullTitle;
    //资讯类型
    private String bullType;




    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getPreNum() {
        return preNum;
    }

    public void setPreNum(int preNum) {
        this.preNum = preNum;
    }

    public int getNextNum() {
        return nextNum;
    }

    public void setNextNum(int nextNum) {
        this.nextNum = nextNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getMaxPageNum() {
        return maxPageNum;
    }

    public void setMaxPageNum(int maxPageNum) {
        this.maxPageNum = maxPageNum;
    }

    public int getBeginNum() {
        return beginNum;
    }

    public void setBeginNum(int beginNum) {
        this.beginNum = beginNum;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getWardType() {
        return wardType;
    }

    public void setWardType(String wardType) {
        this.wardType = wardType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBullTitle() {
        return bullTitle;
    }

    public void setBullTitle(String bullTitle) {this.bullTitle = bullTitle;}

    public String getBullType() {return bullType;}

    public void setBullType(String bullType) {this.bullType =bullType;}


}

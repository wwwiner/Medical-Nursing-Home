package com.neuedu.springboot.model.bean;

import java.util.Date;

/**
 * @Author:mwy
 * @Date 2023/2/15 9:10
 */
public class Salary {
    //主键
    private Integer id;
    //用户账号
    private String userAccount;
    //用户姓名
    private String userName;
    //转账人账号
    private String transferAccount;
    //转账人姓名
    private String transferName;
    //转账时间
    private Date transferTime;
    //转账金额
    private int transferMoney;
    //总转账金额
    private int allTransferMoney;
    //转账类型
    private String transferType;


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

    public String getTransferAccount() {
        return transferAccount;
    }

    public void setTransferAccount(String transferAccount) {
        this.transferAccount = transferAccount;
    }

    public String getTransferName() {
        return transferName;
    }

    public void setTransferName(String transferName) {
        this.transferName = transferName;
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    public int getTransferMoney() {
        return transferMoney;
    }

    public void setTransferMoney(int transferMoney) {
        this.transferMoney = transferMoney;
    }

    public int getAllTransferMoney() {
        return allTransferMoney;
    }

    public void setAllTransferMoney(int allTransferMoney) {
        this.allTransferMoney = allTransferMoney;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }
}

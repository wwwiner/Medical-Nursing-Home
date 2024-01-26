package com.neuedu.springboot.model.bean;

import java.util.Date;

/**
 * 病房表实体类
 * @Author:mwy
 * @Date 2023/2/17 17:27
 */
public class Ward {
    //主键
    private int id;
    //病房编号
    private String wardCode;
    //病房名称
    private String wardName;
    //病房类型
    private String wardType;
    //病房地址
    private String wardAddress;
    //病人账号
    private String userAccount;
    //病人姓名
    private String userName;
    //是否为空
    private String isWardNull;
    //疾病名称
    private String disease;
    //入住时间
    private Date inTime;
    //入住时长
    private String continuedTime;
    //诊断医生姓名
    private String docName;
    //普通病房剩余比
    private float PT;
    //高级病房剩余比
    private float GJ;
    //临终剩余比
    private float LZ;
    //重症监护室剩余比
    private float ICU;
    //普通病房剩余数
    private Integer PTCount;
    //高级病房剩余数
    private Integer GJCount;
    //临终剩余数
    private Integer LZCount;
    //重症监护室剩余数
    private Integer ICUCount;
    //住院缴费金额
    private Integer wardPrice;
    //住院登记表id
    private Integer hosId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getWardType() {
        return wardType;
    }

    public void setWardType(String wardType) {
        this.wardType = wardType;
    }

    public String getWardAddress() {
        return wardAddress;
    }

    public void setWardAddress(String wardAddress) {
        this.wardAddress = wardAddress;
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

    public String getIsWardNull() {
        return isWardNull;
    }

    public void setIsWardNull(String isWardNull) {
        this.isWardNull = isWardNull;
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

    public String getContinuedTime() {
        return continuedTime;
    }

    public void setContinuedTime(String continuedTime) {
        this.continuedTime = continuedTime;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public float getPT() {
        return PT;
    }

    public void setPT(float PT) {
        this.PT = PT;
    }

    public float getGJ() {
        return GJ;
    }

    public void setGJ(float GJ) {
        this.GJ = GJ;
    }

    public float getLZ() {
        return LZ;
    }

    public void setLZ(float LZ) {
        this.LZ = LZ;
    }

    public float getICU() {
        return ICU;
    }

    public void setICU(float ICU) {
        this.ICU = ICU;
    }

    public Integer getWardPrice() {
        return wardPrice;
    }

    public void setWardPrice(Integer wardPrice) {
        this.wardPrice = wardPrice;
    }

    public Integer getHosId() {
        return hosId;
    }

    public void setHosId(Integer hosId) {
        this.hosId = hosId;
    }

    public Integer getPTCount() {
        return PTCount;
    }

    public void setPTCount(Integer PTCount) {
        this.PTCount = PTCount;
    }

    public Integer getGJCount() {
        return GJCount;
    }

    public void setGJCount(Integer GJCount) {
        this.GJCount = GJCount;
    }

    public Integer getLZCount() {
        return LZCount;
    }

    public void setLZCount(Integer LZCount) {
        this.LZCount = LZCount;
    }

    public Integer getICUCount() {
        return ICUCount;
    }

    public void setICUCount(Integer ICUCount) {
        this.ICUCount = ICUCount;
    }
}

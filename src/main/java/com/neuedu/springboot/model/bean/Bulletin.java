package com.neuedu.springboot.model.bean;

import java.util.Date;

/**
 * @Author:mwy
 * @Date 2023/2/8 23:31
 */
public class Bulletin {
    private Integer id;
    private String bullTitle;
    private String userName;
    private String bullType;
    private String content;
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBullTitle() {
        return bullTitle;
    }

    public void setBullTitle(String bullTitle) {
        this.bullTitle = bullTitle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getBullType() {
        return bullType;
    }

    public void setBullType(String bullType) {
        this.bullType = bullType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

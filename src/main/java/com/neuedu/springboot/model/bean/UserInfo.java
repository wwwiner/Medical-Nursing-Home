package com.neuedu.springboot.model.bean;

public class UserInfo {
	private Integer id;
	private String userName;
	private String password;
	private String oldPassword;
	private String realName;
	private String userType;
	private String privilegeLevel;
	private Integer money;
	private String Avatarlink;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPrivilegeLevel() {
		return privilegeLevel;
	}

	public void setPrivilegeLevel(String privilegeLevel) {
		this.privilegeLevel = privilegeLevel;
	}

	public String getAvatarlink() {return Avatarlink;}

	public void setAvatarlink(String Avatarlink) {
		this.Avatarlink = Avatarlink;
	}

	public Integer getMoney() {return money;}

	public void setMoney(Integer money) {this.money = money;}
}

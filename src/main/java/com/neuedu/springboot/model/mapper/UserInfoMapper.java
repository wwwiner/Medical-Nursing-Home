package com.neuedu.springboot.model.mapper;

import com.neuedu.springboot.model.bean.Department;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import org.apache.ibatis.annotations.Mapper;

import com.neuedu.springboot.model.bean.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface UserInfoMapper {
	public UserInfo login(UserInfo user);

	public void updatePassword(UserInfo user);

	public void register(UserInfo user);

	public UserInfo forgetPassword(UserInfo user);

	public List<UserInfo> selectuserType();

	public int selectCountFromUserList();

	public List<UserInfo> selectUserList(DepartmentPageDto departmentPageDto);

	public void updateUser(UserInfo user);

	public void deleteUserById(UserInfo user);

	public void removeUserBatch(List<UserInfo> userInfoList);

	public List<UserInfo> selectDocByDeptType(Department department);

	public void kouQian(UserInfo user);

	public void jiaQian(UserInfo user);

	public UserInfo selectMoneyByUserName(UserInfo user);

	public int selectBalanceByUserName(UserInfo user);

	public String selectAvatarlinkByUserAccount(UserInfo userInfo);


}

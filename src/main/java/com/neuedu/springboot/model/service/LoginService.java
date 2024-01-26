package com.neuedu.springboot.model.service;

import javax.servlet.http.HttpSession;

import com.neuedu.springboot.model.bean.Department;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.springboot.model.bean.UserInfo;
import com.neuedu.springboot.model.mapper.UserInfoMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoginService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private LoginService loginService;
	
	public UserInfo login(UserInfo userInfo) {
		UserInfo loginUser = userInfoMapper.login(userInfo);
		return loginUser;
	}

	public void updatePassword(UserInfo userInfo) {
		userInfoMapper.updatePassword(userInfo);
	}

	public UserInfo register(UserInfo userInfo) {
		//验证注册账号是否存在
		UserInfo UserInfo = userInfoMapper.selectMoneyByUserName(userInfo);
		if(UserInfo != null){
			return null;
		}else {
			userInfoMapper.register(userInfo);
			return loginService.login(userInfo);
		}
	}

	public UserInfo forgetPassword(UserInfo userInfo) {
		UserInfo forgetPasswordUser = userInfoMapper.forgetPassword(userInfo);
		return forgetPasswordUser;
	}

	public List<UserInfo> selectuserType() {
		List<UserInfo> list = userInfoMapper.selectuserType();
		//遍历去重
		List<UserInfo> resultList = new ArrayList<>();
		String result = "";
		for (UserInfo user : list) {
			String userType = user.getUserType();
			if (userType != null) {
				if (!result.contains(userType)) {
					result = result + userType + ",";
				}
			}
		}

		String[] userType = result.split(",");
		for (int i = 0; i < userType.length; i++) {
			UserInfo userInfo = new UserInfo();
			userInfo.setId(i);
			userInfo.setUserType(userType[i]);
			resultList.add(userInfo);
		}
		return resultList;
	}

	public DepartmentPageDto selectUserList(DepartmentPageDto departmentPageDto) {
		DepartmentPageDto resultPageDto = new DepartmentPageDto();

		int pageCount = userInfoMapper.selectCountFromUserList();
		int totalRow = pageCount; //总行数
		int totalPageNum = 0; //总页数
		int pageNum = departmentPageDto.getPageNum();
		int preNum = departmentPageDto.getPageNum(); // 上一页
		int nextNum = departmentPageDto.getPageNum(); //下一页
		int beginNum = 0; //开始查询记录数
		List<UserInfo> list = null; //业务数据
		String realName = departmentPageDto.getRealName();
		String userType = departmentPageDto.getUserType();

		//如果总行数为0,就直按结束
		if (totalRow == 0) {
			return null;
		}
		//计算总页数
		if (totalRow % 10 == 0) {
			totalPageNum = 1;
		} else {
			totalPageNum = (totalRow / 10) + 1;
		}
		//计算上一页和下一页
		if (preNum > 1) {
			preNum--;
		}
		if (nextNum < totalPageNum) {
			nextNum++;
		}
		//计算开始查询记录数
		beginNum = (pageNum - 1) * 10;

		//查询业务数据

		resultPageDto.setTotalRow(totalRow);
		resultPageDto.setTotalPageNum(totalPageNum);
		resultPageDto.setPageNum(pageNum);
		resultPageDto.setPreNum(preNum);
		resultPageDto.setNextNum(nextNum);
		resultPageDto.setBeginNum(beginNum);
		resultPageDto.setMaxPageNum(10);
		resultPageDto.setRealName(realName);
		resultPageDto.setUserType(userType);
		list = userInfoMapper.selectUserList(resultPageDto);
		resultPageDto.setList(list);
		return resultPageDto;
	}

	public String updateUser(UserInfo userInfo) {
		userInfoMapper.updateUser(userInfo);
		return "SUCCESS";
	}


	public String deleteUserById(UserInfo userInfo) {
		userInfoMapper.deleteUserById(userInfo);
		return "SUCCESS";
	}

	public String removeUserBatch(List<UserInfo> userInfoList) {
		userInfoMapper.removeUserBatch(userInfoList);
		return "SUCCESS";
	}

	public List<UserInfo> selectDocByDeptType(Department department) {
		return userInfoMapper.selectDocByDeptType(department);
	}

	public String selectAvatarlinkByUserAccount(UserInfo userInfo) {
		return userInfoMapper.selectAvatarlinkByUserAccount(userInfo);
	}


}

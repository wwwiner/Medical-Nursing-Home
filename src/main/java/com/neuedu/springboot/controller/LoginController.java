package com.neuedu.springboot.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.springboot.model.bean.Department;
import com.neuedu.springboot.model.bean.DepartmentPageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.neuedu.springboot.model.bean.UserInfo;
import com.neuedu.springboot.model.service.LoginService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/user")
@Api("登录管理")
public class LoginController {
	@Autowired
	private LoginService loginService;


	//登陆验证

	@ApiOperation(value = "登录" , notes="用户登录")
	@RequestMapping("/login")
	public UserInfo login(@RequestBody UserInfo userInfo) {
		return loginService.login(userInfo);
	}

	//修改密码
	@ApiOperation(value = "修改密码" , notes="更新登录密码")
	@RequestMapping("/updatePassword")
	public UserInfo updatePassword(@RequestBody UserInfo userInfo) {
		loginService.updatePassword(userInfo);
		return loginService.login(userInfo);
	}

	//注册
	@ApiOperation(value = "注册" , notes="用户注册")
	@RequestMapping("/register")
	public UserInfo register(@RequestBody UserInfo userInfo) {
		return loginService.register(userInfo);
	}

	//忘记密码
	@ApiOperation(value = "忘记密码" , notes="找回密码")
	@RequestMapping("/forgetPassword")
	public UserInfo forgetPassword(@RequestBody UserInfo userInfo) {
		return loginService.forgetPassword(userInfo);
	}

	//查询用户类型
	@ApiOperation(value = "查询用户" , notes="根据类型查询用户")
	@RequestMapping("/selectuserType")
	public List<UserInfo> selectuserType() {
		return loginService.selectuserType();
	}

	//查询用户列表（分页）
	@ApiOperation(value = "查询用户" , notes="分页查询用户")
	@RequestMapping("/selectUserList")
	public DepartmentPageDto selectUserList(@RequestBody DepartmentPageDto departmentPageDto) {
		return loginService.selectUserList(departmentPageDto);
	}

	//通过Id修改用户信息
	@ApiOperation(value = "修改信息" , notes="根据id修改用户信息")
	@RequestMapping("/updateUserById")
	public String updateUser(@RequestBody UserInfo userInfo) {
		return loginService.updateUser(userInfo);
	}


	//通过Id删除用户信息
	@ApiOperation(value = "删除信息" , notes="根据id删除用户信息")
	@RequestMapping("/deleteUserById")
	public String deleteUserById(@RequestBody UserInfo userInfo) {
		return loginService.deleteUserById(userInfo);
	}

	//通过id删除用户（批量）
	@ApiOperation(value = "删除信息" , notes="批量删除信息")
	@RequestMapping("/removeUserBatch")
	public String removeUserBatch(@RequestBody List<UserInfo> userInfoList) {
		loginService.removeUserBatch(userInfoList);
		return "SUCCESS";
	}

	//通过科室类型查询医生姓名
	@ApiOperation(value = "查询医生" , notes="根据科室查找医生")
	@RequestMapping("/selectDocByDeptType")
	public List<UserInfo> selectDocByDeptType(@RequestBody Department department) {
		return loginService.selectDocByDeptType(department);
	}

	//通过账号查询头像链接
	@ApiOperation(value = "查找头像" , notes="根据账号查询头像")
	@RequestMapping("/selectAvatarlinkByUserAccount")
	public String selectAvatarlinkByUserAccount(@RequestBody UserInfo userInfo) {
		return loginService.selectAvatarlinkByUserAccount(userInfo);
	}

	@Value("${hong.path:DefaultValue}")
	private String basePath;

	@PostMapping("/upload")
	public String upload(MultipartFile file) {

		//原始文件名
		String originalFilename = file.getOriginalFilename();//xxx.png
		//对原始名进行截取"."后面的字符
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));//.png

		//使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
		String fileName = UUID.randomUUID().toString() + suffix;

		//创建一个目录对象
		File dir = new File(basePath);

		//判断当前目录是否存在：目录不存在，需要创建
		if (!dir.exists()) dir.mkdirs();

		try {
			//将临时文件转存到指定位置
			file.transferTo(new File(basePath + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	@GetMapping("/download")
	public void download(String name, HttpServletResponse response){
		try {
			//输入流，通过输入流读取文件内容
			FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

			//输出流，通过输出流将文件写回浏览器
			ServletOutputStream outputStream = response.getOutputStream();

//			response.setContentType("image/png");

			int len = 0;
			byte[] bytes = new byte[1024];
			while ((len = fileInputStream.read(bytes)) != -1){
				outputStream.write(bytes,0,len);
				outputStream.flush();
			}
			outputStream.close();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}

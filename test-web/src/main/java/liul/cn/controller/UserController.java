package liul.cn.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import liul.cn.entity.User;
import liul.cn.service.UserService;
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value="dologin",method=RequestMethod.POST)
	public ResponseEntity<String> doLogin(@RequestBody@RequestParam(value="userName",defaultValue="")String userName,@RequestBody@RequestParam(value="password",defaultValue="")String password){
		if ("".equals(userName)||"".equals(password)) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		String token=userService.doLogin(userName, password);
		return null==token?ResponseEntity.status(HttpStatus.NOT_FOUND).body(null):ResponseEntity.ok(token);
	}
	@RequestMapping(value="regist",method=RequestMethod.GET)
	public String regist() {
		return "regist";
	}
	@RequestMapping(value="doregist",method=RequestMethod.POST)
	public ResponseEntity<User> doRegist	(@Valid User user,BindingResult uErrors){
		if (uErrors.hasErrors()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		User query_user=new User();
		query_user.setUserName(user.getUserName());
		if (null!=userService.queryOne(query_user)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return false==userService.insertUser(user)?ResponseEntity.status(HttpStatus.NOT_FOUND).body(null):ResponseEntity.ok(user);
	}
	@RequestMapping(value="queryallusers",method=RequestMethod.GET)
	public ResponseEntity<List<User>> queryAllUsers() {
	    return ResponseEntity.ok(userService.queryAll());
	}
	
}


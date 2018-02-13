package liul.cn.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import liul.cn.entity.User;
import liul.cn.mapper.UserMapper;
@Service
public class UserService extends BaseService<User>{
    @Autowired
    private UserMapper userMapper;
    
    public boolean insertUser(User user) {
    	user.setId(null);
    	user.setPassword(DigestUtils.md5Hex(user.getPassword()));
    	return this.userMapper.insertSelective(user)==1;
    }
    
    public String doLogin(String username,String password) {
    	User record=new User();
    	record.setUserName(username);
    	User user=this.userMapper.selectOne(record);
    	System.out.println(user);
    	if (null==user) {//用户不存在
			return null;
		}
    	if (!DigestUtils.md5Hex(password).equals(user.getPassword())) {//密码错误
			return null;
		}
    	String token=DigestUtils.md5Hex(username+System.currentTimeMillis());
    	return token;
    } 
}

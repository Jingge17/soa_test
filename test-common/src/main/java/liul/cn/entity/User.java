package liul.cn.entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
@Table(name="soa_user")
public class User extends BasePojo{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
	@Min(value=0,message="min")
	@Max(value=128,message="max")
	@Column(name="age")
private Integer age;
	@Min(value=1,message="min")
	@Max(value=2,message="max")
private Integer sex;
	@Length(max=10,min=1,message="长度应为1~10")
    @Column(name="user_nickname")
private String userNickname;
	@Length(max=10,min=6,message="长度应为6~10")
private String userName;
	@Length(max=20,min=6,message="长度应为6~20")
private String password;
	@Length(max=200,min=1,message="长度应为0~200")
private String userIntroduce;



public Long getId() {
	return id;
}



public void setId(Long id) {
	this.id = id;
}





public Integer getAge() {
	return age;
}



public void setAge(Integer age) {
	this.age = age;
}



public Integer getSex() {
	return sex;
}



public void setSex(Integer sex) {
	this.sex = sex;
}



public String getUserNickname() {
	return userNickname;
}



public void setUserNickname(String userNickname) {
	this.userNickname = userNickname;
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



public String getUserIntroduce() {
	return userIntroduce;
}



public void setUserIntroduce(String userIntroduce) {
	this.userIntroduce = userIntroduce;
}



@Override
public String toString() {
	return "User [id=" + id + ", age=" + age + ", sex=" + sex + ", userNickname=" + userNickname + ", userName="
			+ userName + ", password=" + password + ", userIntroduce=" + userIntroduce + "]";
}

}

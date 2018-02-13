package liul.cn.pojo;

import org.apache.solr.client.solrj.beans.Field;

public class UserFS {
	@Field("userid")
	private Long id;
	@Field("age")
	private Long age;
	@Field("sex")
	private Long sex;
	@Field("userNickname")
	private String userNickname;
	@Field("userName")
	private String userName;
	@Field("password")
	private String password;
	@Field("userIntroduce")
	private String userIntroduce;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public Long getSex() {
		return sex;
	}
	public void setSex(Long sex) {
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
		return "UserFS [id=" + id + ", age=" + age + ", sex=" + sex + ", userNickname=" + userNickname + ", userName="
				+ userName + ", password=" + password + ", userIntroduce=" + userIntroduce + "]";
	}
	
}

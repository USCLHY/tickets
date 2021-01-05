package com.yc.tickets.domain;

import java.io.Serializable;

/**
* 用户的实体类
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月15日 下午6:28:22
*/
@SuppressWarnings("all")
public class Users implements Serializable {
	private Integer uid ;  // 用户编号
	private String uname ; // 用户真实姓名
	private String upassword ; // 用户密码
	private String upic ; // 用户头像
	private String utel ; // 用户电话
	private String uidentify ; // 用户身份证号码
	private String uemail ; // 用户邮箱地址
	private Integer status ; // 用户状态，0代表冻结，1代表正常
	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUpic() {
		return upic;
	}
	public void setUpic(String upic) {
		this.upic = upic;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getUidentify() {
		return uidentify;
	}
	public void setUidentify(String uidentify) {
		this.uidentify = uidentify;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", uname=" + uname + ", upassword=" + upassword + ", upic=" + upic + ", utel="
				+ utel + ", uidentify=" + uidentify + ", uemail=" + uemail + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + status;
		result = prime * result + ((uemail == null) ? 0 : uemail.hashCode());
		result = prime * result + uid;
		result = prime * result + ((uidentify == null) ? 0 : uidentify.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + ((upassword == null) ? 0 : upassword.hashCode());
		result = prime * result + ((upic == null) ? 0 : upic.hashCode());
		result = prime * result + ((utel == null) ? 0 : utel.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (status != other.status)
			return false;
		if (uemail == null) {
			if (other.uemail != null)
				return false;
		} else if (!uemail.equals(other.uemail))
			return false;
		if (uid != other.uid)
			return false;
		if (uidentify == null) {
			if (other.uidentify != null)
				return false;
		} else if (!uidentify.equals(other.uidentify))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (upassword == null) {
			if (other.upassword != null)
				return false;
		} else if (!upassword.equals(other.upassword))
			return false;
		if (upic == null) {
			if (other.upic != null)
				return false;
		} else if (!upic.equals(other.upic))
			return false;
		if (utel == null) {
			if (other.utel != null)
				return false;
		} else if (!utel.equals(other.utel))
			return false;
		return true;
	}
	
	
}

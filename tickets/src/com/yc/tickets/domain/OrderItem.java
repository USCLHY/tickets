package com.yc.tickets.domain;

import java.io.Serializable;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月15日 下午6:54:48
*/
@SuppressWarnings("all")
public class OrderItem implements Serializable{
	private Integer oid ; //订单编号
	private Integer operateid ; // 运营编号
	private Integer uid ; // 用户id
	private String identify ;  //乘车人身份证
	private String buytime ;  // 购买时间
	private String name  ; // 乘车人姓名
	private Double money ; // 支付金额
	private String type ; // 票的类型,学生票，成人票
	private String seattype; // 座位类型，软卧。
	private String seatnumber ; // 座位号
	private Integer status ; // 订单状态
	
	private String tname;//列车名
	private String uname;//用户名
	
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getOperateid() {
		return operateid;
	}
	public void setOperateid(Integer operateid) {
		this.operateid = operateid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getSeatnumber() {
		return seatnumber;
	}
	public void setSeatnumber(String seatnumber) {
		this.seatnumber = seatnumber;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public String getBuytime() {
		return buytime;
	}
	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSeattype() {
		return seattype;
	}
	public void setSeattype(String seattype) {
		this.seattype = seattype;
	} 
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "OrderItem [oid=" + oid + ", operateid=" + operateid + ", uid=" + uid + ", identify=" + identify
				+ ", buytime=" + buytime + ", name=" + name + ", money=" + money + ", type=" + type + ", seattype="
				+ seattype + ", seatnumber=" + seatnumber + ", status=" + status + ", tname=" + tname + ", uname="
				+ uname + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buytime == null) ? 0 : buytime.hashCode());
		result = prime * result + ((identify == null) ? 0 : identify.hashCode());
		result = prime * result + ((money == null) ? 0 : money.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((operateid == null) ? 0 : operateid.hashCode());
		result = prime * result + ((seatnumber == null) ? 0 : seatnumber.hashCode());
		result = prime * result + ((seattype == null) ? 0 : seattype.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tname == null) ? 0 : tname.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
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
		OrderItem other = (OrderItem) obj;
		if (buytime == null) {
			if (other.buytime != null)
				return false;
		} else if (!buytime.equals(other.buytime))
			return false;
		if (identify == null) {
			if (other.identify != null)
				return false;
		} else if (!identify.equals(other.identify))
			return false;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (operateid == null) {
			if (other.operateid != null)
				return false;
		} else if (!operateid.equals(other.operateid))
			return false;
		if (seatnumber == null) {
			if (other.seatnumber != null)
				return false;
		} else if (!seatnumber.equals(other.seatnumber))
			return false;
		if (seattype == null) {
			if (other.seattype != null)
				return false;
		} else if (!seattype.equals(other.seattype))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tname == null) {
			if (other.tname != null)
				return false;
		} else if (!tname.equals(other.tname))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		return true;
	}
	
	
	
}

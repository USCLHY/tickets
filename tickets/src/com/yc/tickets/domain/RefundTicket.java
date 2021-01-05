package com.yc.tickets.domain;

import java.io.Serializable;

/**
* 退票实体类
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月15日 下午6:54:48
*/
@SuppressWarnings("all")
public class RefundTicket implements Serializable{
	private Integer rid ; //退票编号
	private Integer operateid ; // 运营编号
	private Integer oid ; // 订单编号
	
	private Integer uid ; // 用户id
	private String identify ;  //乘车人身份证
	private String refundtime ;  // 退票时间
	private String name  ; // 乘车人姓名
	private Double money ; // 退还金额 
	private Integer status ; // 退票状态,待审核2，通过10，失败0
	
	private String tname ;  // 列车名
	private String uname ;  // 退票人
	
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
	
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
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
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public String getRefundtime() {
		return refundtime;
	}
	public void setRefundtime(String refundtime) {
		this.refundtime = refundtime;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identify == null) ? 0 : identify.hashCode());
		result = prime * result + ((money == null) ? 0 : money.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((operateid == null) ? 0 : operateid.hashCode());
		result = prime * result + ((refundtime == null) ? 0 : refundtime.hashCode());
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tname == null) ? 0 : tname.hashCode());
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
		RefundTicket other = (RefundTicket) obj;
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
		if (refundtime == null) {
			if (other.refundtime != null)
				return false;
		} else if (!refundtime.equals(other.refundtime))
			return false;
		if (rid == null) {
			if (other.rid != null)
				return false;
		} else if (!rid.equals(other.rid))
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
	@Override
	public String toString() {
		return "RefundTicket [rid=" + rid + ", operateid=" + operateid + ", oid=" + oid + ", uid=" + uid + ", identify="
				+ identify + ", refundtime=" + refundtime + ", name=" + name + ", money=" + money + ", status=" + status
				+ ", tname=" + tname + ", uname=" + uname + "]";
	}
	 
	
	
}

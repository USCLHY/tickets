package com.yc.tickets.domain;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月16日 下午6:31:44
*/
public class MyRefund {
	private Integer oid ; //订单编号
	
	private String tname ; // 车次名称
	private String startsite ; // 出发站点名称
	private String endsite ; // 到达站点名称  
	
	private String date ; // 出发日期
	private String starttime ; // 出发时间 
	
	private String type ; // 票的类型,学生票，成人票
	private String seattype; // 座位类型，软卧。
	
	private Double money ; // 支付金额
	private String refundtime ;  // 购买时间
	
	private String status ; // 订单的状态

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getStartsite() {
		return startsite;
	}

	public void setStartsite(String startsite) {
		this.startsite = startsite;
	}

	public String getEndsite() {
		return endsite;
	}

	public void setEndsite(String endsite) {
		this.endsite = endsite;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getRefundtime() {
		return refundtime;
	}

	public void setRefundtime(String refundtime) {
		this.refundtime = refundtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MyRefund [oid=" + oid + ", tname=" + tname + ", startsite=" + startsite + ", endsite=" + endsite
				+ ", date=" + date + ", starttime=" + starttime + ", type=" + type + ", seattype=" + seattype
				+ ", money=" + money + ", refundtime=" + refundtime + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((endsite == null) ? 0 : endsite.hashCode());
		result = prime * result + ((money == null) ? 0 : money.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((refundtime == null) ? 0 : refundtime.hashCode());
		result = prime * result + ((seattype == null) ? 0 : seattype.hashCode());
		result = prime * result + ((startsite == null) ? 0 : startsite.hashCode());
		result = prime * result + ((starttime == null) ? 0 : starttime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tname == null) ? 0 : tname.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		MyRefund other = (MyRefund) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (endsite == null) {
			if (other.endsite != null)
				return false;
		} else if (!endsite.equals(other.endsite))
			return false;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (refundtime == null) {
			if (other.refundtime != null)
				return false;
		} else if (!refundtime.equals(other.refundtime))
			return false;
		if (seattype == null) {
			if (other.seattype != null)
				return false;
		} else if (!seattype.equals(other.seattype))
			return false;
		if (startsite == null) {
			if (other.startsite != null)
				return false;
		} else if (!startsite.equals(other.startsite))
			return false;
		if (starttime == null) {
			if (other.starttime != null)
				return false;
		} else if (!starttime.equals(other.starttime))
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
		return true;
	}
	 
	
	
	
}

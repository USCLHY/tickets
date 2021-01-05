package com.yc.tickets.domain;

import java.io.Serializable;

/**
* 运营实体类
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月15日 下午6:50:47
*/
@SuppressWarnings("all")
public class Operate implements Serializable {
	private Integer oid ; // 运营编号
	private Integer tid ; // 列车车次id
	private Integer rworemain ; // 软卧剩余数量
	private Integer yworemain ; // 硬卧剩余数量
	private Integer rzuoremain ; // 软座剩余数量
	private Integer yzuoremain ; // 硬座剩余数量
	private Integer zhanremain ; // 站票剩余数量
	private String date ; // 出发日期
	private String starttime ; // 出发时间
	private String endtime ; // 到达时间
	private Integer status ; // 站点状态,误点，正常
	
	private String tname ; // 车次名称
	private String startsite ; // 出发站点名称
	private String endsite ; // 到达站点名称  
	private String duration ; // 所需时间
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getRworemain() {
		return rworemain;
	}
	public void setRworemain(Integer rworemain) {
		this.rworemain = rworemain;
	}
	public Integer getYworemain() {
		return yworemain;
	}
	public void setYworemain(Integer yworemain) {
		this.yworemain = yworemain;
	}
	public Integer getRzuoremain() {
		return rzuoremain;
	}
	public void setRzuoremain(Integer rzuoremain) {
		this.rzuoremain = rzuoremain;
	}
	public Integer getYzuoremain() {
		return yzuoremain;
	}
	public void setYzuoremain(Integer yzuoremain) {
		this.yzuoremain = yzuoremain;
	}
	public Integer getZhanremain() {
		return zhanremain;
	}
	public void setZhanremain(Integer zhanremain) {
		this.zhanremain = zhanremain;
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
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	@Override
	public String toString() {
		return "Operate [oid=" + oid + ", tid=" + tid + ", rworemain=" + rworemain + ", yworemain=" + yworemain
				+ ", rzuoremain=" + rzuoremain + ", yzuoremain=" + yzuoremain + ", zhanremain=" + zhanremain + ", date="
				+ date + ", starttime=" + starttime + ", endtime=" + endtime + ", status=" + status + ", tname=" + tname
				+ ", startsite=" + startsite + ", endsite=" + endsite + ", duration=" + duration + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((endsite == null) ? 0 : endsite.hashCode());
		result = prime * result + ((endtime == null) ? 0 : endtime.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((rworemain == null) ? 0 : rworemain.hashCode());
		result = prime * result + ((rzuoremain == null) ? 0 : rzuoremain.hashCode());
		result = prime * result + ((startsite == null) ? 0 : startsite.hashCode());
		result = prime * result + ((starttime == null) ? 0 : starttime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
		result = prime * result + ((tname == null) ? 0 : tname.hashCode());
		result = prime * result + ((yworemain == null) ? 0 : yworemain.hashCode());
		result = prime * result + ((yzuoremain == null) ? 0 : yzuoremain.hashCode());
		result = prime * result + ((zhanremain == null) ? 0 : zhanremain.hashCode());
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
		Operate other = (Operate) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (endsite == null) {
			if (other.endsite != null)
				return false;
		} else if (!endsite.equals(other.endsite))
			return false;
		if (endtime == null) {
			if (other.endtime != null)
				return false;
		} else if (!endtime.equals(other.endtime))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (rworemain == null) {
			if (other.rworemain != null)
				return false;
		} else if (!rworemain.equals(other.rworemain))
			return false;
		if (rzuoremain == null) {
			if (other.rzuoremain != null)
				return false;
		} else if (!rzuoremain.equals(other.rzuoremain))
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
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		if (tname == null) {
			if (other.tname != null)
				return false;
		} else if (!tname.equals(other.tname))
			return false;
		if (yworemain == null) {
			if (other.yworemain != null)
				return false;
		} else if (!yworemain.equals(other.yworemain))
			return false;
		if (yzuoremain == null) {
			if (other.yzuoremain != null)
				return false;
		} else if (!yzuoremain.equals(other.yzuoremain))
			return false;
		if (zhanremain == null) {
			if (other.zhanremain != null)
				return false;
		} else if (!zhanremain.equals(other.zhanremain))
			return false;
		return true;
	}
	
	 
	
}

package com.yc.tickets.domain;

import java.io.Serializable;

/**
* 列车车次的实体类
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月15日 下午6:43:56
*/
@SuppressWarnings("all")
public class TrainNumber implements Serializable{
	private Integer tid ; // 列车编号
	private String tname ; // 列车名称 
	private Integer typeid ; // 车次类型id
	private String typename; //车次类型名称
	private String startname;//起始站点名称
	private String endname;//站点站点名称
	private Integer startsite ; // 起始站编号
	private Integer endsite ; // 终点站编号
	private String duration ; // 所需时长
	private Integer rwo ; // 软卧总数量
	private Integer rzuo ; // 软座总数量
	private Integer ywo ; // 硬卧总数量
	private Integer yzuo ; // 硬座总数量
	private Integer zhan ; // 站票总数量
	private Double rwoprice ; // 软卧价格
	private Double rzuoprice ; // 软座价格
	private Double ywoprice ; // 硬卧价格
	private Double yzuoprice ; // 硬座价格
	private Double zhanprice ; // 站票价格
	private Double wostudentprice ; // 卧铺学生票价格
	private Double zuostudentprice ; // 座铺学生票价格
	private Integer status ; // 车次状态 
	
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getStartname() {
		return startname;
	}
	public void setStartname(String startname) {
		this.startname = startname;
	}
	public String getEndname() {
		return endname;
	}
	public void setEndname(String endname) {
		this.endname = endname;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public Integer getStartsite() {
		return startsite;
	}
	public void setStartsite(Integer startsite) {
		this.startsite = startsite;
	}
	public Integer getEndsite() {
		return endsite;
	}
	public void setEndsite(Integer endsite) {
		this.endsite = endsite;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Integer getRwo() {
		return rwo;
	}
	public void setRwo(Integer rwo) {
		this.rwo = rwo;
	}
	public Integer getRzuo() {
		return rzuo;
	}
	public void setRzuo(Integer rzuo) {
		this.rzuo = rzuo;
	}
	public Integer getYwo() {
		return ywo;
	}
	public void setYwo(Integer ywo) {
		this.ywo = ywo;
	}
	public Integer getYzuo() {
		return yzuo;
	}
	public void setYzuo(Integer yzuo) {
		this.yzuo = yzuo;
	}
	public Integer getZhan() {
		return zhan;
	}
	public void setZhan(Integer zhan) {
		this.zhan = zhan;
	}
	public Double getRwoprice() {
		return rwoprice;
	}
	public void setRwoprice(Double rwoprice) {
		this.rwoprice = rwoprice;
	}
	public Double getRzuoprice() {
		return rzuoprice;
	}
	public void setRzuoprice(Double rzuoprice) {
		this.rzuoprice = rzuoprice;
	}
	public Double getYwoprice() {
		return ywoprice;
	}
	public void setYwoprice(Double ywoprice) {
		this.ywoprice = ywoprice;
	}
	public Double getYzuoprice() {
		return yzuoprice;
	}
	public void setYzuoprice(Double yzuoprice) {
		this.yzuoprice = yzuoprice;
	}
	public Double getZhanprice() {
		return zhanprice;
	}
	public void setZhanprice(Double zhanprice) {
		this.zhanprice = zhanprice;
	}
	public Double getWostudentprice() {
		return wostudentprice;
	}
	public void setWostudentprice(Double wostudentprice) {
		this.wostudentprice = wostudentprice;
	}
	public Double getZuostudentprice() {
		return zuostudentprice;
	}
	public void setZuostudentprice(Double zuostudentprice) {
		this.zuostudentprice = zuostudentprice;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TrainNumber [tid=" + tid + ", tname=" + tname + ", typeid=" + typeid + ", typename=" + typename
				+ ", startname=" + startname + ", endname=" + endname + ", startsite=" + startsite + ", endsite="
				+ endsite + ", duration=" + duration + ", rwo=" + rwo + ", rzuo=" + rzuo + ", ywo=" + ywo + ", yzuo="
				+ yzuo + ", zhan=" + zhan + ", rwoprice=" + rwoprice + ", rzuoprice=" + rzuoprice + ", ywoprice="
				+ ywoprice + ", yzuoprice=" + yzuoprice + ", zhanprice=" + zhanprice + ", wostudentprice="
				+ wostudentprice + ", zuostudentprice=" + zuostudentprice + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((endname == null) ? 0 : endname.hashCode());
		result = prime * result + ((endsite == null) ? 0 : endsite.hashCode());
		result = prime * result + ((rwo == null) ? 0 : rwo.hashCode());
		result = prime * result + ((rwoprice == null) ? 0 : rwoprice.hashCode());
		result = prime * result + ((rzuo == null) ? 0 : rzuo.hashCode());
		result = prime * result + ((rzuoprice == null) ? 0 : rzuoprice.hashCode());
		result = prime * result + ((startname == null) ? 0 : startname.hashCode());
		result = prime * result + ((startsite == null) ? 0 : startsite.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
		result = prime * result + ((tname == null) ? 0 : tname.hashCode());
		result = prime * result + ((typeid == null) ? 0 : typeid.hashCode());
		result = prime * result + ((typename == null) ? 0 : typename.hashCode());
		result = prime * result + ((wostudentprice == null) ? 0 : wostudentprice.hashCode());
		result = prime * result + ((ywo == null) ? 0 : ywo.hashCode());
		result = prime * result + ((ywoprice == null) ? 0 : ywoprice.hashCode());
		result = prime * result + ((yzuo == null) ? 0 : yzuo.hashCode());
		result = prime * result + ((yzuoprice == null) ? 0 : yzuoprice.hashCode());
		result = prime * result + ((zhan == null) ? 0 : zhan.hashCode());
		result = prime * result + ((zhanprice == null) ? 0 : zhanprice.hashCode());
		result = prime * result + ((zuostudentprice == null) ? 0 : zuostudentprice.hashCode());
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
		TrainNumber other = (TrainNumber) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (endname == null) {
			if (other.endname != null)
				return false;
		} else if (!endname.equals(other.endname))
			return false;
		if (endsite == null) {
			if (other.endsite != null)
				return false;
		} else if (!endsite.equals(other.endsite))
			return false;
		if (rwo == null) {
			if (other.rwo != null)
				return false;
		} else if (!rwo.equals(other.rwo))
			return false;
		if (rwoprice == null) {
			if (other.rwoprice != null)
				return false;
		} else if (!rwoprice.equals(other.rwoprice))
			return false;
		if (rzuo == null) {
			if (other.rzuo != null)
				return false;
		} else if (!rzuo.equals(other.rzuo))
			return false;
		if (rzuoprice == null) {
			if (other.rzuoprice != null)
				return false;
		} else if (!rzuoprice.equals(other.rzuoprice))
			return false;
		if (startname == null) {
			if (other.startname != null)
				return false;
		} else if (!startname.equals(other.startname))
			return false;
		if (startsite == null) {
			if (other.startsite != null)
				return false;
		} else if (!startsite.equals(other.startsite))
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
		if (typeid == null) {
			if (other.typeid != null)
				return false;
		} else if (!typeid.equals(other.typeid))
			return false;
		if (typename == null) {
			if (other.typename != null)
				return false;
		} else if (!typename.equals(other.typename))
			return false;
		if (wostudentprice == null) {
			if (other.wostudentprice != null)
				return false;
		} else if (!wostudentprice.equals(other.wostudentprice))
			return false;
		if (ywo == null) {
			if (other.ywo != null)
				return false;
		} else if (!ywo.equals(other.ywo))
			return false;
		if (ywoprice == null) {
			if (other.ywoprice != null)
				return false;
		} else if (!ywoprice.equals(other.ywoprice))
			return false;
		if (yzuo == null) {
			if (other.yzuo != null)
				return false;
		} else if (!yzuo.equals(other.yzuo))
			return false;
		if (yzuoprice == null) {
			if (other.yzuoprice != null)
				return false;
		} else if (!yzuoprice.equals(other.yzuoprice))
			return false;
		if (zhan == null) {
			if (other.zhan != null)
				return false;
		} else if (!zhan.equals(other.zhan))
			return false;
		if (zhanprice == null) {
			if (other.zhanprice != null)
				return false;
		} else if (!zhanprice.equals(other.zhanprice))
			return false;
		if (zuostudentprice == null) {
			if (other.zuostudentprice != null)
				return false;
		} else if (!zuostudentprice.equals(other.zuostudentprice))
			return false;
		return true;
	}
	
	
	
	
}

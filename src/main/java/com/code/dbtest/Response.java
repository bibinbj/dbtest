package com.code.dbtest;

import java.util.List;

public class Response {
	
	private String dbType;
	private long avgRespTime;
	private List<Long> allRespTime;
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public long getAvgRespTime() {
		return avgRespTime;
	}
	public void setAvgRespTime(long avgRespTime) {
		this.avgRespTime = avgRespTime;
	}
	public List<Long> getAllRespTime() {
		return allRespTime;
	}
	public void setAllRespTime(List<Long> allRespTime) {
		this.allRespTime = allRespTime;
	}

}

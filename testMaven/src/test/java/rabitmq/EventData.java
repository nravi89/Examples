/*******************************************************************************
 *
 * * Copyright (c) 2018 Yodlee, Inc. All Rights Reserved.
 *
 * *
 *
 * * This software is the confidential and proprietary information of Yodlee, Inc.
 *
 * * Use is subject to license terms.
 *
 *******************************************************************************/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabitmq;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author msusainathan
 */
public class EventData implements Comparable<EventData>{

	@Override
	public String toString() {
		return "EventData [fromDate=" + fromDate + ", toDate=" + toDate + ", userCount=" + userCount
				+ ", notificationDate=" + notificationDate + ", eventLoginName=" + eventLoginName + ", providerAcctId="
				+ providerAcctId + ", eventInfo=" + eventInfo + ", usersInfo=" + usersInfo + ", userData=" + userData
				+ "]";
	}

	public String fromDate;
	public String toDate;
	long userCount;
	public String notificationDate;
	
	private String eventLoginName;
	private String providerAcctId; 
	private String eventInfo;
	
	
	public String getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(String eventInfo) {
		this.eventInfo = eventInfo;
	}

	public String getEventLoginName() {
		return eventLoginName;
	}

	public void setEventLoginName(String eventLoginName) {
		this.eventLoginName = eventLoginName;
	}

	public String getProviderAcctId() {
		return providerAcctId;
	}

	public void setProviderAcctId(String providerAcctId) {
		this.providerAcctId = providerAcctId;
	}

	public String getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}

	public long getUserCount() {
		return userCount;
	}

	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}

	Map<String, UserData> usersInfo = new HashMap<String, UserData>();

	public Map<String, UserData> getUsersInfo() {
		return usersInfo;
	}
	
	private List<UserData> userData;
	
	public List<UserData> getUserData() {
		return userData;
	}

	public void setUserData(List<UserData> userData) {
		this.userData = userData;
	}

	

	public void setUsersInfo(Map<String, UserData> usersInfo) {
		this.usersInfo = usersInfo;
	}



	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public int compareTo(EventData o) {
		Date date1 = null;
		Date date2 = null;
		try {
			
			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z").parse(getNotificationDate());

			date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z").parse(o.getNotificationDate());

			if (date1 == null || date2 == null)
				return 0;

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date2.compareTo(date1);

	}

	

}

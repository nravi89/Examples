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

/**
 *
 * @author msusainathan
 */
public class UserData {

	public String getLoginName() {
		return loginName;
	}

	public String getUrl() {
		return url;
	}

	public String getMethod() {
		return method;
	}

	public String getMethodType() {
		return methodType;
	}

	@Override
	public String toString() {
		return "UserData{" + "loginName=" + loginName + ", url=" + url + ", method=" + method + ", methodType="
				+ methodType + '}';
	}

	String loginName;
	String url;
	String method;
	String methodType;

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
}

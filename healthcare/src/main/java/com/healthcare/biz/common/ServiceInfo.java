package com.healthcare.biz.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("serviceInfo")
public class ServiceInfo {
	@Value("${service.enable}")
	String serviceEnable;
	
	@Value("${service.notice}")
	String serviceNotice;
	
	@Value("${service.ver}")
	String serviceVer;
	
	@Value("${service.popup.yn}")
	String servicePopupYN;
	
	@Value("${service.popup.startDate}")
	String servicePopupStrartDate;
	
	@Value("${service.popup.endDate}")
	String servicePopupEndDate;

	public String getServiceEnable() {
		return serviceEnable;
	}

	public void setServiceEnable(String serviceEnable) {
		this.serviceEnable = serviceEnable;
	}

	public String getServiceNotice() {
		return serviceNotice;
	}

	public void setServiceNotice(String serviceNotice) {
		this.serviceNotice = serviceNotice;
	}
	
	public String getServiceVer() {
		return serviceVer;
	}

	public void setServiceVer(String serviceVer) {
		this.serviceVer = serviceVer;
	}
	
	public String getServicePopupYN() {
		return servicePopupYN;
	}

	public void setServicePopupYN(String servicePopupYN) {
		this.servicePopupYN = servicePopupYN;
	}

	public String getServicePopupStrartDate() {
		return servicePopupStrartDate;
	}

	public void setServicePopupStrartDate(String servicePopupStrartDate) {
		this.servicePopupStrartDate = servicePopupStrartDate;
	}

	public String getServicePopupEndDate() {
		return servicePopupEndDate;
	}

	public void setServicePopupEndDate(String servicePopupEndDate) {
		this.servicePopupEndDate = servicePopupEndDate;
	}

	@Override
	public String toString() {
		return "ServiceInfo [serviceEnable=" + serviceEnable
				+ ", serviceNotice=" + serviceNotice
				+ ", serviceVer=" + serviceVer
				+ ", servicePopupYN=" + servicePopupYN
				+ ", servicePopupStrartDate=" + servicePopupStrartDate
				+ ", servicePopupEndDate=" + servicePopupEndDate + "]";
	}

}

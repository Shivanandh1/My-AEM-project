package com.myproject.core.services;

import java.util.List;

public interface OsgiConfigFactory {
	public String getServiceName();
	public int getServiceId();
	public String serviceUrl();
	public OsgiConfigFactory get(int configId);
	public List<OsgiConfigFactory> getAllConfigs();
	
}

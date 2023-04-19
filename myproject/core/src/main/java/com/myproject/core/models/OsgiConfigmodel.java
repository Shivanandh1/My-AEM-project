package com.myproject.core.models;

import java.util.List;

import com.myproject.core.services.OsgiConfigFactory;

public interface OsgiConfigmodel {
	public String getName();
	public int getId();
     public boolean isData();
     public String[] getNames();
     public String getRunmodes();
     
 
 	public List<OsgiConfigFactory> getAllConfigs();
	
}

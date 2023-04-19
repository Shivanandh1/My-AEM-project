package com.myproject.core.models.impl;

import java.util.List;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.myproject.core.models.OsgiConfigmodel;
import com.myproject.core.services.OsgiConfigFactory;
import com.myproject.core.services.OsgiConfigrn;

@Model(adaptables=SlingHttpServletRequest.class,
			adapters=OsgiConfigrn.class,
			defaultInjectionStrategy =DefaultInjectionStrategy.OPTIONAL)
	public class osgiConfigImpl implements OsgiConfigmodel {
	
	@OSGiService
	OsgiConfigrn osgiConfigrn;

	@Override
	public String getName() {
		return osgiConfigrn.getName();
	}

	@Override
	public int getId() {
		return osgiConfigrn.getId();
	}

	@Override
	public boolean isData() {
		// TODO Auto-generated method stub
		return osgiConfigrn.isData();
	}

	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		return osgiConfigrn.getNames();
	}

	@Override
	public String getRunmodes() {
		// TODO Auto-generated method stub
		return osgiConfigrn.getRunmodes();
	}

	@OSGiService
	OsgiConfigFactory osgiConfigFactory;

	@Override
	public List<OsgiConfigFactory> getAllConfigs() {
		// TODO Auto-generated method stub
		return osgiConfigFactory.getAllConfigs();
	} 
	
	
	
}

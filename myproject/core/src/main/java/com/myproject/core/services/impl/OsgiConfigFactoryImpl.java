package com.myproject.core.services.impl;
import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myproject.core.config.OsgiFactoryAttributes;
import com.myproject.core.services.OsgiConfigFactory;

@Component(service=OsgiConfigFactory.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd=OsgiFactoryAttributes.class,factory=true)

public class OsgiConfigFactoryImpl implements OsgiConfigFactory {
	
	private static final Logger logger= LoggerFactory.getLogger(OsgiConfigFactoryImpl.class);
		
	private int serviceId;
	private String serviceName;
	private String serviceUrl;
	private List<OsgiConfigFactory> allConfigsList;

	@Activate
	@Modified
	protected void activate(final  OsgiFactoryAttributes config) {
		serviceId=config.serviceId();
		serviceName=config.serviceName();
		serviceUrl=config.serviceUrl();
	}
	
@Reference(service=OsgiConfigFactory.class, cardinality = ReferenceCardinality.MULTIPLE, policy=ReferencePolicy.DYNAMIC)

public void bindOsgiConfigFactory (final OsgiConfigFactory config){
	if(allConfigsList==null) {
		allConfigsList=new ArrayList<>();
	}
	allConfigsList.add(config);
	
}
public void unbindOsgiConfigFactory( OsgiConfigFactory config) {
    allConfigsList.remove(config);
}
	@Override
	public String getServiceName() {
		// TODO Auto-generated method stub
		return serviceName;
	}

	@Override
	public int getServiceId() {
		// TODO Auto-generated method stub
		return serviceId;
	}

	@Override
	public String serviceUrl() {
		// TODO Auto-generated method stub
		return serviceUrl;
	}

	@Override
	public OsgiConfigFactory get(int serviceId) {
		for(OsgiConfigFactory configvar: allConfigsList) {
			if(serviceId==configvar.getServiceId())
			return configvar;
			
			
		}
		return null;
		// TODO Auto-generated method stub
	 
	
	}

	@Override
	public List<OsgiConfigFactory> getAllConfigs() {
		// TODO Auto-generated method stub
		return allConfigsList;
	}

}

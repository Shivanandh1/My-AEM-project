package com.myproject.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;


	@ObjectClassDefinition(name="AEM Osgi factory configuration",description="enter osgi configuration")
	public @interface OsgiFactoryAttributes {
		
	@AttributeDefinition(
			name="Config Id"
			, description="Enter service Id",
			type=AttributeType.INTEGER)
	 int serviceId() default 23;
	
	@AttributeDefinition(
			name="Service name",
			description="enter service name",
			type=AttributeType.STRING)
	public String serviceName() default "service 1";
	
	@AttributeDefinition(
			name="service url", 
			description="enter service url",
			type=AttributeType.STRING)
	String serviceUrl() default "service url 1";

}

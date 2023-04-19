package com.myproject.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

import com.myproject.core.services.OsgiConfigrn;

@Component(service=OsgiConfigrn.class ,immediate = true)
@Designate(ocd = OsgiConfigImpl.ServiceConfig.class) 

public class OsgiConfigImpl implements OsgiConfigrn {
	@ObjectClassDefinition(name="adobe test osgi configuration",
			description="adobe osgi config demo")
	
	public @interface ServiceConfig{
		@AttributeDefinition(
				name="service name",
				description="enter service name",
				type=AttributeType.STRING)
	public String getName() default "adobe";
		
	@AttributeDefinition(
			name="service id",
			description="enter service id",
			type=AttributeType.INTEGER)
	public int getId() default 23;
	
	@AttributeDefinition(
			
			name="service data",
			description="enter service data",
			type=AttributeType.BOOLEAN)
	 boolean getisData() default false;
	
@AttributeDefinition(
			
			name="service names",
			description="enter service locales",
			type=AttributeType.STRING)
	public String[] getNames() default {"de","in"};

	
@AttributeDefinition(
			name="run modes",
			description="enter service runmodes",
			options= {
					@Option(label="author", value="author"),
					@Option(label="publish", value="publish")
			},
			type=AttributeType.STRING)
	 String getRunmodes() default "author";
	}

private String Name; 
private int  Id;
private boolean Data;
private String[] Names;
private String runmodes;

@Activate
protected void activate(ServiceConfig serviceConfig){
	Name= serviceConfig.getName();
	Id= serviceConfig.getId();
	Data=serviceConfig.getisData();
	Names=serviceConfig.getNames();
	runmodes=serviceConfig.getRunmodes();
			
	} 
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Name ;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return Id;
	}

	@Override
	public boolean isData() {
		// TODO Auto-generated method stub
		return Data;
	}

	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		return Names;
	}
	
	@Override
	public String getRunmodes() {
		// TODO Auto-generated method stub
		return runmodes;
	}
	
}

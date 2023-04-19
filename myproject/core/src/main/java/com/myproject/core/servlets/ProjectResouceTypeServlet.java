package com.myproject.core.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;

@Component(service=Servlet.class)

@SlingServletResourceTypes(
		methods= {HttpConstants.METHOD_GET,HttpConstants.METHOD_POST},
		resourceTypes="myproject/components/page",
		selectors= {"testing","test"},
	    extensions= {"txt","xml"}    
		)
public class ProjectResouceTypeServlet extends SlingAllMethodsServlet {
	private static final Logger LOG=LoggerFactory.getLogger(ProjectResouceTypeServlet.class);
	
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException {
		final Resource resource=req.getResource();
		resp.setContentType("text/plain");
		resp.getWriter().write("page title:"+resource.getValueMap().get(JcrConstants.JCR_TITLE));
	}
	@Override
	protected void doPost( SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException {
		try {
			LOG.info("-----writinf servlets");
			List<RequestParameter> requestParameterList= req.getRequestParameterList();
			for(RequestParameter requestParameter:requestParameterList ) {
				LOG.info("---parameters----",requestParameter.getName(),requestParameter.getString());
			}
		}catch (Exception e) {
			LOG.info("error in quest",e.getMessage());
			// TODO: handle exception
		} 
		resp.getWriter().write("form submitted");
		
	}
	

}

package com.myproject.core.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service =Servlet.class)
@SlingServletPaths(value = { "/bin/pages" ,"test/pages"})
public class ProjectPathTypeServlet extends SlingAllMethodsServlet {
	
	private static final Logger LOG=LoggerFactory.getLogger(ProjectPathTypeServlet.class);
	@Override
	protected final void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException {
		final ResourceResolver resourceResolver=req.getResourceResolver();
		Page page=resourceResolver.adaptTo(PageManager.class).getPage("content/myproject/us/en");
		JSONArray jsonarray=new JSONArray();
		try {
			Iterator<Page> childPages=page.listChildren();
			while(childPages.hasNext()){
				Page childPage=childPages.next();
				JSONObject pageobject=new JSONObject();
				pageobject.put(childPage.getTitle(), childPage.getPath().toString());
				jsonarray.put(pageobject);
			}
		}catch (Exception e) {
			// TODO: handle exception
		LOG.info("ERROR",e.getMessage());
		}
		resp.setContentType("application/json");
		resp.getWriter().write(jsonarray.toString());
		
	}
	@Override
	protected void doPost(SlingHttpServletRequest req, SlingHttpServletResponse resp) {
		try {
			LOG.info("project path type servlet");
			List<RequestParameter> requestParameterList=req.getRequestParameterList();
			for(RequestParameter requestParameter: requestParameterList) {
				LOG.info("parameters---", requestParameter.getName(), requestParameter.getString());
			}
		}catch (Exception e) {
			LOG.info("error message", e.getMessage());
			// TODO: handle exception
		}
	}

}

package com.myproject.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

@Component(service=Servlet.class)
@SlingServletPaths(value = { "/bin/executeworkflow","geeks/executeworkflow" })
public class ExecuteWorkFlow extends SlingSafeMethodsServlet{
	
	private static final Logger LOG=LoggerFactory.getLogger(ExecuteWorkFlow.class);
	@Override
	protected  void doGet(final SlingHttpServletRequest req,final SlingHttpServletResponse resp) throws IOException {
		String status="Workflow executing";
		final ResourceResolver resourceResolver=req.getResourceResolver();
		String payLoad=req.getRequestParameter("page").getString();
		try {
			if(StringUtils.isNotBlank(payLoad)) {
				WorkflowSession workflowsession=resourceResolver.adaptTo(WorkflowSession.class);
				
				WorkflowModel workflowmodel= workflowsession.getModel("/var/workflow/models/Example");
				
				WorkflowData workflowdata=workflowsession.newWorkflowData("JCR_PATH", payLoad);
				status=workflowsession.startWorkflow(workflowmodel, workflowdata).getState();
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			LOG.info("error occured in workflow",e.getMessage());
		}
		resp.setContentType("application/json");
		resp.getWriter().write(status);
		
	}

}

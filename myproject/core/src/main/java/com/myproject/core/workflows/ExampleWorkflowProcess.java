package com.myproject.core.workflows;

import java.util.Iterator;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service=WorkflowProcess.class, immediate=true, 
              property={"process.label" +"=  example workflow process",
            		  Constants.SERVICE_VENDOR+"=aem example ",
            		  Constants.SERVICE_DESCRIPTION+"=example geeks workflow"}
            ) 

public class ExampleWorkflowProcess implements WorkflowProcess {
	private static final Logger LOG=LoggerFactory.getLogger(ExampleWorkflowProcess.class);
	

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) throws WorkflowException {
		
		LOG.info("\n-----------------");
		// TODO Auto-generated method stub
		try {
			WorkflowData workflowData=workItem.getWorkflowData();
			if(workflowData.getPayloadType().equals("JCR_PATH")) {
				Session session=workflowSession.adaptTo(Session.class);
				String path=workflowData.getPayloadType().toString()+"/jcr:content";
				Node node=(Node) session.getItem(path);
				String[] processArgs=processArguments.get("PROCESS_ARGS","string").toString().split(",");
				MetaDataMap wfd=workItem.getWorkflow().getWorkflowData().getMetaDataMap();
				for(String wfargs:processArgs) {
					String[] args=wfargs.split(",");
					String prop=args[0];
					String value=args[1];
					if(node!=null) {
						wfd.put(prop, value);
						node.setProperty(prop, value);
					}
				}
				
				Set<String> keyset=wfd.keySet();
				Iterator<String> i=keyset.iterator();
				while(i.hasNext()) {
					String key=i.next();
					LOG.info("\n key item",key,wfd.get(key));
					
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			LOG.info("error occured at custom workflow",e.getMessage());
		}
		
		
	}
	

}

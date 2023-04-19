package com.myproject.core.workflows;

import javax.jcr.Node;
import javax.jcr.Session;

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

@Component(service=WorkflowProcess.class,
           property= {
               "process.label"+ "=Example workflow step",
               Constants.SERVICE_VENDOR+"=SHIVA",
               Constants.SERVICE_DESCRIPTION+"=custom workflow step"
               
           })
public class ExampleWorkflowStep implements WorkflowProcess {
private static final Logger LOG=LoggerFactory.getLogger(ExampleWorkflowStep.class);
	
	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowsession, MetaDataMap processArgs) throws WorkflowException {
		// TODO Auto-generated method stub
		LOG.info("custom workflow step starts");
		try {
			WorkflowData workflowData=workItem.getWorkflowData();
			if(workflowData.getPayloadType().equals("JCR_PATH"));
			Session session=workflowsession.adaptTo(Session.class);
			String path=workflowData.getPayloadType().toString()+"/jcr:content";
			Node node=(Node) session.getItem(path);
			String brand=processArgs.get("brand","");
			boolean multinational=processArgs.get("multinational",false);
			LOG.info("brand,multinatioanal",brand,multinational);
			String[] countries=processArgs.get("countries",new String[] {});
			for(String country:countries) {
				LOG.info("countries",country);
			}
			
			
		}catch (Exception e) {
			LOG.info("error occures",e.getMessage());
		}
		
		
	}

}

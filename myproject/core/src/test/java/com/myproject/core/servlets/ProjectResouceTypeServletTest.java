package com.myproject.core.servlets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import java.io.IOException;

import org.apache.sling.servlethelpers.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)

public class ProjectResouceTypeServletTest {
	ProjectResouceTypeServlet unitTest=new ProjectResouceTypeServlet();
     AemContext aemContext=new AemContext();
	@BeforeEach
	void setUp() throws Exception {
		aemContext.build().resource("/content/geeks/test","jcr:title","shiva page");
		aemContext.currentResource("/content/geeks/test");
		
	}

	@Test
	void testDoGetSlingHttpServletRequestSlingHttpServletResponse() throws IOException {
		
		 
		MockSlingHttpServletRequest request=aemContext.request();
		MockSlingHttpServletResponse response=aemContext.response();
		 
		unitTest.doGet(request, response);
		
		assertEquals("page title:shiva page", response.getOutputAsString());
		assertEquals(200, response.getStatus());
	}  

	@Test
	void testdoPost() throws IOException {
        Logger log = mock(Logger.class);

		 MockSlingHttpServletRequest request=aemContext.request();
		 MockSlingHttpServletResponse response=aemContext.response();
		 request.addRequestParameter("fname", "shiva");
		 request.addRequestParameter("lname","nandh");
		 unitTest.doPost(request, response); 
		 
		 assertEquals("shiva",request.getParameter("fname"));
		 assertEquals("nandh",request.getParameter("lname"));
		 assertEquals("form submitted", response.getOutputAsString());
		 	}

}

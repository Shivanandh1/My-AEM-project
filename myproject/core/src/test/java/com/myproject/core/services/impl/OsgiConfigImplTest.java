package com.myproject.core.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class OsgiConfigImplTest {

	AemContext aemContext=new AemContext();
	OsgiConfigImpl configTest;
	private String ServiceName;
	
	@BeforeEach
	void setUp() throws Exception {
		configTest=aemContext.registerService(new OsgiConfigImpl());
		OsgiConfigImpl.ServiceConfig config=mock(OsgiConfigImpl.ServiceConfig.class);
	    when(config.getName()).thenReturn("shiva");
	    when(config.getId()).thenReturn(23);
	    when(config.getNames()).thenReturn(new String[]{"shiva","nandh"});

		configTest.activate(config);
	}
	
	@Test
	void testGetName() {
		assertEquals("shiva",configTest.getName());
	}

	@Test
	void testGetId() {
		assertEquals(23,configTest.getId());
	}

	@Test
	void testIsData() {
		assertEquals(false,configTest.isData());
	}

	@Test
	void testGetNames() {
		assertEquals("shiva",configTest.getNames()[0]);
	}

}

package com.myproject.core.models.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Quota.Resource;

import org.apache.poi.hsmf.datatypes.StringChunk;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.eventbus.Subscribe;
import com.myproject.core.models.SimpleExporter;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = SimpleExporter.class,
resourceType = SimpleExporterImpl.RESOURCE_TYPE,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

@Exporter(name="jackson",extensions = "json",selector="myproject",
  options={
    @ExporterOption(name="SerializationFeature.WRAP_ROOT_VALUE",value = "true")
    @ExporterOption(name="MapperFeature.SORT_PROPERTIES_ALPHABETICALLY",value = "true")
  })
  @JsonRootName("myproject-details")
public class SimpleExporterImpl implements SimpleExporter{

private static final LOGGER=LoggerFactory.getLogger(SimpleExporterImpl.class);
    static final String RESOURCE_TYPE="myproject/components/content/simpleexporter";

    @Inject
    Resource resource;

    @SlingObject
    ResourceResolver resourceResolver;
    @Inject
    SlingHttpServletRequest slingHttpServletRequest;

    @ResourcePath(path="content/myproject/us/en")@Via("resource")
    Resource resourcePage;
    @ScriptVariable
    Page currentPage;

    @Inject
    @Via("resource")
    @Named("jcr:lastModifiedBy")
    String modifiedBy;

    @Inject
    @Via("resource")
    @Default(values="my")
    private String fname;

}
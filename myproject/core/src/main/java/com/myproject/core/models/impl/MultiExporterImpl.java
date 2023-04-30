package com.myproject.core.models.impl;

import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Exporters;
import org.apache.sling.models.annotations.Model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.myproject.core.models.MultiExporter;


@Exporters({
    @Exporter(name="jackson", extensions = "json", selector = "myprojectjson",
     options={
        @ExporterOption( name="SerializationFeature.WRAP_ROOT_VALUE",value ="true")}
    ),
    @Exporter(name="myprojectxml",extensions = "xml",selector = "myprojectxml")

})

@Model(adaptables = SlingHttpServletRequest.class,
adapters = MultiExporter.class,
resourceType = MultiExporterImpl.RESOURCE_TYPE,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@JsonRootName("json-exporter")
@XmlRootElement(name="xml-exporter")
 public class MultiExporterImpl implements MultiExporter{
    static final String RESOURCE_TYPE="myproject/components/content/multiexporter";

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        
        throw new UnsupportedOperationException("Unimplemented method 'getTitle'");
    }

    @Override
    public String getDiscription() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDiscription'");
    }

    @Override
    public List<String> getBooks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBooks'");
    }

    @Override
    public Calendar getDate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDate'");
    }



 }

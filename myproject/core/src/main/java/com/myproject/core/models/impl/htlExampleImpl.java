package com.myproject.core.models.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myproject.core.models.htlExample;

@Model(adaptables=SlingHttpServletRequest.class,
      adapters=htlExample.class,resourceType = {htlExampleImpl.RESOURCE_TYPE},
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class htlExampleImpl implements htlExample {
	
   private static final Logger LOG=LoggerFactory.getLogger(htlExampleImpl.class);
   protected static final String RESOURCE_TYPE = "wknd/components/image-list";

@Inject
Resource resource;
 
@Inject
private List<String> books;

	@Override
	public List<String> getBooks() {
		// TODO Auto-generated method stub
		if(books!=null) {
			return new ArrayList<String>(books);
		}
		return null;
	}

	@Override
	public String[] getBooksArray() {
		// TODO Auto-generated method stub
		String[] booksArray={"java","python","golang"};
		return null;
	}

	@Override
	public Map<String, String> getBooksMap() {
		// TODO Auto-generated method stub
		Map<String,String> mapBooks=new HashMap<>();
		mapBooks.put("book1", "value1");
		mapBooks.put("book2", "value2");
		mapBooks.put("book3", "value3");
		mapBooks.put("book4", "value4");

		return mapBooks;
	}

	@Override
	public List<Map<String, String>> getBooksDetailsMap() {
		// TODO Auto-generated method stubne
		List<Map<String,String>> bookDetailMap= new ArrayList<>();
		try {
		Resource bookDetail=resource.getChild("BooksDetailsMap");
		if(bookDetail!=null) {
		for(Resource book:bookDetail.getChildren()) {
			Map<String,String> bookMap=new HashMap<String, String>();
			bookMap.put("bookname",book.getValueMap().get("bookname",String.class));
			bookMap.put("booksubject", book.getValueMap().get("booksubject",String.class));		
		}
		}
		}catch (Exception e) {
			// TODO: handle exception
			LOG.info("error  message",e.getMessage());
		}
		
		return bookDetailMap;
	}
	

	
}

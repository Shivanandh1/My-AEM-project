package com.myproject.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.myproject.core.bean.ArticleListBean;

@Model(adaptables=Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ArticleListModel {

 private static Logger LOGGER=LoggerFactory.getLogger(ArticleListModel.class);
@Inject
private String articleRootPath;

List<ArticleListBean> articleListBeanArray=new ArrayList<>();

@Self
Resource resource;
 

public String getArticleRootPath(){
    return articleRootPath;
}

@PostConstruct
protected void init(){
    ResourceResolver resourceResolver=resource.getResourceResolver();
    Session session=resourceResolver.adaptTo(Session.class);
    QueryBuilder queryBuilder=resourceResolver.adaptTo(QueryBuilder.class);

   Map<String,String> predicate=new HashMap<>();
   predicate.put("path", articleRootPath);
   predicate.put("type","cq:Page");
   Query query=null;
try {
    query=queryBuilder.createQuery(PredicateGroup.create(predicate),session);

} catch (Exception e) {
   LOGGER.error("error in query");
}

SearchResult searchResult=query.getResult();
  List<ArticleListBean> articleListBeanArray=new ArrayList<ArticleListBean>();


for(Hit hit : searchResult.getHits()){
        ArticleListBean articleListBean=new ArticleListBean();
  String path=null;
  try {

    path=hit.getPath();
    Resource articleResource= resourceResolver.getResource(path);
     Page page=articleResource.adaptTo(Page.class);
     String title=page.getTitle();
     String description=page.getDescription();
  
     articleListBean.setPath(path);
     articleListBean.setTitle(title);
     articleListBean.setDescription(description);

     LOGGER.debug("path:{} \ntitle:{} \nDescription:{}",path,title,description);
       articleListBeanArray.add(articleListBean);
    } 
    catch (RepositoryException e) {
    throw new RuntimeException(e);
      }
}

}

public List<ArticleListBean> getArticleListBeanArray(){
    return articleListBeanArray;
}
}
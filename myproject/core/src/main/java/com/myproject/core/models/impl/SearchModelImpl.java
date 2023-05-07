package com.myproject.core.models.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.search.Predicate;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.core.models.SearchModel;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SearchModelImpl implements SearchModel {

    private List<SearchResult> results;

    @SlingObject
    private ResourceResolver resourceResolver;

    @Inject
    private QueryBuilder queryBuilder;

    @Self
    private SlingHttpServletRequest request;

    @PostConstruct
    protected void init() {
        String queryString = request.getParameter("q");

        if (queryString != null) {
            Query query = queryBuilder.createQuery(PredicateGroup.create((Map) getPredicates()), resourceResolver.adaptTo(javax.jcr.Session.class));
            SearchResult searchResult = query.getResult();
            results = new ArrayList<SearchResult>();

            for (Hit hit : searchResult.getHits()) {
                results.add((SearchResult) hit);
            }
        }
    }

    private PredicateGroup getPredicates() {
        PredicateGroup predicates = new PredicateGroup();
        predicates.setAllRequired(true);
        predicates.add(createFulltextPredicate());
        return predicates;
    }

    private Predicate createFulltextPredicate() {
        return  null;
    }

    @Override
    public List<SearchResult> getResults() {
        return results;
    }

    public String getResultsJson() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}

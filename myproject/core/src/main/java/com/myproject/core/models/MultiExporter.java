package com.myproject.core.models;

import java.util.Calendar;
import java.util.List;

public interface MultiExporter {
    public String getTitle();
    public String getDiscription();
    public List<String> getBooks();
    public Calendar getDate(); 

}

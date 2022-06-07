package com.ecommerce.commerce.model;

import java.util.HashMap;
import java.util.Map;

public class Statistique {
    String date ;
    Long count;

    public Statistique(String date, Long count) {
        this.date = date;
        this.count = count;
    }
    public  Map<String,Object> toMap(){
        Map<String,Object> res = new HashMap<>();
        res.put("date",date);
        res.put("achat",count);
        return  res ;
    }
}

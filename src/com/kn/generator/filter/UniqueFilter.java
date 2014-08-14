/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kn.generator.filter;

import java.util.HashSet;

import com.kn.generator.Generator;
import com.kn.model.CrawlDatum;

/**
 *
 * @author hu
 */
public class UniqueFilter extends Filter{

    public HashSet<String> hashset=new HashSet<String>();
    
    public void addUrl(String url){
         hashset.add(url);
    }

   
      
        
     
  
    
    public UniqueFilter(Generator generator) {
        super(generator);
    }

    @Override
    public CrawlDatum next() {
        CrawlDatum crawldatum=generator.next();
        if(crawldatum==null){
            return null;
        }
        String url=crawldatum.url;
        if(hashset.contains(url)){
            return next();
        }
        else{
            addUrl(url);
            return crawldatum;
        }
    }
    
}

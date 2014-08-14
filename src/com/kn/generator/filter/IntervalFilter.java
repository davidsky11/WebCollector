/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kn.generator.filter;

import com.kn.generator.Generator;
import com.kn.model.CrawlDatum;
import com.kn.model.Page;
import com.kn.util.Config;

/**
 *
 * @author hu
 */
public class IntervalFilter extends Filter{

    public IntervalFilter(Generator generator) {
        super(generator);
    }

    @Override
    public CrawlDatum next() {
        
        CrawlDatum crawldatum=generator.next();
        
         if(crawldatum==null){
            return null;
        }
         
        
        if(crawldatum.status==Page.UNFETCHED){
            return crawldatum;
        }
        if(Config.interval==-1){
            return crawldatum;
        }
       
        Long lasttime=crawldatum.fetchtime;
        if(lasttime+Config.interval>System.currentTimeMillis()){
            return next();
        }
        return crawldatum;
    }

   
    
}

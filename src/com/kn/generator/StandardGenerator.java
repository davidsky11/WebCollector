/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kn.generator;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.reflect.ReflectDatumReader;

import com.kn.model.CrawlDatum;
import com.kn.util.Config;
import com.kn.util.FileUtils;

/**
 *
 * @author hu
 */
public class StandardGenerator extends Generator {

    public String crawl_path;
    public StandardGenerator(String crawl_path){
        this.crawl_path=crawl_path;
        try {
            backup();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        DbUpdater dbupdater=new DbUpdater(crawl_path);
        try {
            if(dbupdater.isLocked()){
                dbupdater.merge();
                dbupdater.unlock();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            initReader();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void backup() throws IOException {

        File oldfile = new File(crawl_path, Config.old_info_path);
        File currentfile = new File(crawl_path, Config.current_info_path);
        FileUtils.copy(currentfile, oldfile);
    }


    public static void main(String[] args) throws IOException {
        Injector inject=new Injector("/home/hu/data/crawl_avro");
        inject.inject("http://www.xinhuanet.com/");
        String crawl_path = "/home/hu/data/crawl_avro";
        StandardGenerator bg = new StandardGenerator(null) {

            @Override
            public boolean shouldFilter(String url) {
                if (Pattern.matches("http://news.xinhuanet.com/world/.*", url)) {
                    return false;
                } else {
                    return true;
                }
            }

        };
     
       

    }

  
    
    @Override
    public CrawlDatum next(){
        if(!iterator.hasNext())
            return null;

        CrawlDatum crawldatum=iterator.next();
        
        if(crawldatum==null){
            return null;
        }
        
        
        if(shouldFilter(crawldatum.url)){
            return next();
        }
        
       
   
        return crawldatum;
    }
    
    
    Iterator<CrawlDatum> iterator;
    public void initReader() throws IOException{
         
        File oldfile=new File(crawl_path, Config.old_info_path);
        DatumReader<CrawlDatum> datumReader = new ReflectDatumReader<CrawlDatum>(CrawlDatum.class);
        DataFileReader<CrawlDatum> dataFileReader = new DataFileReader<CrawlDatum>(oldfile, datumReader);
        iterator=dataFileReader.iterator();
        
        
        
    }

    public boolean shouldFilter(String url) {
        return false;
    }


}

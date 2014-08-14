/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kn.generator;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectDatumWriter;

import com.kn.model.AvroModel;
import com.kn.model.CrawlDatum;
import com.kn.model.Page;
import com.kn.util.Config;
import com.kn.util.Task;


/**
 *
 * @author hu
 */
public class Injector extends Task{
    
    String crawl_path;
    public Injector(String crawl_path){
        this.crawl_path=crawl_path;
    }
    
    public void inject(String url) throws IOException{
        ArrayList<String> urls=new ArrayList<>();
        urls.add(url);
        inject(urls);
    }
    
    public boolean hasInjected(){
        String info_path=Config.current_info_path;
        File inject_file=new File(crawl_path,info_path);
        return inject_file.exists();
    }
    
    
    
    public void inject(ArrayList<String> urls) throws UnsupportedEncodingException, IOException{
         Schema schema = AvroModel.getPageSchema();
        
        String info_path=Config.current_info_path;
        File inject_file=new File(crawl_path,info_path);
        if(!inject_file.getParentFile().exists()){
            inject_file.getParentFile().mkdirs();
        }
        DatumWriter<CrawlDatum> datumWriter = new ReflectDatumWriter<CrawlDatum>(schema);
        DataFileWriter<CrawlDatum> dataFileWriter = new DataFileWriter<CrawlDatum>(datumWriter);
        
        dataFileWriter.create(schema, inject_file);
        
        for(String url:urls){
            CrawlDatum page=new CrawlDatum();
            page.url=url;
            page.status=Page.UNFETCHED;
            dataFileWriter.append(page);
            
            
        }
        dataFileWriter.close();
        
        
        
        
        
    }
    
    
    
    public static void main(String[] args) throws IOException{
        Injector inject=new Injector("/home/hu/data/crawl_avro");
        inject.inject("http://www.xinhuanet.com/");
    }
}

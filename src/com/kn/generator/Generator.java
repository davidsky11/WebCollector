/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kn.generator;


import com.kn.model.CrawlDatum;
import com.kn.util.Task;

/**
 *
 * @author hu
 */
public abstract class Generator extends Task{
   
           
    public abstract CrawlDatum next();
 
    
}

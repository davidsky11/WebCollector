/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kn.parser;

import java.util.ArrayList;

import com.kn.model.Link;

/**
 *
 * @author hu
 */
public class ParseResult {
    public String title;
    public ArrayList<Link> links;
    public ParseResult(String title,ArrayList<Link> links){
        this.title=title;
        this.links=links;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kn.parser;

import com.kn.model.Page;

/**
 *
 * @author hu
 */
public abstract class Parser {
    public abstract ParseResult getParse(Page page) throws Exception;
}

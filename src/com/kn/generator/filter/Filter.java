/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kn.generator.filter;

import com.kn.generator.Generator;

/**
 *
 * @author hu
 */
public abstract class Filter extends Generator{
    Generator generator;
    public Filter(Generator generator){
        this.generator=generator;
    }
}

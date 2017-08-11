/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Lucas
 */
public class JSONIP extends JSONConverter {

    public JSONIP(String source){
        super(source);
    }
    
    public String toIP(){
        JSONItem novoItem = getNextItem();
        return novoItem.getStringValue("ip");
    }
    
}
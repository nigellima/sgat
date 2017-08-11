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
public class JSONLocal extends JSONConverter {
    private JSONItem novoItem;

    public JSONLocal(String source){
        super(source.replace("\t", "").replace("\" :", "\":"));
    }
    
    public String toState(){
        return novoItem.getStringValue("regionName");
    }    
    
    public String toCity(){
        return novoItem.getStringValue("cityName");
    }
    
    public Locale toLocale(){
        novoItem = getNextItem();
        Locale  local = new Locale();
        local.setRegion(toState());
        local.setCity(toCity());
        
        return local;
    }
    
}
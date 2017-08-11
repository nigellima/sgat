/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author nlima.huufma
 */
public class SmartDate {
    
    String[] names = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", 
                      "Junho", "Julho", "Agosto", "Setembro", "Outubro",
                      "Novembro", "Dezembro"};
    int [] years;
    int firstYear = 2007;
    
    public SmartDate()
    {
        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int size = year - firstYear +1;
        years = new int[size];
        for(int i = 0; i < size; i++)
            years[i] = year - i;
    }

    public int[] getYears() {
        return years;
    }

    public void setYears(int[] years) {
        this.years = years;
    }
    
    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
    
    public int getMonthNumber(String month)
    {
        for(int i=0; i < names.length; i++)
            if(names[i].equals(month))
                return i++;
        return -1;
    }
}

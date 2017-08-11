/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import entities.Tipo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HUUFMA
 */
public class TipoCounter 
{
    private Tipo tipo;
    private int[] totals = new int[3];
    
    public TipoCounter(){        
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int[] getTotals() {
        return totals;
    }

    public void setTotals(int[] totals) {
        this.totals = totals;
    }
    
    @Override
    public String toString()
    {
        return tipo.getDescricao();
    }
}

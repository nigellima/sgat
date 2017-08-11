/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

/**
 *
 * @author HUUFMA
 */
public class AtividadeGenero 
{
    public static int TBR = 1;
    public static int NTS = 2;
    public static int TODAS = 3; 
    
    private int genero;
    private String descricao;
    
    
    public AtividadeGenero(){}
    
    public AtividadeGenero(int gender, String descricao){
        this.genero = gender;
        this.descricao = descricao;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}

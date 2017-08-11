
package resources;

import entities.Estado;

public class URLs {    
    private static final String MUNICIPIOS = "http://smart.telessaude.ufrn.br/api/municipios/.json";
    private static final String EQUIPES = "http://smart.telessaude.ufrn.br/api/equipes-saude/ma/.json";
    private static final String UBS = "http://smart.telessaude.ufrn.br/api/estabelecimentos/ma/.json";
    private static final String PROFISSIONAIS = "http://smart.telessaude.ufrn.br/api/profissionais-vinculo/";
    private static final String CBOs = "http://smart.telessaude.ufrn.br/api/cbos/.json";
    
    public static String municipios(){
        return URLs.MUNICIPIOS;
    }
    
    public static String equipes(){
        return URLs.EQUIPES;
    }
    
    public static String ubs(){
        return URLs.UBS;
    }
    
    public static String profissionais(Estado estado){
        return URLs.PROFISSIONAIS + estado.getSigla() + "/.json";
    }
    
    public static String cbos(){
        return URLs.CBOs;
    }
    
}

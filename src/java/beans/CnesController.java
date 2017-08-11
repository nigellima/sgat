/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Cbo;
import entities.Endereco;
import entities.Municipio;
import entities.Pessoa;
import entities.ProfGeral;
import entities.ProfSaude;
import entities.Ubs;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import service.URLConnector;
import sessions.CboFacade;
import sessions.EnderecoFacade;
import sessions.MunicipioFacade;
import sessions.PessoaFacade;
import sessions.ProfGeralFacade;
import sessions.ProfSaudeFacade;
import sessions.UbsFacade;

/**
 *
 * @author nlima.huufma
 */
@ManagedBean(name = "cnesController")
@SessionScoped
public class CnesController implements Serializable {
    private Integer pgCreated;
    private Integer psCreated;
    
    @EJB
    private PessoaFacade ejbPessoa;
    
    @EJB
    private ProfGeralFacade ejbProfGeral;
    
    @EJB
    private ProfSaudeFacade ejbProfSaude;
    
    @EJB
    private MunicipioFacade ejbMun;
    
    @EJB
    private CboFacade ejbCbo;
    
    @EJB
    private UbsFacade ejbUbs;
    
    @EJB
    private EnderecoFacade ejbEndereco;
    
    public String atualizar(){     
        pgCreated = 0;
        psCreated = 0;
        
        atualizarProfissionais();
        
        return null;
    }
    
    public void atualizar(Pessoa p){
        pgCreated = 0;
        psCreated = 0;
        if(!p.getCpf().equals("")){
            //System.out.println("Pessoa: " + p.getNome() + " " + p.getSobrenome());
            URLConnector connector = new URLConnector("http://smart.telessaude.ufrn.br/api/profissional/"+ p.getCpf() +"/vinculos/?format=json");
            try {
                connector.Connect();
                String content  = connector.getPageContent();
                JSONObject obj = new JSONObject(content);
                if(content.length() > 2){
                    String cnsstr = obj.getString("cns");
                    JSONArray json = (JSONArray)obj.get("vinculos");
                    System.out.println(json);

                    JSONObject vinculo = (JSONObject)json.get(0);

                    String cbostr = vinculo.get("coCBO").toString();
                    String munstr = vinculo.get("coMun").toString();
                    String cnesstr = vinculo.get("cnes").toString();

//                    System.out.println("CADASTRO: PROF_GERAL");
//                    System.out.println("PESSOA: " + p.getId());
//                    System.out.println("CBO: " + vinculo.get("coCBO"));
//                    System.out.println("MUNICIPIO: " + vinculo.get("coMun"));
//
//                    System.out.println("CADASTRO: PROF_SAUDE");
//                    System.out.println("PESSOA: " + p.getId());
//                    System.out.println("CNS: " + cnsstr);
//                    System.out.println("EQUIPE: null");
//                    System.out.println("CNES: " + vinculo.get("cnes"));

                    checkProfGeral(p, munstr, cbostr);
                    checkProfSaude(p, cnsstr, cnesstr);

                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    private void atualizarProfissionais(){
        int numPessoas = ejbPessoa.countTotal();
        //System.out.println("Qtd: " + numPessoas);
        int start = 0, range = 100, i = 1;
        
        while(start < numPessoas){
            //System.out.println("Querying from " + start + " to " + (start + range) + " ...");
            List<Pessoa> batch = ejbPessoa.findRangePagination(start, range);
            start += range;

            for(Pessoa p: batch){
                if(!p.getCpf().equals("")){
                    //System.out.println("Pessoa #" + i++ + ": " + p.getNome() + " " + p.getSobrenome());
                    URLConnector connector = new URLConnector("http://smart.telessaude.ufrn.br/api/profissional/"+ p.getCpf() +"/vinculos/?format=json");
                    try {
                        connector.Connect();
                        String content  = connector.getPageContent();
//                        System.out.println(content);
                        JSONObject obj = new JSONObject(content);
                        if(content.length() > 2){
                            String cnsstr = obj.getString("cns");
                            JSONArray json = (JSONArray)obj.get("vinculos");
                            //System.out.println(json);
                            
                            JSONObject vinculo = (JSONObject)json.get(0);
                            
                            String cbostr = vinculo.get("coCBO").toString();
                            String munstr = vinculo.get("coMun").toString();
                            String cnesstr = vinculo.get("cnes").toString();
                                                        
//                            System.out.println("CADASTRO: PROF_GERAL");
//                            System.out.println("PESSOA: " + p.getId());
//                            System.out.println("CBO: " + vinculo.get("coCBO"));
//                            System.out.println("MUNICIPIO: " + vinculo.get("coMun"));
//                                                        
//                            System.out.println("CADASTRO: PROF_SAUDE");
//                            System.out.println("PESSOA: " + p.getId());
//                            System.out.println("CNS: " + cnsstr);
//                            System.out.println("EQUIPE: null");
//                            System.out.println("CNES: " + vinculo.get("cnes"));
                            
                            checkProfGeral(p, munstr, cbostr);
                            checkProfSaude(p, cnsstr, cnesstr);
                           
                        }
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            
            
        }
        
//        System.out.println("pgCreated: " + pgCreated);
//        System.out.println("psCreated: " + psCreated);
    }

    private ProfGeral checkProfGeral(Pessoa p, String munstr, String cbostr) {
        boolean isNew = false;
        ProfGeral pg = ejbProfGeral.getByPessoa(p);

        if(pg==null){
            pg = new ProfGeral();
            isNew = true;
        }
        
        pg.setPessoa(p);
        
        //update prof
        //municipio
        Municipio mun = ejbMun.findByIbge(Integer.parseInt(munstr));
        pg.setMunicipio(mun);

        //cbo
        Cbo cbo = ejbCbo.findByCodigo(cbostr);
        pg.setCbo(cbo);
        
        if(isNew){
            try {
                ejbProfGeral.create(pg);
                pgCreated++;
            }
            catch (Exception e){
                //System.out.println("Error on creating new ProfGeral... " + e.getMessage());
            }
        }
        else {
            try {
                ejbProfGeral.edit(pg);
            }
            catch (Exception e){
                //System.out.println("Error on updating ProfGeral... " + e.getMessage());
            }
        }
                
        return pg;
    }

    private ProfSaude checkProfSaude(Pessoa p, String cnsstr, String cnesstr) {
        boolean isNew = false;
        ProfSaude ps = ejbProfSaude.getByPessoa(p);
        
        if(ps==null){
            ps = new ProfSaude();
            isNew = true;
        }
        
        Ubs ubs = ejbUbs.findByCnes(Integer.parseInt(cnesstr));
        if(ubs == null){
            ubs = new Ubs(); 
            ubs.setCnes(Integer.parseInt(cnesstr));
            //System.out.println("Creating new UBS..." + cnesstr);
            URLConnector connector = new URLConnector("http://smart.telessaude.ufrn.br/api/estabelecimentos-vinculos/?cnes=" + cnesstr + "&format=json");
            try {
                connector.Connect();
                String content  = connector.getPageContent();
                JSONObject obj = new JSONObject(content.substring(1, content.length()-1));
                if(!content.contains("\"successful\":false")){
                    String cnes = obj.getString("cnes");
                    String noFant = obj.getString("noFant");
                    String noEmpr = obj.getString("noEmpr");
                    String logradouro = obj.getString("logradouro");
                    String numero = obj.getString("numero");
                    String cep = obj.getString("cep");
                    String telefone = obj.getString("telefone");
                    String coMun = obj.getString("coMun");
                    
//                    System.out.println("ESTABELECIMENTO...");
//                    System.out.println("CNES: " + cnes);
//                    System.out.println("NOME: " + noFant);
//                    System.out.println("LOGRADOURO: " + logradouro);
//                    System.out.println("NUMERO: " + numero);
//                    System.out.println("CEP: " + cep);
//                    System.out.println("TELEFONE: " + telefone);
//                    System.out.println("MUN: " + coMun);
                    
                    Endereco end = new Endereco();
                    end.setLogradouro(logradouro);
                    end.setNum(numero);
                    end.setCep(cep);
                    end.setTelefone(telefone);
                    end.setMunicipio(ejbMun.findByIbge(Integer.parseInt(coMun)));
                    
                    try {
                        ejbEndereco.create(end);
                        ubs = new Ubs();
                        ubs.setCnes(Integer.parseInt(cnes));
                        ubs.setNome(noFant);
                        ubs.setEndereco(end);
                        ejbUbs.create(ubs);
                    }
                    catch (Exception e){
                        //System.out.println("Error on creating new Estabelecimento UBS... " + e.getMessage());
                    }
                    
                }
            }
            catch(Exception e){
               // System.out.println(e.getMessage());
            }
        }
           
        ps.setEquipe("null");
        ps.setPessoa(p);
        ps.setUbs(ubs);
        ps.setCns(cnsstr);
        
        if(isNew){
            try {
                ejbProfSaude.create(ps);
                psCreated++;
            }
            catch (Exception e){
                //System.out.println("Error on creating new ProfSaude... " + e.getMessage());
            }
        }
        else {
            
            try {
                ejbProfSaude.edit(ps);
            }
            catch (Exception e){
                //System.out.println("Error on updating ProfSaude... " + e.getMessage());
            }  
        }
        
        return ps;
    }
    
}


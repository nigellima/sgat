/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

import entities.Atividade;
import entities.Pessoa;

/**
 *
 * @author nlima.huufma
 */
public class ParticipacaoPessoaSmart {
    private Pessoa pessoa;
    private Atividade atividade;
    private String cnes, cbo;
    boolean status;
    
    public ParticipacaoPessoaSmart(){}
    
    
    public ParticipacaoPessoaSmart(Atividade atv, Pessoa p, String cnes, String cbo)
    {
        this.atividade = atv;
        this.pessoa = p;
        this.cnes = cnes;
        this.cbo = cbo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public String getCbo() {
        return cbo;
    }

    public void setCbo(String cbo) {
        this.cbo = cbo;
    }
     
     
}

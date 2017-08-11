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
public class CertificadoPessoa {
    
    private Pessoa pessoa;
    private Atividade atividade;
    private int tipoCertificado;
    private String nomeTipo;

    
    public CertificadoPessoa(Atividade atv, Pessoa p, int tipo, String tipoC)
    {
        this.atividade = atv;
        this.pessoa = p;
        this.tipoCertificado = tipo;
        this.nomeTipo = tipoC;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
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

    public int getTipoCertificado() {
        return tipoCertificado;
    }

    public void setTipoCertificado(int tipoCertificado) {
        this.tipoCertificado = tipoCertificado;
    }
    
    
    
}

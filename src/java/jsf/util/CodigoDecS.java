/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

/**
 *
 * @author nlima.huufma
 */
public class CodigoDecS {
    String codigo, nome;

    public CodigoDecS(String codigo, String nome)
    {
        this.codigo = codigo;
        this.nome = nome;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

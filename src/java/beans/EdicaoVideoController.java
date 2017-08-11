/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Atividade;
import entities.EdicaoVideo;
import entities.Pessoa;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author nlima.huufma
 */
@ManagedBean(name = "edicaoController")
@ViewScoped
public class EdicaoVideoController {
    
    EdicaoVideo edicao;

    public EdicaoVideo getEdicao() {
        return edicao;
    }

    public void setEdicao(EdicaoVideo edicao) {
        this.edicao = edicao;
    }
    
    
}

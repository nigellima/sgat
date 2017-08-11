/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Atividade;
import entities.Funcionario;
import entities.ParticipacaoViagem;
import entities.Viagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import jsf.util.JsfUtil;
import sessions.AtividadeFacade;
import sessions.FuncionarioFacade;
import sessions.ParticipacaoViagemFacade;
import sessions.ViagemFacade;


@ManagedBean(name = "viagemController")
@SessionScoped
public class ViagemController implements Serializable {
    private Viagem viagem;
    private Atividade atividade;
    private Funcionario funcionario;
    private ParticipacaoViagem participacao;
    private Integer funcionarioId;
    private List<Funcionario> participantes = new ArrayList();
    private List<Funcionario> funcionarios;
    
    @EJB
    private ViagemFacade ejbViagem;
    @EJB
    private ParticipacaoViagemFacade ejbParticipacaoViagem;
    @EJB
    private AtividadeFacade ejbAtividade;
    @EJB
    private FuncionarioFacade ejbFuncionario;

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public ParticipacaoViagem getParticipacao() {
        return participacao;
    }

    public void setParticipacao(ParticipacaoViagem participacao) {
        this.participacao = participacao;
    }
    
    public List<Funcionario> getFuncionarios(){
        return this.funcionarios;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer instituicaoId) {
        this.funcionarioId = instituicaoId;
    }

    public List<Funcionario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Funcionario> participantes) {
        this.participantes = participantes;
    }
    
    public String prepareCreate(Atividade atividade){
        this.funcionarios = ejbFuncionario.findAll();
        this.atividade = atividade;
        Date today = new Date();
        
        viagem = new Viagem();
        viagem.setDtPreenchimento(today);
        if(this.atividade!=null){
            System.out.println("ID: " + this.atividade.getId());
            System.out.println("Recebendo atividade e mostrando view...");
            viagem.setAtividade(atividade);
        }
        
        participacao = new ParticipacaoViagem();
        return "/forms/view_create_viagem";
    }
    
    public void guardarParticipante(){
        Funcionario tmp = ejbFuncionario.findById(funcionarioId);
        participantes.add(tmp);
        System.out.println(participantes);
    }
    
    public void removeParticipacao(Funcionario fun){
       participantes.remove(fun);
    }
    
    private void persistParticipacaoViagem(){
        if(!participantes.isEmpty())
            for(Funcionario f : participantes){
                participacao = new ParticipacaoViagem();
                participacao.setViagem(viagem);
                participacao.setPessoa(f.getPessoa());
                ejbParticipacaoViagem.create(participacao);
            }
    }
    
    public String save(){
        try {
            ejbViagem.create(viagem);
            persistParticipacaoViagem();
            JsfUtil.addSuccessMessage("Viagem cadastrada com sucesso.", "formViagem");
            return "/public/home";
        }
        catch (Exception ex){
            JsfUtil.addErrorMessage(ex, "Erro ao cadastrar Viagem");
        }
        
        return null;
    }
}

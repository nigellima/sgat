package beans;

import entities.Atividade;
import entities.Conexao;
import entities.EdicaoVideo;
import entities.Solicitacao;
import entities.Status;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import sessions.AtividadeFacade;
import sessions.ConexaoFacade;
import sessions.EdicaoVideoFacade;
import sessions.SolicitacaoFacade;
import sessions.StatusFacade;


@ManagedBean(name = "agendaController")
@ViewScoped
public class AgendaController implements Serializable {
    private ScheduleModel model;
    
    private Atividade current;
    private Solicitacao solicitacao;
    private Conexao conexao;
    private List<Conexao> conexoes;
    private ScheduleEvent selected;
    private EdicaoVideo edicao;
    
    @EJB
    private AtividadeFacade ejbAtividade;
    
    @EJB
    private ConexaoFacade ejbConexao;
    
    @EJB
    private StatusFacade ejbStatus;
    
    @EJB
    private SolicitacaoFacade ejbSolicitacao;
    
    @EJB
    private EdicaoVideoFacade ejbEdicao;
    
    
    @PostConstruct
    public void init() {
        model = new DefaultScheduleModel();
        for(Atividade a : atividadesAgendadas()){
            DefaultScheduleEvent event = new DefaultScheduleEvent();
            event.setTitle(" " + a.getModalidade().getDescricao() + "\n" + a.getTema());
            event.setStartDate(formarData(a.getDt(), a.getHrInicio()));
            event.setEndDate(formarData(a.getDt(), a.getHrTermino()));
            event.setAllDay(false);
            event.setEditable(false);
            event.setDescription(a.getDescricao());
            event.setData(a);
            model.addEvent(event); 
        }
    }

    public EdicaoVideo getEdicao() {
        return edicao;
    }

    public void setEdicao(EdicaoVideo edicao) {
        this.edicao = edicao;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
    
    public ScheduleModel getModel(){
        return model;
    }

    public Atividade getCurrent() {
        return current;
    }

    public void setCurrent(Atividade current) {
        this.current = current;
    }

    public Conexao getConexao() {
        return conexao;
    }

    public void setConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    public List<Conexao> getConexoes() {
        return conexoes;
    }

    public void setConexoes(List<Conexao> conexoes) {
        this.conexoes = conexoes;
    }

    public ScheduleEvent getSelected() {
        return selected;
    }

    public void setSelected(ScheduleEvent selected) {
        this.selected = selected;
    }
    
    public List<Atividade> atividadesDeferidas(){
        Status status = ejbStatus.findByDescricao("Deferido");
        return ejbAtividade.findByStatus(status);
    }
    
    public List<Atividade> atividadesRealizadas(){
        Status status = ejbStatus.findByDescricao("Realizada");
        return ejbAtividade.findByStatus(status);
    }
    
    public List<Atividade> atividadesAgendadas(){
        List<Atividade> list = atividadesDeferidas();
        for(Atividade a: atividadesRealizadas())
            list.add(a);
        
        return list;
    }
    
    public Date formarData(Date date, Date time){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime( date );
        calendar.set(Calendar.HOUR, time.getHours());
        calendar.set(Calendar.MINUTE, time.getMinutes());
        calendar.set(Calendar.SECOND, time.getSeconds());
        
        return calendar.getTime();
    }
    
    public void onEventSelect(SelectEvent selectEvent) {
        selected = (ScheduleEvent) selectEvent.getObject();
        Atividade at = (Atividade) selected.getData();
        current = ejbAtividade.findById(at.getId());
        //conexao = ejbConexao.findByAtividade(current);
        conexoes = ejbConexao.findListByAtividade(current);
        solicitacao = ejbSolicitacao.findByAtividade(current);
        edicao = ejbEdicao.findByAtividade(current);
    }
    
    public String getCertificados(Atividade atv)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        AtividadeController atvd = context.getApplication().evaluateExpressionGet(context, "#{atividadeController}", AtividadeController.class);
        //AtividadeController atvd = (AtividadeController) context.getExternalContext().getSessionMap().get("atividadeController");
        System.out.println("null caraio " + atvd);
        //System.out.println("null caraio " + context.getExternalContext().getSessionMap().get("atividadeController"));
        atvd.setCurrent(atv);
         //request.getSession().getAttribute("atividadeController").
        return "/views/view_certificados_list";
    }
}

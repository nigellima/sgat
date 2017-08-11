package beans;

import entities.Atividade;
import entities.Deferimento;
import entities.Local;
import entities.Pessoa;
import entities.Solicitacao;
import entities.Status;
import entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import jsf.util.JsfUtil;
import jsf.util.LazyPessoaDataModel;
import jsf.util.LazySolicitacaoDataModel;
import org.primefaces.model.LazyDataModel;
import sessions.AtividadeFacade;
import sessions.ConexaoFacade;
import sessions.DeferimentoFacade;
import sessions.SolicitacaoFacade;
import sessions.StatusFacade;

@ManagedBean(name = "solicitacaoController")
@SessionScoped
public class SolicitacaoController implements Serializable {

    private int idKey;
    private Local local;
    private Atividade atividade;
    private Deferimento deferimento;
    private Solicitacao selected;
    
    @EJB
    private SolicitacaoFacade ejbSolicitacao;
    @EJB
    private StatusFacade ejbStatus;
    @EJB
    private ConexaoFacade ejbConexao;
    @EJB
    private AtividadeFacade ejbAtividade;
    @EJB
    private DeferimentoFacade ejbDeferimento;
    
    private LazyDataModel<Atividade> lazyModel;
    
    @PostConstruct
    public void init()
    {
        lazyModel = new LazySolicitacaoDataModel(getAtividadesFromSolicitacoesPendentes());
        System.out.println("init");
    }
    
    public LazyDataModel<Atividade> getLazyModel() {
        lazyModel = new LazySolicitacaoDataModel(getAtividadesFromSolicitacoesPendentes());
        return lazyModel;
    }

    private List<Atividade> getAtividadesFromSolicitacoesPendentes()
    {
        List<Atividade> atvs = new ArrayList();
        for(Solicitacao s : getSolicitacoesPendentes())
        {
            atvs.add(s.getAtividade());
        }
        
        return atvs;
    }
    
    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public int getIdKey() {
        return idKey;
    }

    public void setIdKey(int idKey) {
        this.idKey = idKey;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Deferimento getDeferimento() {
        return deferimento;
    }

    public void setDeferimento(Deferimento deferimento) {
        this.deferimento = deferimento;
    }

    public SolicitacaoFacade getEjbSolicitacao() {
        return ejbSolicitacao;
    }

    public void setEjbSolicitacao(SolicitacaoFacade ejbSolicitacao) {
        this.ejbSolicitacao = ejbSolicitacao;
    }

    public StatusFacade getEjbStatus() {
        return ejbStatus;
    }

    public void setEjbStatus(StatusFacade ejbStatus) {
        this.ejbStatus = ejbStatus;
    }

    public ConexaoFacade getEjbConexao() {
        return ejbConexao;
    }

    public void setEjbConexao(ConexaoFacade ejbConexao) {
        this.ejbConexao = ejbConexao;
    }

    public AtividadeFacade getEjbAtividade() {
        return ejbAtividade;
    }

    public void setEjbAtividade(AtividadeFacade ejbAtividade) {
        this.ejbAtividade = ejbAtividade;
    }

    public DeferimentoFacade getEjbDeferimento() {
        return ejbDeferimento;
    }

    public void setEjbDeferimento(DeferimentoFacade ejbDeferimento) {
        this.ejbDeferimento = ejbDeferimento;
    }
    
    public List<Solicitacao> getSolicitacoesPendentes(){
        Status aguardando = getEjbStatus().findByDescricao("Aguardando");
        List<Solicitacao> todas = getEjbSolicitacao().findAll();
        List<Solicitacao> pendentes = new ArrayList<>();
        
        for(Solicitacao s : todas){
            if(s.getAtividade().getStatus().equals(aguardando))
                pendentes.add(s);
        }
        return pendentes;
    }
    
    public int qtdSolicitacoes(){
        List<Solicitacao> pendentes = getSolicitacoesPendentes();
        
        if(!pendentes.isEmpty())
            return pendentes.size();
        
        return 0;
    }
    
    public void setSelectedData(){
        String idKeyString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idAtividade");
        if(idKeyString!=null){
            idKey = Integer.parseInt(idKeyString);
            this.atividade = getEjbAtividade().findById(idKey);
        }
    }
    
    public String deferir(){
        Status deferido = getEjbStatus().findByDescricao("Deferido");
        return Executar(deferido);
    }
    
    public String indeferir(){
        Status indeferido = getEjbStatus().findByDescricao("Indeferido");
        return Executar(indeferido);
    }
    
    public String cancelar(Atividade atv){
        atividade = atv;
        Status indeferido = getEjbStatus().findByDescricao("Cancelada");
        return Executar(indeferido);
    }
    
    public String Executar(Status status){
        try {
            atividade.setStatus(status);
            if(status.getId() != 5)
            {
                atividade.setLocal(local);
                guardarDeferimento();
            }
            getEjbAtividade().edit(atividade);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/BundleSolicitacao").getString("ActionSuccess"));
            if(status.getId() != 5)
                return "list_solicitacao.xhtml";
            else
                return "/public/home";
        } catch (Exception ex){
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/BundleSolicitacao").getString("ActionError"));
        }
        return null;
    }
    
    public void guardarDeferimento(){
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController log = (LoginController) context.getExternalContext().getSessionMap().get("loginController");
        Usuario user = log.getUsuario();
        deferimento = new Deferimento();
        deferimento.setSolicitacao(getEjbSolicitacao().findByAtividade(atividade));
        deferimento.setDtHr(new Date());
        deferimento.setUsuario(user);
        getEjbDeferimento().create(deferimento);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
/*
import dashboard.entities.TbEquipe;
import dashboard.entities.TbPerfil;
import dashboard.entities.TbPessoa;
import dashboard.entities.TbUnidade;
import dashboard.sessions.TbEquipeFacade;
import dashboard.sessions.TbPerfilFacade;
import dashboard.sessions.TbPessoaFacade;
import dashboard.sessions.TbUnidadeFacade;*/
import dashboard.entities.Processos;
import dashboard.entities.TbEquipe;
import dashboard.entities.TbPerfil;
import dashboard.entities.TbPessoa;
import dashboard.entities.TbUnidade;
import dashboard.sessions.ProcessosFacade;
import dashboard.sessions.TbEquipeFacade;
import dashboard.sessions.TbPerfilFacade;
import dashboard.sessions.TbPessoaFacade;
import dashboard.sessions.TbUnidadeFacade;
import entities.Aluno;
import entities.Atividade;
import entities.Avaliacoes;
import entities.Cbo;
import entities.Curso;
import entities.ObjetoAprendizagem;
import entities.ParticipacaoLocal;
import entities.ParticipacaoPessoa;
import entities.Pessoa;
import entities.Presenca;
import entities.PresencaKeys;
import entities.ProfGeral;
import entities.ProfSaude;
import entities.Tipo;
import entities.TipoObjetoApr;
import entities.Ubs;
import integra.EstabelecimentoSaude;
import integra.Integra;
import integra.Profissional;
import integra.Teleconsultoria;
import integra.TeleeducacaoAtividade;
import integra.TeleeducacaoCurso;
import integra.TeleeducacaoObjetoAprendizagem;
import integra.enumeracoes.CanalAcesso;
import integra.enumeracoes.GrauSatisfacao;
import integra.enumeracoes.ResolucaoDuvidaTeleconsultoria;
import integra.enumeracoes.Sexo;
import integra.enumeracoes.TipoAtividade;
import integra.enumeracoes.TipoDeDados;
import static integra.enumeracoes.TipoDeDados.JSON;
import integra.enumeracoes.TipoObjetoAprendizagem;
import integra.enumeracoes.TipoTeleconsultoria;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import jsf.util.DateUtils;

import jsf.util.ParticipacaoPessoaSmart;
import jsf.util.SmartDate;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import sessions.AlunoFacade;
import sessions.AtividadeFacade;
import sessions.AvaliacoesFacade;
import sessions.CboFacade;
import sessions.CursoFacade;
import sessions.ObjetoAprendizagemFacade;
import sessions.ParticipacaoLocalFacade;
import sessions.ParticipacaoPessoaFacade;
import sessions.PessoaFacade;
import sessions.PresencaFacade;
import sessions.PresencaKeysFacade;
import sessions.ProfGeralFacade;
import sessions.ProfSaudeFacade;
import sessions.UbsFacade;

/**
 *
 * @author nlima.huufma
 */
@ManagedBean(name = "smartController")
@SessionScoped
public class SmartController implements Serializable {
    
    SmartDate meses = new SmartDate();
    int year, month;
    String monthName;
    int qtdAtv, qtdPart;
    String url = "http://smarthomologacao.navi.ifrn.edu.br";
    String[] urls = {"http://smarthomologacao.navi.ifrn.edu.br", "http://smart.telessaude.ufrn.br"};
    CnesController cnesBean;
    
    int participacoesValidas, participacoesInvalidas;
    
    Date dt1, dt2;
    
    boolean isVisibleReportPanel = false;
    
    @EJB
    private AtividadeFacade ejbAtividade;
    
    @EJB
    private PresencaFacade ejbParticipacaoPessoa;
    
    @EJB
    private UbsFacade ejbUbs;
    
    @EJB
    private CboFacade ejbCbo;
    
    @EJB
    private ParticipacaoLocalFacade ejbPartLocal;
    
    @EJB
    private ProfGeralFacade ejbProfGeral;
    
    @EJB
    private ProfSaudeFacade ejbProfSaude;
    
    @EJB
    private PessoaFacade ejbPessoa;
    
    @EJB
    private TbUnidadeFacade ejbUnidade;
    
    @EJB
    private TbPessoaFacade ejbTbPessoa;
    
    @EJB
    private TbPerfilFacade ejbTbPerfil;
    
    @EJB
    private TbEquipeFacade ejbTbEquipe;
    
    @EJB
    private ProcessosFacade ejbProcessos;
    
    @EJB
    private ObjetoAprendizagemFacade ejbObjApr;
    
    @EJB
    private CursoFacade ejbCurso;
    
    @EJB
    private AlunoFacade ejbAluno;
    
    @EJB
    private PresencaKeysFacade ejbKey;
    
    @EJB
    private AvaliacoesFacade ejbAvaliacao;

    public int getParticipacoesValidas() {
        return participacoesValidas;
    }

    public void setParticipacoesValidas(int participacoesValidas) {
        this.participacoesValidas = participacoesValidas;
    }

    public int getParticipacoesInvalidas() {
        return participacoesInvalidas;
    }

    public void setParticipacoesInvalidas(int participacoesInvalidas) {
        this.participacoesInvalidas = participacoesInvalidas;
    }

    public Date getDt1() {
        return dt1;
    }

    public void setDt1(Date dt1) {
        this.dt1 = dt1;
    }

    public Date getDt2() {
        return dt2;
    }

    public void setDt2(Date dt2) {
        this.dt2 = dt2;
    }

    

    public boolean isIsVisibleReportPanel() {
        return isVisibleReportPanel;
    }

    public void setIsVisibleReportPanel(boolean isVisibleReportPanel) {
        this.isVisibleReportPanel = isVisibleReportPanel;
    }

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    public AtividadeFacade getEjbAtividade() {
        return ejbAtividade;
    }

    public void setEjbAtividade(AtividadeFacade ejbAtividade) {
        this.ejbAtividade = ejbAtividade;
    }

   
    public SmartDate getMeses() {
        return meses;
    }

    public void setMeses(SmartDate meses) {
        this.meses = meses;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }
    
    public void setDefaultDates() throws ParseException
    {
        Date today = new Date();
        int dateNumbers[] = DateUtils.getSeparatedDateNumbers(today);
        
        dt2 = today;
        
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
                
        String dateStr =  dateNumbers[2]+"-"+((dateNumbers[1]<10)?"0":"") + dateNumbers[1]+"-01";
        dt1 = dateFormater.parse(dateStr);
    }
    
    public String sendNtsMAData() throws JSONException
    {
        //System.out.println("url do envio "+url);
        if(!url.equals("http://smarthomologacao.navi.ifrn.edu.br") && !url.equals("http://smart.telessaude.ufrn.br"))
        {
            FacesContext.getCurrentInstance().addMessage("smart-data:returnMsg", new FacesMessage("URL para envio dos dados é inválida<br/>Insira a url do SMART produção ou homologação."));
            return null;
        }
        Integra integra = new Integra("4d911acb351b8f3477b00c61b135fda8ad9ebef1");
        month = meses.getMonthNumber(monthName)+1;
        String parameter = year +"-"+((month < 10) ? "0" : "")+month;
        String mes = ((month < 10) ? "0" : "")+month +""+year;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        cnesBean = (CnesController) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "cnesController");
        
        if(sendEstabelecimentos(integra, url, mes, parameter))
            if(sendProfissionaisDeSaude(integra, url, mes, parameter))
            {
                sendTeleconsultorias(integra, url, mes, parameter);
                sendAtividadeTeleducacao(integra, url, mes, parameter);
                sendObjetosAprendizagem(integra, url, mes, parameter);
                sendCursos(integra, url, mes, parameter);
            }
        
        return null;
    }
    
    public boolean sendObjetosAprendizagem(Integra integra, String url, String month, String parameter) throws JSONException
    {
        List<ObjetoAprendizagem> objetos = ejbObjApr.findAll();
        TeleeducacaoObjetoAprendizagem objetosApr = new TeleeducacaoObjetoAprendizagem("0000000", month);
        
        for(ObjetoAprendizagem o :  objetos)
        {
            
            objetosApr.AdicionarObjetoAprendizagem(""+o.getId(), dateTimeAtividade(o.getData(), o.getData()),
                                                    (o.getDispTelessaude() != 0), (o.getDispAres() != 0 ), (o.getDispAvasus() != 0),
                                                    (o.getDispRedeSocial()!= 0), (o.getDispOutros()!= 0), getTipoObjApr(o.getTipo()),
                                                    o.getTemaDecs(), o.getUrlAres(), o.getNumAcesso());
        }
        
        //System.out.println(integra.Serializar(TipoDeDados.JSON, objetosApr));
        String response4;
        try
        {
            response4 = integra.enviarDados(url + "/api/v2/objetos-aprendizagem/?format=json", objetosApr);
            //System.out.println("OK obj:"+response4);
            smartMsg(response4, "objetos", true);
        }
        catch (Exception e)
        {
//			e.printStackTrace();
            //System.out.println("ERROR obj:"+e.getMessage());
            smartMsg(e.getMessage(), "objetos", false);
            return false;
        }
        return true;
    }
    
    public boolean sendCursos(Integra integra, String url, String month, String parameter) throws JSONException
    {
        List<Curso> cursosNts = ejbCurso.findAll();
        TeleeducacaoCurso cursos = new TeleeducacaoCurso("0000000", month);
        
        for(Curso c :  cursosNts)
        {
            ArrayList<String> formados = new ArrayList<String>();
            ArrayList<String> reprovados = new ArrayList<String>();
            ArrayList<String> evadidos = new ArrayList<String>();
            ArrayList<String> matriculados = new ArrayList<String>();
                    
            List<Aluno> alunos = ejbAluno.findByCurso(c);
            
            for(Aluno a : alunos)
            {
                if(a.getStatus().getId() == 2)
                    formados.add(a.getCpf());
                else if(a.getStatus().getId() == 3)
                    evadidos.add(a.getCpf());
                else if(a.getStatus().getId() == 4)
                    reprovados.add(a.getCpf());
                
                matriculados.add(a.getCpf());
            }
            
            String[] formadosArray = formados.toArray(new String[formados.size()]);
            String[] reprovadosArray = reprovados.toArray(new String[formados.size()]);
            String[] evadidosArray = evadidos.toArray(new String[formados.size()]);
            String[] matriculadosArray = matriculados.toArray(new String[matriculados.size()]);
            
            cursos.AdicionarCurso(c.getId()+"",
				 dateTimeAtividade(c.getDataInicio(), c.getDataInicio()),
                                 dateTimeAtividade(c.getDataTermino(), c.getDataTermino()),
                                 c.getVagasOfertadas()+"", c.getCodDecs(),
				 c.getCargaHoraria()+"",new String[]{}, new String[]{}, new String[]{}, new String[]{});
            
            cursos.AdicionarCurso(c.getId()+"",
				 dateTimeAtividade(c.getDataInicio(), c.getDataInicio()),
                                 dateTimeAtividade(c.getDataTermino(), c.getDataTermino()),
                                 c.getVagasOfertadas()+"", c.getCodDecs(),
				 c.getCargaHoraria()+"",matriculadosArray, new String[]{}, new String[]{}, new String[]{});
            
            cursos.AdicionarCurso(c.getId()+"",
				 dateTimeAtividade(c.getDataInicio(), c.getDataInicio()),
                                 dateTimeAtividade(c.getDataTermino(), c.getDataTermino()),
                                 c.getVagasOfertadas()+"", c.getCodDecs(),
				 c.getCargaHoraria()+"",new String[]{}, formadosArray, new String[]{}, new String[]{});
            
            cursos.AdicionarCurso(c.getId()+"",
				 dateTimeAtividade(c.getDataInicio(), c.getDataInicio()),
                                 dateTimeAtividade(c.getDataTermino(), c.getDataTermino()),
                                 c.getVagasOfertadas()+"", c.getCodDecs(),
				 c.getCargaHoraria()+"",new String[]{}, new String[]{}, evadidosArray, new String[]{});
            
            cursos.AdicionarCurso(c.getId()+"",
				 dateTimeAtividade(c.getDataInicio(), c.getDataInicio()),
                                 dateTimeAtividade(c.getDataTermino(), c.getDataTermino()),
                                 c.getVagasOfertadas()+"", c.getCodDecs(),
				 c.getCargaHoraria()+"",new String[]{}, new String[]{}, new String[]{}, reprovadosArray);
        }
        
        
        //System.out.println(integra.Serializar(TipoDeDados.JSON, cursos));
        String response4;
        try
        {
            response4 = integra.enviarDados(url + "/api/v2/cursos-teleeducacao/?format=json", cursos);
            //System.out.println("OK obj:"+response4);
            smartMsg(response4, "cursos", true);
        }
        catch (Exception e)
        {
//			e.printStackTrace();
            //System.out.println("ERROR obj:"+e.getMessage());
            smartMsg(e.getMessage(), "cursos", false);
            return false;
        }
        return true;
    }
    
    public boolean sendEstabelecimentos(Integra integra, String url, String month, String parameter) throws JSONException
    {
        List<TbUnidade> unidades = ejbUnidade.findAll();
        EstabelecimentoSaude estabelecimentos = new EstabelecimentoSaude("0000000", month);
        
        for(TbUnidade u :  unidades)
        {
            Ubs ubs = new Ubs();
            ubs.setCnes(u.getCnes());
            boolean usesTeleducacao = false;
            if(ejbPartLocal.findByUbs(ubs) != null)
                usesTeleducacao = true;
            estabelecimentos.AdicionarEstabelecimento(convertCodigoToString(u.getCnes(), 7), true, usesTeleducacao, false);
        }
        
        //System.out.println(integra.Serializar(TipoDeDados.JSON, estabelecimentos));
        String response4;
        try
        {
            response4 = integra.enviarDados(url + "/api/v2/dados-estabelecimentos-saude/?format=json", estabelecimentos);
            //System.out.println("OK estabe:"+response4);
            smartMsg(response4, "estabelecimentos", true);
        }
        catch (Exception e)
        {
//			e.printStackTrace();
           // System.out.println("ERROR estab:"+e.getMessage());
            smartMsg(e.getMessage(), "estabelecimentos", false);
            return false;
        }
        return true;
    }
    
    public boolean sendProfissionaisDeSaude(Integra integra, String url, String month, String parameter) throws JSONException
    {
        Profissional prof = new Profissional("0000000", month);
        
        List<TbPessoa> pessoas = ejbTbPessoa.findAll(); int i = 0;
        for(TbPessoa pe : pessoas)
        {
            String nome = "", cns = "", cpf = "", cbo = "", cnes = "", ine = "";
            
            nome = pe.getNome();
            cpf = pe.getCpf();
            cns = pe.getCns();
            
            TbPerfil perfil = ejbTbPerfil.findByPessoa(pe.getCodigo());
            if(perfil != null)
            {
                cbo = perfil.getCbo();
                TbEquipe eq = ejbTbEquipe.findByCodigo(perfil.getEquipe());
                if(eq != null)
                {
                    ine = convertCodigoToString(eq.getIne(), 10);
                    if(ine.equals("0000000000") || ine.equals("0006736673"))
                        ine = null;
                    TbUnidade unidade = ejbUnidade.findByCodigo(eq.getUnidade());
                    if(unidade != null)
                        cnes = convertCodigoToString(unidade.getCnes(), 7);
                }
            }
            Sexo sexo = (pe.getSexo() == 0) ? Sexo.MASCULINO : Sexo.FEMININO;
            
            if(!nome.isEmpty() && !cpf.isEmpty() && !cbo.isEmpty() && !cnes.isEmpty())
            {
                prof.CadastrarProfissional(cns, cpf, nome, cnes, cbo, ine, "01", sexo);
            }
        }
        //System.out.println("dados de profissionais: "+integra.Serializar(TipoDeDados.JSON, prof));
        String response4;
        try
        {
            response4 = integra.enviarDados(url + "/api/v2/profissionais-saude/?format=json", prof);
            //System.out.println("OK prof:"+response4);
            smartMsg(response4, "profissionais", true);
        }
        catch (Exception e)
        {
            //System.out.println("ERROR prof:"+e.getMessage());
            smartMsg(e.getMessage(), "profissionais", false);
            return false;
        }
        return true;
    }
    
    public boolean sendTeleconsultorias(Integra integra, String url, String month, String parameter) throws JSONException
    {
        List<Processos> processos = ejbProcessos.findByMonth(parameter);
        //System.out.println("Solicitacoes " + processos.size() + " " + parameter);
        Teleconsultoria teleconsultorias = new Teleconsultoria("0000000", month);

        for(Processos processo : processos)
        {
            String dataEnvio = processo.getSoldtenvio() + " " + processo.getSolhoranevio();
            TipoTeleconsultoria tipoTel = (processo.getSoltipocod() == 1) ? TipoTeleconsultoria.SINCRONA : TipoTeleconsultoria.ASSINCRONA;
            String cpf = processo.getStecpf();
            String cbo = convertCodigoToString(processo.getStecbocod(), 6);
            String cnes = convertCodigoToString(processo.getStecnesunid(), 7);
            String codIne = convertCodigoToString(processo.getSteine(), 10);
            if(codIne.equals("0000000000"))
                codIne = null;
            
            List<String> codsCid = new ArrayList();
            String regcid1 = processo.getRegcid1cod();
            String regcid2 = processo.getRegcid2cod();
            
            if(!regcid1.isEmpty())
                codsCid.add(regcid1);
            if(!regcid2.isEmpty())
                codsCid.add(regcid2);
            String[] codigosCID = new String[codsCid.size()];
            for(int i = 0; i < codsCid.size(); i++)
                codigosCID[0] = codsCid.get(i);
            
            
            List<String> codsCiap = new ArrayList();
            String regciap1 = processo.getRegciap1cod();
            String regciap2 = processo.getRegciap2cod();
            String regciap3 = processo.getRegciap3cod();
            
            if(!regciap1.isEmpty())
                codsCiap.add(regciap1);
            if(!regciap2.isEmpty())
                codsCiap.add(regciap2);
            if(!regciap3.isEmpty())
                codsCiap.add(regciap3);
            
            String[] codigosCIAP = new String[codsCiap.size()];
            for(int i = 0; i < codsCiap.size(); i++)
                codigosCIAP[0] = codsCiap.get(i);
            

            
            String dataResp = processo.getSoldtenvresp() + " " + processo.getSolhrenvresp();
            boolean intensaoEncaminha = (processo.getSolintencacod() == 1) ? true : false;
            boolean evitouEncaminhamento = (processo.getSolevitrefcod()== 1) ? true : false;
            
            GrauSatisfacao grauSats = grauSatisfacao(processo.getSolsatisfcod());
            ResolucaoDuvidaTeleconsultoria reslucaoDuv = resolucaoDuvida(processo.getSolclassifcod());
            boolean potencial = (processo.getSolsofcod()== 1) ? true : false;
            teleconsultorias.AdicionarSolicitacao(dataEnvio, tipoTel, CanalAcesso.INTERNET, cpf, cbo, cnes,
                                                  codIne, "01", codigosCID, codigosCIAP, dataResp, evitouEncaminhamento,
                                                  intensaoEncaminha, grauSats, reslucaoDuv, potencial);
        }
       // System.out.println(integra.Serializar(TipoDeDados.JSON, teleconsultorias));

        String response;
        try
        {
            response = integra.enviarDados(url + "/api/v2/teleconsultorias/?format=json", teleconsultorias);
            //System.out.println("OK:"+response);
            smartMsg(response, "teleconsultorias", true);
        }
        catch (Exception e)
        {
            System.out.println("ERROR:"+e.getMessage());
            if(e.getMessage().contains("message") && e.getMessage().contains("successful\":false"))
            {
                JSONObject obj = new JSONObject(e.getMessage());
                JSONArray json = (JSONArray)obj.get("message");
                for(int i = 0; i<json.length(); i++)
                {
                    //System.out.println("array value " + (String)json.get(i));
                    FacesContext.getCurrentInstance().addMessage("smart-data:returnMsg", new FacesMessage("Erro:  " + (String)json.get(i)));
                    if(((String)json.get(i)).contains("vinculo"))
                    {
                        String[] errorMessage = json.get(i).toString().split(":");
                        String[] vinculos = errorMessage[1].split(";");
                        if(sendProfissionais(vinculos, integra, month))
                        {
                            try{
                                response = integra.enviarDados(url + "/api/v2/teleconsultorias/?format=json", teleconsultorias);
                               // System.out.println("OK:"+response);
                                smartMsg(response, "tele-educação", true);
                            }
                            catch(Exception e1)
                            {
                                //System.out.println("ERROR:"+e1.getMessage());
                                smartMsg(e1.getMessage(), "tele-educação", false);
                            }
                        }
                    }
                }
                //smartMsg(e.getMessage(), "tele-educação", false);
                smartMsg(e.getMessage(), "teleconsultorias", false);
            }
            else
                //smartMsg(e.getMessage(), "tele-educação", false);
                smartMsg(e.getMessage(), "teleconsultorias", false);
            
        }
        return true;
    }
    
    public String sendAtividadeTeleducacao(Integra integra, String url, String month, String parameter) throws JSONException
    {
        qtdAtv = 0;
        qtdPart = 0;
        TeleeducacaoAtividade atividades = new TeleeducacaoAtividade("0000000", month);
        
        //System.out.println(parameter);
        for(Atividade atv : ejbAtividade.findByMonth(parameter))
        {            
            //System.out.println("atv retornada " + atv.getCodDecs());
            List<Presenca> participacoes = ejbParticipacaoPessoa.findByAtividade(atv);
            if(!participacoes.isEmpty() && (atv.getCodDecs() != null && !atv.getCodDecs().equals("")))
            {
                
                List<ParticipacaoPessoaSmart> partsSmart = new ArrayList();                
                for(Presenca pp : ejbParticipacaoPessoa.findByAtividade(atv))
                {
                    
                    Pessoa pessoa = pp.getPessoa();
                    cnesBean.atualizar(pessoa);
                    String cbo = null;
                    ProfGeral prof = ejbProfGeral.getByPessoa(pessoa);
                    if(prof != null)
                    {
                        cbo = prof.getCbo().getCodigo();
                        if(cbo.equals("000000"))
                            cbo = null;
                    }
                    else
                        continue;
                    //2151
                    String cnes = null;
                    ProfSaude profSaude = ejbProfSaude.getByPessoa(pessoa);
                    
                    //ParticipacaoLocal partLocal = ejbPartLocal.findByParticipacao(pp);
                    Ubs ubs;
                    if(profSaude!= null){
                        ubs = profSaude.getUbs();
                        if(ubs != null)
                        {
                            cnes = ubs.getCnes().toString();
                            if(cnes != null)
                            {
                                int cnesCod = Integer.parseInt(cnes);
                                cnes = convertCodigoToString(cnesCod, 7);
                            }
                        }
                    }
                    
                    if(cbo != null && cnes != null && !cbo.isEmpty() && !cnes.isEmpty())
                    {
                        partsSmart.add(new ParticipacaoPessoaSmart(atv, pessoa, cnes, cbo));
                    }
                }    
                
                if(!partsSmart.isEmpty())
                {
                    atividades.AdicionarAtividade(atv.getId().toString(), 
                        dateTimeAtividade(atv.getDt(), atv.getHrInicio()), durationAtividade(atv.getHrInicio(),
                        atv.getHrTermino()), getTipo(atv.getTipo()), atv.getCodDecs().trim());
                    qtdAtv++;
                    for(ParticipacaoPessoaSmart ps : partsSmart)
                    {
                        Presenca presenca = ejbParticipacaoPessoa.findByComposedKey(ps.getPessoa(), ps.getAtividade());
                        Avaliacoes aval = null;
                        aval = ejbAvaliacao.findByComposedKey(presenca, 5);
                        
                        GrauSatisfacao grauSats = null;
                        if(aval != null)
                            grauSats =  grauSatisfacao(Integer.parseInt(aval.getResposta()));
                        else
                            grauSats = GrauSatisfacao.NAO_INFORMADO;
                        
                        atividades.AdicionarParticipacaoAtividade(atv.getId().toString(),
                                    dateTimeAtividade(atv.getDt(), atv.getHrInicio()),
                                    ps.getPessoa().getCpf(),                       
                                    ps.getCbo(), ps.getCnes() , 
                                    null, grauSats);
                        qtdPart++;
                    }
                }
            }
        }
        
        //System.out.println(integra.Serializar(TipoDeDados.JSON, atividades));
        String response;
        //System.out.println("Quantidade de participantes " + qtdPart);
        if(qtdAtv==0 || qtdPart==0)
        {
            FacesContext.getCurrentInstance().addMessage("smart-data:returnMsg", new FacesMessage("<br/><br/>Não foram encontrados atividades de teleducação válidas para envio na data indicada!"));
            return null;
        }
        
        try
        {
            if(integra == null)
                System.out.println("deu merda");
            response = integra.enviarDados(url + "/api/v2/atividades-teleeducacao/?format=json", atividades);
            //System.out.println("OK:"+response);
            smartMsg(response, "tele-educação", true);
        }
        catch (Exception e)
        {
            //System.out.println("ERROR:"+e.getMessage());
            if(e.getMessage().contains("message") && e.getMessage().contains("successful\":false"))
            {
                JSONObject obj = new JSONObject(e.getMessage());
                JSONArray json = (JSONArray)obj.get("message");
                for(int i = 0; i<json.length(); i++)
                {
                    //System.out.println("array value " + (String)json.get(i));
                    FacesContext.getCurrentInstance().addMessage("smart-data:returnMsg", new FacesMessage("Erro:  " + (String)json.get(i)));
                    if(((String)json.get(i)).contains("vinculo"))
                    {
                        String[] errorMessage = json.get(i).toString().split(":");
                        String[] vinculos = errorMessage[1].split(";");
                        if(sendProfissionais(vinculos, integra, month))
                        {
                            try{
                                response = integra.enviarDados(url + "/api/v2/atividades-teleeducacao/?format=json", atividades);
                                //System.out.println("OK:"+response);
                                smartMsg(response, "tele-educação", true);
                            }
                            catch(Exception e1)
                            {
                                //System.out.println("ERROR:"+e1.getMessage());
                                smartMsg(e1.getMessage(), "tele-educação", false);
                            }
                        }
                    }
                }
                //smartMsg(e.getMessage(), "tele-educação", false);
            }
            else
                smartMsg(e.getMessage(), "tele-educação", false);
            
        }
        return null;
    }
   
    
    
    public boolean sendProfissionais(String[] vinculos, Integra integra, String mes) throws JSONException
    {
        //String url = "http://smarthomologacao.navi.ifrn.edu.br";
        Profissional prof = new Profissional("0000000", mes);
        boolean successful = true;
        
        for(String vinculo : vinculos)
        {
            //System.out.println("Vinculo novo: " + vinculo);
            vinculo = vinculo.trim();
            if(vinculo.split("/").length == 3)
            {
                String cpf = vinculo.split("/")[0];
                String cbo = vinculo.split("/")[1];
                String cnes = vinculo.split("/")[2];
                String nome = "";
                Sexo sexo = null;
                
                Pessoa p = ejbPessoa.getByCPF(cpf);
                TbPessoa p2 = null;
                if(p==null)
                {
                    p2 = ejbTbPessoa.findByCpf(cpf);
                    if(p2 !=  null)
                    {
                        nome = stripAccents(p2.getNome().trim());
                        sexo = (p2.getSexo() == 0) ? Sexo.MASCULINO : Sexo.FEMININO;
                    }
                }
                else
                {
                    nome = stripAccents(p.getNome().trim() + " " + p.getSobrenome().trim());
                    sexo = (p.getSexo().equals("M")) ? Sexo.MASCULINO : Sexo.FEMININO;
                }
                //TbPessoa = ejbTbPessoa.
                if(p!=null || p2!=null)
                {
                    prof.CadastrarProfissional(null, cpf, nome, cnes, cbo, null, "01", sexo);
                }
            }
        }
        String response5;
        //System.out.println("dados de profissionais: "+integra.Serializar(TipoDeDados.JSON, prof));
        
        try
        {
            response5 = integra.enviarDados(url + "/api/v2/profissionais-saude/?format=json", prof);
            //System.out.println("OK:"+response5);
            smartMsg(response5, "de novos vínculos ", true);
        }
        catch (Exception e)
        {
            //System.out.println("ERROR:"+e.getMessage());
            smartMsg(e.getMessage(), "de novos vínculos ", false);
            successful = false;
        }
        
        return successful;
    }
    
    public static String stripAccents(String s) 
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
    
    private String dateTimeAtividade(Date dt, Date init)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int year = cal.get(Calendar.YEAR);
        int month = 1 + cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(init);

        int hour = cal.get(Calendar.HOUR_OF_DAY );
        int min = cal.get(Calendar.MINUTE);
        return day+"/"+month+"/"+year+" "+hour+":"+((min < 10)? "0" : "")+min+":00";
    }
    
    private String durationAtividade(Date init, Date end)
    {
        long diff = end.getTime() - init.getTime();//as given
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff); 
        
        return minutes + "";
    }
    
    private TipoAtividade getTipo(Tipo atvTipo)
    {
        
        switch (atvTipo.getTipoNt4().getId()) 
        {
            case 1:
                return TipoAtividade.CURSO;
            case 2:
                return TipoAtividade.WEBAULAS_PALESTRAS;
            case 3:
                return TipoAtividade.WEBSEMINARIOS;
            case 4:
                return TipoAtividade.FORUM;
            case 5:
                return TipoAtividade.REUNIAO;
            default:
                return TipoAtividade.WEBAULAS_PALESTRAS;
        }
    }
    
    private TipoObjetoAprendizagem getTipoObjApr(TipoObjetoApr objTipo)
    {
        
        switch (objTipo.getId()) 
        {
            case 1:
                return TipoObjetoAprendizagem.TEXTO;
            case 2:
                return TipoObjetoAprendizagem.MULTIMIDIA;
            case 3:
                return TipoObjetoAprendizagem.IMAGENS;
            case 4:
                return TipoObjetoAprendizagem.APLICATIVOS;
            case 5:
                return TipoObjetoAprendizagem.JOGOS_EDUCACIONAIS;
            case 6:
                return TipoObjetoAprendizagem.OUTROS;
            default:
                return TipoObjetoAprendizagem.MULTIMIDIA;
        }
    }
    
    public GrauSatisfacao grauSatisfacao(int cod)
    {
        switch (cod) 
        {
            case 1:
                return GrauSatisfacao.MUITO_SATISFEITO;
            case 2:
                return GrauSatisfacao.SATISFEITO;
            case 3:
                return GrauSatisfacao.INDIFERENTE;
            case 4:
                return GrauSatisfacao.INSATISFEITO;
            case 5:
                return GrauSatisfacao.MUITO_INSATISFEITO;
            default:
                return GrauSatisfacao.NAO_INFORMADO;
        }
    }
    
    public ResolucaoDuvidaTeleconsultoria resolucaoDuvida(int cod)
    {
        switch (cod) 
        {
            case 1:
                return ResolucaoDuvidaTeleconsultoria.ATENDEU_TOTALMENTE;
            case 2:
                return ResolucaoDuvidaTeleconsultoria.ATENDEU_PARCIALMENTE;
            case 3:
                return ResolucaoDuvidaTeleconsultoria.NAO_ATENDEU;
            default:
                return ResolucaoDuvidaTeleconsultoria.NAO_INFORMADO;
        }
    }
    
    String convertCodigoToString(int codigo, int qtdDigits)
    {
        String codStr = Integer.toString(codigo);
        String zeros="";
        for(int i = 0; i<qtdDigits-codStr.length(); i++)
        {
            zeros +="0";
        }
        return zeros + codStr;
    }
    
    private void smartMsg(String json, String tipoEnvio, boolean isSuccess) throws JSONException
    {
        //System.out.println("String json: " + json);
        if(json.contains("message"))
        {
            JSONObject obj = new JSONObject(json);
            //JSONArray array = (JSONArray)obj.get("message");
            String smartMsg = (String)obj.get("message").toString(); //array.get(0).toString();
           // System.out.println("JSON Message " + smartMsg);
            String lineBreak = "<br/><br/>";
            if(tipoEnvio.equals("estabelecimentos"))
                lineBreak = "";
            if(isSuccess)
            {                
                FacesContext.getCurrentInstance().addMessage("smart-data:returnMsg", new FacesMessage(lineBreak+"Dados de "+tipoEnvio+" enviados com sucesso!\n O SMART retornou a seguinte mensagem: " + smartMsg));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("smart-data:returnMsg", new FacesMessage(lineBreak+"Falha no envio de dados de "+tipoEnvio+"!\n O SMART retornou a seguinte mensagem: " + smartMsg));
            }
        }
        else
            FacesContext.getCurrentInstance().addMessage("smart-data:returnMsg", new FacesMessage("Erro interno. Mensagem: " + json));
    }
    
    public void setReportPanel(boolean status)
    {
        isVisibleReportPanel = status;
    }
    
    public void getReportValidData() throws JSONException, FileNotFoundException, UnsupportedEncodingException
    {
        Map partsMap = new HashMap();
        List<ParticipacaoPessoaSmart> partsValidReport = new ArrayList();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        cnesBean = (CnesController) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "cnesController");
        partsValidReport = new ArrayList();
        month = meses.getMonthNumber(monthName)+1;
        String parameter = year +"-"+((month < 10) ? "0" : "")+month;
        String mes = ((month < 10) ? "0" : "")+month +""+year;
        JSONArray jSONArray = new JSONArray();
        isVisibleReportPanel = true;
        
        String dt1Sql = DateUtils.dateFormatMysql(dt1);
        String dt2Sql = DateUtils.dateFormatMysql(dt2);
        List<Atividade> atvs = ejbAtividade.getFilteredAtividades(dt1, dt2);
        
        participacoesInvalidas = participacoesValidas = 0;
        
        for(Atividade atv : atvs)
        {  
            List<Presenca> participacoes = ejbParticipacaoPessoa.findByAtividade(atv);
            if(!participacoes.isEmpty() && (atv.getCodDecs() != null && !atv.getCodDecs().equals("")))
            {            
                for(Presenca pp : ejbParticipacaoPessoa.findByAtividade(atv))
                {
                    Pessoa pessoa = pp.getPessoa();
                    //cnesBean.atualizar(pessoa);
                    String cbo = null;
                    ProfGeral prof = ejbProfGeral.getByPessoa(pessoa);
                    if(prof != null)
                    {
                        cbo = prof.getCbo().getCodigo();
                        if(cbo.equals("000000"))
                            cbo = null;
                    }
                    else
                        continue;
                    //2151
                    String cnes = null;
                    ProfSaude profSaude = ejbProfSaude.getByPessoa(pessoa);
                    
                    //ParticipacaoLocal partLocal = ejbPartLocal.findByParticipacao(pp);
                    Ubs ubs;
                    if(profSaude!= null){
                        ubs = profSaude.getUbs();
                        if(ubs != null)
                        {
                            cnes = ubs.getCnes().toString();
                            if(cnes != null)
                            {
                                int cnesCod = Integer.parseInt(cnes);
                                cnes = convertCodigoToString(cnesCod, 7);
                            }
                        }
                    }
                    
                    String key = pessoa.getCpf();
                    
                    if(partsMap.containsKey(key))
                    {
                        partsMap.put(key, (int)partsMap.get(key) + 1);
                    }
                    else
                    {
                        partsMap.put(key, 1);
                        ParticipacaoPessoaSmart partPessoa = new ParticipacaoPessoaSmart();
                        partPessoa.setPessoa(pessoa);
                        partPessoa.setCbo(cbo);
                        if(cbo != null && cnes != null && !cbo.isEmpty() && !cnes.isEmpty())
                        {
                            partPessoa.setStatus(true);
                        }
                        else{
                            partPessoa.setStatus(false);
                        }
                        partsValidReport.add(partPessoa);
                    }
                    
                    if(cbo != null && cnes != null && !cbo.isEmpty() && !cnes.isEmpty())
                        participacoesValidas++;
                    else
                        participacoesInvalidas++;
                }    
            }
        }
        
        for(ParticipacaoPessoaSmart p : partsValidReport)
        {
            Pessoa pessoa = p.getPessoa();
            JSONObject j = new JSONObject();
            j.put("fullname", pessoa.getNome() + " "+pessoa.getSobrenome());
            j.put("cpf", pessoa.getCpf());
            
            ProfGeral prof = ejbProfGeral.getByPessoa(pessoa);
            if(prof != null)
            {
                String cbo = prof.getCbo().getCodigo();
                j.put("prof", prof.getCbo().getNome());
            }
            else
                j.put("prof", "");

            j.put("qtd", partsMap.get(pessoa.getCpf()));

            if(p.isStatus())
            {      
                
                j.put("status", "Válido");
            }
            else
            {
               
                j.put("status", "Inválido");
            }
            jSONArray.put(j);
        }
        
        String jsonReport = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/data.json"));
        
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReport );
        }
    }
}

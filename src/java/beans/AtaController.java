
package beans;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.ListItem;
import com.jcraft.jsch.SftpException;
import entities.AtaDigitalizada;

import entities.AtaNts;
import entities.Atividade;
import entities.AtividadeTbr;
import entities.Autentificacao;
import entities.Avaliacoes;
import entities.Cbo;
import entities.Certificados;
import entities.Instituicao;
import entities.Palestras;
import entities.ParticipacaoInstituicao;
import entities.ParticipacaoIp;
import entities.ParticipacaoLocal;
import entities.ParticipacaoPessoa;
import entities.Presenca;
import entities.Pessoa;
import entities.PresencaKeys;
import entities.ProfGeral;
import entities.Status;
import entities.Tipo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import jsf.util.AtividadeGenero;
import jsf.util.CertificadoPessoa;
import jsf.util.DateUtils;
import jsf.util.JsfUtil;
import jsf.util.TipoCounter;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.PieChartModel;
import service.Mailler;
import service.RemoteConnection;
import sessions.AtaDigitalizadaFacade;
import sessions.AtaNtsFacade;
import sessions.AtividadeFacade;
import sessions.AtividadeTbrFacade;
import sessions.AutentificacaoFacade;
import sessions.AvaliacoesFacade;
import sessions.CertificadosFacade;
import sessions.InstituicaoFacade;
import sessions.PalestrasFacade;
import sessions.ParticipacaoInstituicaoFacade;
import sessions.ParticipacaoIpFacade;
import sessions.ParticipacaoLocalFacade;
import sessions.ParticipacaoPessoaFacade;
import sessions.PresencaFacade;
import sessions.PresencaKeysFacade;
import sessions.ProfGeralFacade;
import sessions.SolicitacaoFacade;
import sessions.StatusFacade;
import sessions.TipoFacade;



@ManagedBean(name = "ataController")
@SessionScoped
public class AtaController implements Serializable {
        
    private int idKey;
    private Atividade current;
    private AtaNts ata;
    private String mais_info;
    private Integer instituicaoId;
    private List<Instituicao> instituicoesParticipantes = new ArrayList();
    private AtividadeGenero atividadeGenero;
    
    //Atributos auxiliares
    private String atividadeListaTipo = "";
    List<Atividade> atividadesrecuperadas;
    private PieChartModel pieModel;
    Date dt1Period = null;
    Date dt2Period = null;
    private AtividadeGenero[] generos = {new AtividadeGenero(AtividadeGenero.TODAS, "Todas as atividades"), 
                                        new AtividadeGenero(AtividadeGenero.NTS, "NTS"), 
                                        new AtividadeGenero(AtividadeGenero.TBR, "TBR")};
    private int totalAtividadesRecuperadas = 0;
    private int totalParticipantesAtv = 0;
    private int atvGenero;
    private String[] selectedTipos;
    private String jsonReportAtividade;
    private UploadedFile fileAta;
    private UploadedFile fileAtaSlide;
    private int PALESTRANTE = 1;
    private int MODERADOR = 0;
    private int PARTICIPANTE = 2;
    private boolean sendCertParticipantes = false;
    
    private int qtdParticipantes;

    
    private int qtdPolos;
//    List<ParticipacaoPessoa> partPessoaList;
    List<Presenca> presencaList;
    List<Atividade> atividadesModerador; //atividades por moderador
    List<Palestras> atividadesPalestrantes; // atividades por palestrante
    List<CertificadoPessoa> certificadosPessoa; //Lista de certificados por pessoa para o Data Table
    List<CertificadoPessoa> filteredCertificados;
    private Pessoa pessoaCertificado;
    
    Atividade currentBeingSeen;
    List<TipoCounter> atividadesSomadasObj;
    
    
    @EJB
    private AtividadeFacade ejbAtividade;
    @EJB
    private AtaNtsFacade ejbAtaNts;
    @EJB
    private StatusFacade ejbStatus;
    @EJB
    private SolicitacaoFacade ejbSolicitacao;    
    @EJB
    private InstituicaoFacade ejbInstituicao;
    @EJB
    private ParticipacaoInstituicaoFacade ejbParticipacao;
    @EJB
    private TipoFacade ejbTipo;
    @EJB
    private AtividadeTbrFacade ejbAtividadeTbr;
    @EJB
    private CertificadosFacade ejbCerts;
    @EJB
    private PalestrasFacade ejbPalestras;
    @EJB
    private AutentificacaoFacade ejbAutent;
    @EJB
    private AtaDigitalizadaFacade ejbAtaDigitalizada;
    @EJB
    private ParticipacaoPessoaFacade ejbParticipacaoPessoa;
    @EJB
    private PresencaFacade ejbPresenca;
    @EJB
    private ParticipacaoIpFacade ejbParticipacaoIP;
    @EJB
    private ParticipacaoLocalFacade ejbParticipacaoLocal;
    @EJB
    private ProfGeralFacade ejbProfGeral;
    @EJB
    private PresencaKeysFacade ejbPresencakey;
    @EJB
    private AvaliacoesFacade ejbAval;
    
    
    @PostConstruct
    public void init()
    {
        setDt1Period(getEjbAtividade().firstDate());
        setDt2Period(getEjbAtividade().lastDate());
    }

    public boolean isSendCertParticipantes() {
        return sendCertParticipantes;
    }

    public void setSendCertParticipantes(boolean sendCertParticipantes) {
        this.sendCertParticipantes = sendCertParticipantes;
    }

    public UploadedFile getFileAtaSlide() {
        return fileAtaSlide;
    }

    public void setFileAtaSlide(UploadedFile fileAtaSlide) {
        this.fileAtaSlide = fileAtaSlide;
    }

    public List<TipoCounter> getAtividadesSomadasObj() {
        return atividadesSomadasObj;
    }

    public void setAtividadesSomadasObj(List<TipoCounter> atividadesSomadas) {
        this.atividadesSomadasObj = atividadesSomadas;
    }

    public Pessoa getPessoaCertificado() {
        return pessoaCertificado;
    }

    public void setPessoaCertificado(Pessoa pessoaCertificado) {
        this.pessoaCertificado = pessoaCertificado;
    }

    public List<CertificadoPessoa> getFilteredCertificados() {
        return filteredCertificados;
    }

    public void setFilteredCertificados(List<CertificadoPessoa> filteredCertificados) {
        this.filteredCertificados = filteredCertificados;
    }

    public List<CertificadoPessoa> getCertificadosPessoa() {
        return certificadosPessoa;
    }

    public void setCertificadosPessoa(List<CertificadoPessoa> certificadosPessoa) {
        this.certificadosPessoa = certificadosPessoa;
    }
    
//    public List<ParticipacaoPessoa> getPartPessoaList() {
//        return partPessoaList;
//    }
//
//    public void setPartPessoaList(List<ParticipacaoPessoa> partPessoaList) {
//        this.partPessoaList = partPessoaList;
//    }
        
    public List<Presenca> getPresencaList() {
        return presencaList;
    }

    public void setPresencaList(List<Presenca> presencaList) {
        this.presencaList = presencaList;
    }
    
    public List<Atividade> getAtividadesModerador() {
        return atividadesModerador;
    }

    public void setAtividadesModerador(List<Atividade> atividadesModerador) {
        this.atividadesModerador = atividadesModerador;
    }

    public List<Palestras> getAtividadesPalestrantes() {
        return atividadesPalestrantes;
    }

    public void setAtividadesPalestrantes(List<Palestras> atividadesPalestrantes) {
        this.atividadesPalestrantes = atividadesPalestrantes;
    }
    
    public List<Atividade> getAtividadesrecuperadas() {
        return atividadesrecuperadas;
    }

    public void setAtividadesrecuperadas(List<Atividade> atividadesrecuperadas) {
        this.atividadesrecuperadas = atividadesrecuperadas;
    }
    
    public int getPARTICIPANTE() {
        return PARTICIPANTE;
    }

    public void setPARTICIPANTE(int PARTICIPANTE) {
        this.PARTICIPANTE = PARTICIPANTE;
    }
    
    public AtaDigitalizadaFacade getEjbAtaDigitalizada() {
        return ejbAtaDigitalizada;
    }

    public void setEjbAtaDigitalizada(AtaDigitalizadaFacade ejbAtaDigitalizada) {
        this.ejbAtaDigitalizada = ejbAtaDigitalizada;
    }

    public int getPALESTRANTE() {
        return PALESTRANTE;
    }

    public void setPALESTRANTE(int PALESTRANTE) {
        this.PALESTRANTE = PALESTRANTE;
    }

    public int getMODERADOR() {
        return MODERADOR;
    }

    public void setMODERADOR(int MODERADOR) {
        this.MODERADOR = MODERADOR;
    }

    public UploadedFile getFileAta() {
        return fileAta;
    }

    public void setFileAta(UploadedFile fileAta) {
        this.fileAta = fileAta;
    }

    public String getJsonReportAtividade() {
        return jsonReportAtividade;
    }

    public void setJsonReportAtividade(String jsonReportAtividade) {
        this.jsonReportAtividade = jsonReportAtividade;
    }

    public int getTotalAtividadesRecuperadas() {
        return totalAtividadesRecuperadas;
    }

    public void setTotalAtividadesRecuperadas(int totalAtividadesRecuperadas) {
        this.totalAtividadesRecuperadas = totalAtividadesRecuperadas;
    }

    public String[] getSelectedTipos() {
        return selectedTipos;
    }

    public void setSelectedTipos(String[] selectedTipos) {
        this.selectedTipos = selectedTipos;
    }
    
    
    public int getAtvGenero() {
        return atvGenero;
    }

    public void setAtvGenero(int atvGenero) {
        this.atvGenero = atvGenero;
    }

    
    public AtividadeGenero[] getGeneros() {
        return generos;
    }

    public AtividadeGenero getAtividadeGenero() {
        return atividadeGenero;
    }

    public void setAtividadeGenero(AtividadeGenero atividadeGenero) {
        this.atividadeGenero = atividadeGenero;
    }
    
    public void setGeneros(AtividadeGenero[] generos) {
        this.generos = generos;
    }
    
    
    public String getAtividadeListaTipo() {
        return atividadeListaTipo;
    }

    public void setAtividadeListaTipo(String atividadeListaTipo) {
        this.atividadeListaTipo = atividadeListaTipo;
    }

    public Integer getInstituicaoId() {
        return instituicaoId;
    }

    public void setInstituicaoId(Integer instituicaoId) {
        this.instituicaoId = instituicaoId;
    }

    public List<Instituicao> getInstituicoesParticipantes() {
        return instituicoesParticipantes;
    }

    public void setInstituicoesParticipantes(List<Instituicao> instituicoesParticipantes) {
        this.instituicoesParticipantes = instituicoesParticipantes;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public Date getDt1Period() {
        return dt1Period;
    }

    public void setDt1Period(Date dt1Period) {
        this.dt1Period = dt1Period;
    }

    public Date getDt2Period() {
        return dt2Period;
    }

    public void setDt2Period(Date dt2Period) {
        this.dt2Period = dt2Period;
    }

    
    public String getMais_info() {
        return mais_info;
    }

    public void setMais_info(String mais_info) {
        this.mais_info = mais_info;
    }

    public int getIdKey() {
        return idKey;
    }

    public void setIdKey(int idKey) {
        this.idKey = idKey;
    }

    public Atividade getCurrent() {
        return current;
    }

    public void setCurrent(Atividade current) {
        this.current = current;
    }

    public AtaNts getAta() {
        return ata;
    }

    public void setAta(AtaNts ata) {
        this.ata = ata;
    }

    public int getTotalParticipantesAtv() {
        return totalParticipantesAtv;
    }

    public void setTotalParticipantesAtv(int totalParticipantesAtv) {
        this.totalParticipantesAtv = totalParticipantesAtv;
    }

    public ParticipacaoInstituicaoFacade getEjbParticipacao() {
        return ejbParticipacao;
    }

    public void setEjbParticipacao(ParticipacaoInstituicaoFacade ejbParticipacao) {
        this.ejbParticipacao = ejbParticipacao;
    }
    
    

    public AtividadeFacade getEjbAtividade() {
        return ejbAtividade;
    }

    public void setEjbAtividade(AtividadeFacade ejbAtividade) {
        this.ejbAtividade = ejbAtividade;
    }

    public AtaNtsFacade getEjbAtaNts() {
        return ejbAtaNts;
    }

    public void setEjbAtaNts(AtaNtsFacade ejbAtaNts) {
        this.ejbAtaNts = ejbAtaNts;
    }

    public StatusFacade getEjbStatus() {
        return ejbStatus;
    }

    public void setEjbStatus(StatusFacade ejbStatus) {
        this.ejbStatus = ejbStatus;
    }

    public InstituicaoFacade getEjbInstituicao() {
        return ejbInstituicao;
    }

    public void setEjbInstituicao(InstituicaoFacade ejbInstituicao) {
        this.ejbInstituicao = ejbInstituicao;
    }

    public int getQtdParticipantes() {        
        //partPessoaList = ejbParticipacaoPessoa.findByAtividade(current);
        presencaList = ejbPresenca.findByAtividade(current);
        
        int qtdPessoas = 0;
//        if(partPessoaList!=null)
//            qtdPessoas = partPessoaList.size();
        if(presencaList!=null)
            qtdPessoas = presencaList.size();
        
        qtdParticipantes = qtdPessoas;
        
        return qtdParticipantes;
    }

    public void setQtdParticipantes(int qtdParticipantes) {
        this.qtdParticipantes = qtdParticipantes;
    }

    public int getQtdPolos() {
        List<String> ips = new ArrayList<>();
        boolean novo = true;
//        for(ParticipacaoPessoa p : partPessoaList){
//            System.out.println("Procurando ip da participacao de ..." + p.getPessoa().getNome());
//            ParticipacaoIp partIp = ejbParticipacaoIP.findByParticipacao(p);
//            for(String i : ips){
//                if(partIp!=null)
//                    if(partIp.getIp().equalsIgnoreCase(i))
//                        novo = false;
//            }
//            if(novo&&partIp!=null)
//                ips.add(partIp.getIp());
//        }
        for(Presenca p : presencaList){
            System.out.println("Procurando ip da participacao de ..." + p.getPessoa().getNome());
            for(String i : ips){
                if(p.getIp().equalsIgnoreCase(i))
                    novo = false;
            }
            if(novo)
                ips.add(p.getIp());
        }
        qtdPolos = ips.size() + instituicoesParticipantes.size();
        return qtdPolos;
    }

    public void setQtdPolos(int qtdPolos) {
        this.qtdPolos = qtdPolos;
    }

    public Atividade getCurrentBeingSeen() {
        return currentBeingSeen;
    }

    public void setCurrentBeingSeen(Atividade currentBeingSeen) {
        this.currentBeingSeen = currentBeingSeen;
    }
    
    

    public SolicitacaoFacade getEjbSolicitacao() {
        return ejbSolicitacao;
    }

    public void setEjbSolicitacao(SolicitacaoFacade ejbSolicitacao) {
        this.ejbSolicitacao = ejbSolicitacao;
    }
    
    //GET LIST OF institucoes FROM DATABASE
    public List<Instituicao> getInstituicoes(){
        return getEjbInstituicao().findAll();
    }
    
    public List<Status> statu(){
        Status aguardando = getEjbStatus().findByDescricao("Aguardando");
        Status deferido = getEjbStatus().findByDescricao("Deferido");
        Status indeferido = getEjbStatus().findByDescricao("Indeferido");
        
        List<Status> all = getEjbStatus().findAll();
        
        all.remove(aguardando);
        all.remove(deferido);
        all.remove(indeferido);
        return all;
    }
    
    public List<Atividade> atividadesRealizadas(){
        return buscarPorStatus("Realizada");
    }
    
    public List<Atividade> atividadesCanceladas(){
        return buscarPorStatus("Cancelada");
    }
    
    public List<Atividade> atividadesPendentes(){
        List<Atividade> deferidas = buscarPorStatus("Deferido");
        List<Atividade> pendentes = new ArrayList<>();
                
        Date today = new Date();
        
        for(Atividade a : deferidas){
            if(!DateUtils.ingnoreTime(a.getDt()).before(DateUtils.ingnoreTime(today))){
                if (DateUtils.ingnoreTime(a.getDt()).equals(DateUtils.ingnoreTime(today))){
                    if(DateUtils.ingnoreDate(today, today).before(DateUtils.ingnoreDate(a.getHrTermino(), today))){
                        pendentes.add(a);
                    }
                }
                else if(DateUtils.ingnoreDate(a.getHrTermino(), today).after(DateUtils.ingnoreDate(today, today))){
                    pendentes.add(a);
                }
            }
        }
        
        return pendentes;
    }
    
    public List<Atividade> atasPendentes(){
        List<Atividade> deferidas = buscarPorStatus("Deferido");
        List<Atividade> pendentes = new ArrayList<>();
        
        Date today = new Date();
        
        for(Atividade a : deferidas){
            if(DateUtils.ingnoreTime(a.getDt()).before(DateUtils.ingnoreTime(today))){
                pendentes.add(a);
            }
            else if (DateUtils.ingnoreTime(a.getDt()).equals(DateUtils.ingnoreTime(today))){
                if(DateUtils.ingnoreDate(today, today).after(DateUtils.ingnoreDate(a.getHrTermino(), today))){
                    pendentes.add(a);
                }
            }
        }
     
        return pendentes;
    }
    
    public List<Atividade> buscarPorStatus(String status){
        Status s = getEjbStatus().findByDescricao(status);
        return getEjbAtividade().findByStatus(s);
    }
    
    public void setSelectedData(){
        instituicoesParticipantes = new ArrayList();
        fileAta = null;
        ata = new AtaNts();
        String idKeyString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idAtividade");
        if(idKeyString!=null){
            idKey = Integer.parseInt(idKeyString);
            this.current = getEjbAtividade().findById(idKey);
        }
    }
    
    public boolean hasAtaDigitalizada()
    {
        AtaNts ata = ejbAtaNts.findByAtividade(current);
        
        if(ata != null && current.getStatus().getId() != 1)
            if(ejbAtaDigitalizada.findByAta(ata) != null)
                return true;
        return false;
    }
    
    public boolean hasAtaDigitalizadaSlides()
    {
        AtaNts ata = ejbAtaNts.findByAtividade(current);
        
        if(ata != null && current.getStatus().getId() != 1)
        {
            AtaDigitalizada ad = ejbAtaDigitalizada.findByAta(ata);
                    
            if(ad != null)
            {
                if(ad.getNomeArquivoSlide() != null)
                    if(!ad.getNomeArquivoSlide().equals(""))
                        return true;
            }
        }
        return false;
    }
    
    public String persist(){
        try {
            System.out.println("Trying to save ATA!!");
            ata.setQtdPresentes(qtdParticipantes);
            ata.setQtdPontos(qtdPolos);
            ata.setAtividade(current);  
            getEjbAtividade().edit(current);
            getEjbAtaNts().create(ata);
            if(current.getStatus().getId() != 5)
                checkCertification(current);
            if(fileAta != null || fileAtaSlide != null){
                System.out.println("File found!!");
                AtaDigitalizada ad = new AtaDigitalizada();
                ad.setAta(ata);
                if(fileAta != null)
                    ad.setNomeArquivo(fileAta.getFileName());
                if(fileAtaSlide != null)
                    ad.setNomeArquivoSlide(fileAtaSlide.getFileName());
                
                try{
                    ejbAtaDigitalizada.create(ad);
                    System.out.println("Digital ATA registered!!");
                }
                catch(Exception ex){
                    ejbAtaNts.remove(ata);
                    JsfUtil.addErrorMessage("Erro ao carregar a ata digitalizada! Tente novamente.");
                    System.out.println("Error in saving digital ATA!!");
                    return null;
                }
            }      
            
            try{
                persistParticipacaoInstituicao();
                System.out.println("Inst saved!!");
            }
            catch(Exception ex){
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/BundleAtividade").getString("Ata_Save_Error"));
                System.out.println("Error in saving Inst!!");
                return null;
            }
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/BundleAtividade").getString("Ata_Saved"));
            current = new Atividade();
            ata = new AtaNts();
            System.out.println("Ata Salva com sucesso!!");
            return "list_atas.xhtml";
        } 
        catch (Exception ex){
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/BundleAtividade").getString("Ata_Save_Error"));
            System.out.println("Error in saving ATA!!");
        }
        return null;
    }
    
    public void guardarInstituicao(){
        Instituicao tmp = getEjbInstituicao().findById(instituicaoId);
        instituicoesParticipantes.add(tmp);
        updateQtdPolos(1);
        //System.out.println(instituicoesParticipantes);
    }
    
    public void removeInstituicao(Instituicao inst){
       //System.out.println(p);
       instituicoesParticipantes.remove(inst);
       updateQtdPolos(-1);
       //palestra = new Palestras();
    }
    
    private void updateQtdPolos(int k){
        //this.ata.setQtdPontos(instituicoesParticipantes.size()+qtdPolos);
        qtdPolos += k;
    }
    
    private void persistParticipacaoInstituicao()
    {
        if(!instituicoesParticipantes.isEmpty())
            for(Instituicao inst : instituicoesParticipantes)
            {
                ParticipacaoInstituicao participante = new ParticipacaoInstituicao();
                participante.setAtividade(current);
                participante.setInstituicao(inst);
                getEjbParticipacao().create(participante);
            }
    }
    
    public List<Atividade> atividadesIndeferidas(){
        Status status = ejbStatus.findByDescricao("Indeferido");
        return getEjbAtividade().findByStatus(status);
    }
    
    public void atividadesRecuperadas() throws FileNotFoundException, UnsupportedEncodingException, JSONException, SftpException
    {   
        refreshDataTable();
    }
    
    public void refreshDataTable() throws FileNotFoundException, UnsupportedEncodingException, JSONException, SftpException
    {
        
        
        JSONArray jSONArray = new JSONArray();
        for(Atividade atv : atividadesrecuperadas)
        {
            ata = ejbAtaNts.findByAtividade(atv);
            JSONObject j = new JSONObject();

            j.put("id", atv.getId());
            Calendar cal = Calendar.getInstance();
            cal.setTime(atv.getDt());
            int year = cal.get(Calendar.YEAR);
            int month = 1 + cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            j.put("tema", atv.getTema());
            j.put("data", day+"/"+month+"/"+year);
            
            AtividadeTbr atvTbr =  ejbAtividadeTbr.findByAtividade(atv);
            if(atvTbr != null)
            {
                j.put("finalidade", atvTbr.getFinalidade());
                j.put("tbr", "Sim");
            }
            else
            {
                j.put("finalidade", "");
                j.put("tbr", "Não");
            }
            
            j.put("modalidade", atv.getModalidade().getDescricao());
            j.put("tipo", atv.getTipo().getDescricao());
            List<Presenca> presencas = ejbPresenca.findByAtividade(atv);
            if(!presencas.isEmpty())
                j.put("parts", presencas.size());
            else
            {
                if(ata!= null)
                    j.put("parts", ata.getQtdPresentes());
                else
                    j.put("parts", "");
            }
            j.put("botao", atv.getId());           
            
            
            jSONArray.put(j);
        }
        
        jsonReportAtividade = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/data.json"));
        
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportAtividade );
        }
        System.out.println("update");
    }
    
    public String atividadePage(int pageLink)
    {
        /*  1: Pendentes
            2: Canceladas
            3: Realizadas
            4: Indeferidas
        */
        setDt1Period(getEjbAtividade().firstDate());
        setDt2Period(getEjbAtividade().lastDate());
        atvGenero = pageLink;
        selectedTipos = null;
        switch(pageLink)
        {
            case 1:
                atividadeListaTipo = "Pendentes";
                atividadesrecuperadas = atividadesPendentes();
                System.out.println("recuperadas " + atividadesrecuperadas);
                return "/atividades/view_atividade_table_details";
            case 2:
                atividadeListaTipo = "Canceladas";
                atividadesrecuperadas = atividadesCanceladas();
                 System.out.println("recuperadas " + atividadesrecuperadas);
                return "/atividades/view_atividade_table_details";
            case 3:
                atividadeListaTipo = "Realizadas";
                atividadesrecuperadas = atividadesRealizadas();
                return "/atividades/view_atividade_table_details";
            case 4:
                atividadeListaTipo = "Indeferidas";
                atividadesrecuperadas = atividadesIndeferidas();
                return "/atividades/view_atividade_table_details";
            case 5:
                atividadeListaTipo = "Realizadas";
                atividadesrecuperadas = atividadesRealizadas();
                return "/charts/view_atividades_filter_pie_chart";
            default:
                return "#"; 
        }
    }
    
    
    private List<Atividade> getAtividadesByCategory()
    {
        
        switch(atividadeListaTipo)
        {
            case "Pendentes":
                return  atividadesPendentes();
            case "Canceladas":
                return atividadesCanceladas();
            case "Realizadas":
                return atividadesRealizadas();
            case "Indeferidas":
                return atividadesIndeferidas();
            default:
                return null; 
        }
    }
    
    public List<TipoCounter> atividadesSomadas()
    {
        totalAtividadesRecuperadas = 0;
        totalParticipantesAtv = 0;
        List<TipoCounter> counters = new ArrayList();
        List<Tipo> tipos = ejbTipo.findAll();
        for(Tipo tipo : tipos)
        {
            TipoCounter tipoCount = new TipoCounter();
            int[] totals = {0, 0, 0};
            tipoCount.setTipo(tipo);
            for(Atividade atv : atividadesrecuperadas)
            {
                if(atv.getTipo().equals(tipo))
                {
                    if(atv.getModalidade().getDescricao().equals("Videoconferência"))
                    {
                        totals[0]++;
                        totalAtividadesRecuperadas++;
                    }
                    else if(atv.getModalidade().getDescricao().equals("Webconferência"))
                    {
                        totals[1]++;
                        totalAtividadesRecuperadas++;
                    }
                    else if(atv.getModalidade().getDescricao().equals("Presencial"))
                    {
                        totals[2]++;
                        totalAtividadesRecuperadas++;
                    }
                    ata = ejbAtaNts.findByAtividade(atv);
                    
                    totalParticipantesAtv += ejbPresenca.findByAtividade(atv).size();
                }
            }
            tipoCount.setTotals(totals);
            counters.add(tipoCount);
                    
        }        
        //totalParticipantesAtv = ejbPresenca.count();
        atividadesSomadasObj = counters;
        createPieModelTipos(counters);
        return counters;
    }
    
    private void createPieModelTipos(List<TipoCounter> counters)
    {
        pieModel = new PieChartModel();
        
        for(TipoCounter tipo : counters)
        {
            pieModel.set(tipo.getTipo().getDescricao(), tipo.getTotals()[0] + tipo.getTotals()[1] + tipo.getTotals()[2]);
        }
        pieModel.setLegendPosition("w");
        pieModel.setShowDataLabels(true);
        pieModel.setExtender("pieExtender");
        pieModel.setDiameter(200);
    }
    
    public void updateAtividadesRecuperadas()
    {
        List<Atividade> atividadesUpdated = new ArrayList();
        atividadesrecuperadas = getAtividadesByCategory();
        List<AtividadeTbr> atividadesTbr = ejbAtividadeTbr.findAll();
        for(Atividade atv : atividadesrecuperadas)
        {
            if(atv != null)
            {
                if(dt1Period != null && dt2Period != null)
                {
                    Date dateAtv = DateUtils.ingnoreTime(atv.getDt());
                    Date dt1 = DateUtils.ingnoreTime(dt1Period);
                    Date dt2 = DateUtils.ingnoreTime(dt2Period);
                    if((dateAtv.after(dt1) && dateAtv.before(dt2)) 
                        || (dateAtv.equals(dt1) || dateAtv.equals(dt2)))
                    {
                        if(filter(atv, atividadesTbr))
                           atividadesUpdated.add(atv);    
                    }
                }
                else
                {
                    if(filter(atv, atividadesTbr))
                        atividadesUpdated.add(atv);  
                }                
            }
        }
        
        atividadesrecuperadas = null;
        atividadesrecuperadas = atividadesUpdated;
    }
    
    private boolean filter(Atividade atv, List<AtividadeTbr> atividadesTbr)
    {

        boolean addNTS = true;
        boolean addSpecificTipo = false;
        
        if(selectedTipos.length != 0)
        {
            for(int i = 0; i<selectedTipos.length; i++)
            {
                if(atv.getTipo().getDescricao().equals(selectedTipos[i]))
                {
                    addSpecificTipo = true;
                    break;
                }
                else
                    addSpecificTipo = false;
            }
        }
        else
            addSpecificTipo = true;
        
        if(addSpecificTipo)
        {
            if(getAtvGenero() != 0)
            {
                for(AtividadeTbr atvTbr : atividadesTbr)
                {
                    if(getAtvGenero() == AtividadeGenero.TBR)
                    {
                        if(atv.getId().equals(atvTbr.getAtividade().getId()))
                            return true;
                    }
                    else if(getAtvGenero() == AtividadeGenero.NTS)
                    {
                        if(atv.getId().equals(atvTbr.getAtividade().getId()))
                        {
                           addNTS = false;
                           break;
                        }
                    }
                    else if(getAtvGenero() == AtividadeGenero.TODAS)
                    {
                        return true;
                    }
                }
                if(getAtvGenero() == AtividadeGenero.NTS)
                    return addNTS;
            }
            else
                return true;
        }
        return false;
    }
    
    private boolean sendEmail(Pessoa p)
    {
        if(!p.getEmail().toLowerCase().contains("@fake.com") || !p.getEmail().toLowerCase().contains("@ficticio.com") 
                        || p.getEmail() != null ||  !p.getEmail().equals(""))
        {
            return true;
        }
        return false;
    }
    
    public String viewCertificados(Pessoa pessoa)
    {
        pessoaCertificado = pessoa;
        atividadesModerador = ejbAtividade.findByModerador(pessoa);
        atividadesPalestrantes = ejbPalestras.findByPessoa(pessoa);
//        partPessoaList = ejbParticipacaoPessoa.findByPessoa(pessoa);
        presencaList = ejbPresenca.findByPessoa(pessoa);
        certificadosPessoa = new ArrayList();
        filteredCertificados = null;
        CertificadoPessoa cert;
        for(Atividade atv : atividadesModerador)
        {
            if(ejbPalestras.findByAtividade(atv).isEmpty())
                cert = new CertificadoPessoa(atv, atv.getModerador(), PALESTRANTE, "Palestrante");
            else
                cert = new CertificadoPessoa(atv, atv.getModerador(), MODERADOR, "Moderador");
            certificadosPessoa.add(cert);
        }
        
        for(Palestras pal : atividadesPalestrantes)
        {
            cert = new CertificadoPessoa(pal.getAtividade(), pal.getPessoa(), PALESTRANTE, "Palestrante");
            certificadosPessoa.add(cert);
        }
        
//        for(ParticipacaoPessoa part : partPessoaList)
//        {
//            cert = new CertificadoPessoa(part.getAtividade(), part.getPessoa(), PARTICIPANTE, "Participante");
//            certificadosPessoa.add(cert);
//        }
        
        for(Presenca p : presencaList){
            cert = new CertificadoPessoa(p.getAtividade(), p.getPessoa(), PARTICIPANTE, "Participante");
            certificadosPessoa.add(cert);
        }
        
        return "/views/view_list_certificados_por_pessoa.xhtml";
    }
    
    private void checkCertification(Atividade atv) throws IOException {
        System.out.println("Keys registradas " + ejbPresencakey.findAll());
        Certificados cert = ejbCerts.findByAtividade(atv);
        String emailCC = "telessaude.ma@huufma.br";
        File slides = new File(getFilePathSlides());
        if(fileAtaSlide != null)
            FileUtils.copyInputStreamToFile(fileAtaSlide.getInputstream(), slides);
        else
            slides = null;
        //upload(slides);
        if(cert!=null){
            if(current.getStatus().getId()==4){
                System.out.println("Send certification...");
                Pessoa moderador = atv.getModerador();
                System.out.println("Moderador email: " + moderador.getEmail());
                getPdfDocument(atv, moderador, atv.getTema(), MODERADOR);
                if(sendEmail(moderador))
                {
                    Presenca presenca = ejbPresenca.findByComposedKey(moderador, atv);
                    PresencaKeys pKey = null;
                    if(presenca != null)
                        pKey = ejbPresencakey.getByPresenca(presenca);
                    Mailler.send(moderador.getEmail(), getFilePath(), slides, pKey, true);
                    Mailler.send(emailCC, getFilePath(), slides, pKey, true);
                }

                List<Palestras> palestras = ejbPalestras.findByAtividade(atv);
                for(Palestras palestra : palestras)
                {
                    if(sendEmail(palestra.getPessoa()))
                    {
                        Presenca presenca = ejbPresenca.findByComposedKey(palestra.getPessoa(), atv);
                        PresencaKeys pKey = null;
                        if(presenca != null)
                            pKey = ejbPresencakey.getByPresenca(presenca);
                        getPdfDocument(atv, palestra.getPessoa(), palestra.getTema(), PALESTRANTE);
                        Mailler.send(palestra.getPessoa().getEmail(), getFilePath(), slides, pKey, true);
                        Mailler.send(emailCC, getFilePath(), slides, pKey, true);
                    }
                }
            }
        }
        
            
//        List<ParticipacaoPessoa> participacoes = ejbParticipacaoPessoa.findByAtividade(atv);
        List<Presenca> participacoes = ejbPresenca.findByAtividade(atv);

        if(!participacoes.isEmpty()){
            for(Presenca p : participacoes){
                if(sendEmail(p.getPessoa())){
                    Presenca presenca = ejbPresenca.findByComposedKey(p.getPessoa(), atv);
                    PresencaKeys pKey = null;
                    if(presenca != null)
                        pKey = ejbPresencakey.getByPresenca(presenca);
                    getPdfDocument(atv, p.getPessoa(), atv.getTema(), PARTICIPANTE);
                    Mailler.send(p.getPessoa().getEmail(), getFilePath(), slides, pKey, sendCertParticipantes);
                    Mailler.send(emailCC, getFilePath(), slides, pKey, sendCertParticipantes);
                }
            }
        }
    }
    
    private String generateCode(){
        String uuid = UUID.randomUUID().toString();
        while(ejbAutent.FindByCode(uuid)!=null){
            uuid = UUID.randomUUID().toString();
        }
        
        return uuid;
    }
    
    public StreamedContent certificateDownload(Atividade atv, Pessoa pessoa, String tema, int tipoPessoa) throws FileNotFoundException {
        getPdfDocument(atv, pessoa, tema, tipoPessoa);
        InputStream stream = new FileInputStream(getFilePath());
        return new DefaultStreamedContent(stream, "application/pdf", "Certificado.pdf");
    }
    
    //Building pdf document
    public void getPdfDocument(Atividade atv, Pessoa pessoa, String tema, int tipoPessoa){        
        try {
            File file = new File(getFilePath());
            
            //pdfContent = new Streamed
//            ByteArrayOutputStream output = new ByteArrayOutputStream();  
            FileOutputStream output = new FileOutputStream(file);
//            output.reset();
            Font fontP = new Font(Font.FontFamily.TIMES_ROMAN, 16);
            Font fontLine = new Font(Font.FontFamily.TIMES_ROMAN, 14);
            Font fontLineBold = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
            
            //FileOutputStream file = new FileOutputStream("template.pdf");
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter writer = PdfWriter.getInstance(document, output);  
            document.open();
            
            //Background image
            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image;
            if(atv.getTipo().getId() == 13 && atv.getTema().toLowerCase().contains("coloproctologia")) //quando  SIG telecolo
                image = Image.getInstance(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/img/certificate-background_sig.png"));
            else
                image = Image.getInstance(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/img/certificate-background.png"));
            
            image.scaleAbsolute(PageSize.A4.rotate());
            image.setAbsolutePosition(0, 0);
            canvas.addImage(image);

            //Writing document
            
            //document.add( Chunk.NEWLINE );
            //Paragraph headerTitle =  new Paragraph("Cerficado de Participação", fontHeader);
            //headerTitle.setAlignment(Element.ALIGN_CENTER);
            //document.add(headerTitle);
            document.add( new Paragraph());
            document.add( Chunk.NEWLINE );
                       
            Paragraph p = new Paragraph();
            p.add(new Phrase("Certificamos que ", fontP));
            p.add(new Chunk(pessoa.getNome().toUpperCase().trim(), fontLineBold));
            p.add(new Chunk(" "+pessoa.getSobrenome().toUpperCase().trim(), fontLineBold));
            
            List<Palestras> palestras = ejbPalestras.findByAtividade(atv);
            
           
            if(tipoPessoa==PARTICIPANTE)
            {
                // caso participante não seja um participante de reunião de comitê gestor, gera
                //certificado padrão de participante
                if(atv.getTipo().getId() != 19) 
                {
                    p.add(new Phrase(" participou do(a) ", fontP));
                    p.add(new Chunk(atv.getTipo().getDescricao() + ": ", fontLineBold));
                    p.add(new Chunk(atv.getTema(), fontLineBold));
                    
                    if(checkDefesas(atv)) //defesas e qualificações
                    {
                        p.add(new Phrase(" defendida por ", fontP));
                        p.add(new Chunk(atv.getModerador().getNome()+ " " + 
                                atv.getModerador().getSobrenome()+ ", ", fontLineBold));
                    }
                }
                else //participante de reunião - comitê gestor
                {
                    p.add(new Phrase(" participou da ", fontP));
                    p.add(new Chunk(atv.getTipo().getDescricao() + ": ", fontLineBold));
                    p.add(new Chunk(atv.getTema() + " e deliberações", fontLineBold));
                }
                
                
            }
            else {
                if(!palestras.isEmpty())
                {
                    p.add(new Phrase(" participou do(a) ", fontP));
                    p.add(new Chunk(atv.getTipo().getDescricao() + ": ", fontLineBold));
                    p.add(new Chunk(atv.getTema(), fontLineBold));
                    p.add(new Phrase(" na qualidade de ", fontP));
                    if(tipoPessoa == MODERADOR)    
                        p.add(new Chunk("Moderador", fontLineBold));
                    if(tipoPessoa == PALESTRANTE)
                    {
                        p.add(new Chunk("Palestrante", fontLineBold));
                        p.add(new Phrase(" do tema ", fontP));
                        p.add(new Chunk(tema, fontLineBold));
                    }
                    p.add(new Phrase(" no Programa de Educação Continuada do NTS HU-UFMA", fontP));
                }
                else
                {
                    if(checkDefesas(atv)) //defesas e qualificações
                    {
                        p.add(new Phrase(" apresentou a ", fontP));
                        p.add(new Chunk(atv.getTipo().getDescricao(), fontLineBold));
                        p.add(new Phrase(" ministrando o tema ", fontP));
                        p.add(new Chunk(tema, fontLineBold));
                    }
                    else if(atv.getTipo().getId() == 10)  //gravação de video aula
                    {
                        p.add(new Phrase(" realizou a ", fontP));
                        p.add(new Chunk(atv.getTipo().getDescricao(), fontLineBold));
                        p.add(new Phrase(" ministrando o tema ", fontP));
                        p.add(new Chunk(tema, fontLineBold));
                        p.add(new Phrase(" para o Programa de Educação Continuada do NTS HU-UFMA", fontP));
                    }
                    else if(atv.getTipo().getId() == 19) //reunião do comitê gestor
                    {
                        p.add(new Phrase(" participou da ", fontP));
                        p.add(new Chunk(atv.getTipo().getDescricao(), fontLineBold));
                        p.add(new Phrase(" apresentando ", fontP));
                        p.add(new Chunk(tema, fontLineBold));
                        p.add(new Phrase(", na qualidade de ", fontP));
                        p.add(new Chunk("Palestrante", fontLineBold));
                    }
                    else // geral
                    {
                        p.add(new Phrase(" participou do(a) ", fontP));
                        p.add(new Chunk(atv.getTipo().getDescricao(), fontLineBold));
                        p.add(new Phrase(" ministrando o tema ", fontP));
                        p.add(new Chunk(tema, fontLineBold));
                        p.add(new Phrase(", na qualidade de ", fontP));
                        p.add(new Chunk("Palestrante", fontLineBold));
                        p.add(new Phrase(" do Programa de Educação Continuada do NTS HU-UFMA", fontP));
                    }
                }
            }
            
                
            
            p.add(new Phrase(" no dia ", fontP));
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(atv.getDt());
            int year = cal.get(Calendar.YEAR);
            int month = 1 + cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            
            p.add(new Phrase(day + " de " + DateUtils.monthNumberToMonthName(month) + " de " + year, fontP));
            
            if(tipoPessoa == PARTICIPANTE){
                //Não tem duração pra reunião do comitê gestor
                if(atv.getTipo().getId() != 19)
                    p.add(new Phrase(", com duração de " + timeDuration(atv), fontP));
            }
            else {
                if(palestras.isEmpty())
                {
                    if(checkDefesas(atv))
                        p.add(new Phrase(", com duração de " + timeDuration(atv), fontP));
                    //se não for gravação e nem reunião do comitê gestor, coloca a carga horária
                    else if(atv.getTipo().getId() != 10 && atv.getTipo().getId() != 19) 
                        p.add(new Phrase(", com carga horária de " + timeDuration(atv), fontP));
                }
                else
                    p.add(new Phrase(".", fontP));
            }
            
            
            p.setIndentationLeft(110);
            p.setSpacingBefore(170);
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p);
            
            //Lista de temas no certificado do participante
            if((tipoPessoa==PARTICIPANTE || tipoPessoa==MODERADOR) && !palestras.isEmpty())
            {
                p = new Paragraph();
                document.add( new Paragraph());
                document.add( Chunk.NEWLINE );
                p.add(new Chunk("Temas: ", fontLineBold));
                p.setIndentationLeft(110);
                p.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(p);
                com.itextpdf.text.List list = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED); 
                for(Palestras pal : palestras)
                {
                    ListItem item = new ListItem(pal.getTema());
                    item.setAlignment(Element.ALIGN_JUSTIFIED);
                    list.add(item);
                }
                list.setIndentationLeft(110);
                document.add(list);
            }
            
            
            p = new Paragraph();
            cal.setTime(new Date());
            year = cal.get(Calendar.YEAR);
            month = 1 + cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            PlaceChunck(writer, "São Luís, " + day + " de " + DateUtils.monthNumberToMonthName(month) + " de " + year, 600, 185);
            
            String code = generateCode();
            try{
                System.out.println("Saving Authentification code..." + code);
                Autentificacao autentification = new Autentificacao();
                autentification.setPessoa(pessoa);
                autentification.setAtividade(atv);
                autentification.setCodigo(code);
                ejbAutent.create(autentification);
                System.out.println("Authentification code saved!");
                 
                p = new Paragraph();
                PlaceChunck(writer, "Verifique o código " + code + " em http://telessaude.huufma.br/site/", 120, 20);
            }
            catch (Exception ex){
                System.out.println("Error at saving Authentification code: " + ex);
            }
            
            document.close();
            output.close();
            
            System.out.println("Calling Upload " + file.getName());
            upload(file);
        } 
        catch (Exception e) {  
             JsfUtil.addErrorMessage(e, "Error: createPDF() " + e.getMessage());
        }  
    }
    
    private boolean checkDefesas(Atividade atv)
    {
        if(atv.getTipo().getId() == 1 || atv.getTipo().getId() == 2 
                    || atv.getTipo().getId() == 3 || atv.getTipo().getId() == 4 || atv.getTipo().getId() == 26)
            return true;
        return false;
    }
    
    
    private void PlaceChunck(PdfWriter writer, String text, int x, int y) throws DocumentException, IOException 
    {
        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        cb.saveState();
        cb.beginText();
        cb.moveText(x, y);
        cb.setFontAndSize(bf, 16);
        cb.showText(text);
        cb.endText();
        cb.restoreState();
    }
    
    public String timeDuration(Atividade atv)
    {
        long secs = (atv.getHrTermino().getTime() - atv.getHrInicio().getTime()) / 1000;
        int hours = (int) (secs / 3600);    
        secs = secs % 3600;
        int mins = (int) (secs / 60);
        secs = secs % 60;
        return hours + " hora(s)" + ((mins > 0) ? " e " + mins + " minutos." : ". ");
    }
    
    public void upload(File file){
        FileOutputStream os = null;
        try {
            byte[] b = Files.readAllBytes(file.toPath());
            os = new FileOutputStream(file);
            os.write(b);
            // aqui você pode colcar a gravação do path no BD
            
            System.out.println("Saving " + file.getName() + " to " + file.getPath());
        } 
        catch(IOException ex){  
            System.out.println("Error writing file... " + ex.getMessage());  
        } 
        finally {  
            try {  
                os.flush();  
                os.close();  
            } 
            catch(IOException ex) {  
                System.out.println("Error closing file... " + ex.getMessage());
            }  
        }
    }
    
    public String getFilePath(){
        String path = getProjectPath();
        String fileName = "temp.pdf";
        String completePath;
        if(System.getProperties().get("os.name").toString().trim().equalsIgnoreCase("Linux")){
            completePath = path + "/" + fileName;
        }
        else{
            completePath = path + "\\" + fileName;
        }
        return completePath;
    }
    
    public String getFilePathSlides(){
        String path = getProjectPath();
        String fileName = "slide.pdf";
        String completePath;
        if(System.getProperties().get("os.name").toString().trim().equalsIgnoreCase("Linux")){
            completePath = path + "/" + fileName;
        }
        else{
            completePath = path + "\\" + fileName;
        }
        return completePath;
    }
    
    public String getProjectPath(){
        return Paths.get(".").toAbsolutePath().normalize().toString();
    }
       
    public String prepareReportPalestras() throws JSONException, FileNotFoundException, UnsupportedEncodingException
    {
        JSONArray jSONArray = new JSONArray();
        for(Atividade atv : atividadesRealizadas())
        {
            int qtdPart = ejbPresenca.findByAtividade(atv).size();
            JSONObject j = generateJsonData(atv, atv.getTema(), atv.getModerador(), qtdPart);
            jSONArray.put(j);
            List<Palestras> palestras = ejbPalestras.findByAtividade(atv);
            if(!palestras.isEmpty())
            {
                for(Palestras p : palestras)
                {
                    j = generateJsonData(atv, p.getTema(), p.getPessoa(), qtdPart);
                    jSONArray.put(j);
                }
            }
        }
        
        jsonReportAtividade = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/data.json"));
        
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportAtividade );
        }
        
        return "/views/view_list_palestrantes_por_atividade";
    }
    
    private JSONObject generateJsonData(Atividade atv, String tema, Pessoa palestrante, int qtd) throws JSONException
    {
        JSONObject j = new JSONObject();

        j.put("id", atv.getId());
        Calendar cal = Calendar.getInstance();
        cal.setTime(atv.getDt());
        int year = cal.get(Calendar.YEAR);
        int month = 1 + cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        j.put("data", day+"/"+month+"/"+year);
        j.put("tema", tema);
        if(palestrante != null)
            j.put("palestrante", palestrante.getNome() + " " + palestrante.getSobrenome());
        else
            j.put("palestrante", "Não Informado");
        AtividadeTbr atvTbr =  ejbAtividadeTbr.findByAtividade(atv);
        if(atvTbr != null)
            j.put("finalidade", atvTbr.getFinalidade());
        else
            j.put("finalidade", "");
        j.put("modalidade", atv.getModalidade().getDescricao());
        j.put("tipo", atv.getTipo().getDescricao());
        j.put("participantes", qtd);
            
        return j;
    }
    
    public String handleFileUploaded(FileUploadEvent event) throws SftpException, IOException
    {
        fileAta = event.getFile();
        uploadAtaParticipantes(fileAta, "/opt/AtasNTS");
        return null;
    }
    
    public String handleFileUploadedSlides(FileUploadEvent event) throws SftpException, IOException
    {
        fileAtaSlide = event.getFile();
        uploadAtaParticipantes(fileAtaSlide, "/opt/SlidesAtasNts");
        return null;
    }
    
    public void uploadAtaParticipantes(UploadedFile file, String folder) throws SftpException, FileNotFoundException, IOException
    {
        if(file != null) {
            System.out.println("upload " + file.getFileName());
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " foi enviado com sucesso.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            RemoteConnection con = new RemoteConnection();
            con.connectToRemoteServer(file, folder);
        }        
    }
    
    public StreamedContent getAtaParticipantesFromServer() throws IOException
    {
        AtaNts ata = ejbAtaNts.findByAtividade(current);
        AtaDigitalizada ad;
        InputStream file = null;
        RemoteConnection con;
        if(ata != null)
        {
            ad = ejbAtaDigitalizada.findByAta(ata);
            if(ad!= null)
            {
                con = new RemoteConnection();
                file = con.getAtaFileFromServer(ad.getNomeArquivo(), "/opt/AtasNTS");
            }
            else return null;
        }
        else return null;
            
        if(file != null)
        {
            InputStream is = file;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            buffer.reset();
            int nRead;
            byte[] data = new byte[16384];
            
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            buffer.close();
            con.closeConnection();
            return new DefaultStreamedContent(new ByteArrayInputStream(buffer.toByteArray()), "application/pdf", "ID_" + current.getId() + "_ATA.pdf");
        }
        
        return null;
    }
    
    public StreamedContent getAtaSlidesFromServer() throws IOException
    {
        AtaNts ata = ejbAtaNts.findByAtividade(current);
        AtaDigitalizada ad;
        InputStream file = null;
        RemoteConnection con;
        if(ata != null)
        {
            ad = ejbAtaDigitalizada.findByAta(ata);
            if(ad!= null)
            {
                con = new RemoteConnection();
                String slides = ad.getNomeArquivo();
                if(slides != null)
                    file = con.getAtaFileFromServer(ad.getNomeArquivoSlide(), "/opt/SlidesAtasNts");
            }
            else return null;
        }
        else return null;
            
        if(file != null)
        {
            InputStream is = file;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            buffer.reset();
            int nRead;
            byte[] data = new byte[16384];
            
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            buffer.close();
            con.closeConnection();
            return new DefaultStreamedContent(new ByteArrayInputStream(buffer.toByteArray()), "application/pdf", "ID_" + current.getId() + "_SLIDES.pdf");
        }
        
        return null;
    }
    
    public String createParticipationJSON(Atividade targetAtv) throws FileNotFoundException, UnsupportedEncodingException, JSONException, SftpException {
        currentBeingSeen = targetAtv;
        
        JSONArray jSONArray = new JSONArray();
//        List<ParticipacaoPessoa> participacoes = ejbParticipacaoPessoa.findByAtividade(targetAtv);
        
        List<Presenca> participacoes = ejbPresenca.findByAtividade(targetAtv);

//        for(ParticipacaoPessoa pp : participacoes){
//            ParticipacaoLocal local = ejbParticipacaoLocal.findByParticipacao(pp);
//            Pessoa pessoa = pp.getPessoa();
//            ProfGeral prof = ejbProfGeral.getByPessoa(pessoa);
//            JSONObject j = new JSONObject();
//
//            j.put("nome", pessoa.getNome().toUpperCase());
//            j.put("sobrenome", pessoa.getSobrenome().toUpperCase());
//            if(prof!=null)
//                j.put("prof", prof.getCbo().getNome());
//            else
//                j.put("prof", "");
//            j.put("email", pessoa.getEmail().toUpperCase());
//            j.put("cpf", pessoa.getCpf());
//            j.put("celular", pessoa.getCelular());
//            if(local!=null){
//                j.put("cidade", local.getLocal().getNome().toUpperCase());
//                j.put("estado", local.getLocal().getUf().getSigla().toUpperCase());
//            }
//            else {
//                j.put("cidade", "");
//                j.put("estado", "");
//            }
//                
//            jSONArray.put(j);
//        }

        for(Presenca p : participacoes){
            //ParticipacaoLocal local = ejbParticipacaoLocal.findByParticipacao(pp);
            Pessoa pessoa = p.getPessoa();
            ProfGeral prof = ejbProfGeral.getByPessoa(pessoa);
            List<Avaliacoes> avaliacoes = ejbAval.findByPresenca(p);
            JSONObject j = new JSONObject();

            j.put("nome", pessoa.getNome().toUpperCase());
            j.put("sobrenome", pessoa.getSobrenome().toUpperCase());
            if(prof!=null)
                j.put("prof", prof.getCbo().getNome());
            else
                j.put("prof", "");
            j.put("email", pessoa.getEmail().toUpperCase());
            j.put("cpf", pessoa.getCpf());
            j.put("celular", pessoa.getCelular());
            if(p.getLocal()!=null){
                j.put("cidade", p.getLocal().getNome().toUpperCase());
                j.put("estado", p.getLocal().getUf().getSigla().toUpperCase());
            }
            else {
                j.put("cidade", "");
                j.put("estado", "");
            }
            if(!avaliacoes.isEmpty())
                j.put("avaliacao", "Sim");
            else
                j.put("avaliacao", "Não");
            
                
            jSONArray.put(j);
        }
        
        jsonReportAtividade = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/data.json"));
        
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportAtividade );
        }
        System.out.println("Filling data.json with person information... " + targetAtv);
        return "/views/view_list_participantes";
    }
    
    public String createPartAtividadeJSON() throws FileNotFoundException, UnsupportedEncodingException, JSONException, SftpException {
        
        JSONArray jSONArray = new JSONArray();
        JSONObject j;
        List<Cbo> profList, profIndex;
        
        for(Atividade a : atividadesrecuperadas){
//            List<ParticipacaoPessoa> participacoes = ejbParticipacaoPessoa.findByAtividade(a);
            List<Presenca> participacoes = ejbPresenca.findByAtividade(a);
            profList = new ArrayList<>();
            profIndex = new ArrayList<>();
            
//            for(ParticipacaoPessoa pp : participacoes){
//                Pessoa p = pp.getPessoa();
//                
//                ProfGeral pg = ejbProfGeral.getByPessoa(p);
//                if(pg!=null){
//                    Cbo profissao = pg.getCbo();
//                    if(profissao!=null){
//                        profList.add(profissao);
//                        if(!profIndex.contains(profissao))
//                            profIndex.add(profissao);
//                    }
//                }
//            }

            for(Presenca pp : participacoes){
                Pessoa p = pp.getPessoa();
                
                ProfGeral pg = ejbProfGeral.getByPessoa(p);
                if(pg!=null){
                    Cbo profissao = pg.getCbo();
                    if(profissao!=null){
                        profList.add(profissao);
                        if(!profIndex.contains(profissao))
                            profIndex.add(profissao);
                    }
                }
            }
            
            
            
            for(Cbo p : profIndex){
                j = new JSONObject();
                int count = 0;
                for(Cbo i : profList)
                    if(i.equals(p))
                        count++;
                
                j.put("AtividadeId", a.getId());
                j.put("Profissao", p.getNome());
                j.put("Qtd", count);
                jSONArray.put(j);
            }
            
        }
        
        jsonReportAtividade = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/dataProfissoes.json"));
        
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportAtividade );
        }
        return "/views/view_list_profissoes_por_atividade";
    }
    
    public String createPartsJSON() throws FileNotFoundException, UnsupportedEncodingException, JSONException, SftpException {
        
        JSONArray jSONArray = new JSONArray();
        JSONObject j;
        
        for(Atividade a : atividadesrecuperadas){
//            List<ParticipacaoPessoa> participacoes = ejbParticipacaoPessoa.findByAtividade(a);
            List<Presenca> participacoes = ejbPresenca.findByAtividade(a);
            
//            for(ParticipacaoPessoa pp : participacoes){
//                Pessoa p = pp.getPessoa();
//                ProfGeral pg = ejbProfGeral.getByPessoa(p);
//                ParticipacaoLocal local = ejbParticipacaoLocal.findByParticipacao(pp);
//                
//                j = new JSONObject();
//                
//                j.put("AtividadeId", a.getId());
//                j.put("Tema", a.getTema());
//                j.put("Modalidade", a.getModalidade().getDescricao());
//                j.put("Tipo", a.getTipo().getDescricao());
//                j.put("Nome", p.getNome().trim().toUpperCase() + " " + p.getSobrenome().trim().toUpperCase());
//                if(pg!=null)
//                    j.put("Profissao", pg.getCbo().getNome());
//                else 
//                    j.put("Profissao", " - ");
//                
//                if(local!=null){
//                    j.put("Cidade", local.getLocal().getNome());
//                    j.put("Estado", local.getLocal().getUf().getSigla());
//                }
//                else {
//                    j.put("Cidade", " - ");
//                    j.put("Estado", " - ");
//                }
//                jSONArray.put(j);
//                
//            }

            for(Presenca pp : participacoes){
                Pessoa p = pp.getPessoa();
                ProfGeral pg = ejbProfGeral.getByPessoa(p);
//                ParticipacaoLocal local = ejbParticipacaoLocal.findByParticipacao(pp);
                
                j = new JSONObject();
                
                j.put("AtividadeId", a.getId());
                j.put("Tema", a.getTema());
                j.put("Modalidade", a.getModalidade().getDescricao());
                j.put("Tipo", a.getTipo().getDescricao());
                j.put("Nome", p.getNome().trim().toUpperCase() + " " + p.getSobrenome().trim().toUpperCase());
                if(pg!=null)
                    j.put("Profissao", pg.getCbo().getNome());
                else 
                    j.put("Profissao", " - ");
                
                if(pp.getLocal()!=null){
                    j.put("Cidade", pp.getLocal().getNome());
                    j.put("Estado", pp.getLocal().getUf().getSigla());
                }
                else {
                    j.put("Cidade", " - ");
                    j.put("Estado", " - ");
                }
                jSONArray.put(j);
                
            }
        }
        
        jsonReportAtividade = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/dataProfissoes.json"));
        
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportAtividade );
        }
        return "/views/view_list_participantes_por_atividade";
    }
    
}

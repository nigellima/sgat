/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Atividade;
import entities.Cbo;
import entities.Estado;
import entities.Municipio;
import entities.ParticipacaoIp;
import entities.ParticipacaoLocal;
import entities.ParticipacaoPessoa;
import entities.Pessoa;
import entities.Presenca;
import entities.ProfGeral;
import entities.ProfSaude;
import entities.Ubs;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import jsf.util.DateUtils;
import jsf.util.JsfUtil;
import org.primefaces.event.SelectEvent;
import service.JSONIP;
import service.JSONLocal;
import service.Locale;
import service.NegocioException;
import service.URLConnector;
import sessions.AtividadeFacade;
import sessions.CboFacade;
import sessions.EstadoFacade;
import sessions.MunicipioFacade;
import sessions.ParticipacaoIpFacade;
import sessions.ParticipacaoLocalFacade;
import sessions.ParticipacaoPessoaFacade;
import sessions.PessoaFacade;
import sessions.PresencaFacade;
import sessions.ProfGeralFacade;
import sessions.ProfSaudeFacade;
import sessions.UbsFacade;

/**
 *
 * @author Lucas
 */
@ManagedBean(name = "presencaController")
@SessionScoped
public class PresencaController implements Serializable {
    private static final String KEY = "a9bb7b680fe3f9c60090c7451c4a4c4c74be0fc46a9a185b0e1a6f43a32ba4d9";
        
    private Pessoa pessoa;
    private Pessoa newPerson;
    private ProfGeral profGeral;
    private ProfGeral newProfGeral;
    private ProfSaude profSaude;
    private ProfSaude newProfSaude;
    private String search_tag;
    private List<Ubs> ubsList;
    private Integer ubsId;
    private Integer newCnesId;
    private boolean ubsUpdate;
    private List<Cbo> cboList;
    private String cboId;
    private String newCboId;
    private boolean cboUpdate;
    private List<Estado> estadoList;
    private Integer estadoId;
    private List<Municipio> municipioList;
    private Integer municipioId;
    private Atividade atividade;
    private ParticipacaoPessoa partPessoa;
    private ParticipacaoLocal partLocal;
    private ParticipacaoIp partIp;
    private boolean present = false;
    private Integer password;
    private boolean personFound = false;
    private boolean searched = false;
    private String IPaddress;
    
    @EJB
    private PresencaFacade ejbPresenca;
    @EJB
    private AtividadeFacade ejbAtividade;
    @EJB
    private PessoaFacade ejbPessoa;
    @EJB
    private ProfGeralFacade ejbProfGeral;
    @EJB
    private ProfSaudeFacade ejbProfSaude;
    @EJB
    private UbsFacade ejbUbs;
    @EJB
    private CboFacade ejbCbo;
    @EJB
    private EstadoFacade ejbEstado;
    @EJB
    private MunicipioFacade ejbMunicipio;
    @EJB
    private ParticipacaoPessoaFacade ejbPartPessoa;
    @EJB
    private ParticipacaoLocalFacade ejbPartLocal;
    @EJB
    private ParticipacaoIpFacade ejbPartIp;

    public String getSearch_tag() {
        return search_tag;
    }

    public void setSearch_tag(String search_tag) {
        this.search_tag = search_tag;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(Pessoa newPerson) {
        this.newPerson = newPerson;
    }

    public ProfGeral getProfGeral() {
        return profGeral;
    }

    public void setProfGeral(ProfGeral profGeral) {
        this.profGeral = profGeral;
    }

    public ProfGeral getNewProfGeral() {
        return newProfGeral;
    }

    public void setNewProfGeral(ProfGeral newProfGeral) {
        this.newProfGeral = newProfGeral;
    }

    public String getNewCboId() {
        return newCboId;
    }

    public void setNewCboId(String newCboId) {
        this.newCboId = newCboId;
    }

    public ProfSaude getProfSaude() {
        return profSaude;
    }

    public void setProfSaude(ProfSaude profSaude) {
        this.profSaude = profSaude;
    }

    public ProfSaude getNewProfSaude() {
        return newProfSaude;
    }

    public void setNewProfSaude(ProfSaude newProfSaude) {
        this.newProfSaude = newProfSaude;
    }

    public List<Ubs> getUbsList() {
        return ubsList;
    }

    public void setUbsList(List<Ubs> ubsList) {
        this.ubsList = ubsList;
    }

    public Integer getUbsId() {
        return ubsId;
    }

    public void setUbsId(Integer ubsId) {
        this.ubsId = ubsId;
    }

    public Integer getNewCnesId() {
        return newCnesId;
    }

    public void setNewCnesId(Integer newCnesId) {
        this.newCnesId = newCnesId;
    }

    public boolean isUbsUpdate() {
        return ubsUpdate;
    }

    public void setUbsUpdate(boolean ubsUpdate) {
        if(ubsUpdate){
            JsfUtil.addSuccessMessage("Selecione uma nova Unidade Básica de Saúde.", "profissionalRegistro");
        }
        this.ubsUpdate = ubsUpdate;
    }   

    public List<Cbo> getCboList() {
        return cboList;
    }

    public void setCboList(List<Cbo> cboList) {
        this.cboList = cboList;
    }

    public String getCboId() {
        return cboId;
    }

    public void setCboId(String cboId) {
        this.cboId = cboId;
    }

    public boolean isCboUpdate() {
        return cboUpdate;
    }

    public void setCboUpdate(boolean cboUpdate) {
        if(cboUpdate){
            JsfUtil.addSuccessMessage("Selecione um novo CBO.", "profissionalRegistro");
        }
        this.cboUpdate = cboUpdate;
    }
    
    public List<Estado> getEstadoList() {
        return estadoList;
    }

    public void setEstadoList(List<Estado> estadoList) {
        this.estadoList = estadoList;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    public Integer getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Integer municipioId) {
        this.municipioId = municipioId;
    }

    public ParticipacaoPessoa getPartPessoa() {
        return partPessoa;
    }

    public void setPartPessoa(ParticipacaoPessoa partPessoa) {
        this.partPessoa = partPessoa;
    }

    public ParticipacaoIp getPartIp() {
        return partIp;
    }

    public void setPartIp(ParticipacaoIp partIp) {
        this.partIp = partIp;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public ParticipacaoLocal getPartLocal() {
        return partLocal;
    }

    public void setPartLocal(ParticipacaoLocal partLocal) {
        this.partLocal = partLocal;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public boolean isPersonFound() {
        return personFound;
    }

    public void setPersonFound(boolean personFound) {
        this.personFound = personFound;
    }

    public boolean isSearched() {
        return searched;
    }

    public void setSearched(boolean searched) {
        this.searched = searched;
    }

    public String getIPaddress() {
        return IPaddress;
    }

    public void setIPaddress(String IPaddress) {
        this.IPaddress = IPaddress;
    }
    
    public void updateUbsValue(){
        profSaude.setUbs(ejbUbs.findByCnes(ubsId));
    }
    
    public void updateCboValue(){
        profGeral.setCbo(ejbCbo.findByCodigo(cboId));
    }
    
    public void updateMunicipioValue(){
        System.out.println("Updating Municipio List..");
        municipioList = ejbMunicipio.findByUf(estadoId);
        System.out.println("municipioListSize: " + municipioList.size());
    }
    
    public List<Presenca> findParticipantes(Atividade atv)
    {
        //return ejbPartPessoa.findByAtividade(atv);
        return ejbPresenca.findByAtividade(atv);
    }
    
    private void setSave(){
        partPessoa = new ParticipacaoPessoa();
        partLocal = new ParticipacaoLocal();
        partIp = new ParticipacaoIp();
        newPerson = new Pessoa();
        newProfGeral = new ProfGeral();
        newProfSaude = new ProfSaude();
    }
    
    private void resetSave(){
        ubsUpdate = false;
        cboUpdate = false;
        partPessoa = null;
        partLocal = null;
        partIp = null;
        pessoa = null;
        profSaude = null;
        profGeral = null;
        present = false;
        personFound = true;
        searched = false;
        newPerson = null;
    }
    
    private void loadLists(){   
        //estadoId = 10; //SETTING STANDARD VALUE 10 = MARANHAO
        //municipioId = 211130; //SETTING STANDARD VALUE 211130 = SAO LUIS
        estadoId = 0;
        municipioId = 0;
        
        try {
            IPaddress = getIP();
        } catch (NegocioException ex) {
            System.out.println("Error: " + ex);
        }
        
        if(ubsList==null)
            ubsList = ejbUbs.findAll();
        if(cboList==null)
            cboList = ejbCbo.findAll();
        if(estadoList==null)
            estadoList = ejbEstado.findAll();
        
        //municipioList = ejbMunicipio.findByUf(estadoId);
    }
    
    private Date dayafter(Date dt){
        Calendar c = Calendar.getInstance(); 
        c.setTime(dt); 
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        
        return DateUtils.ingnoreTime(dt);
    }  
    
    private String updatePessoa(){
        if(pessoa!=null){
            try {
                ejbPessoa.edit(pessoa);
                System.out.println("Changing Pessoa... " + pessoa.getNome());
            }
            catch(Exception ex){
                System.out.println("Edit Pessoa Error... " + ex);
                JsfUtil.addErrorMessage("formConfirm", "Houve falha ao salvar alterações de Pessoa.");
            }
        }
        
        return null;
    }
    
    private String updateProfGeral(){
        if(profGeral!=null){
            try {
                ejbProfGeral.edit(profGeral);
                System.out.println("Changing Profession... " + profGeral.getCbo().getNome());
            }
            catch(Exception ex){
                System.out.println("Edit Profession Error... " + ex);
                JsfUtil.addErrorMessage("formConfirm", "Houve falha ao salvar alterações de Profissão.");
            }
        }
        
        return null;
    }
    
    private String updateProfSaude(){
        if(profSaude!=null){
            try {
                ejbProfSaude.edit(profSaude);
                System.out.println("Changing Health Link... " + profSaude.getCns());
            }
            catch(Exception ex){
                System.out.println("Edit Health Link Error... " + ex);
                JsfUtil.addErrorMessage("formConfirm", "Houve falha ao salvar alterações de Vínculo de Profissão de Saúde.");
            }
        }
        
        return null;
    }
    
    private String saveParticipacao(){
        try {
                partPessoa.setAtividade(atividade);
                partPessoa.setPessoa(pessoa);
                System.out.println("Trying to register person participation... " + pessoa.getNome());

                if(ejbPartPessoa.findByComposedKey(pessoa, atividade)!=null){
                    JsfUtil.addSuccessMessage("Sua presença já foi confirmada para esta atividade!", "formList");
                    return logout();
                }

                ejbPartPessoa.create(partPessoa);
                try {
                    Municipio mun = ejbMunicipio.findByIbge(municipioId);
                    partLocal.setParticipacao(partPessoa);
                    partLocal.setLocal(mun);
                    if(profSaude!=null)
                        partLocal.setUbs(profSaude.getUbs());
                    else 
                        partLocal.setUbs(null);

                    partIp.setParticipacao(partPessoa);
                    partIp.setIp(IPaddress);

                    ejbPartLocal.create(partLocal);
                    //ejbPartIp.create(partIp);
                    JsfUtil.addSuccessMessage("Presença confirmada!", "formList");
                    present = true;
                    return logout();
                }
                catch (Exception ex){
                    ejbPartPessoa.remove(partPessoa);
                    JsfUtil.addErrorMessage(ex, "Não foi possível registrar local", "formConfirm");
                }
        }
        catch(Exception ex){
            System.out.println(ex);
            JsfUtil.addErrorMessage(ex, "Não foi possível registrar participação de pessoa", "formConfirm");
        }
                
        return null;
    }
    
    private String getIP() throws NegocioException {
        URLConnector ipCon;
        JSONIP ipJson;
        String ip;
        
        try {
            ipCon = new URLConnector("http://jsonip.com/");
            ipJson = new JSONIP(ipCon.getPageContent());
            System.out.println(ipJson.getSource());
            ip = ipJson.toIP();
            
            return ip;
        }
        catch(Exception e) {
            System.out.println("Error in getting ip address... " + e);
            throw new NegocioException("Not possible connecting to URL");
        }
    }
    
    private String prepareCreate(int key) {
        searched = true;
        personFound = true;
        loadLists();
        
        switch(key){
            case 1:
                pessoa = newPerson;
                newPerson = new Pessoa();
                break;
            case 2:
                profGeral = newProfGeral;
                newProfGeral = new ProfGeral();
                break;
            case 3:
                profSaude = newProfSaude;
                newProfSaude = new ProfSaude();
                break;
            default:
                System.out.println("What r u doing? There is no such key!");
        }
        
        getRecord();
        return null;
    }
    
    public String logIn(){
        search_tag = null;
        atividade = ejbAtividade.findById(password);
        if(atividade!=null){
            Date today = DateUtils.ingnoreTime(new Date());
            Date atvdt = DateUtils.ingnoreTime(atividade.getDt());
            Date now = DateUtils.ingnoreDate(new Date(), new Date());
            Date atvtime = DateUtils.ingnoreDate(atividade.getHrInicio(), new Date());
            if(atividade.getStatus().getId() == 2){
                if((today.equals(atvdt)&&now.after(atvtime)) || (today.equals(dayafter(atvdt))&&now.before(atvtime))){
                    JsfUtil.addSuccessMessage("Atividade encontrada: \"" + atividade.getTema() + "\"", "searchRegistro");
                    resetSave();
                    return "/open/registro_presenca.xhtml";
                }
                else {
                    JsfUtil.addErrorMessage("formList","Atividade não liberada.");
                }
            }
            else {
                JsfUtil.addErrorMessage("formList", "Tempo limite de registro de presença esgotado ou atividade cancelada.");
            }
            JsfUtil.addErrorMessage("formList", "Não é possível registrar presença.");
        }
        else {
            JsfUtil.addErrorMessage("formList", "Não foi possível encontrar atividade.");
        }
        
        return null;
    }
    
    public String logout(){
        resetSave();
        return "/lista.xhtml";
    }
    
    public void onButtonClick(AjaxBehaviorEvent e){
        getRecord();
    }
    
    public void getRecord(){
        resetSave();
        loadLists();
        setSave();
        pessoa = ejbPessoa.getByCPF(search_tag);
        
        searched = true;
        if(pessoa==null){  
            pessoa = ejbPessoa.getByEmail(search_tag);
            if(pessoa==null){
                profSaude = ejbProfSaude.getByCNS(search_tag);
                if(profSaude==null){
                    System.out.println("Not possible finding person...");
                    if(searched)
                        JsfUtil.addErrorMessage("formConfirm", "Não foi possível encontrar um registro de Pessoa.");
                    personFound = false;
                }
                else {
                    pessoa = profSaude.getPessoa();
                }
            }
        }
        
        if(pessoa!=null) {
            if(ejbPartPessoa.findByComposedKey(pessoa, atividade)!=null){
                JsfUtil.addSuccessMessage("Sua presença já foi confirmada para esta atividade!", "formConfirm");
            }
            
            System.out.println("Person found: " + pessoa.getNome());
            profGeral = ejbProfGeral.getByPessoa(pessoa);
            if(profGeral!=null){
                System.out.println("Profession found: " + profGeral.getCbo().getNome());
                profSaude = ejbProfSaude.getByCNS(search_tag);
                if(profSaude!=null){
                    System.out.println("Health Link found: " + profSaude.getUbs().getNome() + "(" + profSaude.getCns() + ")");
                }
                else {
                    profSaude = ejbProfSaude.getByPessoa(pessoa);
                    if(profSaude!=null){
                        System.out.println("Health Link found: " + profSaude.getUbs().getNome() + "(" + profSaude.getCns() + ")");
                    }
                    else {
                        System.out.println("Not possible finding health link...");
                        if(searched)
                             JsfUtil.addErrorMessage("formConfirm", "Não foi possível encontrar um vínculo de profissional de Saúde.");
                    }
                }
            }
            else {
                System.out.println("Not possible finding profession...");
                if(searched)
                     JsfUtil.addErrorMessage("formConfirm", "Não foi possível encontrar nenhum registro de Profissão.");
            }
        }
    }
    
    public String confirmPresence(){
        String retorno;
        
        if(municipioId!=0 && estadoId!=0){
            try {
                if(!pessoa.getEmail().equalsIgnoreCase("")){
                    updatePessoa();
                    updateProfGeral();
                    updateProfSaude();
                    retorno = saveParticipacao();
                    getRecord();
                    return retorno;
                }
                else {
                    JsfUtil.addErrorMessage("formConfirm", "Não é possível confirmar presença sem cadastrar email!!");
                }
            }
            catch (Exception ex){
                JsfUtil.addErrorMessage(ex, "Não foi possível registrar a presença", "formConfirm");
            }   
        }
        else {
            JsfUtil.addErrorMessage( "formConfirm", "Deve ser informado o local de acesso.");
        }
        
        return null;
    }  
    
    public String createPessoa() {
        try {            
            newPerson.setSobrenome(newPerson.getSobrenome().trim());
            ejbPessoa.create(newPerson);
            JsfUtil.addSuccessMessage(newPerson.getNome() + " " + newPerson.getSobrenome() + " cadastrado(a) com sucesso!", "formConfirm");
            JsfUtil.addSuccessMessage("Verifique seus dados e clique em Confirmar Presença.", "formConfirm");
            return prepareCreate(1);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("formConfirm", "Erro ao cadastrar Pessoa! Clique novamente no botão Cadastre-se..");
            return null;
        }
    }

    public String createProfGeral() {
        try {
            if(ejbProfGeral.getByPessoa(pessoa)==null){                
                newProfGeral.setCbo(ejbCbo.findByCodigo(newCboId));
                newProfGeral.setMunicipio(ejbMunicipio.findByIbge(municipioId));
                newProfGeral.setPessoa(pessoa);
                ejbProfGeral.create(newProfGeral);
                JsfUtil.addSuccessMessage(newProfGeral.getCbo().getNome() + " cadastrado com sucesso!", "formConfirm");
                JsfUtil.addSuccessMessage("Verifique seus dados e clique em Confirmar Presença.", "formConfirm");
                return prepareCreate(2);
            }
            else {
                JsfUtil.addSuccessMessage(pessoa.getNome() + " já possui cadastro de profissão!", "formConfirm");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            JsfUtil.addErrorMessage("formConfirm", "Erro ao cadastrar Profissão! Clique novamente no botão Cadastrar..");
        }
        
        return null;
    }

    public String createProfSaude() {
        try {
            if(ejbProfSaude.getByPessoa(pessoa)==null){
                if(ejbProfSaude.getByCNS(newProfSaude.getCns())==null){
                    newProfSaude.setPessoa(pessoa);
                    newProfSaude.setUbs(ejbUbs.findByCnes(newCnesId));
                    newProfSaude.setEquipe("null");
                    ejbProfSaude.create(newProfSaude);
                    JsfUtil.addSuccessMessage(newProfSaude.getCns()+ " cadastrado com sucesso!", "formConfirm");
                    JsfUtil.addSuccessMessage("Verifique seus dados e clique em Confirmar Presença.", "formConfirm");
                    return prepareCreate(3);
                }
                else {
                    JsfUtil.addSuccessMessage("Já existe um cadastro de vínculo profissional de " + newProfSaude.getCns(), "formConfirm");
                }
            }
            else {
                JsfUtil.addSuccessMessage(pessoa.getNome() + " já possui cadastro de vínculo profissional!", "formConfirm");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            JsfUtil.addErrorMessage("formConfirm", "Erro ao cadastrar vínculo profissional! Clique novamente no botão Cadastrar..");
        }
        
        return null;
    }
    
//    public boolean setLocale(){
//        boolean flag = false;
//        Locale local = null;
//        try {
//            local = getLocale();
//        } catch (NegocioException ex) {
//            JsfUtil.addErrorMessage("formConfirm", "Não foi possível encontrar sua localização. Por favor, informe seu Estado e cidade!");
//        }
//        
//        List<Estado> e;
//        List<Municipio> m;
//        
//        if(local!=null){
//            e = ejbEstado.findByDescription(local.getRegion().trim());
//            if(e!=null && e.size()>0){
//                estadoId = e.get(0).getId();
//                m = ejbMunicipio.findByDescription(local.getCity().trim());
//                if(m!=null && m.size() > 0){
//                    municipioId = m.get(0).getIbge();
//                    updateMunicipioValue();
//                    flag = true;
//                }
//            }        
//        }
//        
//        return flag;
//    }
    
    
//    private Locale getLocale() throws NegocioException{
//        URLConnector localCon;
//        JSONLocal localJson;
//        String ip, linkLocale;
//        Locale locale;
//        
//        ip = getIP();
//        if(ip!=null){
//            linkLocale = "http://api.ipinfodb.com/v3/ip-city/?key="+KEY+"&ip="+ip+"&format=json";
//        }
//        else {
//            linkLocale = null;
//        }
//        
//        try {
//            localCon = new URLConnector(linkLocale);
//            localJson = new JSONLocal(localCon.getPageContent());
//            System.out.println(localJson.getSource());
//            locale = localJson.toLocale();
//            
//            return locale;
//        }
//        catch (Exception e){
//            System.out.println("Error in getting locale... " + e);
//            throw new NegocioException("Not possible connecting to URL");
//        }
//    }
    
    static public class ColumnModel implements Serializable {
 
        private String header;
        private String property;
 
        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }
 
        public String getHeader() {
            return header;
        }
 
        public String getProperty() {
            return property;
        }
    }
    
    public void onRowSelect(SelectEvent event){
        this.pessoa = ((Pessoa) event.getObject());
        this.personFound = true;
        this.searched = true;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import dashboard.entities.MunicipiosCoordenadas;
import dashboard.sessions.MunicipiosCoordenadasFacade;
import entities.Atividade;
import entities.AtividadeTbr;
import entities.Avaliacoes;
import entities.Cbo;
import entities.Estado;
import entities.Municipio;
import entities.ObjetoAprendizagem;
import entities.ParticipacaoPessoa;
import entities.Pessoa;
import entities.Presenca;
import entities.ProfGeral;
import entities.Tipo;
import entities.Ubs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import jsf.util.CodigoDecS;
import jsf.util.DateUtils;
import jsf.util.HashmapUtils;
import jsf.util.TipoCounter;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import sessions.AtividadeFacade;
import sessions.AtividadeTbrFacade;
import sessions.AvaliacoesFacade;
import sessions.CboFacade;
import sessions.EstadoFacade;
import sessions.MunicipioFacade;
import sessions.ObjetoAprendizagemFacade;
import sessions.ParticipacaoPessoaFacade;
import sessions.PresencaFacade;
import sessions.ProfGeralFacade;
import sessions.TipoFacade;
import sessions.UbsFacade;

/**
 *
 * @author nlima.huufma
 */

@ManagedBean(name = "graficosController")
@ViewScoped
public class GraficosController  implements Serializable{
    @EJB
    private AtividadeFacade ejbAtividade;

    @EJB
    private PresencaFacade ejbPartPessoa;
    
    @EJB
    private CboFacade ejbCbo;
    
    @EJB
    private ProfGeralFacade ejbProfGeral;
    
    @EJB
    private TipoFacade ejbTipo;
    
    @EJB
    private MunicipiosCoordenadasFacade ejbCoordenadas;
    
    @EJB
    private AtividadeTbrFacade ejbAtvTbr;
    
    @EJB
    private EstadoFacade ejbEstado;
    
    @EJB
    private MunicipioFacade ejbMunicipio;
    
    @EJB
    private UbsFacade ejbUbs;
    
    @EJB
    private ObjetoAprendizagemFacade ejbObjeto;
    
    @EJB
    private AvaliacoesFacade ejbAvaliacoes;
    
    int totalAtividadesMes = 0, totalPartMes = 0;
    String currentMonthYear, finalidade = "";
    Date dt1, dt2;
    
    List<Municipio> municipios = new ArrayList();
    List<Estado> estados = new ArrayList();
    List<Atividade> filtered = new ArrayList();
    List<Presenca> presencas = new ArrayList();
    
    List<Municipio> municipiosSelect = new ArrayList();    
    
    int estadoSelectedId;
    int municipioSelectedId;
    int year;
    int partTotal = 0;
    
    int chartTotal = 0;
    
    int atvSummary[] = new int [4];
    
    String dataArrayProfissoes = "";
    String dataArrayDecs = "";
    String dataArrayEstados = "";
    String dataArrayMunicipios = "";
    String dataArrayLatLong = "";
    String dataTotais = "";
    String dataArrayUbs = "";
    String dataArrayObjetos = "";
    
    String dataArrayP1, dataArrayP2, dataArrayP3, dataArrayP5;
    
    String barChartTitle = "";
    
    boolean sumAtividades = false;
    boolean top15Prof = false;

    
    public void setDefaultDates() throws ParseException
    {
        Date today = new Date();
        int dateNumbers[] = DateUtils.getSeparatedDateNumbers(today);
        
        dt2 = today;
        
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        if(dateNumbers[1] == 1)
        {
            dateNumbers[1] = 12;
            dateNumbers[2]--;
        }
        else
        {
            dateNumbers[1]--;
        }
        
        String dateStr =  dateNumbers[2]+"-"+((dateNumbers[1]<10)?"0":"") + dateNumbers[1]+"-01";
        dt1 = dateFormater.parse(dateStr);
    }

    public String getDataArrayP1() {
        return dataArrayP1;
    }

    public void setDataArrayP1(String dataArrayP1) {
        this.dataArrayP1 = dataArrayP1;
    }

    public String getDataArrayP2() {
        return dataArrayP2;
    }

    public void setDataArrayP2(String dataArrayP2) {
        this.dataArrayP2 = dataArrayP2;
    }

    public String getDataArrayP3() {
        return dataArrayP3;
    }

    public void setDataArrayP3(String dataArrayP3) {
        this.dataArrayP3 = dataArrayP3;
    }

    public String getDataArrayP5() {
        return dataArrayP5;
    }

    public void setDataArrayP5(String dataArrayP5) {
        this.dataArrayP5 = dataArrayP5;
    }

    public int getChartTotal() {
        return chartTotal;
    }

    public void setChartTotal(int chartTotal) {
        this.chartTotal = chartTotal;
    }
    
    public String getDataArrayObjetos() {
        return dataArrayObjetos;
    }

    public void setDataArrayObjetos(String dataArrayObjetos) {
        this.dataArrayObjetos = dataArrayObjetos;
    }

    public String getDataArrayUbs() {
        return dataArrayUbs;
    }

    public void setDataArrayUbs(String dataArrayUbs) {
        this.dataArrayUbs = dataArrayUbs;
    }

    public List<Municipio> getMunicipiosSelect() {
        return municipiosSelect;
    }

    public void setMunicipiosSelect(List<Municipio> municipiosSelect) {
        this.municipiosSelect = municipiosSelect;
    }

    public int getMunicipioSelectedId() {
        return municipioSelectedId;
    }

    public void setMunicipioSelectedId(int municipioSelectedId) {
        this.municipioSelectedId = municipioSelectedId;
    }

    public int getPartTotal() {
        return partTotal;
    }

    public void setPartTotal(int partTotal) {
        this.partTotal = partTotal;
    }

    public int[] getAtvSummary() {
        return atvSummary;
    }

    public void setAtvSummary(int[] atvSummary) {
        this.atvSummary = atvSummary;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDataTotais() {
        return dataTotais;
    }

    public void setDataTotais(String dataTotais) {
        this.dataTotais = dataTotais;
    }

    public String getBarChartTitle() {
        return barChartTitle;
    }

    public void setBarChartTitle(String barChartTitle) {
        this.barChartTitle = barChartTitle;
    }

    public String getDataArrayMunicipios() {
        return dataArrayMunicipios;
    }

    public void setDataArrayMunicipios(String dataArrayMunicipios) {
        this.dataArrayMunicipios = dataArrayMunicipios;
    }

    public int getEstadoSelectedId() {
        return estadoSelectedId;
    }

    public void setEstadoSelectedId(int estadoSelectedId) {
        this.estadoSelectedId = estadoSelectedId;
    }

    public EstadoFacade getEjbEstado() {
        return ejbEstado;
    }

    public void setEjbEstado(EstadoFacade ejbEstado) {
        this.ejbEstado = ejbEstado;
    }

    public String getDataArrayLatLong() {
        return dataArrayLatLong;
    }

    public void setDataArrayLatLong(String dataArrayLatLong) {
        this.dataArrayLatLong = dataArrayLatLong;
    }

    public String getDataArrayEstados() {
        return dataArrayEstados;
    }

    public void setDataArrayEstados(String dataArrayEstados) {
        this.dataArrayEstados = dataArrayEstados;
    }

    public String getDataArrayProfissoes() {
        return dataArrayProfissoes;
    }

    public void setDataArrayProfissoes(String dataArrayProfissoes) {
        this.dataArrayProfissoes = dataArrayProfissoes;
    }

    public boolean isTop15Prof() {
        return top15Prof;
    }

    public void setTop15Prof(boolean top15Prof) {
        this.top15Prof = top15Prof;
    }

    public boolean isSumAtividades() {
        return sumAtividades;
    }

    public void setSumAtividades(boolean sumAtividades) {
        this.sumAtividades = sumAtividades;
    }

    
    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Atividade> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<Atividade> filtered) {
        this.filtered = filtered;
    }

    public List<Presenca> getPresencas() {
        return presencas;
    }

    public void setPresencas(List<Presenca> presencas) {
        this.presencas = presencas;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
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
    
    public String getCurrentMonthYear() {
        return currentMonthYear;
    }

    public void setCurrentMonthYear(String currentMonth) {
        this.currentMonthYear = currentMonth;
    }
    

    public int getTotalAtividadesMes() {
        return totalAtividadesMes;
    }

    public void setTotalAtividadesMes(int totalAtividadesMes) {
        this.totalAtividadesMes = totalAtividadesMes;
    }

    public int getTotalPartMes() {
        return totalPartMes;
    }

    public void setTotalPartMes(int totalPartMes) {
        this.totalPartMes = totalPartMes;
    }

    public String getDataArrayDecs() {
        return dataArrayDecs;
    }

    public void setDataArrayDecs(String dataArrayDecs) {
        this.dataArrayDecs = dataArrayDecs;
    }
    
    public GraficosController()
    {
        
    }
    
    
    public String getAttendanceRowsCurrentMonth()
    {
        Date today = new Date();
        int todayNumber = DateUtils.getSeparatedDateNumbers(today)[0];
        int dateNumbers[] = DateUtils.getSeparatedDateNumbers(today);
        setCurrentMonthYear(DateUtils.monthNumberToMonthName(dateNumbers[1])+"/"+dateNumbers[2]);
        ArrayList rows = new ArrayList();
        String collumns = "['Dia', 'Atividades', 'Participações']";
        rows.add(collumns);
        for(int i = 1; i <= todayNumber; i++)
        {
            dateNumbers[0] = i;
            String date = dateNumbers[2]+"-"+((dateNumbers[1]<10)?"0":"") + dateNumbers[1]+"-"+((dateNumbers[0]<10)?"0":"") + dateNumbers[0];
            List<Atividade> atividades = ejbAtividade.findByDay(date);
            
            partTotal = 0;
            
            for(Atividade atv : atividades)
            {
                List<Presenca> parts = ejbPartPessoa.findByAtividade(atv);
                if(parts != null)
                    partTotal += parts.size();
            }
            totalAtividadesMes+= atividades.size();
            totalPartMes+=partTotal;
            String row = "["+i+", "+atividades.size()+", "+partTotal+" ]";
            System.out.println("size: " + row);
            rows.add(row);
        }
        System.out.println("rows: " + rows.toString());
        
        return rows.toString();
    }
    
    
    public String createPieChartAtividadesTipoNTS(List<TipoCounter> atividadesSomadas)
    {
        ArrayList rows = new ArrayList();
        rows.add("['Tipo', 'Quantidade']");
        for(TipoCounter t : atividadesSomadas)
        {
            String row = "['"+t.getTipo().getDescricao()+"', "+(t.getTotals()[0] + t.getTotals()[1] + t.getTotals()[2])+" ]";
            System.out.println("row: " + row);
            rows.add(row);
        }
        return rows.toString();
    }
        
    
    public void chooseReport()
    {
        sumAtividades = true;
        
        if(sumAtividades)
            getSumAtividades();
    }
    
    public void getDecs() throws JSONException, FileNotFoundException, UnsupportedEncodingException
    {
        chartTotal = 0;
        getSumAtividades();
        JSONArray jSONArray = new JSONArray();
        
        Map decs = new Hashtable();
        for(Atividade a : filtered)
        {
            if(a.getCodDecs()!=null && !a.getCodDecs().equalsIgnoreCase(""))
                decs.put(a.getCodDecs(), 0);
        }
        
        for(Atividade a : filtered)
        {
            if(a.getCodDecs() != null && !a.getCodDecs().equalsIgnoreCase(""))
            {
                if(decs.containsKey(a.getCodDecs()))
                {
                    decs.put(a.getCodDecs(), (int)decs.get(a.getCodDecs()) + 1);
                }
            }
        }
        
        Map sorted = HashmapUtils.sortByValue(decs);
        int i = 0;
        ArrayList rows = new ArrayList();
        String collumns = "['Decs', 'Total']";
        rows.add(collumns);
        for(Object key: sorted.keySet())
        {
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            DecSController decsController 
                = (DecSController) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "decsController");
            CodigoDecS codigo = decsController.findDescritorByCod(key.toString());
            
            if(i < 10){
                i++;
                String row = "['"+codigo.getNome()+"', "+(int)decs.get(key)+"]";
                rows.add(row);
            }
            
            JSONObject j = new JSONObject();
            j.put("codigo", key.toString());
            j.put("descricao", (codigo != null) ? codigo.getNome():"");
            j.put("quantidade", (int)decs.get(key));
            
            jSONArray.put(j);
            chartTotal+= (int)decs.get(key);
        }
        top15Prof = true;
        dataArrayDecs =  rows.toString();
        barChartTitle = "'Os "+numberToWordBR(i)+" descritores com maior número de ocorrências no período de "
                        + DateUtils.dateFormatBR(dt1)+" a " 
                        + DateUtils.dateFormatBR(dt2) + ". São Luís - MA'";
        
        String jsonReportProfissionais = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/dataDecsGrafico.json"));
        
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportProfissionais );
        }
        System.out.println(dataArrayDecs);
    }
    
    public void getEstadosParticipantes() throws JSONException, FileNotFoundException, UnsupportedEncodingException
    {
        chartTotal = 0;
        getSumAtividades();
        JSONArray jSONArray = new JSONArray();
        
        Map estados = new Hashtable();
        for(Estado e : this.estados)
        {
            estados.put(e.getDescricao(), 0);
        }
        ArrayList coords = new ArrayList();
        for(Presenca p : presencas)
        {
            if(p.getLocal() != null)
            {
                String estadoNome = p.getLocal().getUf().getDescricao();
                if(estados.containsKey(estadoNome))
                {
                    //String ibgeStr = Integer.toString(p.getLocal().getIbge()) + "0";
                    //Integer ibge = Integer.parseInt(ibgeStr);
                    MunicipiosCoordenadas coordenadas = ejbCoordenadas.findByCodigo(p.getLocal().getIbge());
                    if(coordenadas != null)
                    {
                        String row = "{lat: " + coordenadas.getLatitude() + ", lng: " + coordenadas.getLongitude() + ", count: 1}";
                        //System.out.println(row);
                        coords.add(row);
                    }
                    estados.put(estadoNome, (int)estados.get(estadoNome) + 1);
                }
            }
        }
        
        Map sorted = HashmapUtils.sortByValue(estados);
        int i = 0;
        ArrayList rows = new ArrayList();
        
        String collumns = "['Estado', 'Total']";
        rows.add(collumns);
        for(Object key: sorted.keySet())
        {
            if(i < 10){
                i++;
                String row = "['"+key.toString()+"', "+(int)estados.get(key)+"]";
                rows.add(row);
            }
            
            JSONObject j = new JSONObject();
            j.put("estado", key.toString());
            j.put("quantidade", estados.get(key));
            
            jSONArray.put(j);
            chartTotal += (int)estados.get(key);
        }
        top15Prof = true;
        dataArrayEstados =  rows.toString();
        dataArrayLatLong = coords.toString();
        barChartTitle = "'Os "+numberToWordBR(i)+" estados com maior número de participações no período de "
                        + DateUtils.dateFormatBR(dt1)+" a " 
                        + DateUtils.dateFormatBR(dt2) + ". São Luís - MA'";
        
        String jsonReportEstados = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/dataEstadosGrafico.json"));
        
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportEstados );
        }
    }
    
    public void getProfissoesParticipantes() throws JSONException, FileNotFoundException, UnsupportedEncodingException, ParseException
    {
        chartTotal = 0;
        getSumAtividades();
        JSONArray jSONArray = new JSONArray();
        Map profissoes = new Hashtable();
        for(Cbo cbo : ejbCbo.findAll())
        {
            profissoes.put(cbo.getNome(), 0);
        }
        
        for(Presenca pe : presencas)
        {
            Pessoa p = pe.getPessoa();
            ProfGeral prof = ejbProfGeral.getByPessoa(p);
            if(prof != null)
            {
                String profNome = prof.getCbo().getNome();
                if(profissoes.containsKey(profNome))
                {
                    profissoes.put(profNome, (int)profissoes.get(profNome) + 1);
                }
            }
        }
        
        Map sorted = HashmapUtils.sortByValue(profissoes);
        int i = 0;
        ArrayList rows = new ArrayList();
        String collumns = "['Profissão', 'Total']";
        rows.add(collumns);
        for(Object key: sorted.keySet())
        {
            int qtd = (int)profissoes.get(key);
            if(qtd > 0)
            {
                if(i < 10){
                    i++;
                    //System.out.println(key.toString() + " - " + profissoes.get(key));
                    String row = "['"+key.toString()+"', "+qtd+"]";
                    rows.add(row);
                }
                JSONObject j = new JSONObject();
                j.put("profissao", key.toString());
                j.put("quantidade", (int)profissoes.get(key));

                jSONArray.put(j);
                chartTotal += (int)profissoes.get(key);
            }
        }
        top15Prof = true;
        dataArrayProfissoes =  rows.toString();
        String jsonReportProfissionais = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/dataProfissionaisGrafico.json"));
        barChartTitle = "'As "+numberToWordBR(i)+" profissões com maior número de participações no período de "
                        + DateUtils.dateFormatBR(dt1)+" a " 
                        + DateUtils.dateFormatBR(dt2) + ". São Luís - MA'";
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportProfissionais );
        }
    }
    
    public void getPresencaByMunicipio() throws JSONException, FileNotFoundException, UnsupportedEncodingException
    {
        chartTotal = 0;
        System.out.println("municipio " + municipioSelectedId);
        List<Presenca> parts;
        List<Atividade> atvs;
        Municipio mun = null;
        if(municipioSelectedId != 0)
        {
            mun = ejbMunicipio.find(municipioSelectedId);
            parts  = ejbPartPessoa.findByLocal(mun);
            atvs = ejbAtividade.getFilteredAtividades(dt1, dt2, 4);
        }
        else
        {
            getSumAtividades();
            parts = presencas;
            atvs = filtered;
        }
        
        
        int esus = 0;
        int saudeMental = 0;
        int aps = 0;
        presencas = new ArrayList();
        JSONArray jSONArray = new JSONArray();
        Map ubs = new Hashtable();
        
        
        for(Presenca p : parts)
        {
            for(Atividade atv : atvs)
            {
                if(p.getAtividade().equals(atv))
                {
                    presencas.add(p);
                    JSONObject j = new JSONObject();
                    j.put("nome", p.getPessoa().getNome() + " " + p.getPessoa().getSobrenome());
                    ProfGeral prof = ejbProfGeral.getByPessoa(p.getPessoa());
                    j.put("profissao", (prof != null) ? prof.getCbo().getNome() : "-");

                    if(p.getUbs() != null)
                    {
                        j.put("ubs", p.getUbs().getNome());
                        int cnes = p.getUbs().getCnes();

                        if(ubs.containsKey(cnes))
                        {
                            ubs.put(cnes, (int)ubs.get(cnes) + 1);
                        }
                        else
                            ubs.put(cnes, 1);
                    }
                    else
                        j.put("ubs", "-");
                    
                    AtividadeTbr atvTbr = ejbAtvTbr.findByAtividade(atv);
                    j.put("atv", p.getAtividade().getTema());
                    if(atvTbr != null)
                    {
                        j.put("finalidade", atvTbr.getFinalidade());
                        
                        if(atvTbr.getFinalidade().toLowerCase().contains("sus"))
                            esus++;
                        else if(atvTbr.getFinalidade().toLowerCase().contains("saúde mental"))
                            saudeMental++;
                        else if(atvTbr.getFinalidade().toLowerCase().contains("aps"))
                            aps++;
                    }
                    else
                        j.put("finalidade", "-");
                    
                    if(p.getUbs()!= null && prof != null)
                        j.put("status", "Válido");
                    else
                        j.put("status", "Inválido");
                    
                    
                    jSONArray.put(j);
                }
            }
        }
        
        ArrayList rows = new ArrayList();
        String collumns = "['Finalidade', 'Total']";
        rows.add(collumns);
        String col1 = "['E-SUS', "+esus+"]";
        rows.add(col1);
        String col2 = "['APS', "+aps+"]";
        rows.add(col2);
        String col3 = "['Saúde Mental', "+saudeMental+"]";
        rows.add(col3);
        chartTotal += esus+aps+saudeMental;
        
        
        
//        for(Object key: ubs.keySet())
//        {
//            if((int)ubs.get(key) > 0)
//            {
//                Ubs u = ejbUbs.findByCnes((int)key);
//                JSONObject j = new JSONObject();
//                j.put("ubs", u.getNome());
//                j.put("quantidade", (int)ubs.get(key));
//
//                jSONArray.put(j);
//            }
//        }
            
        top15Prof = true;
        dataArrayUbs =  rows.toString();
        String jsonReportProfissionais = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/dataUbsGrafico.json"));
        barChartTitle = "'Número de participações registradas online"+ ((mun != null)? (" no município "+mun.getNome()) : " no estado " + ejbEstado.find(estadoSelectedId).getDescricao())  + " no período de "
                        + DateUtils.dateFormatBR(dt1)+" a " 
                        + DateUtils.dateFormatBR(dt2) + ". São Luís - MA'";
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportProfissionais );
        }
    }
    
    public void getMunicipiosParticipantesByEstado() throws JSONException, FileNotFoundException, UnsupportedEncodingException
    {
        chartTotal = 0;
        //List<Atividade> atvs = ejbAtividade.getFilteredAtividades(dt1, dt2, 4);
        getSumAtividades();
        List<Atividade> atvs = filtered;
        System.out.println("Estado ID " + estadoSelectedId);
        JSONArray jSONArray = new JSONArray();
        Map municipios = new Hashtable();
        
        if(estadoSelectedId != 0)
        {
            for(Municipio m : ejbMunicipio.findByUf(estadoSelectedId))
            {
                municipios.put(m.getIbge(), 0);
            }
        }
                
        for(Atividade a : atvs)
        {
            if(!finalidade.equals(""))
            {
                AtividadeTbr atvTbr = ejbAtvTbr.findByAtividade(a);
                if(atvTbr != null)
                    if(atvTbr.getFinalidade().toLowerCase().contains(finalidade.toLowerCase()))
                    {
                        for(Presenca p : ejbPartPessoa.findByAtividade(a))
                        {
                            if(p.getLocal() != null)
                            {
                                if(estadoSelectedId != 0){
                                    if(p.getLocal().getUf().getId().equals(estadoSelectedId))
                                    {
                                        int municipio = p.getLocal().getIbge();
                                        if(municipios.containsKey(municipio))
                                        {
                                            municipios.put(municipio, (int)municipios.get(municipio) +1);
                                        }
                                    }
                                } else{
                                    int municipio = p.getLocal().getIbge();
                                    if(municipios.containsKey(municipio))
                                        municipios.put(municipio, (int)municipios.get(municipio) +1);
                                    else
                                        municipios.put(municipio, 1);
                                }
                            }
                        }
                    }
            }
            else
            {
                for(Presenca p : ejbPartPessoa.findByAtividade(a))
                {
                    if(p.getLocal() != null)
                    {
                        if(estadoSelectedId != 0){
                            if(p.getLocal().getUf().getId().equals(estadoSelectedId))
                            {
                                int municipio = p.getLocal().getIbge();
                                if(municipios.containsKey(municipio))
                                {
                                    municipios.put(municipio, (int)municipios.get(municipio) +1);
                                }
                            }
                        } else{
                            int municipio = p.getLocal().getIbge();
                            if(municipios.containsKey(municipio))
                                municipios.put(municipio, (int)municipios.get(municipio) +1);
                            else
                                municipios.put(municipio, 1);
                        }
                    }
                }
            }
        }
        
        Map sorted = HashmapUtils.sortByValue(municipios);
        int i = 0;
        ArrayList rows = new ArrayList();
        String collumns = "['Municipio', 'Total']";
        rows.add(collumns);
        for(Object key: sorted.keySet())
        {
            String municipio = ejbMunicipio.findByIbge((int)key).getNome();
            int qtd = (int)municipios.get(key);
            if(qtd > 0)
            {
                if(i < 10){
                    i++;
                        String row = "['"+municipio+"', "+qtd+"]";
                        rows.add(row);
                }
                JSONObject j = new JSONObject();
                j.put("municipio", municipio);
                j.put("quantidade", (int)municipios.get(key));

                jSONArray.put(j);
                chartTotal += (int)municipios.get(key);
            }
        }
        top15Prof = true;
        dataArrayMunicipios =  rows.toString();
        String jsonReportProfissionais = jSONArray.toString();
        barChartTitle = "'As "+numberToWordBR(i)+" cidades "+ ((estadoSelectedId!=0) ? "no estado " + ejbEstado.find(estadoSelectedId).getDescricao() : "")+" com maior número de participações no período de "
                        + DateUtils.dateFormatBR(dt1)+" a " 
                        + DateUtils.dateFormatBR(dt2) + ". São Luís - MA'";
        
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/dataMunicipiosGrafico.json"));
        
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportProfissionais );
        }
    }
    
    private String getPieChartPerguntas(List<Avaliacoes> avaliacoes)
    {
        Map map = new Hashtable();
        ArrayList rows = new ArrayList();
        String collumns = "['Nível', 'Valor']";
        rows.add(collumns);
        
        String r1="", r2="", r3="", r4="", r5="";
        
        if(avaliacoes.get(0).getPergunta().getId() == 1)
        {
            r1 = "Péssimo"; r2 = "Ruim";
            r3 = "Indiferente"; r4  = "Bom";
            r5 = "Excelente";
        }
        else if(avaliacoes.get(0).getPergunta().getId() == 2)
        {
            r1 = "Muito Irrelevante"; r2 = "Irrelevante";
            r3 = "Regular"; r4  = "Relevante";
            r5 = "Muito Relevante";
        }
        else if(avaliacoes.get(0).getPergunta().getId() == 3)
        {
            r1 = "Contribuiu Nada"; r2 = "Contribuiu Pouco";
            r3 = "Indiferente"; r4  = "Contribuiu";
            r5 = "Contribuiu Muito";
        }
        else if(avaliacoes.get(0).getPergunta().getId() == 5)
        {
            r1 = "Muito Insatisfeito"; r2 = "Insatisfeito";
            r3 = "Indiferente"; r4  = "Satisfeito";
            r5 = "Muito Satisfeito";
        }
        
        
        for(Avaliacoes aval : avaliacoes)
        {
            //System.out.println("Avals " + avaliacoes.get(0).getPergunta().getId() + " : " + aval);
            if(aval != null)
            { 
                int resp = Integer.parseInt(aval.getResposta());
                if(map.containsKey(resp))
                    map.put(resp, (int)map.get(resp) + 1);
                else
                    map.put(resp, 1);
            }
        }
        
        for(Object key : map.keySet())
        {
            int resp = (int)key;
            String row ="";
            if(resp == 1)
                row = "['"+r1+"', "+map.get(resp)+"]";
            else if(resp == 2)
                row = "['"+r2+"', "+map.get(resp)+"]";
            else if(resp == 3)
                row = "['"+r3+"', "+map.get(resp)+"]";
            else if(resp == 4)
                row = "['"+r4+"', "+map.get(resp)+"]";
            else if(resp == 5)
                row = "['"+r5+"', "+map.get(resp)+"]";
            
            rows.add(row);
        }
        
        return rows.toString();
    }
    
    public void getRespostasAvaliacoes() throws JSONException, FileNotFoundException, UnsupportedEncodingException, ParseException
    {
        chartTotal = 0;
        getSumAtividades();
        JSONArray jSONArray = new JSONArray();
        List<Avaliacoes> avaliacoesP1 = new ArrayList();
        List<Avaliacoes> avaliacoesP2 = new ArrayList();
        List<Avaliacoes> avaliacoesP3 = new ArrayList();
        List<Avaliacoes> avaliacoesP5 = new ArrayList();
        List<Avaliacoes> avaliacoesP6 = new ArrayList();
        
        for(Presenca p : presencas)
        {
            Avaliacoes p1 = ejbAvaliacoes.findByComposedKey(p, 1);
            if(p1 != null)
                avaliacoesP1.add(p1);
            Avaliacoes p2 = ejbAvaliacoes.findByComposedKey(p, 2);
            if(p2 != null)
                avaliacoesP2.add(p2);
            Avaliacoes p3 = ejbAvaliacoes.findByComposedKey(p, 3);
            if(p3 != null)
                avaliacoesP3.add(p3);
            Avaliacoes p5 = ejbAvaliacoes.findByComposedKey(p, 5);
            if(p5 != null)
                avaliacoesP5.add(p5);
            Avaliacoes p6 = ejbAvaliacoes.findByComposedKey(p, 6);
            if(p6 != null)
                avaliacoesP6.add(p6);
            if(p1 != null ||p2 != null ||p3 != null ||p5 != null ||p6 != null)
                chartTotal++;
        }
        //chartTotal = avaliacoesP1.size() + avaliacoesP2.size() + avaliacoesP3.size() + avaliacoesP5.size() + avaliacoesP6.size();
        dataArrayP1 = getPieChartPerguntas(avaliacoesP1);
        dataArrayP2 = getPieChartPerguntas(avaliacoesP2);
        dataArrayP3 = getPieChartPerguntas(avaliacoesP3);
        dataArrayP5 = getPieChartPerguntas(avaliacoesP5);
        
        //System.out.println(dataArrayP1.length() + " " + dataArrayP2.length() + " "+dataArrayP3.length() + " "+dataArrayP5.length());
        
        
        for(Avaliacoes aval : avaliacoesP6)
        {
            if(!aval.getResposta().isEmpty())
            {
                ProfGeral profissao = ejbProfGeral.getByPessoa(aval.getPresenca().getPessoa());
                JSONObject j = new JSONObject();
                j.put("pessoa", aval.getPresenca().getPessoa().getNome() + " " +  aval.getPresenca().getPessoa().getSobrenome());
                j.put("prof", profissao.getCbo().getNome());
                j.put("local", aval.getPresenca().getLocal().getNome() + " - " + aval.getPresenca().getLocal().getUf().getSigla());
                j.put("atv", aval.getPresenca().getAtividade().getTema());
                j.put("sugestao", aval.getResposta());
                jSONArray.put(j);
            }
        }
        top15Prof = true;
        String jsonReportProfissionais = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/dataProfissionaisGrafico.json"));
//        barChartTitle = "'As "+numberToWordBR(i)+" profissões com maior número de participações no período de "
//                        + DateUtils.dateFormatBR(dt1)+" a " 
//                        + DateUtils.dateFormatBR(dt2) + ". São Luís - MA'";
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportProfissionais );
        }
    }
    
    public void getSumAtividades()
    {
        String dt1Sql = DateUtils.dateFormatMysql(dt1);
        String dt2Sql = DateUtils.dateFormatMysql(dt2);
        List<Atividade> atvs = ejbAtividade.getFilteredAtividades(dt1, dt2, 4);
        filtered = new ArrayList();
        presencas = new ArrayList();
        municipios = new ArrayList();
        estados = new ArrayList();
        
        int totalParts = 0;
        
        for(Atividade a : atvs)
        {
            if(!finalidade.equals(""))
            {
                AtividadeTbr atvTbr = ejbAtvTbr.findByAtividade(a);
                if(atvTbr != null)
                    if(atvTbr.getFinalidade().toLowerCase().contains(finalidade.toLowerCase()))
                    {
                        for(Presenca p : ejbPartPessoa.findByAtividade(a))
                            presencas.add(p);
                        filtered.add(a);
                    }
            }
            else
            {
                filtered.add(a);
                for(Presenca p : ejbPartPessoa.findByAtividade(a))
                    presencas.add(p);
            }
        }
        
        for(Presenca p : presencas)
        {
            if(p.getLocal() != null)
            {
                if(!checkThereIsEstado(p.getLocal().getUf()))
                    estados.add(p.getLocal().getUf());
                if(!checkThereIsMunicipio(p.getLocal()))
                    municipios.add(p.getLocal());
            }
        }
        
        System.out.println("Atividades " + filtered.size() + " presencas " + presencas.size() + " municipios " + municipios.size() + " estados " + estados.size());
    }
    
    public void getResumoAtividadesYear() throws ParseException
    {
        
        ArrayList rows = new ArrayList();
        for(int i = 0; i<4; i++)
        {
            atvSummary[i] = 0;
        }
        String collumns = "['Mês', 'Atividades', 'Participantes', 'Estados',  'Municípios']";
        rows.add(collumns);
        System.out.println("ano " + year);
        Date today = new Date();
        int currentMonth = DateUtils.getSeparatedDateNumbers(today)[1];
        currentMonth = (DateUtils.getSeparatedDateNumbers(today)[2] == year) ? currentMonth : 12;
        for(int i = 1; i < currentMonth+1; i++)
        {
            String lastDayMonth = getDateMonthYear(i, year);
            
            DateFormat dtFormat = new SimpleDateFormat("MM/dd/yyyy");
            dt2 = dtFormat.parse(lastDayMonth);
            String dt1Str = ( (i < 10) ? "0"+i : i+"") + "/"+"01"+"/"+year;
            dt1 = dtFormat.parse(dt1Str);
            System.out.println("dates: "+ year+" " + dt1.toString() + " " + dt2.toString());
            getSumAtividades();
            String row = "['"+DateUtils.monthNumberToMonthName(i)+"', "+filtered.size()+", "
                                                                       +presencas.size()+", "
                                                                       +estados.size()+", "
                                                                       +municipios.size()+"]";
            atvSummary[0] += filtered.size();
            atvSummary[1] += presencas.size();
            atvSummary[2] += estados.size();
            atvSummary[3] += municipios.size();
            rows.add(row);
        }
        dataTotais = rows.toString();
        top15Prof = true;
    }
    
    public void getObjetosViews() throws JSONException, FileNotFoundException, UnsupportedEncodingException
    {
        chartTotal = 0;
        System.out.println("objetos grafico");
        List<ObjetoAprendizagem> objetos = ejbObjeto.findAll();
        Map objs = new Hashtable();
        
        JSONArray jSONArray = new JSONArray();
        
        for(ObjetoAprendizagem obj : objetos)
        {
            if(obj.getTipo().getId() == 2) //Multimidia
                objs.put(obj.getId(), obj.getNumAcesso());
        }
        
        Map sorted = HashmapUtils.sortByValue(objs);
        
        int i = 0;
        ArrayList rows = new ArrayList();
        String collumns = "['Video', 'Total de Visualizações']";
        rows.add(collumns);
        
        for(Object key: sorted.keySet())
        {
            ObjetoAprendizagem obj = ejbObjeto.find((Integer)key);
            
            if(obj != null)
            {
                if(i < 10){
                    i++;
                    String row = "['"+obj.getTema()+"', "+obj.getNumAcesso()+"]";
                    rows.add(row);
                }

                
                JSONObject j = new JSONObject();
                j.put("objeto", obj.getTema());
                j.put("quantidade", obj.getNumAcesso());
                chartTotal += obj.getNumAcesso();
                jSONArray.put(j);
            }
        }
        
        top15Prof = true;
        dataArrayObjetos =  rows.toString();
        String jsonReportProfissionais = jSONArray.toString();
        barChartTitle = "'Os 10 objetos de aprendizagem com maior número de visualizações no Youtube."
                        + " São Luís - MA'";
        
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/dataObjetosGrafico.json"));
        
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonReportProfissionais );
        }
    }
    
    private boolean checkThereIsMunicipio(Municipio municipio)
    {
        for(Municipio m : municipios)
        {
            if(m.getIbge() == municipio.getIbge())
                return true;
        }
        return false;
    }
    
    private boolean checkThereIsEstado(Estado estado)
    {
        for(Estado e : estados)
        {
            if(e.getId() == estado.getId())
                return true;
        }
        return false;
    }
    
    private String numberToWordBR(int value)
    {
        switch(value)
        {
            case 1:
                return "";
            case 2:
                return "duas";
            case 3:
                return "três";
            case 4:
                return "quatro";
            case 5:
                return "cinco";
            case 6:
                return "seis";    
            case 7:
                return "sete";
            case 8:
                return "oito";
            case 9:
                return "nove";
            case 10:
                return "dez";
            default:
                return "";
            
        }
    }
    
    private static String getDateMonthYear(int month, int year) 
    {
        Calendar calendar = Calendar.getInstance();
        // passing month-1 because 0-->jan, 1-->feb... 11-->dec
        calendar.set(year, month - 1, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        Date date = calendar.getTime();
        DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
        return DATE_FORMAT.format(date);
    }
    
    public void setMunicipioByEstado(ValueChangeEvent event)
    {
        int estadoId = (int) event.getNewValue();
        Estado estado = ejbEstado.find(estadoId);
        municipiosSelect = ejbMunicipio.findByUf(estado.getId());
    }
    
    public int getQtdOfParticipants(Atividade atv){
        List<Presenca> list = ejbPartPessoa.findByAtividade(atv);
        
        if(list!=null)
            if(!list.isEmpty())
                return list.size();
        
        return 0;
    }
    
    public int getQtdOfDistinctPoints(Atividade atv){
        List<Presenca> list = ejbPartPessoa.findDistinctIps(atv);
        
        if(list!=null)
            if(!list.isEmpty())
                return list.size();
        
        return 0;
    }
    
    public int getQtdOfDistinctCities(Atividade atv){
        List<Municipio> list = ejbPartPessoa.findDistinctCities(atv);
        
        if(list!=null)
            if(!list.isEmpty())
                return list.size();
        
        return 0;
    }
    
    public int getQtdOfDistinctStates(Atividade atv){
        List<Municipio> list = ejbPartPessoa.findDistinctCities(atv);
        
        if(list!=null)
            if(!list.isEmpty()){
                List<Estado> ests = new ArrayList<>();
                
                for(Municipio p : list){
                
                    boolean novo = true;
                
                    for(Estado e : ests){
                        if(p.getUf().getSigla().equals(e.getSigla()))
                            novo = false;
                    }
                    
                    if(novo)
                        ests.add(p.getUf());
                }
                
                return ests.size();
            }
        
        return 0;
    }
    
    public List<String> getDistinctProfs(Atividade atv){
        List<Presenca> list = ejbPartPessoa.findByAtividade(atv);
        Map profissoes = new Hashtable();
        for(Cbo cbo : ejbCbo.findAll())
        {
            profissoes.put(cbo.getNome(), 0);
        }
        
        for(Presenca pe : list)
        {
            Pessoa p = pe.getPessoa();
            ProfGeral prof = ejbProfGeral.getByPessoa(p);
            if(prof != null)
            {
                String profNome = prof.getCbo().getNome();
                if(profissoes.containsKey(profNome))
                {
                    profissoes.put(profNome, (int)profissoes.get(profNome) + 1);
                }
            }
        }
        
        Map sorted = HashmapUtils.sortByValue(profissoes);
        int i = 0;
        List<String> output = new ArrayList<>();
        for(Object key: sorted.keySet()){
            int qtd = (int)profissoes.get(key);
            if(qtd > 0){
                if(i < 5){
                    i++;
                    //System.out.println(key.toString() + " - " + profissoes.get(key));
                    String row = key.toString() + " ("+qtd+")";
                    output.add(row);
                }
            }
        }
        
        return output;
    }
    
    
    public List<String> getDistinctCidades(Atividade atv){
        List<Presenca> list = ejbPartPessoa.findByAtividade(atv);
        Map cidades = new Hashtable();
        
        for(Presenca pe : list)
        {
            Municipio mun = pe.getLocal();
            if(mun != null)
            {
                String munNome = mun.getNome() + " - " + mun.getUf().getSigla();
                if(cidades.containsKey(munNome))
                    cidades.put(munNome, (int)cidades.get(munNome) + 1);
                else
                    cidades.put(munNome, 1);
            }
        }
        
        Map sorted = HashmapUtils.sortByValue(cidades);
        int i = 0;
        List<String> output = new ArrayList<>();
        for(Object key: sorted.keySet()){
            int qtd = (int)cidades.get(key);
            if(qtd > 0){
                    //System.out.println(key.toString() + " - " + profissoes.get(key));
                    String row = key.toString() + " ("+qtd+")";
                    output.add(row);
                
            }
        }
        
        return output;
    }
    
}

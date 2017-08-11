/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.jcraft.jsch.SftpException;
import entities.Abstract;
import entities.Artigo;
import entities.ArtigoAutor;
import entities.Pessoa;
import entities.StatusArtigo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import jsf.util.JsfUtil;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import service.RemoteConnection;
import sessions.ArtigoAbstractFacade;
import sessions.ArtigoAutorFacade;
import sessions.ArtigoFacade;
import sessions.StatusArtigoFacade;

/**
 *
 * @author nigel
 */
@ManagedBean(name = "publicacaoController")
@SessionScoped
public class PublicacaoController implements Serializable{
    
    Abstract resumo;
    Abstract abstractPaper;
    Artigo paper;
    Artigo current;
    List<StatusArtigo> statusList = new ArrayList();
    List<ArtigoAutor> autores = new ArrayList();
    List<Artigo> pubsPessoa = new ArrayList();
    int statusId;
    ArtigoAutor autor;
    private UploadedFile filePaper;
    String currentAutor;
    
    @EJB
    private StatusArtigoFacade ejbStatus;
    
    @EJB
    private ArtigoAutorFacade ejbAutor;
    
    @EJB
    private ArtigoAbstractFacade ejbAbstract;
    
    @EJB
    private ArtigoFacade ejbArtigo;

    public String getCurrentAutor() {
        return currentAutor;
    }

    public void setCurrentAutor(String currentAutor) {
        this.currentAutor = currentAutor;
    }

    public List<Artigo> getPubsPessoa() {
        return pubsPessoa;
    }

    public void setPubsPessoa(List<Artigo> pubsPessoa) {
        this.pubsPessoa = pubsPessoa;
    }

    public Artigo getCurrent() {
        return current;
    }

    public void setCurrent(Artigo current) {
        this.current = current;
    }

    
    public ArtigoFacade getEjbArtigo() {
        return ejbArtigo;
    }

    public void setEjbArtigo(ArtigoFacade ejbArtigo) {
        this.ejbArtigo = ejbArtigo;
    }

    
    public UploadedFile getFilePaper() {
        return filePaper;
    }

    public void setFilePaper(UploadedFile filePaper) {
        this.filePaper = filePaper;
    }

    public List<ArtigoAutor> getAutores() {
        return autores;
    }

    public void setAutores(List<ArtigoAutor> autores) {
        this.autores = autores;
    }

    public ArtigoAutor getAutor() {
        return autor;
    }

    public void setAutor(ArtigoAutor autor) {
        this.autor = autor;
    }

    public List<StatusArtigo> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<StatusArtigo> statusList) {
        this.statusList = statusList;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    
    public Artigo getPaper() {
        return paper;
    }

    public void setPaper(Artigo paper) {
        this.paper = paper;
    }

    public Abstract getResumo() {
        return resumo;
    }

    public void setResumo(Abstract resumo) {
        this.resumo = resumo;
    }

    public Abstract getAbstractPaper() {
        return abstractPaper;
    }

    public void setAbstractPaper(Abstract abstractPaper) {
        this.abstractPaper = abstractPaper;
    }
    
    
    
    public void initObjects()
    {
        paper = new Artigo();
        resumo = new Abstract();
        abstractPaper = new Abstract();
        statusList = ejbStatus.findAll();
        autores = new ArrayList();
        autor = new ArtigoAutor();
        
    }
    
    
    //Saving the actual palestra object to the list
    public void guardarAutor()
    {   
        System.out.println("autor " + autor.getPessoa().getNome());
        if(autor.getPessoa() != null)
        {
            autor.setId(Integer.MAX_VALUE - autores.size());
            autores.add(autor);
            autor = new ArtigoAutor();
        }        
    }
    
    public void removeAutor(ArtigoAutor a)
    {
        //System.out.println(p);
        try{
            ejbAutor.remove(a);
        }
        catch(Exception e)
        {
            System.out.println("Remove failed");
        }
        autores.remove(a);
        autor = new ArtigoAutor();
    }
    //FileUploadEvent event
    public String handleFileUploadedSlide(FileUploadEvent event) throws SftpException, IOException
    {
        filePaper = event.getFile();
        uploadFile(filePaper, "/opt/PapersFiles");
        return null;
    }

    public void uploadFile(UploadedFile file, String folder) throws SftpException, FileNotFoundException, IOException
    {
        if(file != null) {
            System.out.println("upload " + file.getFileName());
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " foi enviado com sucesso.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            RemoteConnection con = new RemoteConnection();
            con.connectToRemoteServer(file, folder);
        }        
    }
    
    
    public String persist()
    {
        System.out.println("aquiiii caraioooo " + filePaper.getFileName());
        try{
            StatusArtigo st = ejbStatus.find(statusId);
            paper.setStatus(st);
            ejbAbstract.create(resumo);
            paper.setResumo(resumo);
            if(abstractPaper.getTitle() != null && !abstractPaper.getTitle().isEmpty())
            {
                ejbAbstract.create(abstractPaper);
                paper.setAbstract1(abstractPaper);
            }
            if(filePaper != null)
                paper.setPdfPath(filePaper.getFileName());
            ejbArtigo.create(paper);
            persistAutores();
            
            JsfUtil.addSuccessMessage("Publicação salva com sucesso!");
            initObjects();
            System.out.println("dps do init");
            JsfUtil.addSuccessMessage("Publicação salvada com sucesso!");
            return "view_list_publication.xhtml";
        }catch(Exception e)
        {
//            e.printStackTrace();
             System.out.println(e);
            JsfUtil.addErrorMessage("Erro ao tentar salvar publicação");
        }
        
        return null;
    }
    
    
    private void persistAutores()
    {        
        for(ArtigoAutor autor : autores)
        {
            autor.setId(null);
            autor.setArtigo(paper);
            ejbAutor.create(autor);
        }
    }
    
    public List<ArtigoAutor> findAutores(Artigo artigo)
    {
        return ejbAutor.findByArtigo(artigo);
    }
    
    public String prepareView(Artigo item)
    {
        current  = item;
        return "/publications/view_view_publication.xhtml";
    }
    
    public boolean hasFinalPaper()
    {
        if(current.getPdfPath() != null && !current.getPdfPath().isEmpty())
            return true;
        return false;
    }
    
    
            
    public StreamedContent getFinalPaperFromServer() throws IOException
    {
        
        InputStream file = null;
        RemoteConnection con;
        
        
        if(hasFinalPaper())
        {
            con = new RemoteConnection();
            file = con.getAtaFileFromServer(current.getPdfPath(), "/opt/PapersFiles");
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
            return new DefaultStreamedContent(new ByteArrayInputStream(buffer.toByteArray()), "application/pdf", current.getPdfPath() + ".pdf");
        }
        
        return null;
    }
    
    //PREPARE Atividade Edit
    public String prepareEditPaper(Artigo paper){
        initObjects();
        current = paper;
        autores = ejbAutor.findByArtigo(current);
        resumo = current.getResumo();
        abstractPaper = current.getAbstract1();
        statusId = current.getStatus().getId();
        statusList = ejbStatus.findAll();
        filePaper = null;
        return "/publications/view_edit_publicacao.xhtml";
    }
    
    public String updatePublication()
    {
        try{
            if(abstractPaper != null)
                current.setAbstract1(abstractPaper);
            
            ejbAbstract.edit(resumo);
            current.setResumo(resumo);
            current.setStatus(ejbStatus.find(statusId));
            if(filePaper != null)
                current.setPdfPath(filePaper.getFileName());
            ejbArtigo.edit(current);
            
                
            for(ArtigoAutor a : autores)
            {
                a.setArtigo(current);
                ejbAutor.edit(a);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return "/publications/view_view_publication.xhtml";
    }
    
    public String viewPublicacoesPorPessoa(Pessoa autor)
    {
        currentAutor = autor.getNome() + " " + autor.getSobrenome();
        pubsPessoa = new ArrayList();
        for(ArtigoAutor a : ejbAutor.findByAutor(autor))
        {
            pubsPessoa.add(a.getArtigo());
        }
        
        return "/views/view_list_publicacoes_por_pessoa.xhtml";
    }
    
    public String submit()
    {
        return "/forms/view_create_atividade.xhtml?faces-redirect=true";
    }
    
}

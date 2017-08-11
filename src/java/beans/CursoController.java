/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Aluno;
import entities.Curso;
import entities.StatusMatricula;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import jsf.util.PessoaUtils;
import moodle.entities.MdlCourse;
import moodle.entities.MdlCourseCompletionCriteria;
import moodle.entities.MdlEnrol;
import moodle.entities.MdlGradeGrades;
import moodle.entities.MdlGradeItems;
import moodle.entities.MdlUser;
import moodle.entities.MdlUserEnrolments;
import moodle.sessions.MdlCourseCompletionCriteriaFacade;
import moodle.sessions.MdlCourseFacade;
import moodle.sessions.MdlEnrolFacade;
import moodle.sessions.MdlGradeGradesFacade;
import moodle.sessions.MdlGradeItemsFacade;
import moodle.sessions.MdlUserEnrolmentsFacade;
import moodle.sessions.MdlUserFacade;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import sessions.AlunoFacade;
import sessions.CursoFacade;
import sessions.StatusMatriculaFacade;

/**
 *
 * @author nigel
 */
@ManagedBean(name = "cursoController")
@SessionScoped
public class CursoController {

    List<MdlCourse> cursos;
    Map<String,String> statusCourse = new HashMap<String,String>();
    List<MdlUser> matriculados = new ArrayList();    
    
    Curso curso;
    
    @EJB
    MdlCourseFacade ejbMldCourse;
    
    
    
    @EJB
    MdlCourseCompletionCriteriaFacade ejbMdlCompletion;
    
    @EJB
    MdlUserFacade ejbUser;
    
    @EJB 
    MdlUserEnrolmentsFacade ejbUserEnrol;
    
    @EJB
    MdlEnrolFacade ejbEnrol;

    @EJB
    MdlGradeGradesFacade ejbGG;
    
    @EJB
    MdlGradeItemsFacade ejbGI;
    
    @EJB
    CursoFacade ejbCursoSgat;
    
    @EJB
    AlunoFacade ejbAluno;
    
    @EJB
    StatusMatriculaFacade ejbStatusMatr;
    
    public List<MdlCourse> getCursos() {
        return cursos;
    }

    public void setCursos(List<MdlCourse> cursos) {
        this.cursos = cursos;
    }

    public Map<String, String> getStatusCourse() {
        return statusCourse;
    }

    public void setStatusCourse(Map<String, String> statusCourse) {
        this.statusCourse = statusCourse;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    
    
    /**
     * Creates a new instance of CursoController
     */
    public CursoController() {
    }
    
    public void importCourses()
    {
        cursos = ejbMldCourse.findAll();
        for(MdlCourse c : cursos)
        {
            Integer id = (int) (long) c.getId();
            MdlCourseCompletionCriteria completion = ejbMdlCompletion.findByCourse(id);
            
            if(completion != null)
            {
                if(completion.getTimeend() == null)
                {
                    statusCourse.put(id+"","Em Andamento");
                    continue;
                }
            }
            
            if(ejbCursoSgat.checkImportedCourse(id, longToDate(c.getStartdate())) != null)
            {
                statusCourse.put(id+"","Importado");
            }
            else
            {
                statusCourse.put(id+"","Não Importado");
            }
            System.out.println("curso " + c.getFullname() + " "+c.getId());
        }        
    }

    public String prepareImport()
    {
        cursos = new ArrayList();
        importCourses();
        return "/cursos/view_list_curso_moodle.xhtml";
    }
    
    public Date longToDate(long stDate)
    {
        return new Date(stDate * 1000);
    }
    
    
    public String getFromStatusMap(long id)
    {
        Integer idInt = (int) (long) id;
        return statusCourse.get(idInt+"");
    }
    
    public String prepareImport(MdlCourse item) throws JSONException, FileNotFoundException, UnsupportedEncodingException
    {
        curso = new Curso();
        curso.setAvaCursoId((int) (long) item.getId());
        curso.setDataInicio(longToDate(item.getStartdate()));
        curso.setTema(item.getFullname());
        matriculados = new ArrayList();
        getMatriculados(item.getId());
        
        System.out.println("matriculados " + matriculados.size());
        curso.setVagasOfertadas(matriculados.size());
        
        MdlCourseCompletionCriteria completion = ejbMdlCompletion.findByCourse(item.getId());
            
        if(completion != null)
        {
            if(completion.getTimeend() != null)
            {
                //System.out.println("time end " + longToDate(completion.getTimeend().longValue()));
                curso.setDataTermino(longToDate(completion.getTimeend().longValue()));
               //curso.setDataTermino(dataTermino);
            }
        }
        return "view_create_curso.xhtml";
    }
    
    public void getMatriculados(long id) throws JSONException, FileNotFoundException, UnsupportedEncodingException
    {
        List<MdlEnrol> enrols = ejbEnrol.findByCourse(id);
        System.out.println("enrols " + enrols);
        
        //List<MdlUser> usersEnroled = new ArrayList();
        JSONArray jSONArray = new JSONArray();
        
        for(MdlEnrol e : enrols)
        {
            //System.out.println("User enrol " + ejbUserEnrol.findByEnrol(e.getId()));
            for(MdlUserEnrolments ue : ejbUserEnrol.findByEnrol(e.getId()))
            {
                MdlUser user = ejbUser.findById(ue.getUserid());
                if(user != null)
                {
                    if(PessoaUtils.isValidCPF(user.getUsername()))
                    {
                        JSONObject j = new JSONObject();
                        j.put("fullname", user.getFirstname() + " " + user.getLastname());
                        j.put("cpf", user.getUsername());
                        jSONArray.put(j);
                        matriculados.add(user);
                    }
                }
                //System.out.println("User " + user.getFirstname());
            }
        }
        
        String jsonMatriculados = jSONArray.toString();
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/data.json"));
        
        try( PrintWriter out = new PrintWriter(file, "UTF-8") ){
            out.println( jsonMatriculados );
        }
    }
    
    
    public String persistCourse()
    {
        StatusMatricula formado = ejbStatusMatr.findById(2);
        StatusMatricula evadido = ejbStatusMatr.findById(3);
        StatusMatricula reprovado = ejbStatusMatr.findById(4);
        List<MdlGradeItems> items = ejbGI.findByCourse(curso.getAvaCursoId());
        
        
        
        int numGrades = items.size() - 1;
        
        try{
            ejbCursoSgat.create(curso);
            for(MdlUser m : matriculados)
            {
                Aluno aluno = new Aluno();
                aluno.setCurso(curso);
                aluno.setCpf(m.getUsername());
                
                int count = 0;
                double sum = 0;
                
                List<MdlGradeGrades> grades = ejbGG.findByUser(m.getId());
                for(MdlGradeItems i : items)
                {
                    for(MdlGradeGrades g : grades)
                    {
                        if(g.getItemid() == i.getId() && !i.getItemtype().equals("course"))
                        {
                            if(g.getFinalgrade() != null)
                            {
                                sum += g.getFinalgrade().doubleValue();
                                count++;
                            }
                        }
                    }
                }
                
                if(count == numGrades)
                {                
                    if(sum/numGrades >= 7)
                        aluno.setStatus(formado);
                    else
                        aluno.setStatus(reprovado);
                }
                else
                    aluno.setStatus(evadido);
                  
                try{
                    ejbAluno.create(aluno);
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return prepareImport();
    }
}

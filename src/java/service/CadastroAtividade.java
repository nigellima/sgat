package service;

import entities.Atividade;
import entities.Local;
import entities.Status;
import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import jsf.util.DateUtils;


public class CadastroAtividade implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Atividade atividade;
    
    //DEFAULT CONSTRUCTOR
    public CadastroAtividade(){
        this.atividade = null;
    }

    //GETTERS AND SETTERS
    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
    
    //SAVE atividade IF IT SATISFIES ALL BUSINESS LOGIC
    public void guardar(Atividade atividade) throws NegocioException {
        Date dataAtual = DateUtils.ingnoreTime(new Date());
        Date dataAtividade = atividade.getDt();
        
        //THROW EXCEPTION IF DATE HAS IS PASSED
        if(dataAtividade != null){
            dataAtividade = DateUtils.ingnoreTime(atividade.getDt());
            //if(dataAtividade.before(dataAtual))
              //  throw new NegocioException(ResourceBundle.getBundle("/resources/ValidationMessages").getString("PastDateException"));
        }        
        else {
            throw new NegocioException(ResourceBundle.getBundle("/resources/ValidationMessages").getString("NullDateException"));
        }
        
        Date hrInicio = atividade.getHrInicio();
        Date hrTermino = atividade.getHrTermino();
        
        //THROW EXCEPTION IF END TIME IS LESS THAN BEGIN TIME
        if(hrInicio != null && hrTermino != null){
            Date standard = hrInicio;
            hrInicio = DateUtils.ingnoreDate(hrInicio, standard);
            hrTermino = DateUtils.ingnoreDate(hrTermino, standard);
            if(hrTermino.before(hrInicio)||hrTermino.equals(hrInicio)){
                throw new NegocioException(ResourceBundle.getBundle("/resources/ValidationMessages").getString("inappropriateTimeException"));
            }
        }
        else {
            throw new NegocioException(ResourceBundle.getBundle("/resources/ValidationMessages").getString("NullTimeException"));
        }
        
        atividade = setDefaultLocation(atividade);
        setAtividade(atividade);
    }
    
    
    //SET DEFAULT LOCAITON TO atividade AS 1
    public Atividade setDefaultLocation(Atividade atividade){
        Local local = new Local();
        Status status = new Status();
        
        local.setId(1);
        status.setId(1);
        
        atividade.setLocal(local);
        atividade.setStatus(status);
        
        return atividade;
    }
}

/*
 * 
 */
package br.edu.ifsul.converter;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.modelo.Aluno;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author Telmo
 */

@Named(value = "converterAluno")
@RequestScoped
public class ConverterAluno  implements Serializable, Converter  {
    
    @EJB
    private AlunoDAO dao; 
    
    public ConverterAluno(){
        
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if(value == null || value.equals("Selecione") || value.equals("Selecione um registro")){
            return null;
        }
        
        Aluno pf = null;
        try{
            pf =  dao.getObjectById(Integer.parseInt(value));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return pf;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         if(value == null){
            return null;
        }
        Aluno pf =  (Aluno) value;
        
        return pf.getId().toString();
    }
    
}

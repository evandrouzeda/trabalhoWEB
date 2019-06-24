package br.edu.ifsul.converter;

import br.edu.ifsul.dao.InstituicaoDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import br.edu.ifsul.modelo.Curso;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author evandrouzeda
 */

@Named(value = "converterInstituicao")
@RequestScoped
public class ConverterInstituicao implements Converter, Serializable {

    @EJB
    private InstituicaoDAO dao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if(value == null || value.equals("Selecione") || value.equals("Selecione um registro")){
            return null;
        }
        return dao.find(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        Curso c = (Curso) value;    
        return c.getId().toString();
    }
}

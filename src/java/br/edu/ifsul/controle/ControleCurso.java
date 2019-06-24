package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CursoDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import br.edu.ifsul.modelo.Curso;
import br.edu.ifsul.util.Util;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author evandrouzeda
 */
@Named(value = "controleCurso")
@ViewScoped
public class ControleCurso implements Serializable {
    
    @EJB
    private CursoDAO dao;

    private Curso objeto;

    private boolean value1; 
    
    public ControleCurso(){

    }
    
    public String listar(){
        return "/privado/curso/crudcurso?faces-redirect=true";
    }

    public void novo(){
        objeto = new Curso();      
    }

    public CursoDAO getDao() {
        return dao;
    }
    
    
    
    public void alterar(Object id){
                try {
                        setObjeto(dao.getObjectById(id));            
                } catch (Exception e){
                        Util.mensagemErro("Erro ao recuperar objeto: " + 
                                        Util.getMensagemErro(e));
                } 
        }

        public void excluir(Object id){
                try {
                        setObjeto(dao.getObjectById(id));
                        dao.remover(getObjeto());
                        Util.mensagemInformacao("Objeto removido com sucesso!");
                } catch (Exception e){
                        Util.mensagemErro("Erro ao remover objeto: " + 
                                        Util.getMensagemErro(e));
                }
        }

        public void salvar(){
                try {
                        if (getObjeto().getId() == null){
                                dao.persist(getObjeto());
                        } else {
                                dao.merge(getObjeto());
                        }
                        Util.mensagemInformacao("Objeto persistido com sucesso!");            
                } catch(Exception e){
                        Util.mensagemErro("Erro ao persistir objeto: " + 
                                        Util.getMensagemErro(e));
                }
        }

    public Curso getObjeto() {
        return objeto;
    }

    public void setObjeto(Curso objeto) {
        this.objeto = objeto;
    }
    
    public boolean isValue1() {
        return value1;
    }
 
    public void setValue1(boolean value1) {
        objeto.setAtivo(value1);
    }
}

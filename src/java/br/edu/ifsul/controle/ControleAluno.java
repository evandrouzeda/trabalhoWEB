
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PermissaoDAO;
import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.modelo.Permissao;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Professor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.HashSet;

/**
 *
 * @author Telmo Junior
 */
@Named(value = "controleUsu")
@ViewScoped
public class ControleAluno implements  Serializable{
    
    @EJB
    private AlunoDAO dao;
    
    @EJB
    private PermissaoDAO daoPermissao;
    
    private Aluno objeto;
    
    private Permissao permissao;
    
    private Boolean isEdit = false;
    
    public ControleAluno(){                
    }
    
    public String listar(){
        return "/privado/usuario/crudusuario?faces-redirect=true";
    }
    
    public void novo(){
        setObjeto(new Aluno());
        isEdit = false;
    }

    public Aluno getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluno objeto) {
        this.objeto = objeto;
    }

    public AlunoDAO getDao() {
        return dao;
    }
    
    public void novaPermissao(){
        
        permissao = new Permissao();
    }
    
    public void salvarPermissao(){
        
        if(objeto.getPermissoes() == null){
            objeto.setPermissoes(new HashSet());           
        }
        objeto.getPermissoes().add(permissao);
                
    }
    
    public void alterar(Object id){
             try {
                    setObjeto(getDao().getObjectById(id));
                    isEdit = true;
             } catch (Exception e){
                     Util.mensagemErro("Erro ao recuperar objeto: " + 
                                     Util.getMensagemErro(e));
             } 
     }

    public void excluir(Object id){
            try {
                    setObjeto(getDao().getObjectById(id));
                    getDao().remover(getObjeto());
                    Util.mensagemInformacao("Objeto removido com sucesso!");
            } catch (Exception e){
                    Util.mensagemErro("Erro ao remover objeto: " + 
                                    Util.getMensagemErro(e));
            }
    }

    public void salvar(){
        try {
            
                if(!getIsEdit()){
                    getDao().persist(objeto);
                    Util.mensagemInformacao("Objeto inserido com sucesso!");
                }else{
                    getDao().merge(objeto);
                    Util.mensagemInformacao("Objeto alterado com sucesso!");
                }
                    
            } catch(Exception e){
                    Util.mensagemErro("Erro ao persistir objeto: " + 
                                    Util.getMensagemErro(e));
            }
    }
    
    public String verificaTipo(Aluno u){
        
        if((u instanceof Aluno) && (u instanceof Professor) ){
            return Professor.class.getSimpleName();
        }else{
            return Aluno.class.getSimpleName();
        }

    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public PermissaoDAO getDaoPermissao() {
        return daoPermissao;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(AlunoDAO dao) {
        this.dao = dao;
    }
    
}

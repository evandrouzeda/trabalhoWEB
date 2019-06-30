/*
 * 
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Aluno;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Telmo
 */
@Stateful
public class AlunoDAO extends DAOGenerico<Aluno> implements Serializable{
    
    public AlunoDAO(){
         super(Aluno.class);
         
        // inicializar as ordenações possiveis        
        listaOrdem.add(new Ordem("nome", "Nome", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);
    }
    @Override
    public Aluno getObjectById(Object id) throws Exception {
        Aluno obj = em.find(Aluno.class, id);
        // Deve-se inicializar as coleções para não gerar erro de LazyInicializationException na lista de permissao
        obj.getPermissoes().size();
        return obj;
    } 
}

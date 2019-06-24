package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Instituicao;
import java.io.Serializable;

/**
 *
 * @author evandrouzeda
 */
public class InstituicaoDAO extends DAOGenerico<Instituicao> implements Serializable{
    public InstituicaoDAO(){
        super(Instituicao.class);
        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);
    }
}

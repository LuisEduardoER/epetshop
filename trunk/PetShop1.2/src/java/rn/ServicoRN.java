/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;


import beans.Servico;
import java.util.List;
import util.DAOFactory;
import util.InterfaceHibernateDAO;

public class ServicoRN {
    
    private InterfaceHibernateDAO<Servico> servicoDao;
    
    public ServicoRN(){
        this.servicoDao = DAOFactory.criarServicoDAO();
    }
    
    public void salvar(Servico servico){
        this.servicoDao.salvar(servico);
    }
    public List<Servico> listar(){
        return this.servicoDao.listar();
    }
    public void atualizar(Servico servico){
        this.servicoDao.atualizar(servico);
    }
    public void excluir(Servico servico){
        this.servicoDao.excluir(servico);
    }
    public Servico carregar(Integer codigo){
        return this.servicoDao.carregar(codigo);
    }
}

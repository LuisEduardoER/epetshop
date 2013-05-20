/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;


import beans.Agenda;
import java.util.List;
import util.DAOFactory;
import util.InterfaceHibernateDAO;

public class AgendaRN {
    
    private InterfaceHibernateDAO<Agenda> agendaDao;
    
    public AgendaRN(){
        this.agendaDao = DAOFactory.criarAgendaDAO();
    }
    
    public void salvar(Agenda agenda){
        this.agendaDao.salvar(agenda);
    }
    public List<Agenda> listar(){
        return this.agendaDao.listar();
    }
    public void atualizar(Agenda agenda){
        this.agendaDao.atualizar(agenda);
    }
    public void excluir(Agenda agenda){
        this.agendaDao.excluir(agenda);
    }
    public Agenda carregar(Integer codigo){
        return this.agendaDao.carregar(codigo);
    }
}

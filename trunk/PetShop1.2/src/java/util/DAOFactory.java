/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Agenda;

/**
 *
 * @author Caio
 */
public class DAOFactory {
    
    public static InterfaceHibernateDAO<Agenda> criarAgendaDAO(){
        HibernateDAO<Agenda> dao = new HibernateDAO<Agenda>(Agenda.class);
        dao.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return dao;
    }
    
}

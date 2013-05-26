/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Agenda;
import beans.Galeria;
import beans.Servico;
import beans.Usuario;
import beans.UsuarioPermissao;

/**
 *
 * @author Caio
 */
public class DAOFactory {
    
    public static InterfaceHibernateDAO<Usuario> criarUsuarioDAO(){
        HibernateDAO<Usuario> dao = new HibernateDAO<Usuario>(Usuario.class);
        dao.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return dao;
    }
    
    public static InterfaceHibernateDAO<UsuarioPermissao> criarUsuarioPermissaoDAO(){
        HibernateDAO<UsuarioPermissao> dao = new HibernateDAO<UsuarioPermissao>(UsuarioPermissao.class);
        dao.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return dao;
    }
    
    public static InterfaceHibernateDAO<Agenda> criarAgendaDAO(){
        HibernateDAO<Agenda> dao = new HibernateDAO<Agenda>(Agenda.class);
        dao.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return dao;
    }
    
    public static InterfaceHibernateDAO<Servico> criarServicoDAO(){
        HibernateDAO<Servico> dao = new HibernateDAO<Servico>(Servico.class);
        dao.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return dao;
    }
    
    public static InterfaceHibernateDAO<Galeria> criarGaleriaDAO(){
        HibernateDAO<Galeria> dao = new HibernateDAO<Galeria>(Galeria.class);
        dao.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return dao;
    }
    
}

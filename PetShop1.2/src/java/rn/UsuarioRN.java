/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;


import beans.Usuario;
import java.util.List;
import util.DAOFactory;
import util.InterfaceHibernateDAO;

public class UsuarioRN {
    
    private InterfaceHibernateDAO<Usuario> usuarioDao;
    
    public UsuarioRN(){
        this.usuarioDao = DAOFactory.criarUsuarioDAO();
    }
    
    public void salvar(Usuario usuario){
        this.usuarioDao.salvar(usuario);
    }
    public List<Usuario> listar(){
        return this.usuarioDao.listar();
    }
    public void atualizar(Usuario usuario){
        this.usuarioDao.atualizar(usuario);
    }
    public void excluir(Usuario usuario){
        this.usuarioDao.excluir(usuario);
    }
    public Usuario carregar(Integer codigo){
        return this.usuarioDao.carregar(codigo);
    }
}

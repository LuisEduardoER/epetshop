/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;


import beans.UsuarioPermissao;
import java.util.List;
import util.DAOFactory;
import util.InterfaceHibernateDAO;

public class UsuarioPermissaoRN {
    
    private InterfaceHibernateDAO<UsuarioPermissao> usuarioPermissaoDao;
    
    public UsuarioPermissaoRN(){
        this.usuarioPermissaoDao = DAOFactory.criarUsuarioPermissaoDAO();
    }
    
    public void salvar(UsuarioPermissao usuariopermissao){
        this.usuarioPermissaoDao.salvar(usuariopermissao);
    }
    public List<UsuarioPermissao> listar(){
        return this.usuarioPermissaoDao.listar();
    }
    public void atualizar(UsuarioPermissao usuariopermissao){
        this.usuarioPermissaoDao.atualizar(usuariopermissao);
    }
    public void excluir(UsuarioPermissao usuariopermissao){
        this.usuarioPermissaoDao.excluir(usuariopermissao);
    }
    public UsuarioPermissao carregar(Integer codigo){
        return this.usuarioPermissaoDao.carregar(codigo);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;


import beans.Galeria;
import java.util.List;
import util.DAOFactory;
import util.InterfaceHibernateDAO;

public class GaleriaRN {
    
    private InterfaceHibernateDAO<Galeria> galeriaDao;
    
    public GaleriaRN(){
        this.galeriaDao = DAOFactory.criarGaleriaDAO();
    }
    
    public void salvar(Galeria galeria){
        this.galeriaDao.salvar(galeria);
    }
    public List<Galeria> listar(){
        return this.galeriaDao.listar();
    }
    public void atualizar(Galeria galeria){
        this.galeriaDao.atualizar(galeria);
    }
    public void excluir(Galeria galeria){
        this.galeriaDao.excluir(galeria);
    }
    public Galeria carregar(Integer codigo){
        return this.galeriaDao.carregar(codigo);
    }
}

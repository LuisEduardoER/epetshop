/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import beans.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import rn.UsuarioRN;

/**
 *
 * @author diogenes
 */
@ManagedBean
@SessionScoped
public class ControllerCliente {

    @ManagedProperty(value="#{usuario}")
    private Usuario cliente;
    private DataModel listaDataModel;
    
    
    public ControllerCliente() {
    }
    
    
    
    
    public DataModel getListaDM(){
        UsuarioRN rn = new UsuarioRN();
        this.listaDataModel = new ListDataModel(rn.listar());
        return listaDataModel;
    }

    
    public void prepararAlterar() {
        this.cliente = (Usuario) this.listaDataModel.getRowData();
    }
    
    public Usuario getCliente() {
        if(cliente == null){
            cliente = new Usuario();
        }
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public DataModel getListaDataModel() {
        return listaDataModel;
    }

    public void setListaDataModel(DataModel listaDataModel) {
        this.listaDataModel = listaDataModel;
    }
 
    
    
}
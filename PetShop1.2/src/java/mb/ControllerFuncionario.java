/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import beans.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import rn.UsuarioRN;

/**
 *
 * @author diogenes
 */
@ManagedBean(name="ControllerFuncionario")
@SessionScoped
public class ControllerFuncionario {

    private DataModel listaDataModel;
    @ManagedProperty(value="#{usuario}")
    private Usuario funcionario;
    private boolean statusFalse;
    private boolean statusTrue;
    
    
    public ControllerFuncionario() {
    }
    
    
    
    public void cadastrar(ActionEvent event){
        this.getFuncionario();
        UsuarioRN rn = new UsuarioRN();
        rn.salvar(this.funcionario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Funcionario Cadastrado"));
    }
    
    public DataModel getListaDM(){
        UsuarioRN rn = new UsuarioRN();
        this.listaDataModel = new ListDataModel(rn.listar());
        return listaDataModel;
    }

    public DataModel getListaDataModel() {        
        return listaDataModel;
    }

    public void setListaDataModel(DataModel listaDataModel) {
        this.listaDataModel = listaDataModel;
    }

    public Usuario getFuncionario() {
        if(funcionario == null){
            funcionario = new Usuario();
        }
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isStatusFalse() {
        return statusFalse;
    }

    public void setStatusFalse(boolean statusFalse) {
        this.statusFalse = statusFalse;
    }

    public boolean isStatusTrue() {
        return statusTrue;
    }

    public void setStatusTrue(boolean statusTrue) {
        this.statusTrue = statusTrue;
    }
    
    
}

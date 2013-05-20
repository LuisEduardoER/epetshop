/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import beans.Servico;
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
    
    
    
    public ControllerFuncionario() {
    }
    
    
    
    public void cadastrar(ActionEvent event){
        this.getFuncionario();
        UsuarioRN rn = new UsuarioRN();
        rn.salvar(this.funcionario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Funcionario Cadastrado"));
    }
    
    
    public void excluir(){
        UsuarioRN rn = new UsuarioRN();
        rn.excluir(this.funcionario);
        FacesContext context = FacesContext.getCurrentInstance();          
        context.addMessage(null, new FacesMessage("Excluído com Sucesso"));
    }
    
    
    public void editar(){
        UsuarioRN rn = new UsuarioRN();
        rn.atualizar(this.funcionario);
        FacesContext context = FacesContext.getCurrentInstance();          
        context.addMessage(null, new FacesMessage("Editado com Sucesso"));
    }
    
    public DataModel getListaDM(){
        UsuarioRN rn = new UsuarioRN();
        this.listaDataModel = new ListDataModel(rn.listar());
        return listaDataModel;
    }

    
    public void prepararAlterar() {
        this.funcionario = (Usuario) this.listaDataModel.getRowData();
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

 
    
    
}

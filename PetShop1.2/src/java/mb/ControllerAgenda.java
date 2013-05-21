/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import beans.Agenda;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import rn.AgendaRN;

/**
 *
 * @author Caio
 */
@ManagedBean(name="controllerAgenda")
@SessionScoped
public class ControllerAgenda {
    
    public ControllerAgenda(){}
    
    @ManagedProperty(value="#{agenda}")
    private Agenda agenda;

    private List<Agenda> lista;
    
    private DataModel listaDataModel;
    
    public void limpar() {
        setAgenda(new Agenda());
    }

    ////////////////////////////////////////////////////////////////////////////   
    
    public DataModel getListaDM() {
        AgendaRN rn = new AgendaRN();
        this.listaDataModel = new ListDataModel(rn.listar());
        return this.listaDataModel;
    }
    
    public List<Agenda> getLista() {
        AgendaRN rn = new AgendaRN();
        this.lista = rn.listar();
        return this.lista;
    }    
       
    public void salvar(){
        AgendaRN rn = new AgendaRN();
        rn.salvar(this.agenda);
        FacesContext context = FacesContext.getCurrentInstance();            
        context.addMessage(null, new FacesMessage("Cadastrado com Sucesso"));
        limpar();
       
    }
    
    public void remover(){
        AgendaRN rn = new AgendaRN();
        this.agenda= (Agenda)this.listaDataModel.getRowData();
        rn.excluir(this.agenda);
        FacesContext context = FacesContext.getCurrentInstance();            
        context.addMessage(null, new FacesMessage("Excluído com Sucesso"));
        limpar();
        
    }
  

    public void alterar() {
        AgendaRN rn = new AgendaRN();
        rn.atualizar(this.agenda);
        FacesContext context = FacesContext.getCurrentInstance();            
        context.addMessage(null, new FacesMessage("Alterado com Sucesso"));
        limpar();
        
    }

    public void prepararAlterar() {
        this.agenda = (Agenda) this.listaDataModel.getRowData();
    }
    
    ////////////////////////////////////////////////////////////////////////////

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public DataModel getListaDataModel() {
        return listaDataModel;
    }

    public void setListaDataModel(DataModel listaDataModel) {
        this.listaDataModel = listaDataModel;
    }
    
}

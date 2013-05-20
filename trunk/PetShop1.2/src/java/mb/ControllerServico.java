/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import beans.Servico;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import rn.ServicoRN;

/**
 *
 * @author Caio
 */
@ManagedBean(name="controllerServico")
@SessionScoped
public class ControllerServico {
    
    public ControllerServico(){}
    
    @ManagedProperty(value="#{servico}")
    private Servico servico;

    private List<Servico> lista;
    
    private DataModel listaDataModel;
    
    public void limpar() {
        setServico(new Servico());
    }

    ////////////////////////////////////////////////////////////////////////////   
    
    public DataModel getListaDM() {
        ServicoRN rn = new ServicoRN();
        this.listaDataModel = new ListDataModel(rn.listar());
        return this.listaDataModel;
    }
    
    public List<Servico> getLista() {
        ServicoRN rn = new ServicoRN();
        this.lista = rn.listar();
        return this.lista;
    }    
       
    public void salvar(){
        ServicoRN rn = new ServicoRN();
        rn.salvar(this.servico);
        FacesContext context = FacesContext.getCurrentInstance();            
        context.addMessage(null, new FacesMessage("Cadastrado com Sucesso"));
        limpar();
       
    }
    
    public void remover(){
        ServicoRN rn = new ServicoRN();
        this.servico= (Servico)this.listaDataModel.getRowData();
        rn.excluir(this.servico);
        FacesContext context = FacesContext.getCurrentInstance();            
        context.addMessage(null, new FacesMessage("Excluído com Sucesso"));
        limpar();
        
    }
  

    public void alterar() {
        ServicoRN rn = new ServicoRN();
        rn.atualizar(this.servico);
        FacesContext context = FacesContext.getCurrentInstance();            
        context.addMessage(null, new FacesMessage("Alterado com Sucesso"));
        limpar();
        
    }

    public void prepararAlterar() {
        this.servico = (Servico) this.listaDataModel.getRowData();
    }
    
    ////////////////////////////////////////////////////////////////////////////

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public DataModel getListaDataModel() {
        return listaDataModel;
    }

    public void setListaDataModel(DataModel listaDataModel) {
        this.listaDataModel = listaDataModel;
    }
    
}

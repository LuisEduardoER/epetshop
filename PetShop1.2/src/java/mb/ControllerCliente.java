/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import beans.Usuario;
import beans.UsuarioPermissao;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import rn.UsuarioPermissaoRN;
import rn.UsuarioRN;

/**
 *
 * @author diogenes
 */
@ManagedBean
@SessionScoped
public class ControllerCliente {

    @ManagedProperty(value="#{usuario}")
    private Usuario usuario;
    private DataModel listaDataModel;
    @ManagedProperty(value="#{usuario_permissao}")
    private UsuarioPermissao permissao;
    
    public ControllerCliente() {
    }
    
    
    
    
      public void limpar() {
        setUsuario(new Usuario());
        setPermissao(new UsuarioPermissao());
    }
    
    public void cadastrar(){
        UsuarioRN rn = new UsuarioRN();
        UsuarioPermissaoRN rn2 = new UsuarioPermissaoRN();
       try{
           if (this.usuario.getSenha().equals(this.usuario.getSenha2())) {
                getPermissao().setUsuario(this.usuario);
                getPermissao().setPermissao("ROLE_CLI");
                getUsuario().setData_cadastro(new Date());
                rn.salvar(this.usuario);
                rn2.salvar(this.permissao);
                limpar();
                FacesContext context = FacesContext.getCurrentInstance();            
                context.addMessage(null, new FacesMessage("Cadastro efetuado com sucesso."));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();  
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Atenção", "Senhas devem ser iguais."));
            }
        } catch(Exception e) {
            //precisa debugar
            FacesContext context = FacesContext.getCurrentInstance();  
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO", "Usuário já cadastrado."));
        }
       }
    
    
    public DataModel getListaDM(){
        UsuarioRN rn = new UsuarioRN();
        this.listaDataModel = new ListDataModel(rn.listar());
        return listaDataModel;
    }

    
    public void prepararAlterar() {
        this.usuario = (Usuario) this.listaDataModel.getRowData();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    

    public DataModel getListaDataModel() {
        return listaDataModel;
    }

    public void setListaDataModel(DataModel listaDataModel) {
        this.listaDataModel = listaDataModel;
    }

    public UsuarioPermissao getPermissao() {
        return permissao;
    }

    public void setPermissao(UsuarioPermissao permissao) {
        this.permissao = permissao;
    }
 
    
    
}
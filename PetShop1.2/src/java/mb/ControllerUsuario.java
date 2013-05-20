/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import beans.Usuario;
import beans.UsuarioPermissao;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import rn.UsuarioPermissaoRN;
import rn.UsuarioRN;

/**
 *
 * @author Caio
 */
@ManagedBean(name="controllerUsuario")
@SessionScoped
public class ControllerUsuario {
    
    public ControllerUsuario(){}
    
    @ManagedProperty(value="#{usuario}")
    private Usuario usuario;
    
    @ManagedProperty(value="#{usuario_permissao}")
    private UsuarioPermissao permissao;

    private List<Usuario> listaUsuario;
    
    private List<UsuarioPermissao> listaPermissao;
    
    private DataModel listaDataModelUsu;
    
    private DataModel listaDataModelPerm;
    
    public void limpar() {
        setUsuario(new Usuario());
        setPermissao(new UsuarioPermissao());
    }
    
    public DataModel getListaDMUsu() {
        UsuarioRN rn = new UsuarioRN();
        this.listaDataModelUsu = new ListDataModel(rn.listar());
        return this.listaDataModelUsu;
    }
    
    public List<Usuario> getListaUsuario() {
        UsuarioRN rn = new UsuarioRN();
        this.listaUsuario = rn.listar();
        return this.listaUsuario;
    }
    
    public void salvarUsuario(){
        try{
            UsuarioRN rn = new UsuarioRN();
            UsuarioPermissaoRN rn2 = new UsuarioPermissaoRN();
            
            if (this.usuario.getSenha().equals(this.usuario.getSenha2())) {
                getUsuario().setData_cadastro(new Date());
                
                getPermissao().setUsuario(this.usuario);
                getPermissao().setPermissao("ROLE_CLI");
                
                rn.salvar(this.usuario);
                rn2.salvar(this.permissao);
                
                FacesContext context = FacesContext.getCurrentInstance();            
                context.addMessage(null, new FacesMessage("Cadastro efetuado."));
                
                limpar();
            } else {
                FacesContext context = FacesContext.getCurrentInstance();  
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atenção", "Senhas devem ser iguais."));
            }
        } catch(Exception e) {
            //precisa debugar
            FacesContext context = FacesContext.getCurrentInstance();  
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO", "Usuário já cadastrado."));
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioPermissao getPermissao() {
        return permissao;
    }

    public void setPermissao(UsuarioPermissao permissao) {
        this.permissao = permissao;
    }

    public List<UsuarioPermissao> getListaPermissao() {
        return listaPermissao;
    }

    public void setListaPermissao(List<UsuarioPermissao> listaPermissao) {
        this.listaPermissao = listaPermissao;
    }

    public DataModel getListaDataModelUsu() {
        return listaDataModelUsu;
    }

    public void setListaDataModelUsu(DataModel listaDataModelUsu) {
        this.listaDataModelUsu = listaDataModelUsu;
    }

    public DataModel getListaDataModelPerm() {
        return listaDataModelPerm;
    }

    public void setListaDataModelPerm(DataModel listaDataModelPerm) {
        this.listaDataModelPerm = listaDataModelPerm;
    }
    
}

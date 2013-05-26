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
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import rn.UsuarioPermissaoRN;
import rn.UsuarioRN;

/**
 *
 * @author diogenes
 */
@ManagedBean(name="controllerFuncionario")
@SessionScoped
public class ControllerFuncionario {
    
    public ControllerFuncionario() {}

    private DataModel listaDataModelUsuario;
    private DataModel listaDataModelPermissao;
    
    @ManagedProperty(value="#{usuario}")
    private Usuario usuario;
    @ManagedProperty(value="#{usuario_permissao}")
    private UsuarioPermissao permissao;
    private String controlPermissao;
    private List<Usuario> listaUsuario;
    private List<UsuarioPermissao> listaPermissao;
    
    public void limpar() {
        setUsuario(new Usuario());
        setPermissao(new UsuarioPermissao());
    }
    
    public void cadastrar(){
        UsuarioRN rn = new UsuarioRN();
        UsuarioPermissaoRN rn2 = new UsuarioPermissaoRN();
        try {
            if (this.usuario.getSenha().equals(this.usuario.getSenha2())) {
                getPermissao().setUsuario(this.usuario);
               if(controlPermissao.equals("Funcionario")){                   
                getPermissao().setPermissao("ROLE_FUNC");   
               }
               else if(controlPermissao.equals("Cliente")){
                getPermissao().setPermissao("ROLE_CLI");      
               }
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

    
    public void excluir(){
        UsuarioRN rn = new UsuarioRN();
        this.usuario = (Usuario) this.listaDataModelUsuario.getRowData();
        rn.excluir(this.usuario);
        limpar();
        FacesContext context = FacesContext.getCurrentInstance();          
        context.addMessage(null, new FacesMessage("Excluído com Sucesso"));
    }
    
    public void editar(){
        UsuarioRN rn = new UsuarioRN();
        rn.atualizar(this.usuario);
        limpar();
        FacesContext context = FacesContext.getCurrentInstance();          
        context.addMessage(null, new FacesMessage("Editado com Sucesso"));
    }
    
    public DataModel getListaDM(){
        UsuarioRN rn = new UsuarioRN();
        this.listaDataModelUsuario = new ListDataModel(rn.listar());
        return listaDataModelUsuario;
    }

    
    public void prepararAlterar() {
        this.usuario = (Usuario) this.listaDataModelUsuario.getRowData();
    }

    public DataModel getListaDataModelUsuario() {
        return listaDataModelUsuario;
    }

    public void setListaDataModelUsuario(DataModel listaDataModelUsuario) {
        this.listaDataModelUsuario = listaDataModelUsuario;
    }

    public DataModel getListaDataModelPermissao() {
        return listaDataModelPermissao;
    }

    public void setListaDataModelPermissao(DataModel listaDataModelPermissao) {
        this.listaDataModelPermissao = listaDataModelPermissao;
    }

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

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public List<UsuarioPermissao> getListaPermissao() {
        return listaPermissao;
    }

    public void setListaPermissao(List<UsuarioPermissao> listaPermissao) {
        this.listaPermissao = listaPermissao;
    }

    public String getControlPermissao() {
        return controlPermissao;
    }

    public void setControlPermissao(String controlPermissao) {
        this.controlPermissao = controlPermissao;
    }
    
}
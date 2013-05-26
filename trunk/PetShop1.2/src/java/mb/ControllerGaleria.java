/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import beans.Galeria;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import rn.GaleriaRN;

/**
 *
 * @author Caio
 */
@ManagedBean(name="controllerGaleria")
@SessionScoped
public class ControllerGaleria {
    
    public ControllerGaleria(){}
    
    @ManagedProperty(value="#{galeria}")
    private Galeria galeria;

    private List<Galeria> lista;
    
    private DataModel listaDataModel;
    
    private static final int BUFFER_SIZE = 6124;
    private byte[] arquivo;
    private String caminhoArquivo;
    private String caminhoBanco;
    
    public void limpar() {
        setGaleria(new Galeria());
    }

    ////////////////////////////////////////////////////////////////////////////   
    
    public DataModel getListaDM() {
        GaleriaRN rn = new GaleriaRN();
        this.listaDataModel = new ListDataModel(rn.listar());
        return this.listaDataModel;
    }
    
    public List<Galeria> getLista() {
        GaleriaRN rn = new GaleriaRN();
        this.lista = rn.listar();
        return this.lista;
    }    
       
    public void salvar(){
        GaleriaRN rn = new GaleriaRN();
        rn.salvar(this.galeria);
        limpar();
        FacesContext context = FacesContext.getCurrentInstance();            
        context.addMessage(null, new FacesMessage("Cadastrado com Sucesso"));       
    }
    
    public void remover(){
        GaleriaRN rn = new GaleriaRN();
        this.galeria= (Galeria)this.listaDataModel.getRowData();
        rn.excluir(this.galeria);
        
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
        String local =  scontext.getRealPath("");
        File file = new File(local+"\\"+this.galeria.getFoto());
        file.delete();
        
        limpar();          
        context.addMessage(null, new FacesMessage("Excluído com Sucesso"));
    }
  

    public void alterar() {
        GaleriaRN rn = new GaleriaRN();
        rn.atualizar(this.galeria);
        limpar();
        FacesContext context = FacesContext.getCurrentInstance();            
        context.addMessage(null, new FacesMessage("Alterado com Sucesso"));
    }

    public void prepararAlterar() {
        this.galeria = (Galeria) this.listaDataModel.getRowData();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public void handleFileUpload(FileUploadEvent event) throws Exception {         
        FacesContext context = FacesContext.getCurrentInstance();
        GaleriaRN rn = new GaleriaRN();
        try {
            ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
            String local =  scontext.getRealPath("resources/images/galeria");
            File diretorio = new File(local);
            if (!diretorio.exists()) {
                diretorio.mkdir();
                context.addMessage(null, new FacesMessage("Aviso", "Pasta criada."));
            } else {
                context.addMessage(null, new FacesMessage("Aviso", "Pasta já existe, adicionado."));
            }
            this.caminhoArquivo = local+"\\"+event.getFile().getFileName();
            ///////////////
            this.caminhoBanco = "resources/images/galeria/"+event.getFile().getFileName();
            ///////////////
            this.arquivo = event.getFile().getContents();
            
            
            
            FileOutputStream fos = new FileOutputStream(caminhoArquivo);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream is = event.getFile().getInputstream();
            while (true) {
                bulk = is.read(buffer);
                if (bulk < 0) {
                   break;
                }
                fos.write(buffer, 0, bulk);
                fos.flush();
                getGaleria().setFoto(this.caminhoBanco);
                rn.salvar(this.galeria);
            }
            fos.close();
            is.close();
            context.addMessage(null, new FacesMessage("Sucesso", event.getFile().getFileName()+" foi salvo."));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Erro", "Arquivo não processado."));
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////

    public Galeria getGaleria() {
        return galeria;
    }

    public void setGaleria(Galeria galeria) {
        this.galeria = galeria;
    }

    public DataModel getListaDataModel() {
        return listaDataModel;
    }

    public void setListaDataModel(DataModel listaDataModel) {
        this.listaDataModel = listaDataModel;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getCaminhoBanco() {
        return caminhoBanco;
    }

    public void setCaminhoBanco(String caminhoBanco) {
        this.caminhoBanco = caminhoBanco;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import beans.Agenda;
import beans.Usuario;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import rn.AgendaRN;
import rn.ServicoRN;
import rn.UsuarioRN;

/**
 *
 * @author Caio
 */
@ManagedBean(name="scheduleController")
@SessionScoped
public class ScheduleController implements Serializable {  
  
    
    
    @ManagedProperty(value="#{agenda}")
    private Agenda agenda;
    
    private List<Agenda> lista;
    
    private DataModel listaDataModel;
    
    public void limpar() {
        setAgenda(new Agenda());
    }
    
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
       
    public void salvar() throws ParseException{    
        //peguei o login
        ExternalContext fc = FacesContext.getCurrentInstance().getExternalContext();  
        String login = fc.getRemoteUser();
        //busquei obj usuario atraves do login
        UsuarioRN rnu = new UsuarioRN();
        List<Usuario> user = rnu.buscaPersonalizada("login", login);
        
        AgendaRN rn = new AgendaRN();
        getAgenda().setUsuario(user.get(0));
        getAgenda().setDia(event.getEndDate());
        getAgenda().setHora(event.getStartDate());
        rn.salvar(this.agenda);
        FacesContext context = FacesContext.getCurrentInstance();            
        context.addMessage(null, new FacesMessage("Cadastrado com Sucesso"));      
    }
    
    public void pegar(){
        eventModel = new DefaultScheduleModel(); 
        getAgenda().setDia(event.getEndDate());
        getAgenda().setHora(event.getStartDate());
        ServicoRN rns = new ServicoRN();
        //peguei o login
        ExternalContext fc = FacesContext.getCurrentInstance().getExternalContext();  
        String login = fc.getRemoteUser();
        //busquei obj usuario atraves do login
        UsuarioRN rnu = new UsuarioRN();
        List<Usuario> user = rnu.buscaPersonalizada("login", login);
        getAgenda().setUsuario(user.get(0));
        getAgenda().setServico(rns.carregar(getAgenda().getServico().getId()));
        eventModel.addEvent(new DefaultScheduleEvent(getAgenda().getServico().getTipo() +"\n"+getAgenda().getUsuario().getNome(), getAgenda().getDia(), getAgenda().getHora()));  
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
    
    public Calendar capturaDia(Date dia){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dia);
        return calendar;
    }

    public void prepararAlterar() {
        this.agenda = (Agenda) this.listaDataModel.getRowData();
    }

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
    
    ////////////////////////////////////////////////////////////////////////////
    
    
    public ScheduleController() {
        
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    private ScheduleModel eventModel;  
      
    private ScheduleEvent event = new DefaultScheduleEvent();
  
 /*
      
    public Date getRandomDate(Date base) {  
        Calendar date = Calendar.getInstance();  
        date.setTime(base);  
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month  
          
        return date.getTime();  
    }
   */   
    public Date getInitialDate() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);  
          
        return calendar.getTime();  
    }  
      
    public ScheduleModel getEventModel() {  
        return eventModel;  
    }  
      
    private Calendar today() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);  
  
        return calendar;  
    }  
      
    private Date previousDay8Pm() {  
        Calendar t = (Calendar) today().clone();  
        t.set(Calendar.AM_PM, Calendar.PM);  
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);  
        t.set(Calendar.HOUR, 8);  
          
        return t.getTime();  
    }  
      
    private Date previousDay11Pm() {  
        Calendar t = (Calendar) today().clone();  
        t.set(Calendar.AM_PM, Calendar.PM);  
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);  
        t.set(Calendar.HOUR, 11);  
          
        return t.getTime();  
    }  
      
    private Date today1Pm() {  
        Calendar t = (Calendar) today().clone();  
        t.set(Calendar.AM_PM, Calendar.PM);  
        t.set(Calendar.HOUR, 1);  
          
        return t.getTime();  
    }  
      
    private Date theDayAfter3Pm() {  
        Calendar t = (Calendar) today().clone();  
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);       
        t.set(Calendar.AM_PM, Calendar.PM);  
        t.set(Calendar.HOUR, 3);  
          
        return t.getTime();  
    }  
  
    private Date today6Pm() {  
        Calendar t = (Calendar) today().clone();   
        t.set(Calendar.AM_PM, Calendar.PM);  
        t.set(Calendar.HOUR, 6);  
          
        return t.getTime();  
    }  
      
    private Date nextDay9Am() {  
        Calendar t = (Calendar) today().clone();  
        t.set(Calendar.AM_PM, Calendar.AM);  
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);  
        t.set(Calendar.HOUR, 9);  
          
        return t.getTime();  
    }  
      
    private Date nextDay11Am() {  
        Calendar t = (Calendar) today().clone();  
        t.set(Calendar.AM_PM, Calendar.AM);  
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);  
        t.set(Calendar.HOUR, 11);  
          
        return t.getTime();  
    }  
      
    private Date fourDaysLater3pm() {  
        Calendar t = (Calendar) today().clone();   
        t.set(Calendar.AM_PM, Calendar.PM);  
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);  
        t.set(Calendar.HOUR, 3);  
          
        return t.getTime();  
    }  
      
    public ScheduleEvent getEvent() {  
        return event;  
    }  
  
    public void setEvent(ScheduleEvent event) {  
        this.event = event;  
    }  
      
    public void addEvent(ActionEvent actionEvent) {  
        if(event.getId() == null) {
            eventModel.addEvent(event);
        }  
        else {
            eventModel.updateEvent(event);
        }  
          
        event = new DefaultScheduleEvent();  
    }  
      
    public void onEventSelect(SelectEvent selectEvent) {  
        event = (ScheduleEvent) selectEvent.getObject();  
    }  
      
    public void onDateSelect(DateSelectEvent selectEvent) {  
        event = new DefaultScheduleEvent(Math.random() + "", selectEvent.getDate(), selectEvent.getDate());          
    }  
      
    /*public void onEventMove(ScheduleEntryMoveEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    }  
      
    public void onEventResize(ScheduleEntryResizeEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    }*/  
      
    private void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
}


package controller;

import entity.Clients;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import logic.ClientsFacade;

@Named(value = "clientsController")
@SessionScoped
public class ClientsController implements Serializable {
    
    private Boolean visible;
    private String clientName;
    private Clients currentClients;
    private List<Clients> clientsList;
    
    @EJB
    private ClientsFacade clientsFacade;
    
    public ClientsController() {
    }
    
    @PostConstruct
    public void init(){
        visible = false;
        currentClients = new Clients();
        clientsList = clientsFacade.findAll();
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public void toggleVisibility(){
        if(visible){
            this.visible = false;
        }
        else{
            visible = true;
        }
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<Clients> getClientsList() {
        return clientsList;
    }

    public void setClientsList(List<Clients> clientsList) {
        this.clientsList = clientsList;
    }

    public ClientsFacade getClientsFacade() {
        return clientsFacade;
    }

    public void setClientsFacade(ClientsFacade clientsFacade) {
        this.clientsFacade = clientsFacade;
    }

    public Clients getCurrentClients() {
        return currentClients;
    }

    public void setCurrentClients(Clients currentClients) {
        this.currentClients = currentClients;
    }
    
    public void save(){
        clientsFacade.create(currentClients);
        currentClients = new Clients();
        clientsList = clientsFacade.findAll();
        visible = false;
    }
    
    public void editCurrentClients(){
        clientsFacade.edit(currentClients);
        currentClients = new Clients();
        clientsList = clientsFacade.findAll();
        visible= false;
    }
    
    public void reset(){
        this.clientName = null;
    }
    
    public void edit(Integer id){
        currentClients = clientsFacade.find(id);
        visible = true;
    }
    
    public void delete(Integer id){
        clientsFacade.remove(clientsFacade.find(id));
        clientsList = clientsFacade.findAll();
        currentClients.setClientName(null);
    }
    
    
}

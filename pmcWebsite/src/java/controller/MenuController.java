
package controller;

import entity.Menu;
import entity.Page;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import logic.MenuFacade;
import logic.PageFacade;
import org.primefaces.event.ReorderEvent;

@Named(value = "mc")
@SessionScoped
public class MenuController implements Serializable {

    private Boolean visible;
    private String menuName;
    private String pageId;
    private Menu currentMenu;
    private List<Menu> menuList;
    private int a;
    
    @EJB
    private MenuFacade menuFacade;
    @EJB
    private PageFacade pageFacade;
    
    public MenuController() {
    }
    
    @PostConstruct
    public void init(){
        visible = false;
        currentMenu = new Menu();
        menuList = menuFacade.findAll();
        a = menuList.size();
        System.out.println("The size od list is" +a);
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public List<Menu> getMenuList() {
        
        System.out.println("The size of list is" +a);
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
    
    public List<Page> getPageList(){
        return pageFacade.findAll();
    }

    public MenuFacade getMenuFacade() {
        return menuFacade;
    }

    public void setMenuFacade(MenuFacade menuFacade) {
        this.menuFacade = menuFacade;
    }

    public PageFacade getPageFacade() {
        return pageFacade;
    }

    public void setPageFacade(PageFacade pageFacade) {
        this.pageFacade = pageFacade;
    }
    
    public void save(){
        currentMenu.setDisplayOrder(a+1);
        menuFacade.create(currentMenu);
        currentMenu = new Menu();
        menuList = menuFacade.findAll();
        visible =false;
    }
    
    public void editCurrentMenu(){
        menuFacade.edit(currentMenu);
        currentMenu = new Menu();
        menuList = menuFacade.findAll();
        visible =false;
    }
    
    public void reset(){
        this.menuName = null;
        this.pageId = null;
    }
    
    public void edit(Integer id){
        currentMenu = menuFacade.find(id);
        visible = true;
    }
    
    public void delete(Integer id){
        menuFacade.remove(menuFacade.find(id));
        menuList = menuFacade.findAll();
        currentMenu.setMenuName(null);
        currentMenu.setPageId(0);
    }
    public void onRowReorder(ReorderEvent event) {
        
      //  FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Row Moved", "From: " + event.getFromIndex() + ", To:" + event.getToIndex());
      //  FacesContext.getCurrentInstance().addMessage(null, msg);
        Menu sourceMenu=menuFacade.findMenuByDisplayOrder(event.getFromIndex()+1);
        Menu destinationMenu=menuFacade.findMenuByDisplayOrder(event.getToIndex()+1);
        menuFacade.swapDisplayOrder(sourceMenu, destinationMenu);
        menuList=menuFacade.findAllByMenuOrder();
        FacesMessage msg1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Row Moved", "From: " + sourceMenu.getMenuName() + ", To:" + destinationMenu.getMenuName());
        FacesContext.getCurrentInstance().addMessage(null, msg1);
        
        
        
    }

}

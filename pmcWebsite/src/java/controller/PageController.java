
package controller;

import entity.Page;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import logic.PageFacade;

/**
 *
 * @author user
 */
@Named(value = "pc")
@SessionScoped
public class PageController implements Serializable {

    private Boolean visible;
    private String pageName;
    private String pageContent;
    private String pageSlug;
    private String firstPage;
    private String logo;
    private Page currentPage;
    private List<Page> pageList;
    
    @EJB
    private PageFacade pageFacade;
    
    public PageController() {
    }
    
    @PostConstruct
    public void init(){
        visible = false;
        firstPage ="AdminHomePage";
        logo = "../resources/images/assets/LOGO.png";
        currentPage = new Page();
        pageList = pageFacade.findAll();
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
            this.visible = true;
        }
    }
    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public String getPageSlug() {
        return pageSlug;
    }

    public void setPageSlug(String pageSlug) {
        this.pageSlug = pageSlug;
    }

    public String getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(String firstPage) {
        this.firstPage = firstPage;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page page) {
        this.currentPage = page;
    }

    public List<Page> getPageList() {
        return pageList;
    }

    public void setPageList(List<Page> pageList) {
        this.pageList = pageList;
    }

    public PageFacade getPageFacade() {
        return pageFacade;
    }

    public void setPageFacade(PageFacade pageFacade) {
        this.pageFacade = pageFacade;
    }
    
    public void save(){
        pageFacade.create(currentPage);
        pageList = pageFacade.findAll();
        currentPage = new Page();
        visible =false;
    }
    
    public void reset(){
        this.pageName=null;
        this.pageContent=null;
        this.pageSlug=null;
    }
    
    public void editCurrentPage(){
        pageFacade.edit(currentPage);
        pageList = pageFacade.findAll();
        currentPage = new Page();
        visible =false;
    }
    
    public void edit(Integer Id){
        currentPage = pageFacade.find(Id);
        visible = true;
    }
    
    public void delete(Integer Id){
        pageFacade.remove(pageFacade.find(Id));
        pageList = pageFacade.findAll();
        currentPage.setPageName(null);
        currentPage.setPageContent(null);
        currentPage.setPageSlug(null);
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}

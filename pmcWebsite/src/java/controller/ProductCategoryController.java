
package controller;

import entity.Page;
import entity.ProductCategory;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import logic.PageFacade;
import logic.ProductCategoryFacade;

/**
 *
 * @author user
 */
@Named(value = "pcc")
@SessionScoped
public class ProductCategoryController implements Serializable {

    private Boolean visible;
    private String categoryName;
    private ProductCategory currentCategory;
    private List<ProductCategory> categoryList;
    
    @EJB
    private ProductCategoryFacade categoryFacade;
    
    public ProductCategoryController() {
    }
    
    @PostConstruct
    public void init(){
        visible = false;
        currentCategory = new ProductCategory();
        categoryList = categoryFacade.findAll();
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ProductCategory getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(ProductCategory currentCategory) {
        this.currentCategory = currentCategory;
    }

    public List<ProductCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ProductCategory> categoryList) {
        this.categoryList = categoryList;
    }
  
    public void save(){
        categoryFacade.create(currentCategory);
        categoryList = categoryFacade.findAll();
        currentCategory = new ProductCategory();
        visible = false;
    }
    
    public void editCurrentCategory(){
        categoryFacade.edit(currentCategory);
        categoryList = categoryFacade.findAll();
        currentCategory = new ProductCategory();
        visible =false;
    }
    
    public void reset(){
        this.categoryName = null;
    }
    
    public void edit(Integer id){
        currentCategory = categoryFacade.find(id);
        visible = true;
    }
    
    public void delete(Integer id){
        categoryFacade.remove(categoryFacade.find(id));
        categoryList = categoryFacade.findAll();
        currentCategory.setProductCategory(null);
    }
}

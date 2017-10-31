
package controller;

import entity.Product;
import entity.Page;
import entity.ProductCategory;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import logic.ProductFacade;
import logic.PageFacade;
import logic.ProductCategoryFacade;

@Named(value = "prc")
@SessionScoped
public class ProductController implements Serializable {

    private Boolean visible;
    private String productName;
    private String productCategory;
    private Product currentProduct;
    private List<Product> productList;
    
    @EJB
    private ProductFacade productFacade;
    @EJB
    private ProductCategoryFacade categoryFacade;
    
    public ProductController() {
    }
    
    @PostConstruct
    public void init(){
        visible = false;
        currentProduct = new Product();
        productList = productFacade.findAll();
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    
    public List<ProductCategory> getProductCategoryList(){
        return categoryFacade.findAll();
    }

    public ProductFacade getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

  
    public void save(){
        productFacade.create(currentProduct);
        currentProduct = new Product();
        productList = productFacade.findAll();
        visible =false;
    }
    
    public void editCurrentProduct(){
        productFacade.edit(currentProduct);
        currentProduct = new Product();
        productList = productFacade.findAll();
        visible =false;
    }
    
    public void reset(){
        this.productName = null;
        this.productCategory = null;
    }
    
    public void edit(Integer id){
        currentProduct = productFacade.find(id);
        visible = true;
    }
    
    public void delete(Integer id){
        productFacade.remove(productFacade.find(id));
        productList = productFacade.findAll();
        currentProduct.setProductName(null);
        currentProduct.setProductCategoryId(0);
    }
}

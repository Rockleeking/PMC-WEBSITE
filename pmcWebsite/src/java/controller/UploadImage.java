
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.primefaces.model.UploadedFile;

@Named(value = "uploadImage")
@SessionScoped
public class UploadImage implements Serializable {
    
    private static final long serialVersionUID =1L;
    private UploadedFile file;
    
    public UploadImage() {
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
}

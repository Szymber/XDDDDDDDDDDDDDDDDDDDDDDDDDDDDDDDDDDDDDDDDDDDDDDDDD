package View;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


@SuppressWarnings("serial")
public class TxtFileChooser extends JFileChooser {

    private FileNameExtensionFilter filter;
    
    public TxtFileChooser() {
        super();
        this.filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    }
    
    public String getFilename() { 
        setFileFilter(filter);
        showOpenDialog(null);
        File f = getSelectedFile();
        if (f != null) {
            return f.getAbsolutePath();
        }
        return null; // file choosing was canceled
    }   
}
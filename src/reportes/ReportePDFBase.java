package reportes;

import com.itextpdf.text.Paragraph;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Michael on 20/06/2015.
 */
public class ReportePDFBase {
    public static final String RUTAPDF = "../Sistema";

    private String nombreArchivo;
    private String titulo;

    private Paragraph detalle;

    private String fecha;

    protected void showDocument(){
        try {
            //abrirlo por pantalla
            File path = new File(nombreArchivo);
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
}

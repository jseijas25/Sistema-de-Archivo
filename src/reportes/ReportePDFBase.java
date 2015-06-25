package reportes;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import funciones._Con;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

public abstract class ReportePDFBase {
    public static final String RUTAPDF = System.getProperty("user.home")+System.getProperty("file.separator")+"Desktop"+System.getProperty("file.separator");
    protected static final String RUTA_LOGO = _Con.RUTA_IMAGENES + System.getProperty("file.separator")+"logo"+System.getProperty("file.separator");

    protected String nombreArchivo;
    protected String titulo;
    protected String ruta;

    protected Paragraph detalle;

    protected String fechaCompleta, hora, fecha_mes_largo, fechaArchivo, fecha_dia, fecha_mes, fecha_anyo;
    protected Calendar calendarFecha;
    protected Document document;
    private PdfWriter writer;

    public ReportePDFBase(){
        calendarFecha = Calendar.getInstance();
        fecha_dia = calendarFecha.get(Calendar.DAY_OF_MONTH) < 10 ? "0"+ calendarFecha.get(Calendar.DAY_OF_MONTH) : ""+ calendarFecha.get(Calendar.DAY_OF_MONTH);
        fecha_mes = calendarFecha.get(Calendar.MONTH) < 10 ? "0" + calendarFecha.get(Calendar.MONTH) : ""+calendarFecha.get(Calendar.MONTH);
        fecha_anyo = ""+calendarFecha.get(Calendar.YEAR);
        fecha_mes_largo = calendarFecha.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        fechaCompleta = "Fecha: "+fecha_dia+"/"+fecha_mes+"/"+fecha_anyo;
        fechaArchivo = fecha_dia + "-" + fecha_mes_largo + "-" + fecha_anyo;
        ruta = RUTAPDF;

        hora = calendarFecha.get(Calendar.HOUR) < 10 ? "0"+calendarFecha.get(Calendar.HOUR) : ""+ calendarFecha.get(Calendar.HOUR);
        hora += calendarFecha.get(Calendar.MINUTE) < 10 ? ":0" + calendarFecha.get(Calendar.MINUTE) : ":" + calendarFecha.get(Calendar.MINUTE) ;
        document = new Document(PageSize.LETTER, 50, 50, 50, 50);
        try{
            createDocument();
        } catch (DocumentException | IOException e){
            e.printStackTrace();
        }
        showDocument();
    }

    protected abstract void createDocument() throws DocumentException, IOException;

    protected void openWriter() throws DocumentException, IOException {
        writer = PdfWriter.getInstance(document, new FileOutputStream(ruta+nombreArchivo));
        writer.setInitialLeading(16);
        com.itextpdf.text.Rectangle rct = new com.itextpdf.text.Rectangle(36, 54, 559, 788);
        writer.setBoxSize("art", rct);
        document.open();
    }

    protected void closeWriter(){
        com.itextpdf.text.Rectangle rect = writer.getBoxSize("art");
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.format("pagina %d", writer.getPageNumber())),
                (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);

        document.close();
        writer.close();
    }

    public void showDocument(){
        try {
            //abrirlo por pantalla
            File path = new File(ruta+nombreArchivo);
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
}

package reportes;

import java.io.File;
import java.io.IOException;

import com.itextpdf.text.*;
import dataBase.ProgramaDAO;

import funciones._Con;
import modelos.Estudiante;
import modelos.Expediente;
import modelos.Programa;

public class ReportePdfSolvencia extends ReportePDFBase {

    @Override
    protected void createDocument() throws DocumentException, IOException {
        Estudiante estudiante = _Con.getInstance().getEstudiante();
        Expediente expediente = _Con.getInstance().getExpediente();
        Programa programa = new Programa();
        programa.setId(expediente.getId_Programa());
        ProgramaDAO.readByID(programa);

        Paragraph cuerpo1, cuerpo2, firma;
        ruta = RUTAPDF+fecha_anyo+System.getProperty("file.separator")+fecha_mes_largo+System.getProperty("file.separator");
        File folder = new File(ruta);
        if(!folder.exists() && !folder.mkdirs()){
            System.out.println("La ruta:\""+ruta+"\" no pudo ser creada. Guardando en escritorio");
            ruta = RUTAPDF;
        }
        document.addAuthor("POSTGRADO");
        document.addTitle("EJEMPLO1");
        nombreArchivo = estudiante.getCedula() + " - Exp " + expediente.getNumber() + " de " + programa.getName() + ".pdf";

        openWriter();

        Image fondo = Image.getInstance(RUTA_LOGO + "reporte.png");
        fondo.scaleToFit(230, 230);
        fondo.setAlignment(Chunk.ALIGN_LEFT);
        document.add(fondo);
        document.add(Chunk.NEWLINE);

        //-----------> fecha<-------------------------

        detalle = new Paragraph(fechaCompleta, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK));
        document.add(detalle);
        detalle = new Paragraph(hora, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK));
        document.add(detalle);
        document.add(Chunk.NEWLINE);

        //-----------> titulo<-------------------------

        detalle = new Paragraph(titulo, FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK));
        detalle.setAlignment(Element.ALIGN_CENTER);
        document.add(detalle);
        document.add(Chunk.NEWLINE);

        //-----------> parrafo 1<-------------------------

        cuerpo1 = new Paragraph(30, "      Quien Suscribe, Ing. Elias Oswaldo Lopez Delgado encargado" +
                " de la Oficina de Grado, del area de Postgrado de Universidad Romulo " +
                "Gallegos, hace constar que a la(el) ciudadana (o): " + estudiante.getNombre() + " " +
                "" + estudiante.getApellido() + ", titular de la " + "cedula de identidad: Nº V- "
                + estudiante.getCedula() + " estudiante regular del programa de " + programa.getName()+
                " postgrado tiene el expediente " + expediente.getNumber()+
                " completo para poder tramitar la inscripcion del Trabajo Especial de Grado," +
                " Trabajo de Grado y/o Tesis Doctoral. ", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL, BaseColor.BLACK));
        cuerpo1.setExtraParagraphSpace(2);
        cuerpo1.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(cuerpo1);

        //-----------> parrafo 2<-------------------------

        cuerpo2 = new Paragraph(30, "      Constancia que se expide a peticion de parte interesada en la ciudad de San" +
                " Juan de los Morros, a los " + fecha_dia + " dias del mes de " +
                fecha_mes_largo + " del año: " + fecha_anyo + ".", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL, BaseColor.BLACK));
        cuerpo2.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(cuerpo2);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        firma = new Paragraph(30, "ATENTAMENTE", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL, BaseColor.BLACK));
        firma.setAlignment(Element.ALIGN_CENTER);
        document.add(firma);

        document.add(Chunk.NEWLINE);
        firma = new Paragraph(30, "ING. ELIAS OSWALDO LOPEZ DELGADO", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL, BaseColor.BLACK));
        firma.setAlignment(Element.ALIGN_CENTER);
        document.add(firma);

        closeWriter();
    }
}

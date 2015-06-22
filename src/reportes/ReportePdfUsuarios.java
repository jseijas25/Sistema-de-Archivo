package reportes;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;

import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.itextpdf.text.pdf.PdfWriter;

import dataBase.UsuarioDAO;
import modelos.Usuario;


public class ReportePdfUsuarios {
    public String resultado = "../Sistema/ReporteUsuario.pdf";
    public String titulo = "REPORTE DE USUARIOS";

    public Paragraph detalle;

    public ReportePdfUsuarios() {
        //Date fechaActual = new Date();
        Calendar fechaActual = Calendar.getInstance();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        String fecha = "Fecha: " + fechaActual.get(Calendar.DAY_OF_MONTH) + "/" + fechaActual.get(Calendar.MONTH) + "/" + fechaActual.get(Calendar.YEAR);

        try {
            Document document = new Document(PageSize.LETTER, 50, 50, 50, 50);
            //Al documento se le puede a�adir cierta metaInformacion
            document.addAuthor("POSTGARDO");
            document.addTitle("EJEMPLO1");
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(resultado));
            writer.setInitialLeading(16);
            Rectangle rct = new Rectangle(36, 54, 559, 788);
            writer.setBoxSize("art", rct);
            document.open();
            //-----------> logo <-------------------------


            String ruta = "../Sistema/imagenesSistema/logo/";
            Image fondo = Image.getInstance(ruta + "usuarios.png");
            fondo.scaleToFit(230, 230);
            fondo.setAlignment(Chunk.ALIGN_LEFT);
            //  fondo.scaleToFit(50,50);//escala de la imagen
            //fondo.setAlignment(Image.MIDDLE | Image.UNDERLYING);
            document.add(fondo);
            //-----------> fecha<-------------------------
            detalle = new Paragraph(fecha, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK));

            document.add(detalle);

            String xtiempo = "Hora:" + fechaActual.get(Calendar.HOUR) + ":" + fechaActual.get(Calendar.MINUTE);
            detalle = new Paragraph(xtiempo, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK));
            document.add(detalle);
            //-----------> titulo<-------------------------
            detalle = new Paragraph(titulo, FontFactory.getFont(FontFactory.HELVETICA, 14, Font.UNDERLINE, BaseColor.BLACK));

            detalle.setAlignment(Element.ALIGN_CENTER);
            //Lo a�adimos al documento
            document.add(detalle);

            document.add(Chunk.NEWLINE);
            //**************************************************************
            //Ejemplos de TABLE


            //A�adir tabla 3 columnas
            PdfPTable table = new PdfPTable(3);
            //A�adir CABECERA
            PdfPCell cell = new PdfPCell(new Phrase("ID", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK)));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("NOMBRE", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK)));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("NIVEL", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK)));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            ArrayList<Usuario> usuarios = new ArrayList<>();
            UsuarioDAO.findAll(usuarios);
            for (Usuario usuario : usuarios) {
                if (usuario.isActive()) {

                    cell = new PdfPCell(new Phrase(String.valueOf(usuario.getId()), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    // cell.setBorder(PdfPCell.NO_BORDER);
                    table.addCell(cell);


                    cell = new PdfPCell(new Phrase(usuario.getNombre(), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK)));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    //cell.setBorder(PdfPCell.NO_BORDER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(String.valueOf(usuario.getNivel()), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    //cell.setBorder(PdfPCell.NO_BORDER);
                    table.addCell(cell);


                }
            }

            document.add(table);


            //FIN Ejemplos de TABLE
            //numero de p�gina

            Rectangle rect = writer.getBoxSize("art");
            //numero de pagina
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.format("pagina %d", writer.getPageNumber())),
                    (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);

            document.close();

        } catch (Exception ex) {

        }
    }
}
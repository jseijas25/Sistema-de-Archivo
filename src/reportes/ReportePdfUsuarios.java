package reportes;

import java.io.IOException;
import java.util.*;


import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import dataBase.UsuarioDAO;
import modelos.Usuario;


public class ReportePdfUsuarios extends ReportePDFBase {

    @Override
    protected void createDocument() throws DocumentException, IOException {

        nombreArchivo = "Reporte Usuarios" + fechaArchivo+".pdf";

        //Al documento se le puede a�adir cierta metaInformacion
        document.addAuthor("POSTGRADO");
        document.addTitle("EJEMPLO1");
        openWriter();

        //-----------> logo <-------------------------

        Image fondo = Image.getInstance(RUTA_LOGO + "usuarios.png");
        fondo.scaleToFit(230, 230);
        fondo.setAlignment(Chunk.ALIGN_LEFT);
        //  fondo.scaleToFit(50,50);//escala de la imagen
        //fondo.setAlignment(Image.MIDDLE | Image.UNDERLYING);
        document.add(fondo);
        //-----------> fecha<-------------------------
        detalle = new Paragraph(fechaCompleta, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK));

        document.add(detalle);

        detalle = new Paragraph(hora, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK));
        document.add(detalle);
        //-----------> titulo<-------------------------
        detalle = new Paragraph("REPORTE DE USUARIOS", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.UNDERLINE, BaseColor.BLACK));

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

        closeWriter();
    }
}
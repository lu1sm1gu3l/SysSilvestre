package util.clases.grlGeneral;




import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
//import jxl.write.WritableCellFormat;
//import jxl.write.WritableFont;
//        <copy todir="${build.classes.dir}">
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author Ju@n
 */

public class CLExportarExcel {

    private JTable oTable;
    private String NamePatch;
    private String[] etiquetasSup;
    private String[] etiquetasInferVertic;
    private String[] etiquetasInferHorizont;
    private String titulo;
    public CLExportarExcel(JTable oTable,String[] etiquetasSup,String[] etiquetasInferHoriz,String[] etiquetasInferVertic,String Titulo)
    {
       this.oTable=oTable;
       this.etiquetasInferVertic=etiquetasInferVertic;
       this.etiquetasInferHorizont=etiquetasInferHoriz;
       this.etiquetasSup=etiquetasSup;
       this.titulo=Titulo;
       
    }
    private void setPatch(String NamePatch)
    {
        this.NamePatch=NamePatch;
    }
    public  void escribirExcel()
    {
        try
        {
            //Se crea el libro Excel
            HSSFWorkbook wb = new HSSFWorkbook();           
            //Se crea una nueva hoja dentro del libro
            HSSFSheet sheet = wb.createSheet("Libro 1");
            sheet.setForceFormulaRecalculation(true);
            

                //Crear una cabecera  Sistema Operacion de Moneda Extranjera
		HSSFRow headerRow = sheet.createRow(0);

	        //Definir una celda en la cabecera y le asignamos un valor
		HSSFCell headerCell = headerRow.createCell((short)2);
                
                headerCell.setCellValue(new HSSFRichTextString(titulo));


            // creando el estilo de la celda
            HSSFFont fuente = wb.createFont();
            HSSFCellStyle estiloNameColumna= wb.createCellStyle();
            estiloNameColumna.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            estiloNameColumna.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            estiloNameColumna.setFillForegroundColor(new HSSFColor.LIGHT_GREEN().getIndex());
            fuente.setFontHeight((short)160);
            estiloNameColumna.setFont(fuente);

            HSSFFont fuenteCelda = wb.createFont();
            HSSFCellStyle estiloCelda= wb.createCellStyle();
            fuenteCelda.setFontHeight((short)160);
            estiloCelda.setFont(fuenteCelda);
           
            sheet.setRowSumsBelow(true);
                // ancho de cada columna



                int numfila=2;
                int numcol=0;
                  
                  for (int i = 0; i < etiquetasSup.length; i=i+2) {
                        HSSFRow ofilaEtiq;
                        ofilaEtiq=sheet.createRow(numfila);
                        HSSFCell celda =ofilaEtiq.createCell(1);
                        HSSFCell celda2 =ofilaEtiq.createCell(2);
                        celda.setCellStyle(estiloCelda);
                        celda2.setCellStyle(estiloCelda);
                        celda.setCellValue(etiquetasSup[i].replace(":", ""));
                        celda2.setCellValue(etiquetasSup[i+1]);
                        numfila++;

                    }
                  numfila++;
                  HSSFRow ofila = null;
                  ofila=sheet.createRow(numfila);
                  numfila++;
                  for(int i=0;i<oTable.getColumnCount();i++)
                  {
                      TableColumn oTableColumn=oTable.getColumnModel().getColumn(i);
                      if(oTableColumn.getPreferredWidth()!=0)
                      {
                      sheet.setColumnWidth((short)numcol,(short)oTableColumn.getPreferredWidth()*25);
                      HSSFCell celda =ofila.createCell(numcol);
                      celda.setCellStyle(estiloNameColumna);
                      celda.setCellValue(oTable.getColumnName(i));
                      numcol++;
                      }
                      
                  }
              if(oTable.getRowCount()>0)
                {

                    for(int fila=0;fila<oTable.getRowCount();fila++)
                    {
                      HSSFRow ofilasDetalle;
                      ofilasDetalle=sheet.createRow(numfila);
                      numcol=0;
                      for(int col=0;col<oTable.getColumnCount();col++)
                        {
                          TableColumn oTableColumn=oTable.getColumnModel().getColumn(col);
                          if(oTableColumn.getPreferredWidth()!=0)
                            {
                              HSSFCell celda =ofilasDetalle.createCell(numcol);
                              Object oCelda=oTable.getValueAt(fila, col);
                              if(oCelda!=null)
                              {

                                try
                                {

                                   celda.setCellValue(Double.parseDouble(oCelda.toString()));
                                }
                                catch(Exception e)
                                {
                                   celda.setCellValue(oCelda.toString());
                                }

                              }else
                               celda.setCellValue(VerificadorDeTxt.convertString(oCelda));
                               celda.setCellStyle(estiloCelda);
                              numcol++;
                            }

                        }
                      numfila++;
                    }

                    numfila++;
                    int col=1;
                    HSSFRow ofilaEtiq=null;
                    for (int i = 0; i < etiquetasInferHorizont.length; i++) {                        
                        if(i%6==0)
                        {
                          numfila++;
                          col=1;
                          ofilaEtiq=sheet.createRow(numfila);
                        }
                        HSSFCell celda =ofilaEtiq.createCell(col);
                        try
                        {
                           celda.setCellValue(Double.parseDouble(etiquetasInferHorizont[i].toString()));
                        }
                        catch(Exception e)
                        {
                            celda.setCellValue(etiquetasInferHorizont[i]);
                        }
                        
                        col++;
                        celda.setCellStyle(estiloCelda);
                    }

                    for (int i = 0; i < etiquetasInferVertic.length; i=i+2) {
                          numfila++;
                          ofilaEtiq=sheet.createRow(numfila);
                        HSSFCell celda =ofilaEtiq.createCell(5);
                        celda.setCellStyle(estiloCelda);
                        celda.setCellValue(etiquetasInferVertic[i]);
                        HSSFCell celda2 =ofilaEtiq.createCell(6);
                        celda2.setCellStyle(estiloCelda);
                        try
                        {
                           celda2.setCellValue(Double.parseDouble(etiquetasInferVertic[i+1].toString()));
                        }
                        catch(Exception e)
                        {
                            celda2.setCellValue(etiquetasInferVertic[i+1]);
                        }

                    }
                   // sheet.setColumnWidth((short)0,(short)2300);
 
               }
             
            //Escribimos los resultados a un fichero Excel
            FileOutputStream fileOut = new FileOutputStream(NamePatch);

            wb.write(fileOut);// Visualizamos el excel
            fileOut.close();
        }
        catch(IOException e)
        {
            System.out.println("Error al escribir el fichero.");
        }
    }

    public  void abrir()
   {
             String file = NamePatch;
            try
                {
                      Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL "+file);
                }
          catch (Exception e)
                {
                       System.out.println("Error al abrir el archivo " + file + "\n" + e.getMessage());
                }


   }

    public void GuardarArchivoExcel(Component oComponent)
    {
      JFileChooser  jFileChooser1 = new JFileChooser();
      int returnVal = jFileChooser1.showSaveDialog(null);
     if (returnVal == jFileChooser1.APPROVE_OPTION)
     {
        File file = jFileChooser1.getSelectedFile();
         if(file!=null)
         {
            if(!file.exists())
            {
            String FileName=file.getPath()+".xls";
            setPatch(FileName);
            escribirExcel();
            abrir();

            }
            else
            {
                 int i=JOptionPane.showConfirmDialog(oComponent,"¿Esta seguro de sobreescribir el archivo?");
                 if(i==JOptionPane.OK_OPTION)
                 {
                    if(file.delete())
                        {
                       // imprimirPDF(file);
                        }
                    else
                        {
                        JOptionPane.showMessageDialog(oComponent,"No se pudo sobreescribir el archivo","Error",JOptionPane.ERROR_MESSAGE);
                        }
                 }
            }
        }
        else
        {
        JOptionPane.showMessageDialog(oComponent,"Debe elegir una ruta para guardar el archivo","Error",JOptionPane.ERROR_MESSAGE);
        }
     }
  }

}

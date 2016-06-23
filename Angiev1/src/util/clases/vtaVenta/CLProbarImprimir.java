/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.vtaVenta;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.OrientationRequested;

import modelo.vtaVenta.entidad.CEComprobanteVenta;
import util.clases.grlGeneral.CLImprimir;
import util.clases.grlGeneral.CLRedondear;
import view.FrmSistemaMenu;

public class CLProbarImprimir implements Printable
{
    CEComprobanteVenta oCEComprobanteVenta;
    private List<CLProbarImprimir> cord;
    private int cor_x=0;
    private int cor_y=0;
    private String texto ="";
  private int length_total=0;
  private int tamanio=0;String pFont="";
    public CLProbarImprimir(int x,int y,
            String ptexto,int ptamanio,String pFont)
    {
            super();
//            CoordenadasLista(oCEComprobanteVenta.getIdTipoComprobanteVenta());
            this.cor_x=x;
            this.cor_y=y;
            this.texto=ptexto;
            this.tamanio=ptamanio;
            this.pFont=pFont;
//            length_total=(CLRedondear.FormatearDosDigitos(oCEComprobanteVenta.getTotalPagar())).length();
              length_total=(CLRedondear.FormatearDosDigitos(14205).length());
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(OrientationRequested.PORTRAIT);

         //   aset.add(MediaSizeName.JIS_B7);


           // job.print();

            aset.add(new Copies(1));
            aset.add(new JobName("Comprobante de Venta", null));

            PrinterJob pj = PrinterJob.getPrinterJob();

            Paper paper= new Paper();
           paper.setSize(634,396);
       //     paper.setSize(wsize,hsize);
           paper.setImageableArea(0,0,634,396);
       //    paper.setImageableArea(0,0,wsize,hsize);
       //    PageFormat pf= new pj.defaultPage();
            PageFormat pf=  pj.defaultPage();
           pf.setPaper(paper);
           pj.setPrintable(this,pf);
          //  pj.setPrintable(this);

            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
            try
            {
                   pj.setPrintService(printService);
                   pj.print(aset);
                  // pj.print();
            }
            catch (PrinterException pe)
            {
                    System.err.println(pe);
            }

        
    }
     public int print (Graphics g, PageFormat f, int pageIndex)
     {



        if (pageIndex > 0) {
            return NO_SUCH_PAGE;

        }

       // Paper opaper= new Paper();
      //      opaper.setImageableArea(50, 50, 400, 500);

    //        f.setPaper(opaper);


         Graphics2D g2d = (Graphics2D) g;
        g2d.translate(f.getImageableX(), f.getImageableY());

        g2d.setFont(new Font(pFont,0,tamanio));

        g2d.drawString(texto, cor_x, cor_y);
          return PAGE_EXISTS;
//           return PAGE_EXISTS;
         }


    
  

     private String AlinearText(float num)
      {

         String numStr=CLRedondear.FormatearDosDigitos(num);
         int lenghTxt=numStr.length();

         for(int i=lenghTxt;i<length_total;i++)
         {
            numStr="  "+numStr;
         }
         return numStr;
     }

}


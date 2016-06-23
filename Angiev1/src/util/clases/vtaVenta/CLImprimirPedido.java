/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.vtaVenta;
import controller.acceso.ConexionBD;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;
import modelo.vtaVenta.entidad.CEPedidoDetalle;

import modelo.vtaVenta.entidad.CEPedidoMatriz;
import util.clases.grlGeneral.CLImprimir;
import util.clases.grlGeneral.CLRedondear;
import view.FrmSistemaMenu;

public class CLImprimirPedido implements Printable
{
    CEPedidoMatriz oCEPedidoMatriz;
    private List<CLImprimir> cord;

  private int length_total=0;
  private boolean isPrint=false;
  private double alto=0;
  private double ancho=0;
  private double altofac=0;
  private double anchofac=0;
  private int idimprbol=0;
  private int idtick=0;
  private String msj="";
  public CLImprimirPedido()
    {

      String mensaje="";
      PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
    PrintService[] services= PrintServiceLookup.lookupPrintServices(null, aset);
       for(int i=0;i<services.length;i++)
        {

         mensaje=mensaje+"indice :"+i+" nombre :"+services[i].getName()+"\n";
        }
    JOptionPane.showMessageDialog(null,mensaje);
  }
    public CLImprimirPedido(CEPedidoMatriz oCEPedidoMatriz,String pmsj)
    {
            super();
             Properties props = new Properties();
            try {
                props.load(ConexionBD.class.getResourceAsStream("PropiedadesDelSistema.properties"));
            } catch (IOException ex) {
                Logger.getLogger(CLImprimirPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
//             alto = Double.parseDouble(props.getProperty("ayn.alto"));
//             ancho = Double.parseDouble(props.getProperty("ayn.ancho"));
//             altofac = Double.parseDouble(props.getProperty("ayn.altofac"));
//             anchofac = Double.parseDouble(props.getProperty("ayn.anchofac"));
//             idimprbol = Integer.parseInt(props.getProperty("ayn.idbol"));
             idtick = Integer.parseInt(props.getProperty("ayn.idtick"));
            this.msj=pmsj;
            CoordenadasLista(oCEPedidoMatriz.getIdTipoComprobante());
            this.oCEPedidoMatriz=oCEPedidoMatriz;
            length_total=(CLRedondear.FormatearDosDigitos(oCEPedidoMatriz.getTotalPagar())).length();
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(OrientationRequested.PORTRAIT);
         // 
           // job.print();
            aset.add(new Copies(1));
            aset.add(new JobName(oCEPedidoMatriz.getTipoComprobante()+" Nº "+oCEPedidoMatriz.getNumComprobante(), null));

            PrinterJob pj = PrinterJob.getPrinterJob();

           double widthPaper=0;
           double heighPaper=0;
           Paper paper= new Paper();
          // paper.setSize(634,396);
          // paper.setImageableArea(0,0,634,396);
           
           PageFormat pf=  pj.defaultPage();
           

           PrintService[] services= PrintServiceLookup.lookupPrintServices(null, aset);
           PrintService printService=PrintServiceLookup.lookupDefaultPrintService();
//           if(oCEPedidoMatriz.getIdTipoComprobante()==2)
//           {

                  widthPaper=72*(8/2.54);
                  heighPaper=72*(23/2.54);
                  paper.setSize(widthPaper,heighPaper);
                  paper.setImageableArea(0,0,widthPaper,heighPaper);
                  
                  pf.setPaper(paper);
                  pj.setPrintable(this,pf);
                  printService=services[idtick];
//            }
//            else
//            {
//               widthPaper=72*(ancho/2.54);
//               heighPaper=72*(alto/2.54);
//               paper.setSize(widthPaper,heighPaper);
//               paper.setImageableArea(0,0,widthPaper,heighPaper);
//
//               pf.setPaper(paper);
//               pj.setPrintable(this,pf);
//
//               printService=services[idimprbol];
//            }
            try
            {
                   pj.setPrintService(printService);
                   pj.print(aset);
                   isPrint=true;
            }
            catch (PrinterException pe)
            {
                    System.err.println(pe);
                    isPrint=false;
            }

        
    }
     public int print (Graphics g, PageFormat f, int pageIndex)
     {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;

        }
        int tamanio=6;
        String format="Roman";
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(f.getImageableX(), f.getImageableY());
        g2d.setFont(new Font(format,0,tamanio));
      //  g2d.drawString(this.AlinearText(14205),cor_x,cor_y);
       if (pageIndex == 0)
       {
                g2d.setFont(new Font(format,0,7));
              g2d.drawString("Señor :"+oCEPedidoMatriz.getCliente(),2,5);//cliente
              g2d.setFont(new Font("tahoma",0,9));
              g2d.drawString("Pedido:  "+oCEPedidoMatriz.getCodigo(),2,17);//numcomp
              g2d.setFont(new Font(format,0,7));
              g2d.drawString(msj,100,17);//numcomp
          //    g2d.drawString("Cant",5,25);//
              
                   for(int i=0,y=30; i<oCEPedidoMatriz.getLstPedidoDetalle().size();i++,y=y+13)
                {
                  CEPedidoDetalle oCEPedidoDetalle= oCEPedidoMatriz.getLstPedidoDetalle().get(i);
                  g2d.setFont(new Font("tahoma",0,6));
                  g2d.drawString("* "+this.DesFormatearDecimal(oCEPedidoDetalle.getCantidad())+"",1,y);
                  g2d.setFont(new Font(format,0,tamanio));
                  g2d.drawString(oCEPedidoDetalle.getUnidadMedida()+"-",10,y);
                  g2d.drawString(oCEPedidoDetalle.getProducto()+"",32,y);
                  g2d.setFont(new Font("tahoma",0,7));
                  y=y+13;
                  g2d.drawString("precio uni : "+AlinearText(oCEPedidoDetalle.getPrecio()),2,y);
                  g2d.setFont(new Font(format,0,tamanio));
                  g2d.drawString("ALM Nº 0"+oCEPedidoDetalle.getIdAlmacen(),80,y);

//                  g2d.drawString(AlinearText(oCEPedidoMatrizDetalle.getExonerado())+"",cord.get(12).getX(),x);
//                  g2d.drawString(AlinearText(oCEPedidoMatrizDetalle.getImporteConDescuento())+"",cord.get(13).getX(),x);
                }
               
          }
           
//           


         
return PAGE_EXISTS;

}

    
     private void CoordenadasLista(int idComp)
    {
      List<CLImprimir> lstImpresion= FrmSistemaMenu.listImprimir;
      cord=new ArrayList<CLImprimir>();

      for(int i=0;i<lstImpresion.size();i++)
        {
          CLImprimir oCLImprimir=lstImpresion.get(i);

          if(idComp==oCLImprimir.getIdTipoComprobante())
          {
           cord.add(oCLImprimir);
          }
        }
    }

     private String AlinearText(float num)
      {
         if(num==0)
         {
             return "";
         }
         String numStr=CLRedondear.FormatearDosDigitos(num);
         int lenghTxt=numStr.length();
         for(int i=lenghTxt;i<length_total;i++)
         {
            numStr="  "+numStr;
         }
         return numStr;
     }

      private  String DesFormatearDecimal( float Numero)
    {


           String numstrg=Float.toString(Numero);
          String numint=numstrg.substring(0,numstrg.indexOf('.'));
         String numdec=numstrg.substring(numstrg.indexOf('.'),numstrg.length());

         String decimal=this.Desformatear(numdec);

         if(decimal.equals(".")){
             decimal="";
         }
         return numint + decimal;

    }
    private  String Desformatear(String num)
    {

        String UltimoDigito=num.substring(num.length()-1,num.length());

        if(UltimoDigito.equals("0"))
          return Desformatear(num.substring(0,num.length()-1));
        else
        return num;
    }

    public boolean isIsPrint() {
        return isPrint;
    }

}


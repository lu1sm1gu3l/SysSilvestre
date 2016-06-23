/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.vtaVenta;
import controller.acceso.ConexionBD;
import controller.vtaVenta.CCSerie;
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
import modelo.grlGeneral.entidad.CEImpresora;

import modelo.vtaVenta.entidad.CEComprobanteVenta;
import modelo.vtaVenta.entidad.CEComprobanteVentaDetalle;
import modelo.vtaVenta.entidad.CESerie;
import util.clases.grlGeneral.CLImprimir;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ParserNumerosALetras;
import view.FrmSistemaMenu;

public class CLImprimirComprobante1 implements Printable
{
    CEComprobanteVenta oCEComprobanteVenta;
    private List<CLImprimir> cord;

  private int length_total=0;
  private boolean isPrint=false;
  private double alto=0;
  private double ancho=0;
  private String Impresora="";

    public CLImprimirComprobante1(CEComprobanteVenta oCEComprobanteVenta)
    {
            super();
            try{            
             CESerie oCESerie=CCSerie.consultarImprimirSerie( FrmSistemaMenu.IdPuntoVenta,oCEComprobanteVenta.getIdTipoComprobanteVenta());
             alto = oCESerie.getAlto();
             ancho = oCESerie.getAncho();
             Impresora=oCESerie.getNombreImpresora();
             CEImpresora oCEImpresora = new CEImpresora();
             int indiceImprisora=oCEImpresora.buscarIndiceImpresora(Impresora);
            CoordenadasLista(oCEComprobanteVenta.getIdTipoComprobanteVenta());
            this.oCEComprobanteVenta=oCEComprobanteVenta;
            length_total=(CLRedondear.FormatearDosDigitos(oCEComprobanteVenta.getMontoTotal())).length();
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(OrientationRequested.PORTRAIT);
         // 
           // job.print();
            aset.add(new Copies(1));
            aset.add(new JobName(oCEComprobanteVenta.getTipoComprobante()+" Nº "+oCEComprobanteVenta.getNumComprobante(), null));

            PrinterJob pj = PrinterJob.getPrinterJob();

           double widthPaper=0;
           double heighPaper=0;
           Paper paper= new Paper();
          // paper.setSize(634,396);
          // paper.setImageableArea(0,0,634,396);
           
           PageFormat pf=  pj.defaultPage();
           

           PrintService[] services= PrintServiceLookup.lookupPrintServices(null, aset);
           PrintService printService=PrintServiceLookup.lookupDefaultPrintService();
           if(oCEComprobanteVenta.getIdTipoComprobanteVenta()==2)
           {
                  widthPaper=72*(ancho/2.54);
                  heighPaper=72*(alto/2.54);
                  paper.setSize(widthPaper,heighPaper);
                  paper.setImageableArea(0,0,widthPaper,heighPaper);                  
                  pf.setPaper(paper);
                  pj.setPrintable(this,pf);
                  printService=services[indiceImprisora];
            }
            else if(oCEComprobanteVenta.getIdTipoComprobanteVenta()==1)
            {

               widthPaper=72*(ancho/2.54);
               heighPaper=72*(alto/2.54);
               paper.setSize(widthPaper,heighPaper);
               paper.setImageableArea(0,0,widthPaper,heighPaper);

               pf.setPaper(paper);
               pj.setPrintable(this,pf);

               printService=services[indiceImprisora];
            }

           else if(oCEComprobanteVenta.getIdTipoComprobanteVenta()==3)
            {
                
                  widthPaper=72*(8/2.54);
                  heighPaper=72*(24/2.54);
                  paper.setSize(widthPaper,heighPaper);
                  paper.setImageableArea(0,0,widthPaper,heighPaper);

                  pf.setPaper(paper);
                  pj.setPrintable(this,pf);
                  printService=services[indiceImprisora];

            }
           else if(oCEComprobanteVenta.getIdTipoComprobanteVenta() == 4)
           {
                  widthPaper=72*(ancho/2.54);
                  heighPaper=72*(alto/2.54);
                  paper.setSize(widthPaper,heighPaper);
                  paper.setImageableArea(0,0,widthPaper,heighPaper);
                  pf.setPaper(paper);
                  pj.setPrintable(this,pf);
                  printService=services[indiceImprisora];
            }
           
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
       catch(Exception e)
       {
        JOptionPane.showMessageDialog(null,"Impresion Cancelada","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
       }

        
    }


     public int print (Graphics g, PageFormat f, int pageIndex)
     {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;

        }
        int tamanio=9;
        String format="Roman";
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(f.getImageableX(), f.getImageableY());
        g2d.setFont(new Font(format,0,tamanio));
      //  g2d.drawString(this.AlinearText(14205),cor_x,cor_y);
       if (pageIndex == 0)
       {
            if(oCEComprobanteVenta.getIdTipoComprobanteVenta()==1)
            {

              g2d.drawString(oCEComprobanteVenta.getCliente(),cord.get(0).getX(),cord.get(0).getY());//cliente
              g2d.drawString(oCEComprobanteVenta.getEmpleado(),cord.get(1).getX(),cord.get(1).getY());//empleado
              g2d.drawString(oCEComprobanteVenta.getDireccion(),cord.get(2).getX(),cord.get(2).getY());//direccion
              g2d.drawString(oCEComprobanteVenta.getDNI(),cord.get(3).getX(),cord.get(3).getY());//DNI
              g2d.drawString(oCEComprobanteVenta.getFecha(),cord.get(4).getX(),cord.get(4).getY());//fecha
              g2d.drawString("Nº Pedido "+oCEComprobanteVenta.getNumPedido(),cord.get(16).getX(),cord.get(16).getY());//numPedido
              g2d.drawString(oCEComprobanteVenta.getNumComprobante(),cord.get(5).getX(),cord.get(5).getY());//numcomp

              g2d.setFont(new Font("tahoma",0,8));
                   for(int i=0,x=cord.get(6).getY(); i<oCEComprobanteVenta.getoLstComprobanteDetalle().size();i++,x=x+cord.get(7).getX())
                {
                  CEComprobanteVentaDetalle oCEComprobanteVentaDetalle= oCEComprobanteVenta.getoLstComprobanteDetalle().get(i);

                  g2d.drawString(this.DesFormatearDecimal(oCEComprobanteVentaDetalle.getCantidad())+"",cord.get(8).getX(),x);
                  g2d.setFont(new Font(format,0,tamanio));
                  g2d.drawString(oCEComprobanteVentaDetalle.getUnidadMedida(),cord.get(9).getX(),x);
                  g2d.drawString(oCEComprobanteVentaDetalle.getProducto(),cord.get(10).getX(),x);
                  g2d.setFont(new Font("tahoma",0,8));
                  g2d.drawString(AlinearTextConCero(oCEComprobanteVentaDetalle.getPrecioconDesc())+"",cord.get(11).getX(),x);
                  g2d.drawString(AlinearText(oCEComprobanteVentaDetalle.getExonerado())+"",cord.get(12).getX(),x);
                  g2d.drawString(AlinearTextConCero(oCEComprobanteVentaDetalle.getImporteConDescuento())+"",cord.get(13).getX(),x);
                }
                g2d.drawString(AlinearText(oCEComprobanteVenta.getMontoTotal())+"",cord.get(14).getX(),cord.get(14).getY());
                ParserNumerosALetras oParserNumerosALetras=new ParserNumerosALetras();
                g2d.setFont(new Font(format,0,tamanio));
                g2d.drawString(oParserNumerosALetras.Convertir(oCEComprobanteVenta.getMontoTotal()+"", true),cord.get(15).getX(),cord.get(15).getY());
          }
            else if (oCEComprobanteVenta.getIdTipoComprobanteVenta() == 2)
            {

                g2d.setFont(new Font(format,0,tamanio));
                g2d.drawString(oCEComprobanteVenta.getCliente(),cord.get(0).getX(),cord.get(0).getY());//cliente
                g2d.drawString(oCEComprobanteVenta.getDireccion(),cord.get(1).getX(),cord.get(1).getY());//direccion
                g2d.drawString(oCEComprobanteVenta.getRUC(),cord.get(2).getX(),cord.get(2).getY());// RUC
                g2d.drawString(oCEComprobanteVenta.getEmpleado(),cord.get(4).getX(),cord.get(4).getY());//empleado
                g2d.drawString(oCEComprobanteVenta.getNumComprobante(),cord.get(6).getX(),cord.get(6).getY());//numcomp
                g2d.drawString(oCEComprobanteVenta.getFecha(),cord.get(5).getX(),cord.get(5).getY());//fecha
                g2d.drawString("Nº Pedido "+oCEComprobanteVenta.getNumPedido(),cord.get(20).getX(),cord.get(20).getY());

                g2d.setFont(new Font("tahoma",0,8));
                float exonerado=0;
                int ultimacordenadaFilaY=0;
                for(int i=0,x=cord.get(7).getX(); i<oCEComprobanteVenta.getoLstComprobanteDetalle().size();i++,x=x+cord.get(8).getX())
                {
                  CEComprobanteVentaDetalle oCEComprobanteVentaDetalle= oCEComprobanteVenta.getoLstComprobanteDetalle().get(i);
                  g2d.drawString(this.DesFormatearDecimal(oCEComprobanteVentaDetalle.getCantidad())+"",cord.get(9).getX(),x);
                  g2d.setFont(new Font(format,0,tamanio));
                  g2d.drawString(oCEComprobanteVentaDetalle.getUnidadMedida(),cord.get(10).getX(),x);
                  g2d.drawString(oCEComprobanteVentaDetalle.getProducto(),cord.get(11).getX(),x);
                  g2d.setFont(new Font("tahoma",0,8));
                  g2d.drawString(AlinearTextConCero(oCEComprobanteVentaDetalle.getPrecioconDesc())+"",cord.get(12).getX(),x);
                  String ExoStr=AlinearText(oCEComprobanteVentaDetalle.getExonerado());
                  g2d.drawString(ExoStr+"",cord.get(13).getX(),x);
                  g2d.drawString(AlinearTextConCero(oCEComprobanteVentaDetalle.getImporteConDescuento())+"",cord.get(14).getX(),x);
                  exonerado=exonerado+oCEComprobanteVentaDetalle.getExonerado();
                  ultimacordenadaFilaY=x;
                }
                if(oCEComprobanteVenta.getMontoPercepcion()>0)
                {
                    ultimacordenadaFilaY=ultimacordenadaFilaY+10;
                    g2d.drawString("OPERACCIÓN SUJETA A PERCEPCIÓN DEL IGV "+oCEComprobanteVenta.getTasaPercepcion()*100+"%  TOTAL "+AlinearTextConCero(oCEComprobanteVenta.getMontoPercepcion()),cord.get(9).getX(),ultimacordenadaFilaY+cord.get(8).getX());
                    g2d.drawString("TOTAL A PAGAR CON PERCEPCIÓN : "+AlinearTextConCero(oCEComprobanteVenta.getTotalPagar()),cord.get(9).getX(),ultimacordenadaFilaY+(cord.get(8).getX()*2));
                }
                g2d.drawString(AlinearText(exonerado)+"",cord.get(15).getX(),cord.get(15).getY());
                g2d.drawString(AlinearText(oCEComprobanteVenta.getSubTotalNetoSinIGV())+"",cord.get(16).getX(),cord.get(16).getY());
                g2d.drawString(AlinearText(oCEComprobanteVenta.getIGV())+"",cord.get(17).getX(),cord.get(17).getY());
                g2d.drawString(AlinearText(oCEComprobanteVenta.getMontoTotal())+"",cord.get(18).getX(),cord.get(18).getY());
                ParserNumerosALetras oParserNumerosALetras=new ParserNumerosALetras();
                g2d.setFont(new Font(format,0,tamanio));
                g2d.drawString(oParserNumerosALetras.Convertir(oCEComprobanteVenta.getMontoTotal()+"", true),cord.get(19).getX(),cord.get(19).getY());
           }

             else if (oCEComprobanteVenta.getIdTipoComprobanteVenta() == 3)
            {

                tamanio = 8;
                g2d.setFont(new Font(format, 0, tamanio));
                g2d.drawString("COORPORACIÓN", 55, 7);
                g2d.drawString("ANGUI & NAIDU E.I.R.L", 50, 20);
                g2d.drawString("TICKET Nº :  "+oCEComprobanteVenta.getNumComprobante(), 50, 34);
                g2d.drawString("Cliente :"+oCEComprobanteVenta.getCliente(), 2, 47);
                g2d.drawString("DNI :"+oCEComprobanteVenta.getDNI(), 2, 60);
                g2d.drawString("Direccion :"+oCEComprobanteVenta.getDireccion(), 2, 75);
                g2d.drawString("Fecha :"+oCEComprobanteVenta.getFecha(), 2, 90);
                int y = 106;
                g2d.setFont(new Font(format, 0, tamanio));
                g2d.drawString("_______________________________________________________", 1, y);
                y += 14;
                g2d.drawString("CANT", 1, y);
                g2d.drawString(" UNI", 21, y);
                g2d.drawString(" PRODUCTO", 43, y);
                g2d.drawString(" PREC", 139, y);
                g2d.drawString(" IMPORT", 163, y);
                y += 4;
                g2d.drawString("_______________________________________________________", 1, y);
                y += 14;
                for (int i = 0; i < oCEComprobanteVenta.getoLstComprobanteDetalle().size();) {
                    CEComprobanteVentaDetalle oCEComprobanteDetalle = (CEComprobanteVentaDetalle)oCEComprobanteVenta.getoLstComprobanteDetalle().get(i);
                    ArrayList array_prod = DividirCadena(oCEComprobanteDetalle.getProducto(), 17);
                    ArrayList array_uni = DividirCadena(oCEComprobanteDetalle.getUnidadMedida(), 4);
                    g2d.drawString(oCEComprobanteDetalle.getCantidad()+"", 1, y);
                    g2d.drawString(oCEComprobanteDetalle.getPrecioconDesc()+"", 142, y);
                    g2d.drawString(CLRedondear.FormatearDosDigitos(oCEComprobanteDetalle.getImporteConDescuento() + oCEComprobanteDetalle.getExonerado())+"", 167, y);
                    int y_aux = y;



                    for (Object object : array_uni) 
                    {
                        String cadena = object.toString();
                        g2d.drawString(cadena, 21, y_aux);
                        y_aux += 14;
                    }

                     for (Object object_prod : array_prod) 
                    {
                        String cadena = object_prod.toString();
                        g2d.drawString(cadena, 44, y);
                        y += 14;
                    }

                    y = Math.max(y, y_aux);
                    y -= 14;
                    i++;
                    y += 14;
                }

                y += 6;
                g2d.drawString("_______________________________________________________", 1, y);
                y += 60;
                g2d.drawString("TOTAL: ", 132, y);
                g2d.drawString(AlinearText(oCEComprobanteVenta.getMontoTotal())+"", 162, y);
                y += 25;
                g2d.drawString("SI DESEA RECLAME SU COMPROBANTE DE VENTA ", 1, y);
                y += 25;
               g2d.drawString("MUCHAS GRACIAS POR SU COMPRA ", 55, y);


               
            }


           else if(oCEComprobanteVenta.getIdTipoComprobanteVenta() == 4)
            {

              g2d.drawString(oCEComprobanteVenta.getCliente(),cord.get(0).getX(),cord.get(0).getY());//cliente
              g2d.drawString(oCEComprobanteVenta.getEmpleado(),cord.get(1).getX(),cord.get(1).getY());//empleado
              g2d.drawString(oCEComprobanteVenta.getDireccion(),cord.get(2).getX(),cord.get(2).getY());//direccion
              g2d.drawString(oCEComprobanteVenta.getDNI(),cord.get(3).getX(),cord.get(3).getY());//DNI
              g2d.drawString(oCEComprobanteVenta.getFecha(),cord.get(4).getX(),cord.get(4).getY());//fecha
              g2d.drawString("Nº Pedido "+oCEComprobanteVenta.getNumPedido(),cord.get(16).getX(),cord.get(16).getY());//numPedido
              g2d.drawString(oCEComprobanteVenta.getNumComprobante(),cord.get(5).getX(),cord.get(5).getY());//numcomp

              g2d.setFont(new Font("tahoma",0,8));
                   for(int i=0,x=cord.get(6).getY(); i<oCEComprobanteVenta.getoLstComprobanteDetalle().size();i++,x=x+cord.get(7).getX())
                {
                  CEComprobanteVentaDetalle oCEComprobanteVentaDetalle= oCEComprobanteVenta.getoLstComprobanteDetalle().get(i);

                  g2d.drawString(this.DesFormatearDecimal(oCEComprobanteVentaDetalle.getCantidad())+"",cord.get(8).getX(),x);
                  g2d.setFont(new Font(format,0,tamanio));
                  g2d.drawString(oCEComprobanteVentaDetalle.getUnidadMedida(),cord.get(9).getX(),x);
                  g2d.drawString(oCEComprobanteVentaDetalle.getProducto(),cord.get(10).getX(),x);
                  g2d.setFont(new Font("tahoma",0,8));
                  g2d.drawString(AlinearTextConCero(oCEComprobanteVentaDetalle.getPrecioconDesc())+"",cord.get(11).getX(),x);
                  g2d.drawString(AlinearText(oCEComprobanteVentaDetalle.getExonerado())+"",cord.get(12).getX(),x);
                  g2d.drawString(AlinearTextConCero(oCEComprobanteVentaDetalle.getImporteConDescuento())+"",cord.get(13).getX(),x);
                }
                g2d.drawString(AlinearText(oCEComprobanteVenta.getMontoTotal())+"",cord.get(14).getX(),cord.get(14).getY());
                ParserNumerosALetras oParserNumerosALetras=new ParserNumerosALetras();
                g2d.setFont(new Font(format,0,tamanio));
                g2d.drawString(oParserNumerosALetras.Convertir(oCEComprobanteVenta.getMontoTotal()+"", true),cord.get(15).getX(),cord.get(15).getY());
          }
//
//           


         }
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
     private String AlinearTextConCero(float num)
      {

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

private ArrayList DividirCadena(String cadena, int cant_digito) {
    try{
                ArrayList Array;
        Array = new ArrayList();
        cadena = cadena.trim();
        int CantCaracteres = cadena.length();
        int inicio = 0;
        int fin = cant_digito;
        if (fin > CantCaracteres) {
            fin = CantCaracteres;
                }
        do {
            Array.add(cadena.substring(inicio, fin));
            inicio = fin;
           fin += cant_digito;
                } while (fin <= CantCaracteres);
        if (fin > CantCaracteres) {
            fin = CantCaracteres;
            Array.add(cadena.substring(inicio, fin));
                }
       Array.remove("");
        return Array;

         }
        catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Mensaje de Error", 0);
        return null;
            }
    }
    public boolean isIsPrint() {
        return isPrint;
    }

}


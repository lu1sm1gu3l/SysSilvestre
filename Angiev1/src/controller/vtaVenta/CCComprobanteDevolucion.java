/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.vtaVenta;


import java.util.List;
import modelo.vtaVenta.datos.CDComprobanteDevolucion;
import modelo.vtaVenta.entidad.CEComprobanteVentaMatriz;
import modelo.vtaVenta.entidad.CENotaCredito;

/**
 *
 * @author Katya
 */
public class CCComprobanteDevolucion {

public static CENotaCredito ListarComprobanteDevolucionPorId(int pId)
    {
     return CDComprobanteDevolucion.ListarComprobanteDevolucionPorId(pId );
    }
public static String[] InsComprobanteDevolucion(CENotaCredito oCENotaCredito,CEComprobanteVentaMatriz oCEComprobanteVentaMatriz,int pIdPunto,int pIdAlmacen)
    {
      return CDComprobanteDevolucion.InsComprobanteDevolucion(oCENotaCredito,oCEComprobanteVentaMatriz,pIdPunto,pIdAlmacen);
    }
public static boolean UPDComprobanteDevolucion(CENotaCredito oCENotaCredito)
    {
      return CDComprobanteDevolucion.UPDComprobanteDevolucion(oCENotaCredito);
    }
public static int AnularComprobanteDevolucion(CENotaCredito oCENotaCredito)
    {
        return CDComprobanteDevolucion.AnularComprobanteDevolucion(oCENotaCredito);
    }

  public static List<CENotaCredito> BuscarNotaCreditoPorCliente(long id)
    {
        return CDComprobanteDevolucion.ListarCompDevol("",3,id,0,null);
    }
     public static List<CENotaCredito> BusqAvanzada(String pDato,int idestado,String pFechaFin)
    {
        return CDComprobanteDevolucion.ListarCompDevol(pDato,4, 0,idestado,pFechaFin);
    }

     public static List<CENotaCredito> BusqNum(String pNum)
    {
        return CDComprobanteDevolucion.ListarCompDevol(pNum,2,0,0,null);
    }

}

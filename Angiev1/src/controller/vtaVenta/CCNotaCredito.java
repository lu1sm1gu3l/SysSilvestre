/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.vtaVenta;


import java.util.List;
import modelo.vtaVenta.datos.CDNotaCredito;
import modelo.vtaVenta.entidad.CEComprobanteVentaMatriz;
import modelo.vtaVenta.entidad.CENotaCredito;

/**
 *
 * @author Katya
 */
public class CCNotaCredito {


public static String[] InsNotaCredito(CENotaCredito oCENotaCredito,CEComprobanteVentaMatriz oCEComprobanteVentaMatriz,int pIdPunto,int pIdAlmacen)
    {
      return CDNotaCredito.InsNotaCredito(oCENotaCredito,oCEComprobanteVentaMatriz, pIdPunto,pIdAlmacen);
    }
public static boolean UPDNotaCredito(CENotaCredito oCENotaCredito)
    {
      return CDNotaCredito.UPDNotaCredito(oCENotaCredito);
    }
public static int AnularNotaCredito(CENotaCredito oCENotaCredito)
    {
        return CDNotaCredito.AnularNotaCredito(oCENotaCredito);
    }

public static CENotaCredito ListarNotaCredito(int id)
    {
     return CDNotaCredito.CosultarNotaCreditoPorId(id);
    }

  public static List<CENotaCredito> BuscarNotaCreditoPorCliente(long id)
    {
        return CDNotaCredito.ListarNotaCredito("",3,id,0,null);
    }
     public static List<CENotaCredito> BusqAvanzada(String pDato,int idestado,String pFechaFin)
    {
        return CDNotaCredito.ListarNotaCredito(pDato,4, 0,idestado,pFechaFin);
    }

     public static List<CENotaCredito> BusqNum(String pNum)
    {
        return CDNotaCredito.ListarNotaCredito(pNum,2,0,0,null);
    }
}

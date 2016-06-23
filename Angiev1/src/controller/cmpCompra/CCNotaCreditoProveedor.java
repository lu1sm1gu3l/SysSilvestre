/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.cmpCompra;


import java.util.List;
import modelo.cmpCompra.datos.CDNotaCreditoProveedor;
import modelo.cmpCompra.entidad.CENotaCreditoProveedor;


/**
 *
 * @author Katya
 */
public class CCNotaCreditoProveedor {

public static CENotaCreditoProveedor ListarNotaCreditoProveedor(long pid)
    {
     return CDNotaCreditoProveedor.CosultarNotaCreditoProveedorPorId(pid);
    }
public static String InsNotaCreditoProveedor(CENotaCreditoProveedor oCENotaCreditoProveedor)
    {
      return CDNotaCreditoProveedor.InsNotaCreditoProveedor(oCENotaCreditoProveedor);
    }
public static boolean UPDNotaCreditoProveedor(CENotaCreditoProveedor oCENotaCreditoProveedor)
    {
      return CDNotaCreditoProveedor.UPDNotaCreditoProveedor(oCENotaCreditoProveedor);
    }
public static int AnularNotaCreditoProveedor(CENotaCreditoProveedor oCENotaCreditoProveedor)
    {
        return CDNotaCreditoProveedor.AnularNotaCreditoProveedor(oCENotaCreditoProveedor);
    }

public static int AnularNotaCreditoProveedorPorDev(CENotaCreditoProveedor oCENotaCreditoProveedor)
    {
        return CDNotaCreditoProveedor.AnularNotaCreditoProveedor(oCENotaCreditoProveedor);
    }

  public static List<CENotaCreditoProveedor> BuscarNotaCreditoPorProveedor(long id)
    {
        return CDNotaCreditoProveedor.ListarNotaCredito("",3,id,0,null);
    }
     public static List<CENotaCreditoProveedor> BusqAvanzada(String pDato,int idestado,String pFechaFin)
    {
        return CDNotaCreditoProveedor.ListarNotaCredito(pDato,4, 0,idestado,pFechaFin);
    }

     public static List<CENotaCreditoProveedor> BusqNum(String pNum)
    {
        return CDNotaCreditoProveedor.ListarNotaCredito(pNum,2,0,0,null);
    }

     public static String VerificarNotaCreditoPorCompra(long pid)
    {
     return CDNotaCreditoProveedor.VerificarNotaCreditoPorCompra(pid);
    }
}

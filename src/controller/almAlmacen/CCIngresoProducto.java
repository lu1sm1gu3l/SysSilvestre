/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.almAlmacen;

import java.util.List;
import modelo.almAlmacen.datos.CDIngresoProducto;
import modelo.almAlmacen.entidad.CEIngresoProducto;
import modelo.almAlmacen.entidad.CEIngresoProductoDetalle;
import modelo.cmpCompra.entidad.CEOrdenCompraDetalle;

/**
 *
 * @author Morales
 */
public class CCIngresoProducto {

    public static String InsIngresoProducto(CEIngresoProducto oCEIngresoProducto)
    {
        return CDIngresoProducto.InsIngresoProducto(oCEIngresoProducto);
    }

    public static String InsIngresoProductoPorGuiaRem(CEIngresoProducto oCEIngresoProducto)
    {
        return CDIngresoProducto.InsIngresoProductoPorGuiaRemision(oCEIngresoProducto);
    }

    public static int UPDIngresoProducto(CEIngresoProducto oCEIngresoProducto)
    {
        return CDIngresoProducto.UPDIngresoProducto(oCEIngresoProducto);
    }

  

   public static int AnularIngresoProducto(CEIngresoProducto oCEIngresoProducto)
    {
        return CDIngresoProducto.AnularIngresoProducto(oCEIngresoProducto);
    }
   public static List<CEOrdenCompraDetalle> CargarSaldoCantidades(int pIdTipoIngreso,long pIdIngreso)
    {
        return CDIngresoProducto.CargarSaldoCantidades(pIdTipoIngreso, pIdIngreso);
   }
   public static CEIngresoProductoDetalle VerificarUltimoMovimiento(long pIdProducto,int pIdAlmacen)
    {
        return CDIngresoProducto.VerificarUltimoIngreso(pIdProducto, pIdAlmacen);
   }
    
   public static long VerificarUltimoIDIngreso()
    {
        return CDIngresoProducto.VerificarUltimoIngreso();
   }

   public static CEIngresoProducto ConsultarPorId(long id)
    {
        return CDIngresoProducto.ConsultarIngresoProductosPorId(id);
    }

    public static List<CEIngresoProducto> BuscarPorProveedor(long id)
    {
        return CDIngresoProducto.ListarIngresoProductosPorCodigo("",3,id,0,0,null);
    }
     public static List<CEIngresoProducto> BusqAvanzadaIngreso(String pDato,int idestado, int idtipocomp,String pFechaFin)
    {
        return CDIngresoProducto.ListarIngresoProductosPorCodigo(pDato,4, 0,idestado,idtipocomp,pFechaFin);
    }

     public static List<CEIngresoProducto> BuscarPorCodigo(String pDato)
    {
        return CDIngresoProducto.ListarIngresoProductosPorCodigo(pDato,2,0,0,0,null);
    }



}

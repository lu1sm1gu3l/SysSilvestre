/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.almAlmacen;

import java.sql.Connection;
import java.util.List;
import modelo.almAlmacen.datos.CDSalidaProducto;
import modelo.almAlmacen.entidad.CESalidaProducto;
import modelo.almAlmacen.entidad.CESalidaProductoDetalle;
import modelo.vtaVenta.entidad.CEPedidoMatriz;

/**
 *
 * @author Morales
 */
public class CCSalidaProducto {

    public static String InsSalidaProducto(CESalidaProducto oCESalidaProducto)
    {
        return CDSalidaProducto.InsSalidaProducto(oCESalidaProducto);
    }
    public static String InsSalidaProductoPorCobro(Connection conexion,List<CESalidaProducto> lstSalida)
    {
        return CDSalidaProducto.InsSalidaProductoPorCobro(conexion, lstSalida);
    }
    public static List<CEPedidoMatriz> ListaPedidosPendientesASalida(int pIdCns, int pIdSucursal)
    {
        return CDSalidaProducto.ListaPedidosPendientesASalida(pIdCns, pIdSucursal);
    }
    public static int UPDSalidaProducto(CESalidaProducto oCESalidaProducto)
    {
        return CDSalidaProducto.UPDSalidaProducto(oCESalidaProducto);
    }


   public static CESalidaProducto ConsultarPorId(long pIdSalidaProducto)
    {
        return CDSalidaProducto.ListarSalidaProductoPorCodigo(pIdSalidaProducto);
    }


    public static List<CESalidaProducto> BuscarPorProveedor(long id)
    {
        return CDSalidaProducto.ListarSalidasProductosPorCodigo("",3,id,0,0,null);
    }
     public static List<CESalidaProducto> BusqAvanzada(String pDato,int idestado, int idtipocomp,String pFechaFin)
    {
        return CDSalidaProducto.ListarSalidasProductosPorCodigo(pDato,4, 0,idestado,idtipocomp,pFechaFin);
    }

     public static List<CESalidaProducto> BuscarPorCodigo(String pDato)
    {
        return CDSalidaProducto.ListarSalidasProductosPorCodigo(pDato,2,0,0,0,null);
    }
     public static CESalidaProductoDetalle VerificarUltimoMovimientoSalida(long pIdProducto,int pIdAlmacen)
    {
        return CDSalidaProducto.VerificarUltimaSalida(pIdProducto, pIdAlmacen);
   }

     public static boolean VerificarUltimoMovimientoSalidaPorNC(long pIdProducto,int pIdAlmacen,int pNotaCreditoDet)
    {
        return CDSalidaProducto.VerificarUltimaSalidaPoNC(pIdProducto, pIdAlmacen,pNotaCreditoDet);
   }

     public static String veificarStockPorNC(long pIdProducto,int pIdAlmacen,float pCantidad,int pUnidadMedida)
    {
        return CDSalidaProducto.veificarStockPorNC(pIdProducto, pIdAlmacen,pCantidad,pUnidadMedida);
   }
   public static int AnularSalidaProducto(CESalidaProducto oCESalidaProducto)
    {
        return CDSalidaProducto.AnularSalidaProducto(oCESalidaProducto);
    }
}

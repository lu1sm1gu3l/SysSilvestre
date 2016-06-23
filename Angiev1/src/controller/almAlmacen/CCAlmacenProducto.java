/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.almAlmacen;

import java.util.ArrayList;
import java.util.List;
import modelo.almAlmacen.datos.CDAlmacenProducto;
import modelo.almAlmacen.entidad.CEAlmacenProducto;

/**
 *
 * @author Luiggi
 */
public class CCAlmacenProducto {

public static List<CEAlmacenProducto>  consultarAlamcenPorProducto(long idProducto)
{
        return CDAlmacenProducto.consultarAlamcenPorProducto(idProducto,1,0);
}
public static List<CEAlmacenProducto>  consultarAlamcenPorProductoPedido(long idProducto)
{
        return CDAlmacenProducto.consultarAlamcenPorProducto(idProducto,4,0);
}
public static boolean UPDAlmacenProducto(CEAlmacenProducto oCEAlmacenProducto)
    {
    return  CDAlmacenProducto.UPDAlmacenProducto(oCEAlmacenProducto);
}
public static List<CEAlmacenProducto>  consultarAlamcenesPorProducto(long idProducto)
{
        return CDAlmacenProducto.consultarAlamcenPorProducto(idProducto,2,0);
}

public static List<CEAlmacenProducto>  consultarProductoPorAlmacen(long idProducto,int pIdAlmacen)
{
        return CDAlmacenProducto.consultarAlamcenPorProducto(idProducto,3,pIdAlmacen);
}

public static ArrayList<CEAlmacenProducto> listarAlmacen(int tipoCns,String pDato,int pIdAlmacen)
{
        return CDAlmacenProducto.listarAlmacen(tipoCns, pDato, pIdAlmacen);
}

public static ArrayList<CEAlmacenProducto> listarDetalleProducto(String pDato,int pIdAlmacen)
{
        return CDAlmacenProducto.listarAlmacenProducto(1, pDato, pIdAlmacen);
}

public static ArrayList<CEAlmacenProducto> listarDetalleProductoConPercep(String pDato,int pIdAlmacen)
{
        return CDAlmacenProducto.listarAlmacenProducto(2, pDato, pIdAlmacen);
}

public static ArrayList<CEAlmacenProducto> listarAlmacenConProveedor(int tipoCns,String pDato,int pIdAlmacen)
{
        return CDAlmacenProducto.listarAlmacen(tipoCns, pDato, pIdAlmacen);
}

public static CEAlmacenProducto ConsultarAlmacenProd(long pId,int pIdAlmacen)
{
        return CDAlmacenProducto.ConsultarAlmcenProd(3, pId, pIdAlmacen);
}
public static boolean FinalizarPeriodo(String pDescripcion)
    {
        
      return CDAlmacenProducto.FinalizarPeriodo(pDescripcion);
    }

public static ArrayList<CEAlmacenProducto> listarStockPorUnidadPedido(CEAlmacenProducto oCEAlmacenProducto)
    {
    return  CDAlmacenProducto.listarStockPorUnidadPedido(oCEAlmacenProducto);
    }


}

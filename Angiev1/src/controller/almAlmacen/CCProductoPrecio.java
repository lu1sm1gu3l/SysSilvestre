package controller.almAlmacen;

import java.util.List;
import modelo.almAlmacen.datos.CDProductoPrecio;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;


public class CCProductoPrecio {

     public static List<CEProductoPrecio>  consultarPrecioProducto(long idProducto)
      {
        return CDProductoPrecio.consultarPrecioProducto(idProducto);
     }
     public static List<CEProductoPrecio>  consultarPrecioProductoUnidad(long idProducto,int idUnidad)
      {
        return CDProductoPrecio.consultarPrecioProductoUnidad(idProducto,idUnidad);
     }
     public static List<CEProductoPrecio>  consultarPrecioMediaProducto(long idProducto)
      {
        return CDProductoPrecio.consultarPrecioUnidadMedidaProducto(idProducto);
     }
    public static boolean  abmProductoPrecio(List<CEProductoPrecio> oCEProductoPrecios,CEProducto oCEProducto,List<CEUnidadMedidaProducto> oLstUnidadMedida)
     {
        return CDProductoPrecio.ABMProductoPrecio(oCEProductoPrecios,oCEProducto,oLstUnidadMedida);
     }
   
}

package controller.almAlmacen;

import java.util.List;
import modelo.almAlmacen.datos.CDProducto;
import modelo.almAlmacen.entidad.CEEquivalenciaUnidad;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import util.clases.almAlmacen.CLEquivalenciaABM;
import util.clases.vtaVenta.JTreeTableJerarquia.ObjetoJerarquia;

public class CCProducto
{
    public static boolean registrarProducto(CEProducto oCEProducto)
    {
        return CDProducto.registrarProducto(oCEProducto);
    }
    public static boolean eliminarProducto(CEProducto oCEProducto)
    {
        return CDProducto.eliminarProducto(oCEProducto.getIdProducto());
    }
    public static boolean editarProducto(CEProducto oCEProducto,List<CLEquivalenciaABM> oLstCLEquivalenciaABMs)
    {
        return CDProducto.actualizarProducto(oCEProducto,oLstCLEquivalenciaABMs);
    }
    public static boolean editarTodoProducto(CEProducto oCEProducto,List<CLEquivalenciaABM> oLstCLEquivalenciaABMs)
    {
        return CDProducto.actualizarTodoProducto(oCEProducto,oLstCLEquivalenciaABMs);
    }
    public static List<ObjetoJerarquia> consultarProductoPorMarca(int codigo,int pIdRubro,int pIdCategoria,int pIdFamilia,int pIdSubfamilia,String pFecha1,String pFecha2)
    {
        return CDProducto.consultarListaProductoPorIdFamiliaVenta(codigo, pIdRubro, pIdCategoria, pIdFamilia, pIdSubfamilia, pFecha1, pFecha2);
    }
    public static List<CEProducto> consultarProductoPorMarca(int IdMarca,int IdSubfamilia)
    {
        return CDProducto.consultarProductoxMarca(IdMarca,IdSubfamilia);
    }
    public static List<CEProducto> consultarListaProducto()
    {
        return CDProducto.consultarListadoDeProductos(3,"");
    }
     public static List<CEProducto> consultarListaProductoDetallado()
    {
        return CDProducto.consultarListadoDeProductos(7,"");
    }
    public static List<CEProducto> consultarListaProductoPorDescripcion(int pCodigo,String parametro)
    {        
        return CDProducto.consultarListadoDeProductos(pCodigo,parametro);
    }
     public static List<CEEquivalenciaUnidad> consultarEquivalenciaPorProducto(long IdProducto)
    {
        return CDProducto.consultarEquivalenciaProducto(IdProducto);
    }
     public static List<CEUnidadMedidaProducto> consultarUnidadMedidaProducto(long IdProducto)
    {
        return CDProducto.consultarUnidadMedidaProducto(IdProducto);
    }
      public static List<CEProducto>  consultarProducto(String pDato)
      {
        return CDProducto.consultarProducto(pDato,1);
    }
      public static List<CEProducto>  consultarProductoconPercepcion(String pDato)
      {
        return CDProducto.consultarProducto(pDato,2);
    }
}

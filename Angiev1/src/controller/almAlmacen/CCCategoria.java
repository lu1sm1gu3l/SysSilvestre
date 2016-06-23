package controller.almAlmacen;

import java.util.List;
import modelo.almAlmacen.datos.CDCategoria;
import modelo.almAlmacen.entidad.CECategoria;
import util.clases.vtaVenta.JTreeTableJerarquia.ObjetoJerarquia;

public class CCCategoria
{
    public static List<CECategoria> consultarListaCategoriaPorIdRubro(int pIdRubro)
    {
       return CDCategoria.consultarListaCategoriasPorIdRubro(2,pIdRubro);
    }
     public static List<CECategoria> consultarListaCategoriaPorIdRubrosSinVacios(int pIdRubro)
    {
       return CDCategoria.consultarListaCategoriasPorIdRubro(3,pIdRubro);
    }
     public static List<ObjetoJerarquia> consultarListaCategoriaPorIdRubrosVenta(int codigo,int pIdRubro,String pFecha1,String pFecha2)
    {
       return CDCategoria.consultarListaCategoriasPorIdRubroVenta(codigo, pIdRubro, pFecha1, pFecha2);
    }
    public static int ABMCategoria(int pIntAMB,CECategoria oCECategoria)
    {
         return CDCategoria.ABMCategoria(pIntAMB, oCECategoria);
     }
}

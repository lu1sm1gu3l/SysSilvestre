package controller.almAlmacen;

import java.util.List;

import modelo.almAlmacen.datos.CDFamilia;
import modelo.almAlmacen.entidad.CEFamilia;
import util.clases.vtaVenta.JTreeTableJerarquia.ObjetoJerarquia;

public class CCFamilia
{
    public static int ABMFamilia(int pIntAMB,CEFamilia oCDFamilia)
    {
    return CDFamilia.ABMFamilia(pIntAMB, oCDFamilia);
    }
    public static List<CEFamilia> consultarListaFamiliaPorIdCategoria(int pIdCategoria)
    {
       return CDFamilia.consultarListaFamiliasPorIdCategoria(2,pIdCategoria);
    }
    public static List<CEFamilia> consultarListaFamiliaPorIdCategoriaSinVacios(int pIdCategoria)
    {
       return CDFamilia.consultarListaFamiliasPorIdCategoria(3,pIdCategoria);
    }
    public static List<ObjetoJerarquia> consultarListaFamiliaPorIdCategoriaVenta(int codigo,int pIdRubro,int pIdCategoria,String pFecha1,String pFecha2)
    {
       return CDFamilia.consultarFamiliaPorIdCategoriaVenta(codigo, pIdRubro,pIdCategoria, pFecha1, pFecha2);
    }
}

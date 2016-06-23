package controller.almAlmacen;

import java.util.List;
import modelo.almAlmacen.datos.CDSubfamilia;
import modelo.almAlmacen.entidad.CESubfamilia;
import util.clases.vtaVenta.JTreeTableJerarquia.ObjetoJerarquia;


public class CCSubFamilia
{

    public static int ABMSubFamilia(int pIntAMB,CESubfamilia oCDSubfamilia)
    {
      return CDSubfamilia.ABMSubFamilia(pIntAMB, oCDSubfamilia);
    }
   public static List<CESubfamilia> consultarListaFamiliaPorIdFamilia(int pIdFamilia)
    {
      return CDSubfamilia.consultarListaSubfamiliaPorIdFamilia(2,pIdFamilia);
    }
   public static List<CESubfamilia> consultarListaFamiliaPorIdFamiliaSinVacios(int pIdFamilia)
    {
      return CDSubfamilia.consultarListaSubfamiliaPorIdFamilia(3,pIdFamilia);
    }
    public static List<ObjetoJerarquia> consultarListaSubFamiliaPorIdCategoriaVenta(int codigo,int pIdRubro,int pIdCategoria,int pIdFamilia,String pFecha1,String pFecha2)
    {
      return CDSubfamilia.consultarListaSubFamiliaPorIdFamiliaVenta(codigo, pIdRubro,pIdCategoria,pIdFamilia,pFecha1, pFecha2);
    }
}

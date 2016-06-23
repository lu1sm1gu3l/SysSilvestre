package controller.almAlmacen;

import java.util.List;
import modelo.almAlmacen.datos.CDRubro;
import modelo.almAlmacen.entidad.CERubro;
import util.clases.vtaVenta.JTreeTableJerarquia.ObjetoJerarquia;

public class CCRubro
{
    public static List<CERubro> consultarListaRubros()
    {
       return CDRubro.consultarListaRubros();
    }
    public static List<ObjetoJerarquia> consultarListaRubrosNumVentas(int IdCodigo,String Fecha1,String Fecha2)
    {
       return CDRubro.consultarListaRubrosNumVenta(IdCodigo, Fecha1, Fecha2);
    }
    public static int ABMRubro(int pIntAMB,CERubro oCDRubro)
    {
        return CDRubro.ABMRubro(pIntAMB, oCDRubro);
    }

}

package controller.vtaVenta;

import java.util.List;
import modelo.vtaVenta.datos.CDTipoPago;
import modelo.vtaVenta.entidad.CETipoPago;

public class CCTipoIngreso
{
    public static List<CETipoPago> consultarTipoIngresoFinaciero()
    {
       return CDTipoPago.listarTipoIngresoFinaciero(1);
    }

       public static List<CETipoPago> consultarTipoEgresoFinaciero()
    {
       return CDTipoPago.listarTipoIngresoFinaciero(2);
    }
}

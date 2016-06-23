package controller.almAlmacen;

import java.util.List;
import modelo.almAlmacen.datos.CDUnidadMedidaVenta;
import modelo.almAlmacen.entidad.CEUnidadMedidaVenta;

public class CCUnidadMedidaVenta
{
    public static List<CEUnidadMedidaVenta> consultarListaUnidadMedidaVentas()
    {
       return CDUnidadMedidaVenta.consultarListaUnidadMedidaVentas();
    }

    public static int ABMUnidadMedidaVenta(int pIntAMB,CEUnidadMedidaVenta oCDUnidadMedidaVenta)
    {
        return CDUnidadMedidaVenta.ABMUnidadMedidaVenta(pIntAMB, oCDUnidadMedidaVenta);
    }



}

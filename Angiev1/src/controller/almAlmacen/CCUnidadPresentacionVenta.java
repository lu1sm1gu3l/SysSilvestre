package controller.almAlmacen;

import java.util.List;
import modelo.almAlmacen.datos.CDUnidadPresentacionVenta;
import modelo.almAlmacen.entidad.CEUnidadPresentacionVenta;

public class CCUnidadPresentacionVenta
{
    public static List<CEUnidadPresentacionVenta> consultarListaUnidadPresentacionVentas()
    {
       return CDUnidadPresentacionVenta.consultarUnidadPresentacionVenta();
    }

    public static int ABMUnidadPresentacionVenta(int pIntAMB,CEUnidadPresentacionVenta oCDUnidadMedidaVenta)
    {
        return CDUnidadPresentacionVenta.ABMUnidadPresentacionVenta(pIntAMB, oCDUnidadMedidaVenta);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.almAlmacen;

import java.util.List;
import modelo.almAlmacen.entidad.CEProductoPrecio;

/**
 *
 * @author Morales
 */
public class CLPrecio {
    
    
    public static float obtenerPrecio(List<CEProductoPrecio> listaPrecioProducto,float cantidad)
{

    if(listaPrecioProducto!=null){
                for (CEProductoPrecio oCEProductoPrecioTemp : listaPrecioProducto)
                {

                        float RangoInicial=oCEProductoPrecioTemp.getRangoInicial();
                        float RangoFinal=oCEProductoPrecioTemp.getRangoFinal();
                        if(cantidad>=RangoInicial&&cantidad<RangoFinal)
                        {
                        return oCEProductoPrecioTemp.getPrecioUnitario();

                        }

                }
            }
    return 0;
}
public static float calcularPrecioTotal(float pCantidad, float pPrecioUnit)
{

    return pCantidad*pPrecioUnit;
}
}

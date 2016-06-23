/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.tsoTesoreria;

import java.util.List;
import modelo.tsoTesoreria.datos.CDCuadreCaja;
import modelo.tsoTesoreria.entidad.CECuadreCaja;
import modelo.tsoTesoreria.entidad.CECuadreCajaTipoPago;

/**
 *
 * @author Luiggi
 */
public class CCCuadreCaja {


    public static int INSCuadreCaja(CECuadreCaja oCECuadreCaja)
    {
        return CDCuadreCaja.INSCuadreCaja(oCECuadreCaja);
    }
    public static int UPDCuadreCaja(CECuadreCaja oCECuadreCaja)// opcion 1=cierre ; opcion 2= cuadre
    {
        return CDCuadreCaja.UPDCuadreCaja(oCECuadreCaja, 2);
    }
    public static int UPDCierreCaja(CECuadreCaja oCECuadreCaja)// opcion 1=cierre ; opcion 2= cuadre
    {
        return CDCuadreCaja.UPDCuadreCaja(oCECuadreCaja, 1);
    }
     public static CECuadreCaja ObtenerCuadreCajaApertura(int pIdPuntoVenta)
    {
         return CDCuadreCaja.ObtenerCuadreCaja(pIdPuntoVenta,null,1);
     }
     public static CECuadreCaja ObtenerCuadreCajaCierre(int pIdPuntoVenta,String pFecha)
    {
         return CDCuadreCaja.ObtenerCuadreCaja(pIdPuntoVenta,pFecha,2);
     }
     public static List<CECuadreCajaTipoPago> listarIngresoTipoPago(String pFecha,int pIdPuntoVenta)
    {
         return CDCuadreCaja.listarIngresoTipoPago(pFecha, pIdPuntoVenta);
     }
}

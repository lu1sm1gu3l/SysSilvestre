/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.vtaVenta;

import java.util.ArrayList;
import modelo.vtaVenta.datos.CDPuntoVenta;
import modelo.vtaVenta.entidad.CEPuntoVenta;

/**
 *
 * @author Luiggi
 */
public class CCPuntoVenta {


    public static ArrayList<CEPuntoVenta> listarPuntoVenta(int pIdSucursal)
    {
        return CDPuntoVenta.listarPuntoVenta(pIdSucursal, false,1);
    }
    public static ArrayList<CEPuntoVenta> listarCaja(int pIdSucursal)
    {
       return CDPuntoVenta.listarPuntoVenta(pIdSucursal, true,1);
    }
    public static ArrayList<CEPuntoVenta> listarPuntoVenta_Todo(int pIdSucursal)
    {
       return CDPuntoVenta.listarPuntoVenta(pIdSucursal, true,2);
    }
    public static int ABMPuntoVenta(int pIntAMB,CEPuntoVenta oCEPuntoVenta)
    {
        return CDPuntoVenta.ABMPuntoVenta(pIntAMB, oCEPuntoVenta);
    }
}

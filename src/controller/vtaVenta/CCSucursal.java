/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.vtaVenta;

import java.util.ArrayList;
import modelo.vtaVenta.datos.CDSucursal;
import modelo.vtaVenta.entidad.CESucursal;

/**
 *
 * @author Katya
 */
public class CCSucursal {

    public static ArrayList<CESucursal> getAll()
    {
        return CDSucursal.getSucursal(1);
    }

}

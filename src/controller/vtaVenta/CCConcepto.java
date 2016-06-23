/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.vtaVenta;

import java.util.ArrayList;
import modelo.vtaVenta.datos.CDConcepto;
import modelo.vtaVenta.entidad.CEConcepto;

/**
 *
 * @author Katya
 */
public class CCConcepto {

    public static ArrayList<CEConcepto> getAll()
    {
        return CDConcepto.getAll(1);
    }
    public static ArrayList<CEConcepto> getNotaCerditoProveedor()
    {
        return CDConcepto.getAll(2);
    }
    public static ArrayList<CEConcepto> getNotaCerditoProveedorMasTodos()
    {
        return CDConcepto.getAll(3);
    }

}

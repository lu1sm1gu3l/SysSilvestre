/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.almAlmacen;

import java.util.ArrayList;
import modelo.almAlmacen.datos.CDAlmacen;
import modelo.almAlmacen.entidad.CEAlmacen;

/**
 *
 * @author Morales
 */
public class CCAlmacen {


    public static ArrayList<CEAlmacen> listarAlmacenSucursal()
    {
       return CDAlmacen.listarAlmacen(1, 0);
    }
     public static ArrayList<CEAlmacen> listarAlmacen()
    {
       return CDAlmacen.listarAlmacen(2, 0);
    }
      public static ArrayList<CEAlmacen> listarAlmacenPorSucursal(int pIdSucursal)
    {
       return CDAlmacen.listarAlmacen(3, pIdSucursal);
    }

      public static ArrayList<CEAlmacen> listarAlmacenSucursalMasTodos()
    {
       return CDAlmacen.listarAlmacen(4,0);
    }
}

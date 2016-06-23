/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vtaVenta;

import java.util.ArrayList;
import modelo.vtaVenta.datos.CDTipoDescuento;
import modelo.vtaVenta.entidad.CETipoDescuento;


/**
 *
 * @author Joel
 */
public class CCTipoDescuento{


    public static ArrayList<CETipoDescuento> listaAll() {
      return CDTipoDescuento.getAll();
    }







    
}

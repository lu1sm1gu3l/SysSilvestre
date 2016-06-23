/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vtaVenta;

import java.util.ArrayList;
import modelo.vtaVenta.datos.CDCondicion;
import modelo.vtaVenta.entidad.CECondicion;


/**
 *
 * @author Joel
 */
public class CCCondicion{


    public static ArrayList<CECondicion> listaAll() {
      return CDCondicion.getAll();
    }







    
}

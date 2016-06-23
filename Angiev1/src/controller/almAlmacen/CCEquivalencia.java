/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.almAlmacen;

import java.util.ArrayList;
import modelo.almAlmacen.datos.CDEquivalencia;
import modelo.almAlmacen.entidad.CEEquivalenciaUnidad;

/**
 *
 * @author Katya
 */
public class CCEquivalencia {


     public static ArrayList<CEEquivalenciaUnidad> consultarUnidadMedidaVentasPorIdProducto(int IdProducto)
    {
       return CDEquivalencia.consultarUnidadMedidaVentasPorIdProducto(IdProducto);
    }
}

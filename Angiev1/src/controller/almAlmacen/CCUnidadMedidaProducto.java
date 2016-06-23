/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.almAlmacen;

import java.util.List;
import modelo.almAlmacen.datos.CDUnidadMedidaProducto;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;


public class CCUnidadMedidaProducto {

     public static List<CEUnidadMedidaProducto>  consultarEquivalenciaPorProducto(long idProducto)
    {
         return CDUnidadMedidaProducto.consultarEquivalenciaPorProducto(idProducto);
     }

}

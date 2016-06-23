/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vtaVenta;

import java.util.ArrayList;
import modelo.vtaVenta.datos.CDTipoComprobante;
import modelo.vtaVenta.entidad.CETipoComprobante;


public class CCTipoComprobante{


    public static ArrayList<CETipoComprobante> listaAll() {
      return CDTipoComprobante.getAll(1);
    }

    public static ArrayList<CETipoComprobante> listaComprobanteInvantarios() {
      return CDTipoComprobante.getAll(2);
    }

    public static ArrayList<CETipoComprobante> listarTodos() {
      return CDTipoComprobante.getAll(3);
    }
    public static ArrayList<CETipoComprobante> listarCompVentasMasTodos() {
      return CDTipoComprobante.getAll(4);
    }
    public static ArrayList<CETipoComprobante> listaProforma() {
      return CDTipoComprobante.getAll(5);
    }
    public static ArrayList<CETipoComprobante> listarCompCompraMasTodos() {
      return CDTipoComprobante.getAll(6);
    }




    
}

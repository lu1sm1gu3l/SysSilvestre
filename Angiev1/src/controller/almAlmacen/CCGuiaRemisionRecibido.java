/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.almAlmacen;

import java.util.List;
import modelo.almAlmacen.datos.CDGuiaRemisionRecibido;
import modelo.almAlmacen.entidad.CEGuiaRemisionRecibido;

/**
 *
 * @author Morales
 */
public class CCGuiaRemisionRecibido {

    public static boolean InsGuiaRemisionRecibido(CEGuiaRemisionRecibido oCEGuiaRemisionRecibido)
    {
        return CDGuiaRemisionRecibido.InsGuiaRemisionRecibido(oCEGuiaRemisionRecibido);
    }

    public static int UPDGuiaRemisionRecibido(CEGuiaRemisionRecibido oCEGuiaRemisionRecibido)
    {
        return CDGuiaRemisionRecibido.UPDGuiaRemisionRecibido(oCEGuiaRemisionRecibido);
    }

 
   public static int AnularGuiaRemisionRecibido(CEGuiaRemisionRecibido oCEGuiaRemisionRecibido)
    {
        return CDGuiaRemisionRecibido.AnularGuiaRemisionRecibido(oCEGuiaRemisionRecibido);
    }


   public static CEGuiaRemisionRecibido ConsultarPorId(int id)
    {
        return CDGuiaRemisionRecibido.ConsultarGuiaRemisionRecibidosPorId(id);
    }

    public static List<CEGuiaRemisionRecibido> BuscarPorProveedor(long id)
    {
        return CDGuiaRemisionRecibido.ListarGuiaRemisionRecibidosPorCodigo("",3,id,0,null);
    }
     public static List<CEGuiaRemisionRecibido> BusqAvanzadaIngreso(String pDato,int idestado,String pFechaFin)
    {
        return CDGuiaRemisionRecibido.ListarGuiaRemisionRecibidosPorCodigo(pDato,4, 0,idestado,pFechaFin);
    }

     public static List<CEGuiaRemisionRecibido> BuscarPorCodigo(String pDato)
    {
        return CDGuiaRemisionRecibido.ListarGuiaRemisionRecibidosPorCodigo(pDato,2,0,0,null);
    }



}

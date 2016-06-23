/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.almAlmacen;

import java.util.List;
import modelo.almAlmacen.datos.CDTransferenciaAlmacen;
import modelo.almAlmacen.entidad.CETransferenciaAlmacen;

/**
 *
 * @author Morales
 */
public class CCTransferenciaAlmacen {

    public static String InsTransferenciaAlmacen(CETransferenciaAlmacen oCETransferenciaAlmacen)
    {
        return CDTransferenciaAlmacen.InsTransferenciaAlmacen(oCETransferenciaAlmacen);
    }
    public static int UPDTransferenciaAlmacen(CETransferenciaAlmacen oCETransferenciaAlmacen)
    {
        return CDTransferenciaAlmacen.UPDTransferenciaAlmacen(oCETransferenciaAlmacen);
    }

   public static List<CETransferenciaAlmacen> BusqAvanzada(int pIdOrigen,int pIdDestino,String FechaIni,String FechaFin)
    {
        return CDTransferenciaAlmacen.ListarTransferenciaAlmacenPorCodigo(pIdOrigen,pIdDestino,FechaIni,FechaFin,1);
    }

   public static List<CETransferenciaAlmacen> BuscarPorCodigo(String NumTransferencia)
    {
        return CDTransferenciaAlmacen.ListarTransferenciaAlmacenPorCodigo(0,0,NumTransferencia,null,2);
    }

   public static CETransferenciaAlmacen ConsultarPorId(long pId)
    {
        return CDTransferenciaAlmacen.ListarTransferenciaAlmacenPorId(1,pId);
    }



   public static int AnularTransferenciaAlmacen(CETransferenciaAlmacen oCETransferenciaAlmacen)
    {
        return CDTransferenciaAlmacen.AnularTransferenciaAlmacen(oCETransferenciaAlmacen);
    }
}

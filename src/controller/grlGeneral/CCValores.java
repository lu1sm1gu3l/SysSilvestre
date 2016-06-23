/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.grlGeneral;
import java.util.List;
import modelo.grlGeneral.datos.CDValores;
import modelo.grlGeneral.entidad.CEValores;
/**
 *
 * @author Luiggi
 */

public class CCValores {
    public static List<CEValores> ListadoValores(int pIntABM,int pIntCodigo)
    {
     return CDValores.ListadoValores(pIntABM, pIntCodigo);   
    }
     public static int ABMPlanEstudioDetalle(int pIntAMB,CEValores oCEValores)
    {
        return CDValores.ABMPlanEstudioDetalle(pIntAMB, oCEValores);
    }
     public static float obenertIGVActual()
     {
       return CDValores.obenertIGVActual();
     }
     public static float obenertPercpcionActual()
     {
       return CDValores.obenertPercpcionActual();
     }
      public static int obenerFechaDelSistema(String pFecha)
     {
       return CDValores.obenerFechaDelSistema(pFecha,1);
     }
      public static int obenerFechaDelSistemaDMY(String pFecha)
     {
       return CDValores.obenerFechaDelSistema(pFecha,2);
     }
      
}

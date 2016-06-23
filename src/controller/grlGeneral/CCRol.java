
package controller.grlGeneral;



import java.util.List;
import modelo.grlGeneral.datos.CDRol;
import modelo.grlGeneral.entidad.CERol;

public class CCRol
{
      public static boolean ejecutarProcedimientoBM(int pintOperacion,CERol poRol)
       {
           return CDRol.ABM(poRol, pintOperacion);

       }
       public static CERol ejecutarProcedimientoAlta(CERol oRolCE)
       {
           return CDRol.Alta(oRolCE,1);

       }

       public static List<CERol > listaRol()
       {
         return  CDRol.Listado();
       }

    public CCRol() {
    }
       
       public  List<CERol> ListarRol()
     {
        return new CDRol().ListarRoles();

     }
       public  List<CERol>consultarRol(String datoComponente)
    {
       CDRol oRolCD=new CDRol();
       return oRolCD.buscarRol(datoComponente);
    }
}

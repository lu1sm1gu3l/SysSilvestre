
package controller.grlGeneral;



import java.util.List;
import modelo.grlGeneral.datos.CDComponente;
import modelo.grlGeneral.entidad.CEComponente;

public class CCComponente
{
      public static boolean ejecutarProcedimientoBM(int pintOperacion,CEComponente poComponente)
       {
           return CDComponente.ABM(poComponente, pintOperacion);

       }
       public static CEComponente ejecutarProcedimientoAlta(CEComponente oComponenteCE)
       {
           return CDComponente.Alta(oComponenteCE,1);

       }

       public static List<CEComponente > listaComponente()
       {
         return  CDComponente.Listado();
       }
public  List<CEComponente>consultarComponente(String datoComponente)
    {
       CDComponente oComponenteCD=new CDComponente();
       return oComponenteCD.buscarComponente(datoComponente);
    }

    public CCComponente()
    {
    }
       

}


package controller.grlGeneral;

import java.util.List;
import modelo.grlGeneral.datos.CDAccion;
import modelo.grlGeneral.entidad.CEAccion;

public class CCAccion
{
     public CCAccion()
    {}
    public static boolean ejecutarProcedimientoBM(int pintOperacion,CEAccion poAccion)
    {
        return CDAccion.ABM(pintOperacion, poAccion);
    }

    public static CEAccion ejecutarProcedimientoAlta(CEAccion oAccionCE)
    {
        return CDAccion.Alta(oAccionCE);
    }

    public static List<CEAccion> listaAccion()
    {
        return CDAccion.Listado();
    }

   

}

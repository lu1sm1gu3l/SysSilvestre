package controller.grlGeneral;
import java.util.ArrayList;
import java.util.List;
import modelo.grlGeneral.datos.CDVinculacionRolComponenteAccion;
import modelo.grlGeneral.entidad.CEComponenteAccion;
import modelo.grlGeneral.entidad.CEVinculacionRolComponenteAccionMatriz;
import util.clases.grlGeneral.CLObjectABM;

/**
 *
 * @author Administrador
 */
public class CCVinculacionRolComponenteAccion {

    public  List<CEVinculacionRolComponenteAccionMatriz> ListarVinculacionComponenteAccion(int pIdUnidad)
     {
        return new CDVinculacionRolComponenteAccion().ListarVinculacionComponenteAccion(pIdUnidad);
     }
    public boolean ABMVinculacionComponenteAccion(ArrayList<CLObjectABM> oListaTipoAmbiente)
    {
         return new CDVinculacionRolComponenteAccion().ABMVinculacionComponenteAccion(oListaTipoAmbiente);
    }
          public  List<CEComponenteAccion>consultarComponenteAccion(String datoComponente)
    {
       CDVinculacionRolComponenteAccion oComponenteAccionCD=new CDVinculacionRolComponenteAccion();
       return oComponenteAccionCD.buscarComponenteAccion(datoComponente);
    }

     public  List<CEVinculacionRolComponenteAccionMatriz> ListarVinculacionRolComponenteAccion()
     {
        return new CDVinculacionRolComponenteAccion().ListarVinculacionRolComponenteAccion();
     }

     public static List<CEVinculacionRolComponenteAccionMatriz> PermisosPorAccion(String Componente,int vIdRol)
     {
        return new CDVinculacionRolComponenteAccion().PermisosPorAccion(Componente,vIdRol);
     }

}

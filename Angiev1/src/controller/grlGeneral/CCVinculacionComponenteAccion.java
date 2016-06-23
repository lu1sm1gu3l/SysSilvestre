package controller.grlGeneral;
import java.util.ArrayList;
import java.util.List;
import modelo.grlGeneral.datos.CDVinculacionComponenteAccion;
import modelo.grlGeneral.entidad.CEVinculacionComponenteAccionMatriz;
import util.clases.grlGeneral.CLObjectABM;

public class CCVinculacionComponenteAccion {

    public  List<CEVinculacionComponenteAccionMatriz> ListarVinculacionComponenteAccion(int pIdUnidad)
     {
        return new CDVinculacionComponenteAccion().ListarVinculacionComponenteAccion(pIdUnidad);
     }
    public boolean ABMVinculacionAsignaturaDepartamento(ArrayList<CLObjectABM> oListaTipoAmbiente)
    {
         return new CDVinculacionComponenteAccion().ABMVinculacionComponenteAccion(oListaTipoAmbiente);
    }

}

package controller.grlGeneral;

import java.util.List;
import modelo.grlGeneral.datos.CDUsuarioSistema;
import modelo.grlGeneral.entidad.CEUsuarioSistema;

public class CCUsuarioSistema
{
    public static List<CEUsuarioSistema> ListarUsuarioSistema(int Usuario)
        {
             return CDUsuarioSistema.ListadoUsuarioSistema(Usuario);
        }


}

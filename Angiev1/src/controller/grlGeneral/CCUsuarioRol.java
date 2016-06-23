package controller.grlGeneral;

import java.util.ArrayList;
import java.util.List;
import modelo.grlGeneral.datos.CDUsuarioRol;
import modelo.grlGeneral.entidad.CEUsuarioRolMatriz;
import util.clases.grlGeneral.CLObjectABM;

public class CCUsuarioRol 
    {
      public  List<CEUsuarioRolMatriz> ListarUsuarioRol(int pIdCns,int pIdUsuario)
      {
        return new CDUsuarioRol().ListarUsuarioRol(pIdCns,pIdUsuario);
      }
      public boolean ABMUsuarioRol(ArrayList<CLObjectABM> oListABMUsuarioRol)
      {
        return new CDUsuarioRol().ABMUsuarioRol(oListABMUsuarioRol);
      }


    }

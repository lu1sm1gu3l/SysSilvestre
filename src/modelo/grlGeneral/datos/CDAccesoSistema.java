package modelo.grlGeneral.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import modelo.grlGeneral.entidad.CEAccesoSistema;

public class CDAccesoSistema
{
        public static boolean salirSistema(CEAccesoSistema oAccion)
        {   Connection conexion = ConexionBD.obtenerConexion();
            try
                {
           String sql="{CALL GRLABMAccesoUsuarioSistema(?,?,?)}";
            CallableStatement sentencia = conexion.prepareCall(sql);
            sentencia.setInt(1,2);
            sentencia.setLong(2, oAccion.getIdUsuarioSistema());
            sentencia.setInt(3,oAccion.getIdUsuario());
            sentencia.registerOutParameter(2,Types.BIGINT);
            sentencia.executeUpdate();
            return true;
            }
            catch(Exception e)
            {  e.printStackTrace();
                return false;
            }
        }
        public static long entrarSistema(CEAccesoSistema oAccion)
        {   Connection conexion = ConexionBD.obtenerConexion();
            try
                {
            String sql="{CALL GRLABMAccesoUsuarioSistema(?,?,?)}";
            CallableStatement sentencia = conexion.prepareCall(sql);
            sentencia.setInt(1,1);
            sentencia.setLong(2, oAccion.getIdUsuarioSistema());
            sentencia.setInt(3,oAccion.getIdUsuario());
            sentencia.registerOutParameter(2,Types.BIGINT);
            sentencia.executeUpdate();
            oAccion.setIdUsuarioSistema(sentencia.getInt(2));
            return oAccion.getIdUsuarioSistema();
            }
            catch(Exception e)
            {  e.printStackTrace();
                return oAccion.getIdUsuarioSistema();
            }
        }

}

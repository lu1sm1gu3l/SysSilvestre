package modelo.grlGeneral.datos;
import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.grlGeneral.entidad.CEUsuarioSistema;

public class CDUsuarioSistema
{
     public static List<CEUsuarioSistema> ListadoUsuarioSistema(int pIdUsuario)
        {
            Connection conexion = ConexionBD.obtenerConexion();
            try
                {
                    List<CEUsuarioSistema> oVctUsuarioSistema = new ArrayList<CEUsuarioSistema>();
                    String sql = "{CALL GRLCPRCNSUsuarioSistema("+pIdUsuario+")}";
                    CallableStatement sentencia = conexion.prepareCall(sql);
                    ResultSet rs = sentencia.executeQuery();
                    if(rs!=null)
                    {
                        while(rs.next())
                        {
                            CEUsuarioSistema oUsuarioSistema = new CEUsuarioSistema();
                            oUsuarioSistema.setIdUsuario(rs.getInt(1));
                            oUsuarioSistema.setFechaEntrada(rs.getString(2));
                            oUsuarioSistema.setHoraEntrada(rs.getString(3));
                            oUsuarioSistema.setFechaSalida(rs.getString(4));
                            oUsuarioSistema.setHoraSalida(rs.getString(5));
                            oVctUsuarioSistema.add(oUsuarioSistema);
                        }
                        return oVctUsuarioSistema;
                    }
                    return null;
                }
            catch (Exception e)
                {   e.printStackTrace();
                    return null;
                }
        }

 
}

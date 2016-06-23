package modelo.grlGeneral.datos;
import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.grlGeneral.entidad.CEAccion;
public class CDAccion
{
    public static boolean ABM(int codigo_ABM,CEAccion oAccion)
        {   Connection conexion = ConexionBD.obtenerConexion();
            try
                {
            String sql="{CALL CMRSPRABMAccion("+codigo_ABM+","+oAccion.getIdAccion()+",'"+oAccion.getDescripcion()+"','"+oAccion.getAbreviatura()+"'" + ")}";
            CallableStatement sentencia = conexion.prepareCall(sql);
            sentencia.executeUpdate();
            return true;
            }
            catch(Exception e)
            {  e.printStackTrace();
                return false;
            }
        }

    public static CEAccion Alta(CEAccion oAccion)
    {
        Connection conexion = ConexionBD.obtenerConexion();
        try
        {
            String sql = "{call CMRSPRINSAccion(?,?)}";
            CallableStatement sentencia = conexion.prepareCall(sql);
            sentencia.setString(1,oAccion.getDescripcion());
            sentencia.setString(2,oAccion.getAbreviatura());
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next())
            {
                CEAccion oCEAccion = new CEAccion();
                oCEAccion.setIdAccion(resultado.getInt(1));
                oCEAccion.setDescripcion(resultado.getString(2));
                oCEAccion.setAbreviatura(resultado.getString(3));
                return oCEAccion;
            }
            return null;
            
        }
        catch(SQLException e)
        
            {
                e.printStackTrace();
                return null;
            }
        
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

        public static List<CEAccion> Listado()
         {
                Connection conexion = ConexionBD.obtenerConexion();
                try
                {
                    List<CEAccion> LstCEAccion = new ArrayList<CEAccion>();
                    String sql = "{call GRLSPRCNSAccion()}";
                    CallableStatement sentencia = conexion.prepareCall(sql);
                    ResultSet resultado = sentencia.executeQuery();
                    if (resultado!=null)
                {
                    while (resultado.next())
                    {
                        CEAccion oCEAccion = new CEAccion();
                        oCEAccion.setIdAccion(resultado.getInt(1));
                        oCEAccion.setDescripcion(resultado.getString(2));
                        oCEAccion.setAbreviatura(resultado.getString(3));
                        LstCEAccion.add(oCEAccion);
                    }
                    conexion.close();
                    return LstCEAccion;
                    }
               return null;

                }
                catch(SQLException e)

            {
                e.printStackTrace();
                return null;
            }
        
                catch(Exception e)
                {
                    e.printStackTrace();
                    return null;
                }
         }
}



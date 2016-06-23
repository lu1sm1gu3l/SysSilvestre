package modelo.grlGeneral.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CDAtencionSistema
{

    public static int ConsultarCondicionSistema()
    {
        Connection conexion = ConexionBD.obtenerConexion();
        try
        {
            int valor = 0;
            String sql = "{CALL GRLSPRCNSCondicionSistema(?)}";
            CallableStatement sentencia = conexion.prepareCall(sql);
            sentencia.registerOutParameter(1, java.sql.Types.BIT);
            sentencia.execute();
            valor = (sentencia.getInt(1));
            conexion.close();
            return valor;
        }
        catch (SQLException e)
        {
            System.out.print(e);
            return 0;
        }
        catch (Exception e)
        {
            System.out.print(e);
            return 0;
        }
    }

    public static int ConsultaSistema()
    {
        Connection conexion = ConexionBD.obtenerConexion();
        try
        {
            boolean value = false;
            boolean value2 = false;
            String sql = "{CALL GRLSPRCNSEstadoSistema(?,?)}";
            CallableStatement sentencia = conexion.prepareCall(sql);
            sentencia.registerOutParameter(1, java.sql.Types.INTEGER);
            sentencia.registerOutParameter(2, java.sql.Types.INTEGER);
            sentencia.execute();
            value = sentencia.getBoolean(1);
            value2 = sentencia.getBoolean(2);
            conexion.close();
            if (value && value2)
            {
                return 1;
                // Abierto y Cerrado 
            }
            else
            {
                if (value && !value2)
                {
                    return 2;
                    // Abierto y No Cerrado
                }
                else
                {
                    if (!value && value2)
                    {
                        return 3;
                        // No Abierto y Cerrado
                    }
                    else
                    {
                        return 4;
                        // No Abierto y No Cerrado
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.print(e);
            return 0;
        }
        catch (Exception e)
        {
            System.out.print(e);
            return 0;
        }
    }

    public static boolean AperturaSistema(int IdUsuario)
    {
        Connection conexion = ConexionBD.obtenerConexion();
        try
        {
            String sql = "{CALL GRLSPRAperturarSistema(?)}";
            CallableStatement sentencia = conexion.prepareCall(sql);
            sentencia.setInt(1, IdUsuario);
            sentencia.execute();
            conexion.close();
            return true;
        }
        catch (SQLException e)
        {
            System.out.print(e);
            return false;
        }
        catch (Exception e)
        {
            System.out.print(e);
            return false;
        }
    }

    public static boolean CerrarSistema(int IdUsuario)
    {
        Connection conexion = ConexionBD.obtenerConexion();
        try
        {
            String sql = "{CALL GRLSPRCerrarSistema(?)}";
            CallableStatement sentencia = conexion.prepareCall(sql);
            sentencia.setInt(1, IdUsuario);
            sentencia.execute();
            conexion.close();
            return true;
        }
        catch (SQLException e)
        {
            System.out.print(e);
            return false;
        }
        catch (Exception e)
        {
            System.out.print(e);
            return false;
        }
    }
}

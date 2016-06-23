package modelo.almAlmacen.datos;

import controller.acceso.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.almAlmacen.entidad.CETipoIngresoSalida;

public class CDTipoIngresoSalida
{
      public static ArrayList<CETipoIngresoSalida>  consultarListaTipoIngresoSalida(int id_opcion)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               ArrayList<CETipoIngresoSalida> oLstAnioSemestreAcademicoMatriz = new ArrayList<CETipoIngresoSalida>();
               String sql="{call ALMSPRCNSTipoIngresoSalida("+id_opcion+")}";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                while(rs.next())
                {
                    CETipoIngresoSalida oCETipoIngresoSalida=new CETipoIngresoSalida();
                    oCETipoIngresoSalida.setIdTipoIngresoSalida(rs.getInt(1));
                    oCETipoIngresoSalida.setDescripcion(rs.getString(2));
                    oLstAnioSemestreAcademicoMatriz.add(oCETipoIngresoSalida);
                }
                return oLstAnioSemestreAcademicoMatriz;
                }
               return null;
            }
         catch (SQLException e)
            {
             System.out.print(e);
             return null;
            }
        catch (Exception e)
            {
            System.out.print(e);
            return null;
            }
        finally
            {
                try
                {
                conexion.close();
                }
                catch (SQLException ex)
                {
                System.out.print("No se puede cerrar la conexion");
                }
            }
      }



}

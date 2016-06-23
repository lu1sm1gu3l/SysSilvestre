package modelo.almAlmacen.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.almAlmacen.entidad.CEUnidadPresentacionVenta;

public class CDUnidadPresentacionVenta
{
      public static List<CEUnidadPresentacionVenta>  consultarUnidadPresentacionVenta()
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEUnidadPresentacionVenta> oLstAnioSemestreAcademicoMatriz = new ArrayList<CEUnidadPresentacionVenta>();
               String sql="{call ALMSPRCNSUnidadPresentacionVenta(1,null)}";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                while(rs.next())
                {
                    CEUnidadPresentacionVenta oCEUnidadPresentacionVenta=new CEUnidadPresentacionVenta();
                    oCEUnidadPresentacionVenta.setIdUnidadPresentacionVenta(rs.getInt(1));
                    oCEUnidadPresentacionVenta.setAbreviatura(rs.getString(3));
                    oCEUnidadPresentacionVenta.setDescripcion(rs.getString(2));
                    oLstAnioSemestreAcademicoMatriz.add(oCEUnidadPresentacionVenta);
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

     
     public static int ABMUnidadPresentacionVenta(int pIntAMB,CEUnidadPresentacionVenta oCDUnidadMedidaVenta)
        {
           Connection conexion;
           conexion = ConexionBD.obtenerConexion();
            try
              {
                CallableStatement sentenciaABMUnidadMedidaVenta;
                conexion.setAutoCommit(false);
                conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

                String sql= "{call ALMSPRABMUnidadPresentacion(?,?,?,?)}";
                sentenciaABMUnidadMedidaVenta = conexion.prepareCall(sql);
                sentenciaABMUnidadMedidaVenta.setInt(1, pIntAMB);
                sentenciaABMUnidadMedidaVenta.setInt(2, oCDUnidadMedidaVenta.getIdUnidadPresentacionVenta());
                sentenciaABMUnidadMedidaVenta.setString(3, oCDUnidadMedidaVenta.getAbreviatura());
                sentenciaABMUnidadMedidaVenta.setString(4, oCDUnidadMedidaVenta.getDescripcion());
                sentenciaABMUnidadMedidaVenta.executeUpdate();
               conexion.commit();

               return 1;
                }
            catch (Exception e)
              {
                System.out.print(e);
                  try
                    {
                      conexion.rollback();
                    }
                  catch(SQLException sqlExcep)
                    {
                      sqlExcep.printStackTrace();
                    }
                return 0;
              }
           finally
            {
              try
                {
                 conexion.close();
                }
              catch(SQLException e)
                {
                  System.out.print(e);
                }
            }

      }
    

}

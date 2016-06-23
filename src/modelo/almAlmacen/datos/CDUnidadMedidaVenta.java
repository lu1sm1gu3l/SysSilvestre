package modelo.almAlmacen.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.almAlmacen.entidad.CEUnidadMedidaVenta;

public class CDUnidadMedidaVenta
{
      public static List<CEUnidadMedidaVenta>  consultarListaUnidadMedidaVentas()
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEUnidadMedidaVenta> oLstAnioSemestreAcademicoMatriz = new ArrayList<CEUnidadMedidaVenta>();
               String sql="{call ALMSPRCNSUnidadMedidaVenta(1,null)}";
               Statement sentencia=conexion.createStatement();
               ResultSet rs=sentencia.executeQuery(sql);
               if (rs!=null)
                {
                while(rs.next())
                {
                    CEUnidadMedidaVenta oCEUnidadMedidaVenta=new CEUnidadMedidaVenta();
                    oCEUnidadMedidaVenta.setIdUnidadVentaMedida(rs.getInt(1));
                    oCEUnidadMedidaVenta.setAbreviatura(rs.getString(2));
                    oCEUnidadMedidaVenta.setDescripcion(rs.getString(3));
                    oLstAnioSemestreAcademicoMatriz.add(oCEUnidadMedidaVenta);
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

     
     public static int ABMUnidadMedidaVenta(int pIntAMB,CEUnidadMedidaVenta oCDUnidadMedidaVenta)
        {
           Connection conexion;
           conexion = ConexionBD.obtenerConexion();
            try
              {
                CallableStatement sentenciaABMUnidadMedidaVenta;
                conexion.setAutoCommit(false);
                conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

                String sql= "{call ALMSPRABMUnidadMedidaVenta(?,?,?,?)}";
                sentenciaABMUnidadMedidaVenta = conexion.prepareCall(sql);
                sentenciaABMUnidadMedidaVenta.setInt(1, pIntAMB);
                sentenciaABMUnidadMedidaVenta.setInt(2, oCDUnidadMedidaVenta.getIdUnidadVentaMedida());
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

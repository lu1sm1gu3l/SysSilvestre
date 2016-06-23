package modelo.grlGeneral.datos;

import controller.acceso.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.grlGeneral.entidad.CEValores;

/**
 *
 * @author Luiggi
 */
public class CDValores
{
 public static int ABMPlanEstudioDetalle(int pIntAMB,CEValores oCEValores)
  {
       Connection conexion;
       conexion = ConexionBD.obtenerConexion();//5 elimina
        try
          {
            CallableStatement sentenciaBMCEPlanDetalle;
            conexion.setAutoCommit(false);
            conexion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            String sql_= "{call CLESPRABMValores(?,?,?,?,?,?)}";
            sentenciaBMCEPlanDetalle = conexion.prepareCall(sql_);
            sentenciaBMCEPlanDetalle.setInt(1, pIntAMB);
            sentenciaBMCEPlanDetalle.setInt(2, oCEValores.getmIntIdValores());
            sentenciaBMCEPlanDetalle.setInt(3, oCEValores.getmIntCodigo());
            sentenciaBMCEPlanDetalle.setString(4, oCEValores.getmStrAbreviatura());
            sentenciaBMCEPlanDetalle.setString(5, oCEValores.getmStrDescripcion());
            sentenciaBMCEPlanDetalle.registerOutParameter(6, java.sql.Types.INTEGER);
            sentenciaBMCEPlanDetalle.executeUpdate();
            int id=sentenciaBMCEPlanDetalle.getInt(6);
           conexion.commit();
           
           return id;
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
    public static List<CEValores> ListadoValores(int pIntABM,int pIntCodigo)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               List<CEValores> LstCeValores = new ArrayList<CEValores>();
               String sql="{CALL CLESPRCNSValores("+pIntABM+","+pIntCodigo+")}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               ResultSet rs=sentencia.executeQuery();
               if (rs!=null)
                {
                while (rs.next())
                {
                    CEValores oCEValores=new CEValores();
                    oCEValores.setmIntIdValores(rs.getInt(1));
                    oCEValores.setmIntCodigo(rs.getInt(2));
                    oCEValores.setmStrAbreviatura(rs.getString(3));
                    oCEValores.setmStrDescripcion(rs.getString(4));
                    LstCeValores.add(oCEValores);
                }
                return LstCeValores;
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
                    if(conexion!=null)
                    {
                conexion.close();}
                }
                catch (SQLException ex)
                {
                System.out.print("No se puede cerrar la conexion");
                }
            }
    }

 public static float obenertIGVActual()
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               float igvActual=0;
               String sql="{CALL GRLSPRCNSIgvActual(?)}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.registerOutParameter(1, java.sql.Types.DECIMAL);
               sentencia.executeUpdate();
               igvActual=sentencia.getFloat(1);
                
               return igvActual;
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
        finally
            {
                try
                {
                    if(conexion!=null)
                    {
                conexion.close();}
                }
                catch (SQLException ex)
                {
                System.out.print("No se puede cerrar la conexion");
                }
            }
    }

 public static float obenertPercpcionActual()
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               float PercepcionActual=0;
               String sql="{CALL GRLSPRCNSPercepcionActual(?)}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.registerOutParameter(1, java.sql.Types.DECIMAL);
               sentencia.executeUpdate();
               PercepcionActual=sentencia.getFloat(1);

               return PercepcionActual;
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
        finally
            {
                try
                {
                    if(conexion!=null)
                    {
                conexion.close();}
                }
                catch (SQLException ex)
                {
                System.out.print("No se puede cerrar la conexion");
                }
            }
    }
 public static int obenerFechaDelSistema(String pFecha,int opcion)
      {
         Connection conexion = ConexionBD.obtenerConexion();
         try {
               int FechaSistema=0;
               String sql="{CALL GRLSPRCNSFechaSistema(?,?,?)}";
               CallableStatement sentencia=conexion.prepareCall(sql);
               sentencia.registerOutParameter(1, java.sql.Types.TINYINT);
               sentencia.setString(2, pFecha);
               sentencia.setInt(3, opcion);
               sentencia.executeUpdate();
               FechaSistema=sentencia.getInt(1);

               return FechaSistema;
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
        finally
            {
                try
                {
                    if(conexion!=null)
                    {
                conexion.close();}
                }
                catch (SQLException ex)
                {
                System.out.print("No se puede cerrar la conexion");
                }
            }
    }
}

